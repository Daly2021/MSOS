package club.msos.dao;

import club.msos.pojo.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface messageMapper {

    List<Message> selectMessage(Message message);

    int deleteMessage(@Param("message_id")Integer message_id);

    int insertMessage(Message message);

    int updateMessage(Message message);

}
