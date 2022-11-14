package owlhome.AOP_practice.advices;


import org.springframework.aop.MethodBeforeAdvice;
import owlhome.AOP_practice.object_for_advice.Bird;

import java.lang.reflect.Method;


public class MethodBeforeAdviceImpl implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        if(target instanceof Bird) {
            System.out.printf("Comrade %s started your question!\n", ((Bird) target).getName());
        }
    }
}