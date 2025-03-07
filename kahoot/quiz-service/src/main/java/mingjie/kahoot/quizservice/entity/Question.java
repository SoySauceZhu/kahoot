package mingjie.kahoot.quizservice.entity;



import java.time.LocalDateTime;

public class Question {
    private Long id;
    private Long quizId;
    private String questionText;
    private String questionType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Question() {
        }

        public Question(Long id, Long quizId, String questionText, String questionType, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.id = id;
            this.quizId = quizId;
            this.questionText = questionText;
            this.questionType = questionType;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getQuizId() {
            return quizId;
        }

        public void setQuizId(Long quizId) {
            this.quizId = quizId;
        }

        public String getQuestionText() {
            return questionText;
        }

        public void setQuestionText(String questionText) {
            this.questionText = questionText;
        }

        public String getQuestionType() {
            return questionType;
        }

        public void setQuestionType(String questionType) {
            this.questionType = questionType;
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
