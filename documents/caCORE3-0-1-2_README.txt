                            Readme.txt
     
                           caCORE Client
                          Version 3.0.1.2
                         September 9, 2005
    
================================================================
                            Contents
================================================================
    
    1.0 Introduction
    2.0 Required Software
          2.1 Java 2 Platform
          2.2 Apache Ant
    3.0 Running the TestClient and WSTestClient Example  	 
          3.1 TestClient Example
	  3.2 WSTestClient Example
   
    4.0 License
    
================================================================
                        1.0 Introduction
================================================================
    
    This distribution consists of a client.jar file, required
    library files, a TestClient.java program and an Ant build script.
    This also has WSTestClient.java to test caCORE web service

    Please see the following for the associated release notes:

         - caCORE3.0_notes.txt 

================================================================
                     2.0 Required Software 
================================================================
    
----------------------------------------------------------------
2.1 Java 2 Platform (required)
----------------------------------------------------------------

    Java 2 Platform Enterprise Edition (J2EE) or Standard
    Edition (J2SE) is required to compile and run caCORE. 
    J2SDK 1.4.2_06 or later version is required. You can 
    download the JDK from Sun Microsystems, Inc. at the 
    following locations:
    
        http://java.sun.com/j2ee/
        http://java.sun.com/j2se/

----------------------------------------------------------------
2.2 Apache Ant
----------------------------------------------------------------
    To successfully run the TestClient program using the enclosed 
    build file you will need to have Apache Ant installed. The 
    program has been tested with Ant-1.6.2 .
   

    Ant is an open source compile tool available at:
    
         - http://ant.apache.org/
================================================================
          3.0 Running the TestClient and WSTestClient Program
================================================================
    
    
    

----------------------------------------------------------------
3.1 Running the TestClient Example
----------------------------------------------------------------

    1. Unzip the Client.zip file.
    2. In the directory which contains the TestClient.java file
       type "ant rundemo". The TestProgram will run.

----------------------------------------------------------------
3.2 Running the TestEVS Example
----------------------------------------------------------------

    1. Unzip the Client.zip file.
    2. cd to webservice from the root directory which contains the WSTestClient.java file
       type "ant runevs". The Test Program will run.
    
    
----------------------------------------------------------------
3.2 Running the WSTestClient Example
----------------------------------------------------------------

    1. Unzip the Client.zip file.
    2. cd to webservice from the root directory which contains the WSTestClient.java file
       type "ant runws". The Test Program will run.
    

================================================================
                          4.0 License
================================================================
    
    The caBIO version 3.0 software is licensed under the terms
    contained in the licence located at:
    
        - http://ncicb.nci.nih.gov/core/caBIO/technical_resources/core_jar/license
    
    This product includes software developed by the
    Apache Software Foundation (http://www.apache.org/).
    Apache SOAP, Crimson, Xerces, and Xalan are part of Apache
    XML project, Tomcat, ORO, and Lucene are part of Apache
    Jakarta project. All aforementioned Apache projects are trademarks of 
    The Apache Software Foundation. For further
    open source licensing issues pertaining to Apache Software
    Foundation, visit:
    
        - http://www.apache.org/LICENSE 
    
    Sun, Sun Microsystems, Solaris, Java, JavaServer Web
    Development Kit, and JavaServer Pages are trademarks or
    registered trademarks of Sun Microsystems, Inc. The jaxp.jar
    and jaxb-rt-1.0-ea.jar are redistributed as whole binary
    jars and are subject to the Sun license terms as stated in
    
        - http://java.sun.com/xml/docs/summer02/LICENSE.html
    
    UNIX is a registered trademark in the United States and
    other countries, exclusively licensed through X/Open
    Company, Ltd.

    Oracle is a registered trademark of Oracle Corporation.
    
    Windows, WindowsNT, and Win32 are registered trademarks of
    Microsoft Corp. 
    
    All other product names mentioned herein and throughout the
    entire caCORE project are trademarks of their respective
    owners.

    Hibernate is Free Software. The LGPL license is sufficiently flexible 
    to allow the use of Hibernate in both open source and commercial projects.
    http://www.gnu.org/copyleft/lesser.html
 

    The caBIO dataset includes the results of queries to the 100K
    mapping array annotations in the NetAffx(tm) Analysis Center 
    of Affymetrix, Inc. ("Affymetrix").  Use of these annotations 
    are subject to the Affymetrix terms and conditions concerning 
    the use of content obtained from the NetAffx(tm) Analysis Center, 
    which are found at http://www.affymetrix.com/site/terms.affx.
    
    
    
//end

