package mingjie.kahoot.gameservice.dto;


public class OptionCreateRequest {
    private String content;
    private int order;


    public void setOrder(int order) {
        this.order = order;
    }


    public OptionCreateRequest() {
    }

    public String getContent() {
        return content;
    }

    public int getOrder() {
        return order;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
