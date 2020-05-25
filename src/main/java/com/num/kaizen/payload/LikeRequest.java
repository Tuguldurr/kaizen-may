package com.num.kaizen.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LikeRequest {

    @NotNull
    private Long postId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
