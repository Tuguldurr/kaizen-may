package com.num.kaizen.payload;

import com.num.kaizen.model.Post;
import com.num.kaizen.model.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentRequest {

    @NotNull
    private Long postId;

    @NotBlank
    private String text;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
