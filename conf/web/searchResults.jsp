<%@page contentType="text/html"%>
<HTML>
<HEAD>
<title>SearchIndex</title>
<link href="styleSheet.css" type="text/css" rel="stylesheet" />

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
		 gov.nih.nci.common.util.search.*,		 
		 java.util.*" %>
<%
	IndexSearchUtils searchUtils = (IndexSearchUtils)session.getAttribute("indexSearchUtils");
	List results = searchUtils.getDisplayResults();
	String searchString = searchUtils.getSearchQuery().getKeyword();
	int pageSize = searchUtils.getPageSize();
	String expand = request.getParameter("expand")!= null?request.getParameter("expand"):"false";
	String recordNumber = request.getParameter("recordNumber")!= null?request.getParameter("recordNumber"):null;
	System.out.println("Record number:" + recordNumber);
	String startIndex = request.getParameter("startIndex")!=null?request.getParameter("startIndex"):null;
	
%>
<HR COLOR=BLUE>
<form method=post action="searchService.jsp" name=form1>
<table width="100%">
	<tr valign="top" align="left">
		<td>
 			<img src="images/minisearch.jpg" name="caCORE Search API" border="0" align=middle>
 		</td>
 		
 		<td align=left valign=top>
 			<INPUT TYPE=TEXT SIZE=60 name="searchString" value="<%=searchString%>">					
			<INPUT TYPE=SUBMIT NAME="submit" VALUE="Search">					
 		</td>
 		<hr color=blue>
<tr bgColor="#FAF8CC"><td align=left><%=searchUtils.getResultCounter()%> records found</td><td align=right>
<%
if(startIndex != null){
	searchUtils.setStartIndex(Integer.parseInt(startIndex));
	results = searchUtils.getDisplayResults();
}
System.out.println("StartIndex: "+ searchUtils.getStartIndex());
if(searchUtils.getResultCounter() >= pageSize){
	if(searchUtils.getStartIndex() > 0 && searchUtils.getStartIndex()>= pageSize){
	    int preStartIndex = searchUtils.getStartIndex() - pageSize;
		%><a href="searchService.jsp?startIndex=<%=preStartIndex%>"> previous </a><%
	}
	int end = searchUtils.getStartIndex() + pageSize -1;
	%>
	<%=searchUtils.getStartIndex()%> to <%=end%> 
	<%
	if((searchUtils.getStartIndex()+ pageSize) < searchUtils.getResultCounter()){
	    int nextStartIndex = searchUtils.getStartIndex() + pageSize;
	    %><a href="searchService.jsp?startIndex=<%=nextStartIndex%>"> next </a><%
}

}

%></td><tr>
</table>
</form>


<hr color=black>
<table>
<%

String queryUrl = request.getContextPath()+"/GetHTML?query=";
int recordCounter = 0;
if(recordNumber != null){
	recordCounter = Integer.parseInt(recordNumber);
}

	for(int i=0; i<results.size(); i++){	
		SearchResult result = (SearchResult)results.get(i);	
		String className = result.getClassName();
		Integer hit = result.getHit();
		String id = result.getId()!=null?result.getId():null;
		String queryString = queryUrl + className +"[id="+id+"]";
		System.out.println("queryString: "+ queryString);
			%>
			<tr><td><p><a href="<%=queryString%> target='_blank'"><b><font size=4 color=blue><%=hit%>.<%=className%></font></b></a></p></td></tr>			
			<%
		String infoText = null;
		if(expand.equals("true") && recordCounter == i){
			for(Iterator it=result.getProperties().keySet().iterator();it.hasNext();){
						String key = (String) it.next();
						String value = (String)result.getProperties().get(key);
						if(!key.equalsIgnoreCase("_hibernate_class")){
							%>							
							<tr><td><b><i><font size:4><%=key%>:</b></i> <%=value%></font></td></tr>
							<%
						}
			}
		}else{
					for(Iterator it=result.getProperties().keySet().iterator();it.hasNext();){
						String key = (String) it.next();
						String value = (String)result.getProperties().get(key);
						if(!key.equalsIgnoreCase("_hibernate_class")){
							if(searchUtils.getKeyText(result.getKeyword(), value)!=null){
								infoText = searchUtils.getKeyText(result.getKeyword(), value);
								break;
							}
						}
					}		
					if(infoText != null && infoText.trim().length()>10){
						String num = String.valueOf(i);
						%>						
						<tr><td><br><p><font size=4><%=infoText%></font>
						<a href="searchResults.jsp?expand=true&recordNumber=<%=num%>"><font size:2>more...</font></a>
						</p>
						<br></td></tr>
						<%
					}else{
					for(Iterator it=result.getProperties().keySet().iterator();it.hasNext();){
						String key = (String) it.next();
						String value = (String)result.getProperties().get(key);
						if(!key.equalsIgnoreCase("_hibernate_class")){
							%>
							<br>
							<table>
							<tr><td><b><i><font size:4><%=key%>:</b></i> <%=value%></font></td></tr>
							</table>
							<%
						}
					}}

		}
			
	}
	%>
</table>
</BODY>
</HTML>