package owlhome.AOP_practice.advices;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import owlhome.AOP_practice.object_for_advice.Bird;


public class MethodInterceptorAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Bird bird = null;
        Object retVal = null;

        if(invocation.getThis() instanceof Bird) {
            bird = (Bird) invocation.getThis();

            System.out.printf("Comrade %s started your question!\n", bird.getName());

            retVal = invocation.proceed();

            System.out.printf("Comrade %s done your questions!\n", bird.getName());
        }

        return retVal;
    }
}
