package mingjie.kahoot.gameservice.util;

import mingjie.kahoot.gameservice.model.Question;
import mingjie.kahoot.gameservice.model.QuestionVO;

public class QuestionConverter {

//    public static QuestionDTO convertToDTO(Question question) {
//            QuestionDTO dto = new QuestionDTO();
//            dto.setId(question.getId());
//            dto.setContent(question.getContent());
//            dto.setTimeLimit(question.getTimeLimit());
//            dto.setCorrectAnswer(question.getCorrectAnswer());
//            dto.setCreatedAt(question.getCreatedAt());
//            return dto;
//        }

        public static QuestionVO convertToVO(Question question) {
            QuestionVO vo = new QuestionVO();
            vo.setContent(question.getContent());
            vo.setTimeLimit(question.getTimeLimit());
            return vo;
        }
}
