package gov.nih.nci.system.dao.aop;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.Advised;

/**
 * An AOP method interceptor that remotely executes methods unless they
 * are marked with some given annotation. 
 * 
 * @author <a href="mailto:rokickik@mail.nih.gov">Konrad Rokicki</a>
 */
public abstract class RemoteMethodInterceptor 
        implements MethodInterceptor, Serializable {
    
    private static Logger log = Logger.getLogger(RemoteMethodInterceptor.class);  
    
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Method method = invocation.getMethod();
        Class implClass = invocation.getThis().getClass();
        Method methodImpl = implClass.getMethod(method.getName(), 
            method.getParameterTypes());

        checkIfSupported(methodImpl);
        
        if (isClientSafe(methodImpl)) {
            log.info("calling locally: "+implClass.getName()+"."+methodImpl.getName());
            return invocation.proceed();
        }

        log.info("calling remotely: "+implClass.getName()+"."+methodImpl.getName());

        Object[] objs = invocation.getArguments();
        for(int i=0; i<objs.length; i++) {
            if (objs[i] != null) {
                if (Enhancer.isEnhanced(objs[i].getClass())) {
                    objs[i] = unwrap(objs[i]);
                }
            }
        }
        
        Object ret = executeRemotely(invocation.getThis(), methodImpl.getName(), 
            getParameterTypes(methodImpl), objs);

        if (ret == null) return null;
        
        if (ret.getClass().getName().startsWith("java")) {
            // never try to proxy Java classes
            return ret;
        }
        
        if (isClientSafe(ret.getClass())) {
            return ret;
        }
        else {
            return getProxy(ret);
        }
    }
    
    /**
     * Returns the underlying object that the specified proxy is advising. 
     * @param proxy
     * @return
     * @throws Exception
     */
    private Object unwrap(Object proxy) throws Exception {
        
        Object interceptor = null;
        int i = 0;
        while (true) {
            Field field = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_"+i);
            field.setAccessible(true);
            Object value = field.get(proxy);
            if (value.getClass().getName().contains("EqualsInterceptor")) {
                interceptor = value;
                break;
            }
            i++;
        }
        
        Field field = interceptor.getClass().getDeclaredField("advised");
        field.setAccessible(true);
        Advised advised = (Advised)field.get(interceptor);
        Object realObject = advised.getTargetSource().getTarget();
        return realObject;
    }

    private String[] getParameterTypes(Method methodImpl) {
        String[] paramClasses = 
            new String[methodImpl.getParameterTypes().length];
        int i = 0;
        for(Class paramClass : methodImpl.getParameterTypes()) {
            if (paramClass == null) continue;
            paramClasses[i++] = paramClass.getName();
        }
        return paramClasses;
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
     * Returns true if the given method is safe to execute on the client.
     * @param method
     * @return
     */
    protected abstract boolean isClientSafe(Method method);

    /**
     * Returns true if the given class is safe to pass to the client without 
     * wrapping in a proxy. This also means all the methods are client safe.
     * @param method
     * @return
     */
    protected abstract boolean isClientSafe(Class method);

    /**
     * Allows for an opportunity for the subclass to throw an 
     * UnsupportedOperationException if the given method is not supported for
     * caCORE usage.
     * @param method
     * @return
     */
    protected abstract void checkIfSupported(Method method);
}
