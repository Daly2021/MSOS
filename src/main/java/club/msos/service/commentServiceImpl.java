package club.msos.service;

import club.msos.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentServiceImpl implements commentService{

    @Autowired
    club.msos.dao.commentMapper commentMapper;
    @Override
    public List<Comment> selectComment(Comment comment) {
        return commentMapper.selectComment(comment);
    }

    @Override
    public int deleteComment(Integer comment_id) {
        return commentMapper.deleteComment(comment_id);
    }

    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insertComment(comment);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }
}
