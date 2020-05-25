package com.num.kaizen.repository;

import com.num.kaizen.model.Like;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Page<Like> findByPostId(Long postId, Pageable pageable);
}