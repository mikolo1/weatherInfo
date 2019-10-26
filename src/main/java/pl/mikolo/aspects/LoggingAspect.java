package pl.mikolo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("@annotation(pl.mikolo.annotations.ExecutionTimeLog)")
    public void handleLoggingAnnotation() {
    }

    @Around("handleLoggingAnnotation()")
    public Object handleLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();

        Long id = (Long) joinPoint.getArgs()[0];

        Object o = joinPoint.proceed();
        log.info("Execution time: " + (System.nanoTime() - startTime) + " for id: " + id);
        return o;
    }

}
