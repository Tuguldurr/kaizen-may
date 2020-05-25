package com.num.kaizen.payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

public class PostRequest {
    @NotBlank
    @Size(max = 140)
    private String title;

    @NotBlank
    @Size(max = 700)
    private String text;

    @NotBlank
    @Size(max = 100)
    private String type;

    @NotNull
    private Long score;

    @Null
    @Size(min = 2, max = 6)
    @Valid
    private List<CommentRequest> comments;


    // Getter and Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public List<CommentRequest> getComments() {
        return comments;
    }

    public void setComments(List<CommentRequest> comments) {
        this.comments = comments;
    }
}
