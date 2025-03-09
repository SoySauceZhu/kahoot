package mingjie.kahoot.gameservice.dto;

public class QuestionUpdateRequest {

    private String content;
    private Integer timeLimit;
    private Long[] correctAnswer;

    public String getContent() {
        return content;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public Long[] getCorrectAnswer() {
        return correctAnswer;
    }
}
