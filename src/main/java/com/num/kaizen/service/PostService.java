package com.num.kaizen.service;

import com.num.kaizen.exception.ResourceNotFoundException;
import com.num.kaizen.model.*;
import com.num.kaizen.payload.PostRequest;
import com.num.kaizen.payload.PostResponse;
import com.num.kaizen.payload.CommentRequest;
import com.num.kaizen.payload.LikeRequest;
import com.num.kaizen.repository.LikeRepository;
import com.num.kaizen.repository.PostRepository;
import com.num.kaizen.repository.CommentRepository;
import com.num.kaizen.repository.UserRepository;
import com.num.kaizen.security.UserPrincipal;
import com.num.kaizen.util.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikeRepository likeRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    public Post createPost(PostRequest postRequest) {
        Post post = new Post();

        post.setTitle(postRequest.getTitle());
        post.setText(postRequest.getText());
        post.setType(postRequest.getType());
        post.setScore(postRequest.getScore());

        return postRepository.save(post);
    }

//    public Like pushLike(Long postId, UserPrincipal currentUser) {
//
//        Post post = postRepository.findById(postId)
//                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
//
//        User user = userRepository.getOne(currentUser.getId());
//
//        User creator = userRepository.findById(post.getCreatedBy())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", post.getCreatedBy()));
//
//        Like like = new Like();
//        like.setPost(post);
//        like.setUser(user);
//
//        return likeRepository.save(like);
//    }

    public PostResponse castCommentAndGetUpdatedPost(Long postId, CommentRequest commentRequest, UserPrincipal currentUser) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        User user = userRepository.getOne(currentUser.getId());

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setText(commentRequest.getText());

        commentRepository.save(comment);

        User creator = userRepository.findById(post.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", post.getCreatedBy()));

        return ModelMapper.mapPostToPostResponse(post, creator);
    }


    public PostResponse castLikeAndGetUpdatedPost(Long postId, LikeRequest likeRequest, UserPrincipal currentUser) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        User user = userRepository.getOne(currentUser.getId());

        Like like = new Like();
        like.setPost(post);
        like.setUser(user);

        likeRepository.save(like);

        User creator = userRepository.findById(post.getCreatedBy())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", post.getCreatedBy()));

        return ModelMapper.mapPostToPostResponse(post, creator);
    }
}





