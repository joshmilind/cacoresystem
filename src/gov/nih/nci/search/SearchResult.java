package gov.nih.nci.search;
import java.io.*;

import org.apache.lucene.document.Document;
/*
 * Created on Apr 2, 2007
 * Shaziya Muhsin
 * 
 */
public class SearchResult implements Serializable{

    private static final long serialVersionUID = 1234567890L;
   
    private java.lang.String className;
    public java.lang.String getClassName()
    {
        return className;
    }
    public void setClassName(java.lang.String className)
    {
        this.className = className;
    }
    
        
    private java.lang.Integer hit;
    public java.lang.Integer getHit()
    {
        return hit;
    }
    public void setHit(java.lang.Integer hit)
    {
        this.hit = hit;
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
    
    private java.util.HashMap properties;
    public java.util.HashMap getProperties(){
        return properties;
    }
    public void setProperties(java.util.HashMap properties){
        this.properties = properties;
    }
    
    private String displayText;
    public String getDisplayText(){
        return displayText;
    }
    public void setDisplayText(String txt){
        displayText = txt;
    }
    private String id;
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }
    public int hashCode()
    {
        int h = 0;
        
        if(getHit() != null)
            h += getHit().hashCode();
        
        return h;
    }
    
}
