package mingjie.kahoot.gameservice.dto;

import java.util.List;

public class GameCreateRequest {
    private String title;
    private String description;
    private List<QuestionCreateRequest> questionCreateRequests;

    public GameCreateRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionCreateRequest> getQuestionCreateRequests() {
        return questionCreateRequests;
    }

    public void setQuestionCreateRequests(List<QuestionCreateRequest> questionCreateRequests) {
        this.questionCreateRequests = questionCreateRequests;
    }

    public String getDescription() {
        return description;
    }


    public List<QuestionCreateRequest> getQuestions() {
        return questionCreateRequests;
    }

}
