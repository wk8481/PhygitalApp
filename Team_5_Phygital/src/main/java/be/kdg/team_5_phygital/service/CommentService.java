package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Comment;
import be.kdg.team_5_phygital.repository.CommentRepository;
import be.kdg.team_5_phygital.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getComment(int commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getCommentsByProjectId(int projectId) {
        return commentRepository.findCommentsByProjectIdEquals(projectId);
    }

    @Transactional
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public boolean updateComment(int commentId, String name) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            return false;
        }
        comment.setText(name);
        commentRepository.save(comment);
        return true;
    }

    @Transactional
    public boolean deleteComment(int commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isEmpty()) {
            return false;
        }
        commentRepository.deleteById(commentId);
        return true;
    }
}
