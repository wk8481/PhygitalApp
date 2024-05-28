package be.kdg.team_5_phygital.controller.api;

import be.kdg.team_5_phygital.controller.api.dto.CommentDto;
import be.kdg.team_5_phygital.controller.api.dto.NewCommentDto;
import be.kdg.team_5_phygital.domain.Comment;
import be.kdg.team_5_phygital.service.CommentService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentsController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    ResponseEntity<CommentDto> getComment(@PathVariable("id") int commentId) {
        Comment comment = commentService.getComment(commentId);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(modelMapper.map(comment, CommentDto.class));
    }

    @GetMapping
    ResponseEntity<List<CommentDto>> getAllComments() {
        List<Comment> allComments = commentService.getAllComments();
        if (allComments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            List<CommentDto> commentDtos = allComments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).collect(Collectors.toList());
            return ResponseEntity.ok(commentDtos);
        }
    }

    @PostMapping
    ResponseEntity<CommentDto> saveComment(@RequestBody @Valid NewCommentDto commentDto) {
        if (commentService.getCommentByText(commentDto.getText()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        logger.info("Creating new comment: {}", commentDto.getText());
        Comment createdComment = commentService.saveComment(commentDto.getText(), commentDto.getProjectId());
        return new ResponseEntity<>(modelMapper.map(createdComment, CommentDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("{commentId}")
    ResponseEntity<Void> deleteComment(@PathVariable("commentId") int commentId) {
        if (commentService.deleteComment(commentId)) {
            logger.info("Comment deleted");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.error("Comment could not be found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
