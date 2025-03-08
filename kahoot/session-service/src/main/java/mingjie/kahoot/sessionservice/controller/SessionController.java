package mingjie.kahoot.sessionservice.controller;


import mingjie.kahoot.sessionservice.entity.Session;
import mingjie.kahoot.sessionservice.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing sessions.
 */
@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * Retrieves all sessions.
     *
     * @return a list of all sessions
     */
    @GetMapping
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    /**
     * Retrieves a session by its ID.
     *
     * @param id the ID of the session
     * @return the session with the specified ID, or a 404 response if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Session session = sessionService.getSessionById(id);
        if (session == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(session);
    }

    /**
     * Creates a new session.
     *
     * @param newSession the session to create
     * @return the created session
     */
    @PostMapping
    public ResponseEntity<Session> createSession(@RequestBody Session newSession) {
        Session savedSession = sessionService.createSession(newSession);
        return ResponseEntity.ok(savedSession);
    }

    /**
     * Updates an existing session.
     *
     * @param id the ID of the session to update
     * @param updatedSession the updated session data
     * @return the updated session, or a 404 response if not found
     */
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

    /**
     * Deletes a session by its ID.
     *
     * @param id the ID of the session to delete
     * @return a 204 response if the session was deleted, or a 404 response if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        Session dbSession = sessionService.getSessionById(id);
        if (dbSession == null) {
            return ResponseEntity.notFound().build();
        }
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Starts a session by its ID.
     *
     * @param id the ID of the session to start
     * @return a 200 response if the session was started
     */
    @PostMapping("/{id}/start")
    public ResponseEntity<Void> startSession(@PathVariable Long id) {
        sessionService.startSession(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Ends a session by its ID.
     *
     * @param id the ID of the session to end
     * @return a 200 response if the session was ended
     */
    @PostMapping("/{id}/end")
    public ResponseEntity<Void> endSession(@PathVariable Long id) {
        sessionService.endSession(id);
        return ResponseEntity.ok().build();
    }
}