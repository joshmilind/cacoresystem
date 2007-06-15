package gov.nih.nci.system.dao.aop;

import gov.nih.nci.system.applicationservice.EVSApplicationService;

import java.lang.annotation.Annotation;

import org.LexGrid.LexBIG.Utility.Remote;

/**
 * Implementation of a RemoteMethodInterceptor that executes @Remote methods 
 * using the EVSApplicationService.
 * 
 * @author <a href="mailto:rokickik@mail.nih.gov">Konrad Rokicki</a>
 */
public class LexBigMethodInterceptor extends RemoteMethodInterceptor {

    @Override
    public Object executeRemotely(Object object, String method, 
            String[] paramClasses, Object[] args) throws Exception {
        
        EVSApplicationService service = EVSApplicationService.getRemoteInstance();
        return service.executeRemotely(object, method, paramClasses, args);
    }

    @Override
    public Class<? extends Annotation> getAnnotationClass() {
        return Remote.class;
    }
    
}
