package project.laundry.AOP.Handler;


import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import project.laundry.exception.DuplicateUserException;
import project.laundry.exception.EntityNotFoundException;
import project.laundry.exception.FormNullPointerException;


@Aspect
@Component
@RequiredArgsConstructor
public class ExceptionHandlerAspect {

    private final ExceptionHandlerAdvice advice;
    @Pointcut("execution(* project.laundry.service.*.*(..))")
    public void serviceLayer(){}

    @Pointcut("execution(* project.laundry.controller.*.*(..))")
    public void controllerLayer() {}

    @Around("serviceLayer()")
    public Object serviceHandleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (DuplicateUserException e) {
            advice.handleDuplicateUserException(e);
        } catch (EntityNotFoundException e) {
            advice.handleEntityNotFoundException(e);
        }
        return null;
    }

    @Around("controllerLayer()")
    public Object controllerHandlerException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            joinPoint.proceed();
        } catch (FormNullPointerException e) {
            advice.handleFormNullPointerException(e);
        }

        return null;
    }
}
