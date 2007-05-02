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

	String searchString = request.getAttribute("searchString")!= null?request.getAttribute("searchString"):null;
	String queryType = request.getAttribute("FULL_TEXT_SEARCH")!= null?request.getAttribute("FULL_TEXT_SEARCH"):null;
	String fuzzySearch = request.getAttribute("FUZZY_SEARCH")!= null?request.getAttribute("FUZZY_SEARCH"):null;
	String pageSize = request.getAttribute("PAGE_SIZE")!= null?request.getAttribute("PAGE_SIZE"):null;	
	
	IndexSearchUtils searchUtils = null;
	if(session.get("indexSearchUtils") == null){
	    searchUtils = new IndexSearchUtils();
	    searchUtils.setStartIndex(0);
	    if(pageSize != null){
	        searchUtils.setPageSize(Integer.parseInt(pageSize));
	    }
	}else{
	    searchUtils = session.get("indexSearchUtils");
	}
	SearchQuery searchQuery = new SearchQuery();
	if(searchString != null){	   
	    searchQuery.setKeyword(searchString);	    
	    if(fuzzySearch != null){
	        searchQuery.setFuzzySearch(true);
	    }
	    if(queryType == null){
	        searchQuery.setQueryType("HIBERNATE_SEARCH");
	    }
	    searchUtils.setSearchQuery(searchQuery);
	    session.setAttribute("indexSearchUtils", searchUtils);
	}
	response.sendRedirect(request.getContextPath()+"/"+IndexService);	

%>



</BODY>
</HTML>