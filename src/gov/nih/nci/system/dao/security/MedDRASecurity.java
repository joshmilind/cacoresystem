package gov.nih.nci.system.dao.security;
import gov.nih.nci.common.util.*;
import gov.nih.nci.evs.query.*;
import gov.nih.nci.evs.security.*;
import gov.nih.nci.system.applicationservice.SecurityException;
/**
 * Validates security token against the MedDRA vocabulary
 * @author Shaziya Muhsin
 */

public class MedDRASecurity implements DAOSecurity {
    /**
     * Validates the security token against MedDRA. If valid returns a access code.
     * @param securityToken - specify the securityToken value 
     * @return returns an authentication code 
     */
public Object getAuthenticationCode(Object securityToken)throws SecurityException{	
	Object validToken = null;
	boolean valid = true;
	if(securityToken == null){
		throw new SecurityException("Invalid access token for MedDRA - Permission Denied");
	}
	try{    		
        msso.validator.MSSOUserValidatorClient validator = new msso.validator.MSSOUserValidatorClient();
        valid = validator.validateID((String)securityToken).equalsIgnoreCase("true");            
	}catch(Exception ex){
		valid = false;    		
	}	
	if(!valid){
		throw new SecurityException("Invalid access token for MedDRA - Permission Denied");
	}else{
		validToken = securityToken;
	}
	return validToken;
}
}
