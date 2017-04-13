HOW TO BUILD THE caCORE APIs

1.  Check out/download the caCORE SDK CVS project (cacoresdk).

2.  Check out/download the caCORE API system CVS project (cacoresystem).
    Make sure that the directories where you save the files share the same
    parent.  For example, use c:\dev\cacoresdk and c:\dev\cacoresystem.

3.  Edit the build.xml file in the caCORE API (cacoresystem) directory:
    - Set the "basedir" attribute of the <project> tag to the full path name
      where you saved the caCORE SDK project (e.g, c:\dev\cacoresdk)
    - If the name of the directory where you saved the caCORE API files is no
      called "cacoresystem", change the "value" attribute of the cacore_home
      property to the name of this directory.
	  
4.  Edit the core-deploy.properties in the caCORE API (cacoresystem) directory 
    to set the paths and properties to your JBoss and database applications.
    
5.  From the caCORE API (cacoresystem) directory, run the "build-system" Ant 
    task.

    
    