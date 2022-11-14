package owlhome.AOP_practice.poincuts;


import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import owlhome.AOP_practice.object_for_advice.Bird;

import java.lang.reflect.Method;


public class StaticSayQuotePointcut extends StaticMethodMatcherPointcut {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals("sayQuote");
    }

    @Override
    public ClassFilter getClassFilter() {
        return Bird.class::isAssignableFrom;
    }
}
