// QuestionVO.java
package mingjie.kahoot.gameservice.model;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionVO {
    private String content;
    private int timeLimit;
    private List<OptionVO> options;




    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }


    public List<OptionVO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionVO> options) {
        this.options = options;
    }
}