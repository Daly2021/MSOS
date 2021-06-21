package club.msos.dao;

import club.msos.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface userMapper {
    List<User> selectUser(User user);

    int insertUser(User user);

    int updateUser(User user);

    int deleteUser(@Param("user_id") String user_id);
}
