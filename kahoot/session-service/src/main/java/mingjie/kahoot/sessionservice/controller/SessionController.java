package mingjie.kahoot.sessionservice.controller;

import mingjie.kahoot.sessionservice.entity.Session;
import mingjie.kahoot.sessionservice.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Session session = sessionService.getSessionById(id);
        if (session == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(session);
    }

    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody Session newSession) {
        Session savedSession = sessionService.createSession(newSession);
        return ResponseEntity.ok(savedSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id,
                                                 @RequestBody Session updatedSession) {
        Session dbSession = sessionService.getSessionById(id);
        if (dbSession == null) {
            return ResponseEntity.notFound().build();
        }
        dbSession.setQuizId(updatedSession.getQuizId());
        dbSession.setHostUserId(updatedSession.getHostUserId());
        dbSession.setSessionCode(updatedSession.getSessionCode());
        dbSession.setStatus(updatedSession.getStatus());
        dbSession.setStartTime(updatedSession.getStartTime());
        dbSession.setEndTime(updatedSession.getEndTime());
        sessionService.updateSession(dbSession);
        return ResponseEntity.ok(dbSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        Session dbSession = sessionService.getSessionById(id);
        if (dbSession == null) {
            return ResponseEntity.notFound().build();
        }
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<Void> startSession(@PathVariable Long id) {
        sessionService.startSession(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<Void> endSession(@PathVariable Long id) {
        sessionService.endSession(id);
        return ResponseEntity.ok().build();
    }
}
