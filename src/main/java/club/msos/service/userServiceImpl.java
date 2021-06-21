package club.msos.service;

import club.msos.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public class userServiceImpl implements userService{

    @Autowired
    club.msos.dao.userMapper userMapper;

    @Override
    public List<User> selectUser(User user) {
        return userMapper.selectUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUser(String user_id) {
        return userMapper.deleteUser(user_id);
    }
}
