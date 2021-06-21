package club.msos.service;

import club.msos.pojo.UpdateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class updateUserServiceImpl implements updateUserService{

    @Autowired
    club.msos.dao.updateUserMapper updateUserMapper;

    @Override
    public List<UpdateUser> selectUpdateUser() {
        return updateUserMapper.selectUpdateUser();
    }

    @Override
    public int insertUpdateUser(UpdateUser updateUser) {
        return updateUserMapper.insertUpdateUser(updateUser);
    }
}
