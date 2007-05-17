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
		 java.lang.reflect.*,
		 java.util.*" %>
<%
	IndexSearchUtils searchUtils = (IndexSearchUtils)session.getAttribute("indexSearchUtils");
	List results = searchUtils.getDisplayResults();
	String searchString = searchUtils.getSearchQuery().getKeyword();
	int pageSize = searchUtils.getPageSize();
	String expand = request.getParameter("expand")!= null?request.getParameter("expand"):"false";
	String recordNumber = request.getParameter("recordNumber")!= null?request.getParameter("recordNumber"):null;
	
	String startIndex = request.getParameter("startIndex")!=null?request.getParameter("startIndex"):null;
	
%>
<form method=post action="searchService.jsp" name=form1>
<table width="100%">
	<tr valign="top" align="left">
	<%
	String adrs = request.getContextPath()+"/indexSearch.jsp";
	%>
		<td><a href="<%=adrs%>">
 			<img src="images/minisearch.jpg" name="caCORE Search API" border="0" align=middle>
 			</a>
 		</td>
 		
 		<td align=left valign=top>
 			<INPUT TYPE=TEXT SIZE=60 name="searchString" value="<%=searchString%>">					
			<INPUT TYPE=SUBMIT NAME="submit" VALUE="Search">
			<INPUT TYPE=HIDDEN NAME="FULL_TEXT_SEARCH" value="FULL_TEXT_SEARCH">
 		</td>
 		
<tr bgColor="#FAF8CC"><td align=left width=25%><%=searchUtils.getResultCounter()%> records found</td><td align=right>
<%
if(startIndex != null){
	searchUtils.setStartIndex(Integer.parseInt(startIndex));
	results = searchUtils.getDisplayResults();
}

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

<table>
<%

String queryUrl = request.getContextPath()+"/GetHTML?query=";
int recordCounter = 0;
if(recordNumber != null){
	recordCounter = Integer.parseInt(recordNumber);
}
if(searchUtils.getSearchQuery().getQueryType().equals("FULL_TEXT_SEARCH")){
	for(int i=0; i<results.size(); i++){	
		SearchResult result = (SearchResult)results.get(i);	
		String className = result.getClassName();
		Integer hit = result.getHit();
		String id = result.getId()!=null?result.getId():null;
		String queryString = queryUrl + className +"[id="+id+"]";
		
			%>
			<tr><td><font color=blue><a href="<%=queryString%> target='_BLANK'"><div class="aheading"><%=hit%>.<%=className%></div></a></font></td></tr>			
			<%
		String infoText = null;
		if(expand.equals("true") && recordCounter == i){
			for(Iterator it=result.getProperties().keySet().iterator();it.hasNext();){
						String key = (String) it.next();
						String value = (String)result.getProperties().get(key);
						if(!key.equalsIgnoreCase("_hibernate_class")){
							%>							
							<tr><td><b><i><div class="apara"><%=key%>:</b></i> <%=value%></div></td></tr>
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
					
					for(Iterator it=result.getProperties().keySet().iterator();it.hasNext();){
						String key = (String) it.next();
						String value = (String)result.getProperties().get(key);
						if(!key.equalsIgnoreCase("_hibernate_class")){
							%>							
							<table style="word-break:break-all;table-layout:fixed" >
							<tr><td width=25%></td><td cellpadding=2><div class="apara"><b><i><%=key%>:</b></i> <%=value%></div></td></tr>
							</table>
							<%
						}
					}

		}
		%><hr><%
			
	}
}else{
	//hibernate search
	for(int i=0; i<results.size(); i++){
		Object result = results.get(i);
		int counter = i + 1;
		%>
		<tr><td><div class="aheading"<%=counter%>.<%=result.getClass().getName()%></a></td></tr>
		<%
		Field[] fields = result.getClass().getDeclaredFields();
		for(int f=0; f<fields.length; f++){
		Field field = fields[f];
		field.setAccessible(true);
		if(field.get(result)!=null){
		String fieldName = field.getName();
		String fieldValue = "";
		try{
			if(field.getType().getName().startsWith("java")&& field.get(result)!=null){
				fieldValue = String.valueOf(field.get(result));
			}
			
		}catch(Exception ex){
		}
		if(!fieldValue.equals("")){
		%>
		<tr>
		<td></td>
		<td><%=fieldName%></td>
		<td><%=fieldValue%></td>
		</tr>
		<%
		
		}
		
		}
		}	
	}

}
	%>
</table>
<HR COLOR=BLUE>

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