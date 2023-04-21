package project.laundry.AOP.Handler;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import project.laundry.AOP.Handler.signup.SignupExceptionHandlerAdvice;
import project.laundry.exception.DuplicateUserException;
import project.laundry.exception.EntityNotFoundException;
import project.laundry.exception.FormNullPointerException;


@Aspect
@Component
@RequiredArgsConstructor
public class ExceptionHandlerAspect {

    private final SignupExceptionHandlerAdvice advice;
    @Pointcut("execution(* project.laundry.service.*.*(..))")
    public void serviceLayer(){}

    @Pointcut("execution(* project.laundry.controller.*.*(..)) && execution(* project.laundry.controller.LoginController.*(..))")
    public void controllerLayer() {}

    @Around("serviceLayer()")
    public Object serviceHandleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (DuplicateUserException e) {
            return advice.handleDuplicateUserException(e);
        } catch (EntityNotFoundException e) {
            return advice.handleEntityNotFoundException(e);
        }
    }

    @Around("controllerLayer()")
    public Object controllerHandlerException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (FormNullPointerException e) {
            return advice.handleFormNullPointerException(e);
        }
    }
}
