package project.laundry.exception.AOP.Handler;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.laundry.data.dto.common.signUpDto;
import project.laundry.exception.DataIntegrityViolationException;
import project.laundry.exception.FormNullPointerException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<signUpDto> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        signUpDto rs = new signUpDto("회원 가입에 실패 했습니다. : " + e, false, null);
        return ResponseEntity.badRequest().body(rs);
    }

    @ExceptionHandler(FormNullPointerException.class)
    public ResponseEntity<signUpDto> handleFormNullPointerException(FormNullPointerException e) {
        signUpDto rs = new signUpDto("올바르지 않은 요청입니다. : " + e, false, null);

        return ResponseEntity.internalServerError().body(rs);
    }

}
