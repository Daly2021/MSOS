package club.msos.service;

import club.msos.pojo.UpdateUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface updateUserService {

    List<UpdateUser> selectUpdateUser();

    int insertUpdateUser(UpdateUser updateUser);
}
