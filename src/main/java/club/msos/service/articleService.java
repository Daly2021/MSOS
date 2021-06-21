package club.msos.service;

import club.msos.pojo.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface articleService {
    List<Article> selectArticleDesc();

    List<Article> selectArticle(Article article);

    List<Article> selectArticleByTitle(Article article);

    int deleteArticle(Integer article_id);

    int insertArticle(Article article);

    int updateArticle(Article article);
}
