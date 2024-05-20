package be.kdg.team_5_phygital.repository;

import be.kdg.team_5_phygital.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findCommentsByProjectIdEquals(int projectId);
}
