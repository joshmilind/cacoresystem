
<HTML>
<HEAD>
<TITLE>Happy Page</TITLE>

</HEAD>
<FRAMESET rows="10%,85%,5%" title="Happy Page">
	<FRAME src="Header.jsp" name="pageHeader" scrolling="no">
	<FRAMESET cols="25%,75%" title="Happy Page" >
		<FRAMESET rows="30%,70%" title="" >
			<FRAME src="Packages.jsp" name="packageList" title="All Packages" scrolling="yes">
			<FRAME src="Classes.jsp" name="packageClasses" title="All classes" scrolling="yes">
		</FRAMESET>
	<FRAME src="Criteria.jsp" name="classFrame" title="Criteria Page" scrolling="yes">		
	</FRAMESET>
	<FRAME src="Footer.jsp" name="pageFooter" scrolling="no">
</FRAMESET>
</HTML>