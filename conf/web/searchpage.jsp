<%@page contentType="text/html"%>
<HTML>
<HEAD>
<title>SearchIndex</title>
<link href="styleSheet.css" type="text/css" rel="stylesheet" />
<SCRIPT> 

</SCRIPT>
</HEAD>
<BODY>


<!-- end of logo --> 

<%@ page import="gov.nih.nci.search.*,
		 gov.nih.nci.system.applicationservice.*,
		 java.lang.reflect.*,
		 java.util.*" %>

<HR COLOR=BLUE>
<form method=post action="searchpage.jsp" name=form1>
<table width="100%">
	<tr valign="top" align="center">
		<td>
 			<img src="images/searchicon.jpg" name="caCORE Search API" border="0" align=middle>
 		</td>
 		</tr>
 		</tr>
 		<td align=center>
 			<INPUT TYPE=TEXT name="searchString" value="" >
			<INPUT TYPE=HIDDEN NAME="check" VALUE="true"> 	
			<INPUT TYPE=SUBMIT NAME="submit" VALUE="Search">
			<INPUT TYPE=SUBMIT NAME="reset" VALUE="Reset">			
 		</td>
 		<tr>
 		<td align=center>
 			<INPUT TYPE=CHECKBOX NAME="FULL_TEXT_SEARCH" VALUE="YES"/>Quick Search
 		</td>
 		</tr>
 	</tr>
</table>
	
</form>
<HR COLOR=BLUE>
<% 
try{
	String searchString = request.getParameter("searchString");
	String luceneQuery = "NO";
	String url = null;
	if(request.getParameter("FULL_TEXT_SEARCH")!=null){
	 	luceneQuery=request.getParameter("FULL_TEXT_SEARCH");
	}	
	String check = request.getParameter("check");
	
	String servletPath = request.getServletPath();
	String queryPath = request.getRequestURL().toString();	
	System.out.println("Servlet path: "+ servletPath);
	System.out.println("query path: "+ queryPath);
	
	if(servletPath!=null && queryPath!=null){
		if(queryPath.indexOf(servletPath)>-1){
			url = queryPath.substring(0, queryPath.lastIndexOf(servletPath));		
		}
	}	
	
	System.out.println("URL: "+url);
	ApplicationService appService = ApplicationServiceProvider.getApplicationService();;
	
	if(url != null){
		appService = ApplicationServiceProvider.getRemoteInstance(url +"/http/remoteService");
	}else{
		appService = ApplicationServiceProvider.getApplicationService();
	}
	
	
	SearchQuery query = new SearchQuery();
	query.setKeyword(searchString);
	System.out.println("Lucene Query: "+ luceneQuery);
	if(!luceneQuery.equalsIgnoreCase("yes")){
		query.setQueryType("HIBERNATE_SEARCH");
	}
	
	List results = appService.search(SearchQuery.class, query);
	System.out.println("Record counter: "+ results.size());
	if(results.size()>0){	
	%>
	<hr>	
	<table>
	
	<%
	for(int i=0; i<results.size(); i++){
	
			Object result = results.get(i);
			
			java.util.HashMap properties = new java.util.HashMap();
			if(result.getClass().getName().endsWith("SearchResult")){
				%>Number of records found: <%=results.size()%><%
				gov.nih.nci.search.SearchResult r =  (gov.nih.nci.search.SearchResult)result;
				properties = r.getProperties();	
				String hit = String.valueOf(r.getHit());
			%>	
				<tr><td><B><U><%=hit%>.<%=r.getClassName()%></B></U></td></tr>
			<%
				for(Iterator it=properties.keySet().iterator();it.hasNext();){
					String key = (String) it.next();
					String value = (String)properties.get(key);
					if(!key.equalsIgnoreCase("_hibernate_class")){
						%>
						<tr><td></td><td><%=key%> - <%=value%></td></tr>
						<%
					}
				}
					
			}else if(result.getClass().getName().endsWith("HashMap")){	
				System.out.println("Result: "+ result.getClass().getName());
				HashMap map = (HashMap)result;
				int total = 0;
				for(Iterator it= map.keySet().iterator(); it.hasNext();){
					String key = (String) it.next();
					List l = (List) map.get(key);
					total += l.size();
					}
				%>Number of records found: <%=total%><%
				int counter = 0;
				for(Iterator it= map.keySet().iterator(); it.hasNext();){
					String key = (String) it.next();
					List l = (List) map.get(key);
					total += l.size();
					
					for(Iterator t=l.iterator(); t.hasNext();){
						Object value = t.next();

				counter++;
				%>
				<tr><td><B><U><%=counter%>.<%=key%></B></U></td></tr>
				<%
				
				Field[] fields = value.getClass().getDeclaredFields();
				for(int f=0; f<fields.length; f++){
					Field field = fields[f];
					field.setAccessible(true);
					if(field.get(value)!=null){
						try{
							%>
							<tr>
							<td></td>
							<td><%=field.getName()%></td>
							<td><%=field.get(value)%></td>
							</tr>
							<%
						}catch(Exception ex){
						
						}
					}
				}
						
					}
				}
			}else{ 			
				%>Number of records found: <%=results.size()%><%
				int counter = i + 1;%>
				<tr><td><B><U><%=counter%>.<%=result.getClass().getName()%></B></U></td></tr>
				<%
				System.out.println("Result: "+ result.getClass().getName());
				Field[] fields = result.getClass().getDeclaredFields();
				for(int f=0; f<fields.length; f++){
					Field field = fields[f];
					field.setAccessible(true);
					if(field.get(result)!=null){
						try{
							%>
							<tr>
							<td></td>
							<td><%=field.getName()%></td>
							<td><%=field.get(result)%></td>
							</tr>
							<%
						}catch(Exception ex){
						
						}
					}
				}	
			}			
		}
	%>
	<!--
	Number of records found: <%=results.size()%>
		
		
			
		
		</table>	
	-->
	<%}else if(check.equalsIgnoreCase("true")){
		%>No records found<%
	}

}catch(Exception ex){
	System.out.println("JSP Error: "+ ex.getMessage());
}
%>



</BODY>
</HTML>