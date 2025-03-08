package mingjie.kahoot.sessionservice.controller;

import mingjie.kahoot.sessionservice.entity.SessionParticipant;
import mingjie.kahoot.sessionservice.service.SessionParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing session participants.
 */
@RestController
@RequestMapping("/participants")
public class SessionParticipantController {

    private final SessionParticipantService participantService;

    @Autowired
    public SessionParticipantController(SessionParticipantService participantService) {
        this.participantService = participantService;
    }

    /**
     * Retrieves participants by session ID.
     *
     * @param sessionId the ID of the session
     * @return a list of participants in the specified session
     */
    @GetMapping
    public List<SessionParticipant> getParticipantsBySession(@RequestParam Long sessionId) {
        return participantService.getParticipantsBySession(sessionId);
    }

    /**
     * Retrieves a participant by their ID.
     *
     * @param id the ID of the participant
     * @return the participant with the specified ID, or a 404 response if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<SessionParticipant> getParticipantById(@PathVariable Long id) {
        SessionParticipant participant = participantService.getParticipantById(id);
        if (participant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(participant);
    }

    /**
     * Adds a new participant.
     *
     * @param newParticipant the participant to add
     * @return the added participant
     */
    @PostMapping
    public ResponseEntity<SessionParticipant> addParticipant(@RequestBody SessionParticipant newParticipant) {
        SessionParticipant saved = participantService.addParticipant(newParticipant);
        return ResponseEntity.ok(saved);
    }

    /**
     * Updates an existing participant.
     *
     * @param id      the ID of the participant to update
     * @param updated the updated participant data
     * @return the updated participant, or a 404 response if not found
     */
    @PutMapping("/{id}")
    public ResponseEntity<SessionParticipant> updateParticipant(@PathVariable Long id,
                                                                @RequestBody SessionParticipant updated) {
        SessionParticipant dbParticipant = participantService.getParticipantById(id);
        if (dbParticipant == null) {
            return ResponseEntity.notFound().build();
        }
        dbParticipant.setSessionId(updated.getSessionId());
        dbParticipant.setUserId(updated.getUserId());
        dbParticipant.setJoinedAt(updated.getJoinedAt());
        dbParticipant.setLeftAt(updated.getLeftAt());
        dbParticipant.setFinalScore(updated.getFinalScore());
        participantService.updateParticipant(dbParticipant);
        return ResponseEntity.ok(dbParticipant);
    }

    /**
     * Removes a participant by their ID.
     *
     * @param id the ID of the participant to remove
     * @return a 204 response if the participant was removed, or a 404 response if not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeParticipant(@PathVariable Long id) {
        SessionParticipant dbParticipant = participantService.getParticipantById(id);
        if (dbParticipant == null) {
            return ResponseEntity.notFound().build();
        }
        participantService.removeParticipant(id);
        return ResponseEntity.noContent().build();
    }
}