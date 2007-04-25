package gov.nih.nci.search;
/*
 * Created on Apr 23, 2007
 * Shaziya Muhsin
 * 
 */
public class RangeFilter {
    public RangeFilter() {      
    }
    public RangeFilter(String name, String start, String end){
        fieldName = name;
        startRange = start;
        endRange = end;
    }
    private String fieldName = null;
    public void setFieldName(String name){
        fieldName = name;
    } 
    public String getFieldName(){
        return fieldName;
    }
    private String startRange = null;
    public void setStartRange(String start){
        startRange = start;
    }
    public String getStartRange(){
        return startRange;
    }
    private String endRange = null;
    public void setEndRange(String end){
        endRange = end;
    }
    public String getEndRange(){
        return endRange;
    }
}
