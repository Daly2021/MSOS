package club.msos.service;

import club.msos.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface commentService {
    List<Comment> selectComment(Comment comment);

    int deleteComment(Integer comment_id);

    int insertComment(Comment comment);

    int updateComment(Comment comment);
}
