package mingjie.kahoot.sessionservice.dao;


import mingjie.kahoot.sessionservice.entity.SessionParticipant;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SessionParticipantMapper {

    SessionParticipant findById(Long id);

    List<SessionParticipant> findBySessionId(Long sessionId);

    int insertParticipant(SessionParticipant participant);

    int updateParticipant(SessionParticipant participant);

    int deleteParticipant(Long id);

    int deleteBySessionId(Long sessionId);
}
