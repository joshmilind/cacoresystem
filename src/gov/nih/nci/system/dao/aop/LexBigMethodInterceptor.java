package gov.nih.nci.system.dao.aop;

import gov.nih.nci.system.applicationservice.EVSApplicationService;

import java.io.Serializable;
import java.lang.annotation.Annotation;

import org.LexGrid.LexBIG.Impl.logging.LoggerFactory;
import org.LexGrid.LexBIG.Utility.LgClientSideSafe;
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
    protected Class<? extends Annotation> getAnnotationClass() {
        return LgClientSideSafe.class;
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
        
        Class[] interfaces = obj.getClass().getInterfaces();
        int countInterfaces = 0;
        for(Class iclass : interfaces) {
            if (iclass != Serializable.class) {
                countInterfaces++;
            }
        }
        
        if (countInterfaces == 0) {
            // No real interfaces found, 
            // so let's use CGLIB to proxy the class itself.
            pfb.setProxyTargetClass(true);
        }
        
        pfb.addAdvice(new LexBigMethodInterceptor());
        return pfb.getProxy();
    }
}
