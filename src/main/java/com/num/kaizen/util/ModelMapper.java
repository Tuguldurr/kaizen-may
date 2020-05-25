package com.num.kaizen.util;

import com.num.kaizen.model.Comment;
import com.num.kaizen.model.Post;
import com.num.kaizen.model.User;
import com.num.kaizen.payload.CommentResponse;
import com.num.kaizen.payload.PostResponse;
import com.num.kaizen.payload.UserSummary;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper {
    public static PostResponse mapPostToPostResponse(Post post, User creator) {

        PostResponse postResponse = new PostResponse();

        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setText(post.getText());
        postResponse.setScore(post.getScore());
        postResponse.setCreationDateTime(post.getCreatedAt());

        List<CommentResponse> commentResponses = post.getComments().stream().map(comment -> {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setText(comment.getText());

            return commentResponse;
        }).collect(Collectors.toList());

        postResponse.setComments(commentResponses);

        UserSummary creatorSummary = new UserSummary(creator.getId(), creator.getUsername(), creator.getName());
        postResponse.setCreatedBy(creatorSummary);

        return postResponse;
    }
}
