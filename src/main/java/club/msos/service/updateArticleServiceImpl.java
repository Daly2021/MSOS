package club.msos.service;

import club.msos.pojo.UpdateArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class updateArticleServiceImpl implements updateArticleService{
    @Autowired
    club.msos.dao.updateArticleMapper updateArticleMapper;

    @Override
    public List<UpdateArticle> selectUpdateArticle() {
        return updateArticleMapper.selectUpdateArticle();
    }

    @Override
    public int insertUpdateArticle(UpdateArticle updateArticle) {
        return updateArticleMapper.insertUpdateArticle(updateArticle);
    }
}
