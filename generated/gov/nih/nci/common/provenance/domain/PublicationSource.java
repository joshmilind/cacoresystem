package gov.nih.nci.common.provenance.domain;

import gov.nih.nci.common.provenance.domain.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.HQLCriteria;
import java.util.*;
import org.apache.log4j.Logger;

/**
 * <!-- LICENSE_TEXT_START -->
 * <!-- LICENSE_TEXT_END -->
 */
 
  /**
   * An implementing subclass of Source. Describes a source for which an electronic online version is 
   * not available, but for which a printed version of the data is available. 
   * 
   */

public  class PublicationSource  extends gov.nih.nci.common.provenance.domain.Source 	implements java.io.Serializable 
{
	private static final long serialVersionUID = 1234567890L;
	
	
		
	public java.lang.String authors;
	public java.lang.String getAuthors()
	{
		return authors;
	}
	public void setAuthors(java.lang.String authors)
	{
		this.authors = authors;
	}
	
		
	public java.lang.Integer endPage;
	public java.lang.Integer getEndPage()
	{
		return endPage;
	}
	public void setEndPage(java.lang.Integer endPage)
	{
		this.endPage = endPage;
	}
	
		
	public java.lang.Integer startPage;
	public java.lang.Integer getStartPage()
	{
		return startPage;
	}
	public void setStartPage(java.lang.Integer startPage)
	{
		this.startPage = startPage;
	}
	
		
	public java.lang.String title;
	public java.lang.String getTitle()
	{
		return title;
	}
	public void setTitle(java.lang.String title)
	{
		this.title = title;
	}
	
		
	public java.lang.Integer volume;
	public java.lang.Integer getVolume()
	{
		return volume;
	}
	public void setVolume(java.lang.Integer volume)
	{
		this.volume = volume;
	}
	
		
	public java.lang.Integer year;
	public java.lang.Integer getYear()
	{
		return year;
	}
	public void setYear(java.lang.Integer year)
	{
		this.year = year;
	}

	public boolean equals(Object obj)
	{
		boolean eq = false;
		if(obj instanceof PublicationSource) 
		{
			PublicationSource c =(PublicationSource)obj; 			 
			Long thisId = getId();		
			
			if(thisId != null && thisId.equals(c.getId()))
				eq = true;
			
			}
			return eq;
		}
		
	public int hashCode()
	{
		int h = 0;
		
		if(getId() != null)
			h += getId().hashCode();
		
		return h;
	}
}