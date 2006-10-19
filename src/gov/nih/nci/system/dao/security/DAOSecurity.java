package gov.nih.nci.system.dao.security;
import gov.nih.nci.system.applicationservice.SecurityException;
import gov.nih.nci.system.dao.security.SecurityKey;
import gov.nih.nci.system.dao.security.UserCredentials;

public interface DAOSecurity{
		
	public SecurityKey getSecurityKey(UserCredentials credentials) throws SecurityException;
}
