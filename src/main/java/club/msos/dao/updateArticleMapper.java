package club.msos.dao;


import club.msos.pojo.UpdateArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface updateArticleMapper {

    List<UpdateArticle> selectUpdateArticle();

    int insertUpdateArticle(UpdateArticle updateArticle);
}
