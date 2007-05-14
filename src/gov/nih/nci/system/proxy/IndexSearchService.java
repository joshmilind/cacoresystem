package gov.nih.nci.system.proxy;
import java.io.IOException;
import java.util.*;
import gov.nih.nci.search.*;
import gov.nih.nci.system.applicationservice.*;
import gov.nih.nci.common.util.*;
import gov.nih.nci.common.util.search.IndexSearchUtils;
import gov.nih.nci.system.dao.impl.search.*;
import javax.servlet.http.*;
import javax.servlet.*;


import org.apache.log4j.Logger;
/*
 * Created on May 1, 2007
 * Shaziya Muhsin
 *
 */
public class IndexSearchService extends HttpServlet {

    private static Logger log= Logger.getLogger(IndexSearchService.class.getName());
    private int pageSize = 1000;
    public IndexSearchService() {
    }

    /**
     * Handls Post requests
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("searchResults.jsp");
        HttpSession session = request.getSession();
        IndexSearchUtils searchUtils = session.getAttribute("indexSearchUtils")!=null?(IndexSearchUtils)session.getAttribute("indexSearchUtils"):null;
        if(searchUtils != null){
            dispatcher = request.getRequestDispatcher("searchResults.jsp");
            if(!session.isNew() && !searchUtils.isNewQuery() && searchUtils.getResultCounter()>0){
                searchUtils.organizeResults();
            }else{
                try{
                    List results = query(searchUtils.getSearchQuery(), getURL(request));
                    searchUtils.setResultSet(results);
                    searchUtils.setResultCounter(results.size());
                    searchUtils.setNewQuery(false);
                    searchUtils.organizeResults();
                }catch(Exception ex){
                    throw new ServletException(ex);
                }
            }
            session.setAttribute("indexSearchUtils",searchUtils);
        }
        else{
            dispatcher = request.getRequestDispatcher("indexSearch.jsp");
        }
        dispatcher.forward(request,response);
    }
    private List query(SearchQuery searchQuery, String url) throws Exception{
       // SearchAPIDAO searchAPI = new SearchAPIDAO();
       // return searchAPI.query(searchQuery);
        ApplicationService appService = null;

        if(url != null){
            appService = ApplicationServiceProvider.getRemoteInstance(url +"/http/remoteService");
        }else{
            appService = ApplicationServiceProvider.getApplicationService();
        }
        return appService.search(SearchQuery.class, searchQuery);
    }

    private List query(SearchQuery searchQuery) throws Exception{
        SearchAPIDAO searchAPI = new SearchAPIDAO();
        return searchAPI.query(searchQuery);
    }
    /**
     * Unload servlet
     */
    public void destroy(){
        super.destroy();
    }

    /**
     * Handls Get requests
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request, response);
    }

      private String getURL(HttpServletRequest request)throws ServletException{
        String url = null;
        try{
            String servletPath = request.getServletPath();
            String queryPath = request.getRequestURL().toString();
            if(servletPath!=null && queryPath!=null){
                if(queryPath.indexOf(servletPath)>-1){
                    url = queryPath.substring(0, queryPath.lastIndexOf(servletPath));
                }
            }
        }catch(Exception ex){
            throw new ServletException(ex.getMessage());
        }
        return url;
      }



}
