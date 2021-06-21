package club.msos.dao;

import club.msos.pojo.UpdateUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface updateUserMapper {

    List<UpdateUser> selectUpdateUser();

    int insertUpdateUser(UpdateUser updateUser);
}
