package club.msos.dao;

import club.msos.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface commentMapper {
    List<Comment> selectComment(Comment comment);

    int deleteComment(@Param("comment_id") Integer comment_id);

    int insertComment(Comment comment);

    int updateComment(Comment comment);
}
