package club.msos.dao;

import club.msos.pojo.Links;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface linksMapper {
    List<Links> selectLinks(Links links);

    List<Links> selectLinksByTitle(Links links);

    int deleteLinks(@Param("links_id") Integer links_id);

    int updateLinks(Links links);

    int insertLinks(Links links);
}
