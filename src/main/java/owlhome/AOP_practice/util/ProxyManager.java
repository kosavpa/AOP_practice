package owlhome.AOP_practice.util;


import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import owlhome.AOP_practice.advices.AfterReturningAdviceImpl;
import owlhome.AOP_practice.advices.MethodBeforeAdviceImpl;
import owlhome.AOP_practice.advices.MethodInterceptorAdvice;
import owlhome.AOP_practice.object_for_advice.Bird;
import owlhome.AOP_practice.object_for_advice.Crow;
import owlhome.AOP_practice.object_for_advice.Flying;
import owlhome.AOP_practice.object_for_advice.Owl;
import owlhome.AOP_practice.poincuts.DynamicSayHoorayPointcut;
import owlhome.AOP_practice.poincuts.SayName;
import owlhome.AOP_practice.poincuts.StaticSayQuotePointcut;

import java.util.HashMap;
import java.util.Map;


public class ProxyManager {
    private static Map<AdviceName, Advice> advices = new HashMap<>();
    private static Map<PointcutName, Pointcut> pointcuts = new HashMap<>();
    private static Map<BirdType, Bird> birds = new HashMap<>();

    static {
        advices.put(AdviceName.METHOD_BEFORE, new MethodBeforeAdviceImpl());
        advices.put(AdviceName.AFTER_RETURNING, new AfterReturningAdviceImpl());
        advices.put(AdviceName.METHOD_INTERCEPTOR, new MethodInterceptorAdvice());

        pointcuts.put(PointcutName.STATIC_SAY_QUOTE, new StaticSayQuotePointcut());
        pointcuts.put(PointcutName.DYNAMIC_SAY_HOORAY, new DynamicSayHoorayPointcut());
        pointcuts.put(PointcutName.JDK_REGEXP_METHOD_POINCUT, new JdkRegexpMethodPointcut());

        birds.put(BirdType.OWL, new Owl("Savunya"));
        birds.put(BirdType.VORONA, new Crow("Vorona"));
    }

    public static Flying getBirdProxyWhitSimpleAdvice(BirdType birdType, AdviceName adviceName, boolean neededCGLIB){
        ProxyFactory proxyFactory = new ProxyFactory();
        Flying bird = birds.get(birdType);

        proxyFactory.setProxyTargetClass(neededCGLIB);
        proxyFactory.setTarget(bird);
        proxyFactory.addAdvice(advices.get(adviceName));

        return (Flying) proxyFactory.getProxy();
    }

    public static Flying getBirdProxyWhitAdviceAndPointcut(BirdType birdType, AdviceName adviceName, PointcutName pointcutName, boolean neededCGLIB){
        ProxyFactory proxyFactory = new ProxyFactory();
        Pointcut point = pointcuts.get(pointcutName);
        Advice advice = advices.get(adviceName);
        Advisor advisor = new DefaultPointcutAdvisor(point, advice);
        Flying bird = birds.get(birdType);

        proxyFactory.setProxyTargetClass(neededCGLIB);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(bird);

        return (Flying) proxyFactory.getProxy();
    }

    public static Flying getBirdProxyWithNameMethodMatcherPointcut(BirdType birdType, AdviceName adviceName, String methodName, boolean neededCGLIB){
        ProxyFactory proxyFactory = new ProxyFactory();
        Advice advice = advices.get(adviceName);
        NameMatchMethodPointcutAdvisor pointcutAdvisor = new NameMatchMethodPointcutAdvisor(advice);
        Flying bird = birds.get(birdType);

        proxyFactory.setProxyTargetClass(neededCGLIB);
        proxyFactory.addAdvisor(pointcutAdvisor);
        proxyFactory.setTarget(bird);

        return (Flying) proxyFactory.getProxy();
    }

    public static Flying getBirdProxyWithRegexpMethodMatcherPointcut(BirdType birdType, AdviceName adviceName, String regexp, boolean neededCGLIB){
        ProxyFactory proxyFactory = new ProxyFactory();
        JdkRegexpMethodPointcut pointcut = (JdkRegexpMethodPointcut) pointcuts.get(PointcutName.JDK_REGEXP_METHOD_POINCUT);
        Advice advice = advices.get(adviceName);
        Flying bird = birds.get(birdType);
        pointcut.setPattern(regexp);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        proxyFactory.setProxyTargetClass(neededCGLIB);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(bird);

        return (Flying) proxyFactory.getProxy();
    }

    public static Flying getBirdProxyWithAnnotationSayNamePointcut(BirdType birdType, AdviceName adviceName, boolean neededCGLIB){
        ProxyFactory proxyFactory = new ProxyFactory();
        AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(SayName.class);
        Advice advice = advices.get(adviceName);
        Flying bird = birds.get(birdType);
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        proxyFactory.setProxyTargetClass(neededCGLIB);
        if(!neededCGLIB) proxyFactory.setInterfaces(Flying.class);
        proxyFactory.addAdvisor(advisor);
        proxyFactory.setTarget(bird);

        return (Flying) proxyFactory.getProxy();
    }
}
