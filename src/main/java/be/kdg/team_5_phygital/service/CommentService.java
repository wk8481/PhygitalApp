package be.kdg.team_5_phygital.service;

import be.kdg.team_5_phygital.domain.Comment;
import be.kdg.team_5_phygital.domain.Project;
import be.kdg.team_5_phygital.repository.CommentRepository;
import be.kdg.team_5_phygital.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private static final Logger log = LoggerFactory.getLogger(CommentService.class);
    private final CommentRepository commentRepository;
    private final ProjectRepository projectRepository;

    public CommentService(CommentRepository commentRepository, ProjectRepository projectRepository) {
        this.commentRepository = commentRepository;
        this.projectRepository = projectRepository;
    }

    public Comment getComment(int commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentByText(String text) {
        return commentRepository.findByText(text).orElse(null);
    }

    public List<Comment> getCommentsByProjectId(int projectId) {
        return commentRepository.findCommentsByProjectIdEquals(projectId);
    }

    @Transactional
    public Comment saveComment(String text, int projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        Comment comment = new Comment(text, project);
        return commentRepository.save(comment);
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
