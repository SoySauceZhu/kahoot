// OptionVO.java
package mingjie.kahoot.gameservice.model;

@Deprecated
public class OptionVO {
    private String content;
    private int order;

    // Getters and Setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}