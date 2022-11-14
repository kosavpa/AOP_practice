package owlhome.AOP_practice.advices;


import org.springframework.aop.AfterReturningAdvice;
import owlhome.AOP_practice.object_for_advice.Bird;

import java.lang.reflect.Method;


public class AfterReturningAdviceImpl implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        if(target instanceof Bird){
            System.out.printf("Comrade %s done your questions!\n", ((Bird) target).getName());
        }
    }
}
