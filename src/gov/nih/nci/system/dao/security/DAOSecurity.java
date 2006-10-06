package gov.nih.nci.system.dao.security;
import gov.nih.nci.system.applicationservice.SecurityException;

public interface DAOSecurity{
		
	public Object getAuthenticationCode(Object request) throws SecurityException;
}
