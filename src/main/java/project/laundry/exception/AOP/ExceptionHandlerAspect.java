package project.laundry.exception.AOP;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import project.laundry.exception.AOP.Handler.ExceptionHandlerAdvice;
import project.laundry.exception.DataIntegrityViolationException;
import project.laundry.exception.FormNullPointerException;


@Aspect
@Component
@RequiredArgsConstructor
public class ExceptionHandlerAspect {

    private final ExceptionHandlerAdvice advice;
    @Pointcut("execution(* project.laundry.service.*.*(..))")
    public void serviceLayer(){}

    @Around("serviceLayer()")
    public Object HandleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (DataIntegrityViolationException e) {
            advice.handleDataIntegrityViolationException(e);
        } catch (FormNullPointerException e) {
            advice.handleFormNullPointerException(e);
        }
        return null;
    }
}
