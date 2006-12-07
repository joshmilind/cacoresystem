                         Readme.txt

    
                           Version 3.2

                           December 22, 2006

    

================================================================

                            Contents

================================================================

    

    1.0 Introduction

    2.0 Required Software

          2.1 Java 2 Platform

          2.2 Apache Ant

    3.0 Example Programs       

          3.1 Running the TestClient Example

          3.2 Running the TestEVS Example

          3.2 Running the EVSWSClient Example

          3.4 Running the WSTestClient

          3.5 Running the TestXML Example

          3.6 Running the TestDSR Example

      

    4.0 License

    

================================================================

                        1.0 Introduction

================================================================

    

    This distribution consists of a cacore32-client.jar file, required

    library files, demo programs and an Ant build script.

 

    The demo programs include:

        - TestClient - demonstrates various ways to execute

                  searches with and without using Application Service.

        - TestEVS - demonstrates various ways to 

                  search the Enterprise Vocabulary Services (EVS).

        - EVSWSClient - demonstrates various ways to search

                  EVS using web services.

        - WSTestClient - demonstrates various ways to search

                  web services.

        - TestDSR - demonstrates various ways to search caDSR objects.

        - TestXML - demonstrates the usage of the XMLUtility class.
        
        - TestCQL - demonstrates various ways to execute

                  searches using the SDK Query Object.

        - TestSVG - demonstrates the usage of the caBIO SVG manipulation tool 
 

    Please see the following for the associated release notes:

 

         - caCORE3.2_notes.txt 

 

================================================================

                     2.0 Required Software 

================================================================

    

----------------------------------------------------------------

2.1 Java 2 Platform (required)

----------------------------------------------------------------

 

    Java 2 Platform Enterprise Edition (J2EE) or Standard

    Edition (J2SE) is required to compile and run caCORE. 

    J2SDK jdk1.5.0.04 or later version is required. You can 

    download the JDK from Sun Microsystems, Inc. at the 

    following locations:

    

        http://java.sun.com/j2ee/

        http://java.sun.com/j2se/

 

----------------------------------------------------------------

2.2 Apache Ant

----------------------------------------------------------------

    To successfully run the TestClient program using the enclosed 

    build file you will need to have Apache Ant installed. The 

    program has been tested with Ant-1.6.5.

   

 

    Ant is an open source compile tool available at:

    

        http://ant.apache.org/

================================================================

          3.0 Example Programs

================================================================

    

----------------------------------------------------------------

3.1 Running the TestClient Example

----------------------------------------------------------------

 

    1. Unzip the Client.zip file.

    2. In the directory that contains the TestClient.java file.

       Type "ant rundemo". The Test Program will run.

 

----------------------------------------------------------------

3.2 Running the TestEVS Example

----------------------------------------------------------------

 

    1. Unzip the Client.zip file.

    2. cd to the directory that contains the TestEVS.java file.

       Type "ant runevs". The Test Program will run.

    

    

----------------------------------------------------------------

3.3 Running the EVSWSClient Example

----------------------------------------------------------------

 

    1. Unzip the Client.zip file.

    2. cd to the directory that contains the EVSWSClient.java file.

       Type "ant runevsws". The Test Program will run.

    

----------------------------------------------------------------

3.4 Running the WSTestClient Example

----------------------------------------------------------------

 

    1. Unzip the Client.zip file.

    2. cd to the directory that contains the WSTestClient.java file.

       Type "ant runws". The Test Program will run.

 

----------------------------------------------------------------

3.5 Running the TestXML Example

----------------------------------------------------------------

 

    1. Unzip the Client.zip file.

    2. cd to the directory that contains the TestXML.java file.

       Type "ant runxmltest". The Test Program will run.

 

----------------------------------------------------------------

3.6 Running the TestDSR Example

----------------------------------------------------------------

 

    1. Unzip the Client.zip file.

    2. cd to the directory that contains the TestDSR.java file.

       Type "ant rundsr". The Test Program will run.

 
----------------------------------------------------------------

3.7 Running the TestCQL Example

----------------------------------------------------------------

 

    1. Unzip the Client.zip file.

    2. cd to the directory that contains the TestCQL.java file.

       Type "ant runcqldemo". The Test Program will run.
 

----------------------------------------------------------------

3.2 Running the TestSVG Example

----------------------------------------------------------------

 

    1. Unzip the Client.zip file.

    2. cd to the directory that contains the TestSVG.java file.

       Type "ant runsvgdemo". The Test Program will run.
       
================================================================

                          4.0 License

================================================================

    

    The caBIO version 3.2 software is licensed under the terms

    contained in the licence located at:

    

        http://ncicb.nci.nih.gov/download/cabiolicenseagreement.jsp

    

    This product includes software developed by the

    Apache Software Foundation (http://www.apache.org/).

    -Apache SOAP, Crimson, Xerces, and Xalan are part of the Apache

    XML project.
    
    -Tomcat, ORO, and Lucene are part of the Apache Jakarta project. 

    All aforementioned Apache projects are trademarks of 

    the Apache Software Foundation. For further

    open source licensing issues pertaining to the Apache Software

    Foundation, visit:

    

        http://www.apache.org/LICENSE 

    

    Sun, Sun Microsystems, Solaris, Java, JavaServer Web

    Development Kit, and JavaServer Pages are trademarks or

    registered trademarks of Sun Microsystems, Inc. The jaxp.jar

    and jaxb-rt-1.0-ea.jar are redistributed as whole binary

    jars and are subject to the Sun license terms as stated in

    

        http://java.sun.com/xml/docs/summer02/LICENSE.html

    

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

 

    This product includes software developed by Castor (http://www.castor.org), which is

    licensed under the Exolab license:

 

        http://www.castor.org/license.html

 

 

    The caBIO dataset includes the results of queries to the 100K

    mapping array annotations in the NetAffx(tm) Analysis Center 

    of Affymetrix, Inc. ("Affymetrix").  Use of these annotations 

    are subject to the Affymetrix terms and conditions concerning 

    the use of content obtained from the NetAffx(tm) Analysis Center, 

    which are found at http://www.affymetrix.com/site/terms.affx.

    

    

    

//end
