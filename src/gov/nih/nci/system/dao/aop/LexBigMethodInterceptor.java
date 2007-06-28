package gov.nih.nci.system.dao.aop;

import gov.nih.nci.system.applicationservice.EVSApplicationService;

import java.lang.annotation.Annotation;

import org.LexGrid.LexBIG.Impl.logging.LoggerFactory;
import org.LexGrid.LexBIG.Utility.LgClientSideSafe;

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
    public Object executeRemotely(Object object, String method, 
            String[] paramClasses, Object[] args) throws Exception {
        
        EVSApplicationService service = EVSApplicationService.getRemoteInstance();
        return service.executeRemotely(object, method, paramClasses, args);
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return LgClientSideSafe.class;
    }

}
