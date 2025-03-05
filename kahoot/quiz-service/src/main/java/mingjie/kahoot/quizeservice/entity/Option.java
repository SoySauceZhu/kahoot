package mingjie.kahoot.quizeservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    private Long id;
    private Long questionId;
    private String optionText;
    private boolean isCorrect;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}