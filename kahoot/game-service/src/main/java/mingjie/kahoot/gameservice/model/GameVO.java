package mingjie.kahoot.gameservice.model;

import java.time.LocalDateTime;
import java.util.List;

@Deprecated
public class GameVO {
    private String title;
    private String description;
    private String gameCode;
    private Long creatorId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<QuestionVO> questions;

    // Default constructor
    public GameVO() {
    }

    // Parameterized constructor
    public GameVO(String title, String description, String gameCode, Long creatorId, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.title = title;
        this.description = description;
        this.gameCode = gameCode;
        this.creatorId = creatorId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public List<QuestionVO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionVO> questions) {
        this.questions = questions;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
        this.gameCode = gameCode;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    // toString method
    @Override
    public String toString() {
        return "GameVO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", gameCode='" + gameCode + '\'' +
                ", creatorId=" + creatorId +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameVO gameVO = (GameVO) o;

        return gameCode != null ? gameCode.equals(gameVO.gameCode) : gameVO.gameCode == null;
    }

    @Override
    public int hashCode() {
        return gameCode != null ? gameCode.hashCode() : 0;
    }
}