package mingjie.kahoot.gameservice.dto;

public class GameUpdateRequest {
    private String title;
    private String description;
    private String status;
    private Boolean isDeleted;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }
}
