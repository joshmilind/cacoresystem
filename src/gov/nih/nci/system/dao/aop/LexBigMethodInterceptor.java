package gov.nih.nci.system.dao.aop;

import gov.nih.nci.system.applicationservice.EVSApplicationService;

import java.lang.reflect.Method;

import org.LexGrid.annotations.LgAdminFunction;
import org.LexGrid.annotations.LgClientSideSafe;
import org.LexGrid.LexBIG.Impl.logging.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;

/**
 * Implementation of a RemoteMethodInterceptor that executes methods
 * using the EVSApplicationService, unless they have the 
 * @LgClientSideSafe annotation.  
 * 
 * @author <a href="mailto:rokickik@mail.nih.gov">Konrad Rokicki</a>
 */
public class LexBigMethodInterceptor extends RemoteMethodInterceptor {
    
    static {
        LoggerFactory.setLightweight(true);
    }
    
    @Override
    protected Object executeRemotely(Object object, String method, 
            String[] paramClasses, Object[] args) throws Exception {
        EVSApplicationService service = EVSApplicationService.getRemoteInstance();
        return service.executeRemotely(object, method, paramClasses, args);
    }

    @Override
    protected boolean isClientSafe(Object object) {
        if (object instanceof Method) {
            return ((Method)object).isAnnotationPresent(LgClientSideSafe.class);
        }
        else {
            return ((Class)object).isAnnotationPresent(LgClientSideSafe.class);
        }
    }
    
    @Override
    protected void checkIfSupported(Method method) {
        if (method.isAnnotationPresent(LgAdminFunction.class)) {
            throw new UnsupportedOperationException(
                "Admin functions cannot be executing using the remote API");
        }
    }

    @Override
    protected Object getProxy(Object obj) {
        if (!obj.getClass().getName().startsWith("org.LexGrid")) {
            // only proxy LexBIG classes
            return obj;
        }
        return LexBigMethodInterceptor.createProxy(obj);
    }
    
    /**
     * Utility method to create a proxy for the given class,
     * advised by the LexBigMethodInterceptor. 
     * @param obj
     * @return
     */
    public static Object createProxy(Object obj) {
        ProxyFactory pfb = new ProxyFactory(obj);
        pfb.setProxyTargetClass(true);
        pfb.addAdvice(new LexBigMethodInterceptor());
        return pfb.getProxy();
        
    }
}
