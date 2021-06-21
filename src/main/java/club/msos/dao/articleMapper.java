package club.msos.dao;

import club.msos.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface articleMapper {
    List<Article> selectArticleDesc();

    List<Article> selectArticle(Article article);

    List<Article> selectArticleByTitle(Article article);

    int deleteArticle(@Param("article_id") Integer article_id);

    int insertArticle(Article article);

    int updateArticle(Article article);
}
