package mingjie.kahoot.sessionservice.dao;


import mingjie.kahoot.sessionservice.entity.Session;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SessionMapper {

    Session findById(Long id);

    Session findBySessionCode(String sessionCode);

    List<Session> findAll();

    int insertSession(Session session);

    int updateSession(Session session);

    int deleteSession(Long id);
}
