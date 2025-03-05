package mingjie.kahoot.quizservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Long id;
    private Long quizId;
    private String questionText;
    private String questionType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
