package gov.nih.nci.system.dao.security;
import gov.nih.nci.common.util.*;
import gov.nih.nci.evs.query.*;
import gov.nih.nci.evs.security.*;
import gov.nih.nci.system.applicationservice.SecurityException;
import java.util.*;
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
private static HashSet validTokenCollection = new HashSet();
	
public MedDRASecurity(){	
	System.out.println("MedDRASecurity.....");
	resetCache(100000,60000);
}

public Object getAuthenticationCode(Object securityToken)throws SecurityException{	
	Object validToken = null;
	boolean valid = false;
	if(securityToken == null){
		throw new SecurityException("Invalid access token for MedDRA - Permission Denied");
	}
	if(!found((String)securityToken)){
		try{    		
	        msso.validator.MSSOUserValidatorClient validator = new msso.validator.MSSOUserValidatorClient();
	        valid = validator.validateID((String)securityToken).equalsIgnoreCase("true");	        
		}catch(Exception ex){
			if(ex.getMessage() == null){
				throw new SecurityException("MedDRA not responding - unable to validate token");
			}
			else{
				throw new SecurityException(ex.getMessage());
			}   		
		}	
	}else{
		valid = true;
	}	
	System.out.println("Validating token .....");
	if(!valid){
		String msg = "\nA valid license number is required to access MedDRA through the caCORE API. NCI staff and collaborators please contact NCICB for NCI's license. " +
		"Other users contact MedDRA MSSO:\n 12011 Sunset Hill Road; Reston, VA 20190-3285; phone: 877-258-8280; e-mail: MSSOhelp@ngc.com; http://meddramsso.com";		
		throw new SecurityException("Permission Denied - Invalid access token for MedDRA" + msg);
	}else{
		validTokenCollection.add(securityToken);
		validToken = securityToken;
	}
	return validToken;
}

private boolean found(String token){
	boolean found = false;
	for(Iterator i = validTokenCollection.iterator(); i.hasNext();){
		String tokenString = (String)i.next();
		if(tokenString.equals(token)){
			found = true;
			break;
		}
	}
	return found;
}

private void resetCache(int delay, int interval){	
	System.out.println("RESETTING CACHE.....");
	Timer timer = new Timer();
	timer.scheduleAtFixedRate(new TimerTask(){
			public void run(){
				validTokenCollection = new HashSet();
			}
		}, delay, interval);
}
}
