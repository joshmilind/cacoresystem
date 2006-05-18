/**
 * Created on Mar 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import gov.nih.nci.system.applicationservice.*;

import java.util.*;

import org.apache.log4j.Logger;
import gov.nih.nci.common.util.*;
import gov.nih.nci.evs.query.*;
/**
 * @author LeThai
 *
 */
public class EVSMonitor extends Thread
{
    // -1 mean run indefinitely, a positive integer means run that numbers of time
    int runTime = 0; 
    //  delay in seconds
    int timeDelay = 0; 
    private static Logger log = Logger.getLogger(EVSMonitor.class.getName());
    
    public void run()
    {
        String prodUrl  = "http://cabio.nci.nih.gov/@PROJECT_NAME@/http/remoteService";
        String stageUrl = "http://cabio-stage.nci.nih.gov/@PROJECT_NAME@/http/remoteService";
        String qaUrl    = "http://cabio-qa.nci.nih.gov/@PROJECT_NAME@/http/remoteService";
        String localUrl = "http://localhost:8080/@PROJECT_NAME@/http/remoteService";
        //String genUrl = "http://@WEB_SERVER_NAME@:@WEB_SERVER_PORT@/@PROJECT_NAME@/http/remoteService";
		//ApplicationService appService = ApplicationService.getRemoteInstance(prodUrl);

		ApplicationService appService = ApplicationServiceProvider.getApplicationService();
        
        try
        {            
            EVSQuery evsQuery = new EVSQueryImpl();
            List evsResults = new ArrayList();
            PrintUtils print = new PrintUtils();
            evsQuery.getDescLogicConceptNameByCode("NCI_Thesaurus", "C12932");
            
            int i = 0;

            while (true) 
            {
                long startTime = System.currentTimeMillis(), endTime = 0;
                evsResults = (List) appService.evsSearch(evsQuery);
                endTime = System.currentTimeMillis();
                print.printEVSResults(evsResults);
                log.info(new Date() + "\tEVS Server is running.");
                System.out.println(new Date() + "\tEVS Server is running. Query took: " + (endTime - startTime) + " ms.");

                //startTime = System.currentTimeMillis();

                Thread.sleep(timeDelay * 1000);
                if (++i > runTime && runTime != -1)
                    break;

            }
            
            /*if(runTime == -1)
            {
                for(;;)
                {
                    long startTime = System.currentTimeMillis(),endTime =0;
                    evsResults = (List)appService.evsSearch(evsQuery);
                    endTime = System.currentTimeMillis();

                    print.printEVSResults(evsResults);
                    log.info(new Date() +"\tEVS Server is running.");
                    System.out.println(new Date() + "\tEVS Server is running.");
                    this.sleep(timeDelay * 1000);
                  
                }
                
            }
            else if(runTime > 0)
            {
                for( int i=0; i< runTime; i++)
                {
                    long startTime = System.currentTimeMillis(),endTime =0;
                    evsResults = (List)appService.evsSearch(evsQuery);
                    endTime = System.currentTimeMillis();

                    print.printEVSResults(evsResults);
                    log.info(new Date() +"\tEVS Server is running.");
                    System.out.println(new Date() + "\tEVS Server is running.");
                    startTime = System.currentTimeMillis();                    
                    this.sleep(timeDelay * 1000);
                    endTime = System.currentTimeMillis();
                    //System.out.println("delay in miliseconds = "+ (endTime - startTime));
                    
                }
                
            }   */     

        }
        catch(Exception ex)
        {
            String error = ex.getMessage();
            if(error != null)
            {
            	 log.error(new Date() +"\tEVS Server is down. Error: " + error);
                 System.out.println(new Date() + "\tEVS Server is down. Error: " + error);
            }
            
        }
    }
    
    void monitor(int runTime, int minutesDelay)
    {
        this.runTime = runTime;
        this.timeDelay = minutesDelay;
        
        this.start();
        

    }
    /**
     * @param args
     */
    public static void main(String[] args) 
    {
        // TODO Auto-generated method stub
        int runTime = 0; // -1 mean run indefinitely, a positive integer means run that numbers of time
        int timeDelay = 0; // delay in seconds
        if(args.length > 0)
        {
            runTime = new Integer(args[0]).intValue();
            timeDelay = new Integer(args[1]).intValue(); 
            //System.out.println("runTime: " + runTime + "\ttimeDelay: " + timeDelay);
        }
        
        EVSMonitor evsMonitor = new EVSMonitor();              
        evsMonitor.monitor(runTime, timeDelay);
        
    }
    

}
