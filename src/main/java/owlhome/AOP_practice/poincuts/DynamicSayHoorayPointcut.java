package owlhome.AOP_practice.poincuts;


import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import owlhome.AOP_practice.object_for_advice.Bird;

import java.lang.reflect.Method;


public class DynamicSayHoorayPointcut extends DynamicMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals("sayHooray");
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        return (((Integer) args[0]).intValue()%2) == 0;
    }

    @Override
    public ClassFilter getClassFilter() {
        return Bird.class::isAssignableFrom;
    }
}
