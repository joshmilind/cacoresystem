@echo off
rem OLD caCORE Ant task
rem @ant -f core-build.xml %1 %2 %3


ant -f ..\cacoretoolkit\build.xml -Dfile.build.custom=..\cacore\build-custom-cacore.xml -Dfile.properties.custom=..\cacore\build-custom-cacore.properties %1 %2 %3

