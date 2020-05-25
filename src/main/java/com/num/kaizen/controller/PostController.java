package com.num.kaizen.controller;

import com.num.kaizen.model.*;
import com.num.kaizen.payload.*;
import com.num.kaizen.repository.LikeRepository;
import com.num.kaizen.repository.PostRepository;
import com.num.kaizen.repository.UserRepository;
import com.num.kaizen.repository.CommentRepository;
import com.num.kaizen.security.CurrentUser;
import com.num.kaizen.security.UserPrincipal;
import com.num.kaizen.service.PostService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostService postService;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostRequest postRequest) {
        Post post = postService.createPost(postRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{postId}")
                .buildAndExpand(post.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Пост амжилттай үүсгэгдлээ. Үнжий <3"));
    }

    @PostMapping("/{postId}/comments")
    @PreAuthorize("hasRole('USER')")
    public PostResponse castComment(@CurrentUser UserPrincipal currentUser,
                                    @PathVariable Long postId,
                                    @Valid @RequestBody CommentRequest commentRequest) {

        return postService.castCommentAndGetUpdatedPost(postId, commentRequest, currentUser);
    }

    @PostMapping("/{postId}/likes")
    @PreAuthorize("hasRole('USER')")
    public PostResponse castLike(@CurrentUser UserPrincipal currentUser,
                                 @PathVariable Long postId,
                                 @Valid @RequestBody LikeRequest likeRequest) {

        return postService.castLikeAndGetUpdatedPost(postId, likeRequest, currentUser);
    }

//    @PostMapping("/{postId}/likes")
//    @PreAuthorize("hasRole('USER')")
//    public Like pushLike(@CurrentUser UserPrincipal currentUser,
//                         @PathVariable Long postId,
//                         @Valid @RequestBody LikeRequest likeRequest) {
//
//        return postService.pushLike(postId, currentUser);
//
//    }
}
