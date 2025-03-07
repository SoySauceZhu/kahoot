package mingjie.kahoot.sessionservice.service;

import mingjie.kahoot.sessionservice.dao.SessionMapper;
import mingjie.kahoot.sessionservice.dao.SessionParticipantMapper;
import mingjie.kahoot.sessionservice.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionService {

    private final SessionMapper sessionMapper;
    private final SessionParticipantMapper participantMapper;

    @Autowired
    public SessionService(SessionMapper sessionMapper, SessionParticipantMapper participantMapper) {
        this.sessionMapper = sessionMapper;
        this.participantMapper = participantMapper;
    }

    public Session getSessionById(Long id) {
        return sessionMapper.findById(id);
    }

    public Session getSessionByCode(String code) {
        return sessionMapper.findBySessionCode(code);
    }

    public List<Session> getAllSessions() {
        return sessionMapper.findAll();
    }

    public Session createSession(Session session) {
        sessionMapper.insertSession(session);
        return session;
    }

    public int updateSession(Session session) {
        return sessionMapper.updateSession(session);
    }

    public int deleteSession(Long id) {
        // Optional: delete participants first
        participantMapper.deleteBySessionId(id);
        return sessionMapper.deleteSession(id);
    }

    // Additional logic for starting/ending session
    public void startSession(Long id) {
        Session dbSession = sessionMapper.findById(id);
        if (dbSession == null) return;
        dbSession.setStatus("IN_PROGRESS");
        dbSession.setStartTime(LocalDateTime.now());
        sessionMapper.updateSession(dbSession);
    }

    public void endSession(Long id) {
        Session dbSession = sessionMapper.findById(id);
        if (dbSession == null) return;
        dbSession.setStatus("ENDED");
        dbSession.setEndTime(LocalDateTime.now());
        sessionMapper.updateSession(dbSession);
    }
}