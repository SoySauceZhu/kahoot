package mingjie.kahoot.sessionservice.service;

import mingjie.kahoot.sessionservice.dao.SessionParticipantMapper;
import mingjie.kahoot.sessionservice.entity.SessionParticipant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionParticipantService {

    private final SessionParticipantMapper participantMapper;

    @Autowired
    public SessionParticipantService(SessionParticipantMapper participantMapper) {
        this.participantMapper = participantMapper;
    }

    public SessionParticipant getParticipantById(Long id) {
        return participantMapper.findById(id);
    }

    public List<SessionParticipant> getParticipantsBySession(Long sessionId) {
        return participantMapper.findBySessionId(sessionId);
    }

    public SessionParticipant addParticipant(SessionParticipant participant) {
        participantMapper.insertParticipant(participant);
        return participant;
    }

    public int updateParticipant(SessionParticipant participant) {
        return participantMapper.updateParticipant(participant);
    }

    public int removeParticipant(Long id) {
        return participantMapper.deleteParticipant(id);
    }
}