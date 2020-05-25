package com.num.kaizen.repository;

import com.num.kaizen.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c where c.user.id = :userId and c.post.id in :postIds")
    List<Comment> findByUserIdAndPostIdIn(@Param("userId") Long userId, @Param("postIds") List<Long> postIds);

    @Query("SELECT c FROM Comment c where c.user.id = :userId and c.post.id = :postId")
    Comment findByUserIdAndPostId(@Param("userId") Long userId, @Param("postId") Long postId);

    @Query("SELECT COUNT(c.id) from Comment c where c.user.id = :userId")
    long countByUserId(@Param("userId") Long userId);

    @Query("SELECT c.post.id FROM Comment c WHERE c.user.id = :userId")
    Page<Long> findCommentedPostIdsByUserId(@Param("userId") Long userId, Pageable pageable);

    Page<Comment> findByPostId(Long postId, Pageable pageable);
}
