package gov.nih.nci.common.util;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.search.annotations.*;
import org.hibernate.search.bridge.StringBridge;
/*
 * Created on Mar 29, 2007
 * Shaziya Muhsin
 * 
 */
public class ObjectBridge implements StringBridge {

    
    public String objectToString(Object object) {
        //This code will currently convert Collections and Map types
        StringBuilder valueList = new StringBuilder();
        if(object instanceof Collection){
            for(Iterator i = ((Collection)object).iterator(); i.hasNext();){
                Object value = i.next();
                if(value instanceof Date){
                   valueList.append(getDateString((Date)object));                   
                }
                valueList.append(value);
            }
        }else if(object instanceof Map){
            for(Iterator i = ((Map)object).keySet().iterator(); i.hasNext();){
                String key = (String)i.next();
                Object value = ((Map)object).get(key);
                valueList.append(key +":"+ value);
            }
        }else if(object instanceof Date){
            valueList.append(getDateString((Date)object));
        }        
        return valueList.toString();
    }
    
    private String getDateString(Date date){        
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(date);
    }


}
