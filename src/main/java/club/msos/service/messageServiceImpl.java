package club.msos.service;

import club.msos.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messageServiceImpl implements messageService{

    @Autowired
    club.msos.dao.messageMapper messageMapper;
    @Override
    public List<Message> selectMessage(Message message) {
        return messageMapper.selectMessage(message);
    }

    @Override
    public int deleteMessage(Integer message_id) {
        return messageMapper.deleteMessage(message_id);
    }

    @Override
    public int insertMessage(Message message) {
        return messageMapper.insertMessage(message);
    }

    @Override
    public int updateMessage(Message message) {
        return messageMapper.updateMessage(message);
    }
}
