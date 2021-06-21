package club.msos.service;

import club.msos.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userService {

    List<User> selectUser(User user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(String user_id);
}
