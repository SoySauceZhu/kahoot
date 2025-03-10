package mingjie.kahoot.gameservice.dto;

import mingjie.kahoot.gameservice.model.Type;

import java.util.List;

public class QuestionCreateRequest {

    private String content;

    private int timeLimit = 10;

    private Long[] correctAnswer;

    private List<OptionCreateRequest> optionCreateRequests;
//    private List<Option> options;

    public void setContent(String content) {
        this.content = content;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setCorrectAnswer(Long[] correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setOptionCreateRequests(List<OptionCreateRequest> optionCreateRequests) {
        this.optionCreateRequests = optionCreateRequests;
    }

    public QuestionCreateRequest() {
    }


//    public boolean isAnswerIndicesValid() {
//        if (options == null || correctAnswer == null) return false;
//        for (Long index : correctAnswer) {
//            if (index < 0 || index >= options.size()) {
//                return false;
//            }
//        }
//        return true;
//    }

    public String getContent() {
        return content;
    }



    public int getTimeLimit() {
        return timeLimit;
    }


    public Long[] getCorrectAnswer() {
        return correctAnswer;
    }


    public List<OptionCreateRequest> getOptionCreateRequests() {
        return optionCreateRequests;
    }
//
//
//    public List<Option> getOptions() {
//        return options;
//    }
}
