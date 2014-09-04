@echo off

PATH=%PATH%;%~d0%~p0libs
set JAVA_EXE=.\jre\bin\javaw.exe

start /B "Sikuli-IDE" ..\jre\bin\javaw.exe -Xms64M -Xmx512M -Dfile.encoding=UTF-8 -Dpython.path="..\lib\sikuli-script.jar/" -jar "..\lib\sikuli-ide.jar" %*
