package club.msos.service;


import club.msos.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class articleServiceImpl implements articleService{

    @Autowired
    club.msos.dao.articleMapper articleMapper;

    @Override
    public List<Article> selectArticleDesc() {
        return articleMapper.selectArticleDesc();
    }

    @Override
    public List<Article> selectArticle(Article article) {
        return articleMapper.selectArticle(article);
    }

    @Override
    public List<Article> selectArticleByTitle(Article article) {
        return articleMapper.selectArticleByTitle(article);
    }

    @Override
    public int deleteArticle(Integer article_id) {
        return articleMapper.deleteArticle(article_id);
    }

    @Override
    public int insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateArticle(article);
    }
}
