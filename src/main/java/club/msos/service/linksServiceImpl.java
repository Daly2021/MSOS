package club.msos.service;

import club.msos.pojo.Links;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class linksServiceImpl implements linksService{

    @Autowired
    club.msos.dao.linksMapper linksMapper;
    @Override
    public List<Links> selectLinks(Links links) {
        return linksMapper.selectLinks(links);
    }

    @Override
    public List<Links> selectLinksByTitle(Links links) {
        return linksMapper.selectLinksByTitle(links);
    }

    @Override
    public int deleteLinks(Integer links_id) {
        return linksMapper.deleteLinks(links_id);
    }

    @Override
    public int updateLinks(Links links) {
        return linksMapper.updateLinks(links);
    }

    @Override
    public int insertLinks(Links links) {
        return linksMapper.insertLinks(links);
    }
}
