package cl.elegardo.dogapi.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceAspect.class);

    @Around("execution(* demo.dogapi.service.impl..*(..)) && args(request)")
    public Object log1(ProceedingJoinPoint pjp, String request) throws Throwable {
        StopWatch sw = new StopWatch();
        try {
            sw.start();
            return pjp.proceed();
        } finally {
            sw.stop();
            LOGGER.info("timelapsed:" + sw.getTotalTimeMillis() + "(ms), " 
                        + "breed:" + request + ", " + " - "
                        + pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName() + "(..)");

            LOGGER.debug("timelapsed:" + sw.getTotalTimeMillis() + "(ms), " 
                        + "breed:" + request + ", " + " - "
                        + pjp.getSignature().getName() + "(..))");
        }
    }

    @AfterThrowing(pointcut = "execution(* demo.dogapi.service.impl..*(..))", throwing = "error")
    public void exceptionLogger(JoinPoint jointPoint, Throwable error) {
        LOGGER.error(jointPoint.getSignature().getName() + "(..)" 
                    + ", Type:" + error.getClass().getSimpleName()
                    + ", Message: " + error.getMessage());
    }

}