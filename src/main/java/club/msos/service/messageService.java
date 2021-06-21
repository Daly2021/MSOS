package club.msos.service;

import club.msos.pojo.Message;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface messageService {
    List<Message> selectMessage(Message message);

    int deleteMessage(Integer message_id);

    int insertMessage(Message message);

    int updateMessage(Message message);
}
