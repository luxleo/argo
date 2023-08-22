package com.project.argo.repository.article.comment;

import com.project.argo.domain.article.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
