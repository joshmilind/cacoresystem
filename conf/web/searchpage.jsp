<%@page contentType="text/html"%>
<HTML>
<HEAD>
<title>SearchIndex</title>
<link href="styleSheet.css" type="text/css" rel="stylesheet" />
<SCRIPT> 

</SCRIPT>
</HEAD>
<BODY>

<!-- nci hdr begins -->
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="hdrBG">
        <tr>
          <td width="283" height="37" align="left">
          <a href="http://www.cancer.gov"><img alt="National Cancer Institute" src="images/logotype.gif" width="283" height="37" border="0"/></a>
          </td>          
          <td width="295" height="37" align="right">
          <a href="http://www.cancer.gov"><img alt="U.S. National Institutes of Health | www.cancer.gov" src="images/tagline.gif" width="295" height="37" border="0"/></a>
          </td>
        </tr>
      </table>
  
  <!-- nci hdr ends -->
<!-- end of logo --> 

<%@ page import="gov.nih.nci.search.*,
		 gov.nih.nci.common.util.*,	
		 gov.nih.nci.system.applicationservice.*,
		 java.lang.reflect.*,
		 java.util.*" %>
<%
	String searchString = "";
	if(request.getParameter("searchString")!= null){
		searchString = request.getParameter("searchString");
	}

%>

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
 			<INPUT TYPE=TEXT name="searchString" value=<%=searchString%> >
			<INPUT TYPE=HIDDEN NAME="check" VALUE="true">			
			<INPUT TYPE=SUBMIT NAME="submit" VALUE="Search">
			<INPUT TYPE=SUBMIT NAME="reset" VALUE="Reset">			
 		</td>
 		<tr>
 		<td align=center>
 			<INPUT TYPE=CHECKBOX NAME="FULL_TEXT_SEARCH" VALUE="YES"/>Quick Search
 		</td>
 		</tr>
  		<tr>
  		<td align=center>
  			<INPUT TYPE=CHECKBOX NAME="FUZZY_SEARCH" VALUE="YES"/>Fuzzy Search
  		</td>
 		</tr>
 	</tr>
</table>
	
</form>
<HR COLOR=BLUE>
<% 
try{
	
	String luceneQuery = "NO";
	String url = null;
	if(request.getParameter("FULL_TEXT_SEARCH")!=null){
	 	luceneQuery=request.getParameter("FULL_TEXT_SEARCH");
	}
	String fuzzy = "NO";
	if(request.getParameter("FUZZY_SEARCH")!=null){
		fuzzy=request.getParameter("FUZZY_SEARCH");
	}
	String check = request.getParameter("check");
	
	String servletPath = request.getServletPath();
	String queryPath = request.getRequestURL().toString();	
	if(servletPath!=null && queryPath!=null){
		if(queryPath.indexOf(servletPath)>-1){
			url = queryPath.substring(0, queryPath.lastIndexOf(servletPath));		
		}
	}	
	
	
	ApplicationService appService = ApplicationServiceProvider.getApplicationService();;
	
	if(url != null){
		appService = ApplicationServiceProvider.getRemoteInstance(url +"/http/remoteService");
	}else{
		appService = ApplicationServiceProvider.getApplicationService();
	}
	
	
	SearchQuery query = new SearchQuery();
	query.setKeyword(searchString);
	
	if(!luceneQuery.equalsIgnoreCase("yes")){
		query.setQueryType("HIBERNATE_SEARCH");
	}
	
	if(fuzzy.equalsIgnoreCase("yes")){
		query.setFuzzySearch(true);
	}
	
	List results = appService.search(SearchQuery.class, query);
	
	if(results.size()>0){
	%>Number of records found: <%=results.size()%><%
	%>
	<hr>	
	<table>
	
	<%
	for(int i=0; i<results.size(); i++){
	
			Object result = results.get(i);
			
			java.util.HashMap properties = new java.util.HashMap();
			if(result.getClass().getName().endsWith("SearchResult")){			
			
				gov.nih.nci.search.SearchResult r =  (gov.nih.nci.search.SearchResult)result;
				properties = r.getProperties();	
				String hit = String.valueOf(r.getHit());
				
			%>	
				<tr><td><B><U><%=hit%>.<%=r.getClassName()%></B></U></td></tr>
			<%
				String info = null;
				for(Iterator it=properties.keySet().iterator();it.hasNext();){
					String key = (String) it.next();
					String value = (String)properties.get(key);
					if(JSPUtils.getKeyDescription(r.getKeyword(), value)!=null){
						info = JSPUtils.getKeyDescription(r.getKeyword(), value);
						break;
					}
					
				}
				if(info !=null){
					%>
						<tr><td></td><td><%=info%></td></tr>
					<%
				}				
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
				
				HashMap map = (HashMap)result;
				int total = 0;
				for(Iterator it= map.keySet().iterator(); it.hasNext();){
					String key = (String) it.next();
					List l = (List) map.get(key);
					total += l.size();
					}
				%>Number of records found : <%=total%><%
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
				
				int counter = i + 1;%>
				<tr><td><B><U><%=counter%>.<%=result.getClass().getName()%></B></U></td></tr>
				<%
				
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
<br>
<hr>
<br>
<!-- footer begins -->
      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ftrTable">
        <tr>
          <td valign="top">
            <div align="center">
              <a href="http://www.cancer.gov/"><img src="images/footer_nci.gif" width="63" height="31" alt="National Cancer Institute" border="0"/></a>
              <a href="http://www.dhhs.gov/"><img src="images/footer_hhs.gif" width="39" height="31" alt="Department of Health and Human Services" border="0"/></a>
              <a href="http://www.nih.gov/"><img src="images/footer_nih.gif" width="46" height="31" alt="National Institutes of Health" border="0"/></a>
              <a href="http://www.firstgov.gov/"><img src="images/footer_firstgov.gif" width="91" height="31" alt="FirstGov.gov" border="0"/></a>
            </div>
          </td>
        </tr>
      </table>
  <!-- footer ends -->

</BODY>
</HTML>