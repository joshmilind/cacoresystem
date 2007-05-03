<%@page contentType="text/html"%>
<HTML>
<HEAD>
<title>SearchIndex</title>
</HEAD>
<BODY>


<%@ page import="gov.nih.nci.search.*,
		 gov.nih.nci.common.util.search.*,			 
		 java.util.*" %>
<%

	String searchString = request.getParameter("searchString")!= null?request.getParameter("searchString"):"";
	String queryType = request.getParameter("FULL_TEXT_SEARCH")!= null?request.getParameter("FULL_TEXT_SEARCH"):"";
	String fuzzySearch = request.getParameter("FUZZY_SEARCH")!= null?request.getParameter("FUZZY_SEARCH"):"";
	String pageSize = request.getParameter("PAGE_SIZE")!= null?request.getParameter("PAGE_SIZE"):"";	
	String startIndex = request.getParameter("startIndex")!= null?request.getParameter("startIndex"):"";	
	System.out.println("SearchService: "+searchString+queryType+fuzzySearch+pageSize+"\t"+startIndex);	
	
	IndexSearchUtils searchUtils = new IndexSearchUtils();
	SearchQuery searchQuery = null;
	String url = request.getContextPath()+"/IndexService";
	
	if(startIndex.equals("") && searchString.equals("")){
	    url = request.getContextPath()+"/indexSearch.jsp";	   
	}else if(!startIndex.equals("") && searchString.equals("")){
	    //check if session is valid
	    if(session.getAttribute("indexSearchUtils")!=null){
	        searchUtils = (IndexSearchUtils)session.getAttribute("indexSearchUtils");
	        searchUtils.setStartIndex(Integer.parseInt(startIndex));
	    }else{
	        url = request.getContextPath()+"/indexSearch.jsp";	
	    }
	}else {	    
	    searchQuery = new SearchQuery();
	    searchQuery.setKeyword(searchString);
	    if(queryType.equals("")){
	        searchQuery.setQueryType("HIBERNATE_SEARCH");    
            	}  
	    if(pageSize.length()>0){            	
	       searchUtils.setPageSize(Integer.parseInt(pageSize));
            }   
	}
	
        if(searchQuery != null){        
            searchUtils.setSearchQuery(searchQuery);
        }
	session.setAttribute("indexSearchUtils", searchUtils);	
	response.sendRedirect(url);		

%>



</BODY>
</HTML>