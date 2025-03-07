package mingjie.kahoot.sessionservice.entity;

import java.time.LocalDateTime;

public class SessionParticipant {
    private Long id;
    private Long sessionId;
    private Long userId;
    private LocalDateTime joinedAt;
    private LocalDateTime leftAt;
    private Integer finalScore;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public SessionParticipant() {
    }

    public SessionParticipant(Long id, Long sessionId, Long userId, LocalDateTime joinedAt, LocalDateTime leftAt, Integer finalScore, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.userId = userId;
        this.joinedAt = joinedAt;
        this.leftAt = leftAt;
        this.finalScore = finalScore;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public LocalDateTime getLeftAt() {
        return leftAt;
    }

    public void setLeftAt(LocalDateTime leftAt) {
        this.leftAt = leftAt;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
