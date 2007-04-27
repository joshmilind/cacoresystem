package gov.nih.nci.search;
import java.io.*;
/*
 * Created on Apr 2, 2007
 * ShaziyaMuhsin
 * 
 */
public class SearchQuery implements Serializable {
    private static final long serialVersionUID = 1234567890L;
    public enum QueryTypes {
        FULL_TEXT_SEARCH, 
        HIBERNATE_SEARCH;        
        }
    private java.lang.Integer id;
    public java.lang.Integer getId()
    {
        return id;
    }
    public void setId(java.lang.Integer id)
    {
        this.id = id;
    }
   
    private java.lang.String keyword;
    public java.lang.String getKeyword()
    {
        return keyword;
    }
    public void setKeyword(java.lang.String keyword)
    {
        this.keyword = keyword;
    }
    
        
    private java.lang.String source;
    public java.lang.String getSource()
    {
        return source;
    }
    public void setSource(java.lang.String source)
    {
        this.source = source;
    }
            
        
    private java.util.Collection resultCollection = new java.util.HashSet();
    public java.util.Collection getResultCollection()
    {
        
        return resultCollection;
    }
    
    public void setResultCollection(java.util.Collection resultCollection)
    {
        this.resultCollection = resultCollection;
    }   
        
    private String queryType = QueryTypes.FULL_TEXT_SEARCH.name();
    public String getQueryType(){
        return queryType;
    }
    public void setQueryType(String type)throws Exception{
        boolean valid = false;
        String types = "";
        for(QueryTypes qType: QueryTypes.values()){
            if(qType.name().equalsIgnoreCase(type)){
                queryType = qType.name();
                valid = true;
                break;
            }
            types += qType.name() +" ";
        }
        if(!valid){
            throw new Exception("Invalid Query Type: "+ type +"\nValid query types are "+ types);
        }
    }
    
    private RangeFilter rangeFilter;
    public void setRangeFilter(RangeFilter rangeFilter){
        this.rangeFilter = rangeFilter;
    }
    public RangeFilter getRangeFilter(){
        return rangeFilter;
    }
    
    private Boolean fuzzySearch = false;
    public void setFuzzySearch(boolean fuzzy){
        this.fuzzySearch = fuzzy;
    }
    public boolean getFuzzySearch(){
        return fuzzySearch;
    }

    public int hashCode()
    {
        int h = 0;
        
        if(getId() != null)
            h += getId().hashCode();
        
        return h;
    }

    

}
