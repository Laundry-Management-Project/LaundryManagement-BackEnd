package project.laundry.AOP.Handler.signup;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.laundry.data.response.common.SignupDto;
import project.laundry.exception.*;

@RestControllerAdvice
public class SignupExceptionHandlerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<SignupDto> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        SignupDto rs = new SignupDto("회원 가입에 실패 했습니다. : " + e, false);
        rs.setUid("");
        return ResponseEntity.badRequest().body(rs);
    }

    @ExceptionHandler(FormNullPointerException.class)
    public ResponseEntity<SignupDto> handleFormNullPointerException(FormNullPointerException e) {
        SignupDto rs = new SignupDto("올바르지 않은 요청입니다. : " + e, false);
        rs.setUid("");
        return ResponseEntity.internalServerError().body(rs);
    }

    @ExceptionHandler(UserNullPointerException.class)
    public ResponseEntity<SignupDto> handleUserNullPointerException(UserNullPointerException e) {
        SignupDto rs = new SignupDto("가입되지 않은 사용자 입니다. : " + e, false);
        rs.setUid("");
        return ResponseEntity.badRequest().body(rs);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<SignupDto> handleDuplicateUserException(DuplicateUserException e) {
        SignupDto rs = new SignupDto("중복된 아이디 입니다.", false);
        rs.setUid("");
        return ResponseEntity.badRequest().body(rs);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {

        return ResponseEntity.internalServerError().body(null);
    }

}
