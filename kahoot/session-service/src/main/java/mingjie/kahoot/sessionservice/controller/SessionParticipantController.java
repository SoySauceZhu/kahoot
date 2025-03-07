package mingjie.kahoot.sessionservice.controller;

import mingjie.kahoot.sessionservice.entity.SessionParticipant;
import mingjie.kahoot.sessionservice.service.SessionParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participants")
public class SessionParticipantController {

    private final SessionParticipantService participantService;

    @Autowired
    public SessionParticipantController(SessionParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping
    public List<SessionParticipant> getParticipantsBySession(@RequestParam Long sessionId) {
        return participantService.getParticipantsBySession(sessionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionParticipant> getParticipantById(@PathVariable Long id) {
        SessionParticipant participant = participantService.getParticipantById(id);
        if (participant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(participant);
    }

    @PostMapping
    public ResponseEntity<SessionParticipant> addParticipant(@RequestBody SessionParticipant newParticipant) {
        SessionParticipant saved = participantService.addParticipant(newParticipant);
        return ResponseEntity.ok(saved);
    }

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

