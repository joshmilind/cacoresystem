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

	String searchString = request.getParameter("searchString")!= null?request.getParameter("searchString"):null;
	String queryType = request.getParameter("FULL_TEXT_SEARCH")!= null?request.getParameter("FULL_TEXT_SEARCH"):null;
	String fuzzySearch = request.getParameter("FUZZY_SEARCH")!= null?request.getParameter("FUZZY_SEARCH"):null;
	String pageSize = request.getParameter("PAGE_SIZE")!= null?request.getParameter("PAGE_SIZE"):null;	
	String startIndex = request.getParameter("startIndex")!= null?request.getParameter("startIndex"):null;	
	System.out.println("SearchService: "+searchString+queryType+fuzzySearch+pageSize+"\t"+startIndex);	
	
	
	IndexSearchUtils searchUtils = null;
	SearchQuery searchQuery = null;
	
	
	if(searchString != null){
		    searchQuery = new SearchQuery();
		    searchQuery.setKeyword(searchString);	    
		    if(fuzzySearch != null){
		        searchQuery.setFuzzySearch(true);
		    }
		    if(queryType == null){
		       searchQuery.setQueryType("HIBERNATE_SEARCH");		       
		    }
		}
		
		if(session.isNew()){
		    searchUtils = new IndexSearchUtils();
		    searchUtils.setStartIndex(0);
		    if(pageSize != null){
		        searchUtils.setPageSize(Integer.parseInt(pageSize));
		    }
		}else{
		    System.out.println("Session value of utils: "+ session.getAttribute("indexSearchUtils") );
			
			   if(session.getAttribute("indexSearchUtils") == null){
			   System.out.println("null session");
				    searchUtils = new IndexSearchUtils();
				    searchUtils.setStartIndex(0);
				    if(pageSize != null){
				        searchUtils.setPageSize(Integer.parseInt(pageSize));
				    }
			   }else{
				    searchUtils = (IndexSearchUtils)session.getAttribute("indexSearchUtils");			    
			   }		
		}
		if(searchUtils.getSearchQuery()!=null){
			String oldTerm = searchUtils.getSearchQuery().getKeyword();
			System.out.println("Old term: "+ oldTerm);
			if(oldTerm != null && searchString != null){
				if(!oldTerm.equals(searchString)){
					searchUtils = new SearchUtils();
					searchUtils.setNewQuery(true);	
					searchQuery.setQueryType("FULL_TEXT_SEARCH");				
				}
				
			}
		}
		
		if(searchQuery != null){
			searchUtils.setSearchQuery(searchQuery);
		}else if(startIndex != null){		
			int start = Integer.parseInt(startIndex);
			searchUtils.setStartIndex(start);		   	
		}
		
	
	
	session.setAttribute("indexSearchUtils", searchUtils);
	System.out.println("Session attribute: "+ session.getAttribute("indexSearchUtils"));
	String url = request.getContextPath()+"/IndexService";
	System.out.println("url: "+url);
	response.sendRedirect(url);	
	

%>



</BODY>
</HTML>