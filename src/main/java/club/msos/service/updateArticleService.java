package club.msos.service;

import club.msos.pojo.UpdateArticle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface updateArticleService {

    List<UpdateArticle> selectUpdateArticle();

    int insertUpdateArticle(UpdateArticle updateArticle);
}
