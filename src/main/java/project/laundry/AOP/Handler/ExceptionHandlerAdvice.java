package project.laundry.AOP.Handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.laundry.data.dto.common.businessDto;
import project.laundry.data.dto.common.signUpDto;
import project.laundry.data.dto.responseStatus;
import project.laundry.exception.*;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<signUpDto> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        signUpDto rs = new signUpDto("회원 가입에 실패 했습니다. : " + e, false, "");
        return ResponseEntity.badRequest().body(rs);
    }

    @ExceptionHandler(FormNullPointerException.class)
    public ResponseEntity<signUpDto> handleFormNullPointerException(FormNullPointerException e) {
        signUpDto rs = new signUpDto("올바르지 않은 요청입니다. : " + e, false, "");

        return ResponseEntity.internalServerError().body(rs);
    }

    @ExceptionHandler(UserNullPointerException.class)
    public ResponseEntity<signUpDto> handleUserNullPointerException(UserNullPointerException e) {
        signUpDto rs = new signUpDto("가입되지 않은 사용자 입니다. : " + e, false, "");
        return ResponseEntity.badRequest().body(rs);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<signUpDto> handleDuplicateUserException(DuplicateUserException e) {
        signUpDto rs = new signUpDto("중복된 아이디 입니다.", false, "");
        return ResponseEntity.badRequest().body(rs);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {

        return ResponseEntity.internalServerError().body(null);
    }

}
