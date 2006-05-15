@echo off
rem OLD caCORE Ant task
rem @ant -f core-build.xml %1 %2 %3


ant -f ..\cacoresdk\build.xml -Dfile.build.custom=..\caCORESYSTEM\build-custom-cacore.xml -Dfile.properties.custom=..\caCORESYSTEM\build-custom-cacore.properties %1 %2 %3

