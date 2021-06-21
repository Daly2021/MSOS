package club.msos.service;

import club.msos.pojo.Links;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface linksService {

    List<Links> selectLinks(Links links);

    List<Links> selectLinksByTitle(Links links);

    int deleteLinks(Integer links_id);

    int updateLinks(Links links);

    int insertLinks(Links links);
}
