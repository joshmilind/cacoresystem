package gov.nih.nci.search;
/*
 * Created on May 7, 2007
 * Shaziya Muhsin
 * 
 */
public class Sort {
    private Boolean sortByClassName = false;
    
    public void setSortByClassName(Boolean ascOrder){
        sortByClassName = ascOrder;
    }
    public Boolean getSortByClassName(){
        return sortByClassName;
    }

}
