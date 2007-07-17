package gov.nih.nci.system.dao.aop;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * An AOP method interceptor that remotely executes methods unless they
 * are marked with some given annotation. 
 * 
 * @author <a href="mailto:rokickik@mail.nih.gov">Konrad Rokicki</a>
 */
public abstract class RemoteMethodInterceptor 
        implements MethodInterceptor, Serializable {

    public Object invoke(MethodInvocation invocation) throws Throwable {

        Method method = invocation.getMethod();
        Class implClass = invocation.getThis().getClass();
        Method methodImpl = implClass.getMethod(method.getName(), 
            method.getParameterTypes());
        
        if (methodImpl.isAnnotationPresent(getAnnotationClass())) {
            System.out.println("calling locally: "+methodImpl.getName());
            return invocation.proceed();
        }

        System.out.println("calling remotely: "+methodImpl.getName());
        
        int i = 0;
        String[] paramClasses = 
            new String[methodImpl.getParameterTypes().length];
        
        for(Class paramClass : methodImpl.getParameterTypes()) {
            if (paramClass == null) continue;
            paramClasses[i++] = paramClass.getName();
        }

        Object ret = executeRemotely(invocation.getThis(), 
            methodImpl.getName(), paramClasses, invocation.getArguments());
        
        if (ret == null) return null;
        
        if (ret.getClass().getName().startsWith("java")) {
            // never try to proxy Java classes
            return ret;
        }
        
        if (ret.getClass().isAnnotationPresent(getAnnotationClass())) {
            return ret;
        }
        else {
            return getProxy(ret);
        }
    }
    
    /**
     * Implement this to actually execute the given method remotely.
     * @param object Serializable object on which the method is to be executed
     * @param method Method name to execute
     * @param paramClasses Fully qualified class names of all parameters
     * @param args Serializable parameters
     * @return The method's return value
     * @throws Exception
     */
    protected abstract Object executeRemotely(Object object, String method, 
                    String[] paramClasses, Object[] args) throws Exception;

    /**
     * Returns an AOP proxy for the given object. 
     * @param object the object to be proxied
     * @return a proxy
     */
    protected abstract Object getProxy(Object object);
    
    /**
     * Implement this to return the annotation class we should check for. 
     * Methods with this annotation will be executed remotely.
     * @return an annotation class
     */
    protected abstract Class<? extends Annotation> getAnnotationClass();
    
}
