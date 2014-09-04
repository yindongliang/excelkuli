technical point£ºread excel file£¬invoke python script to use sikuli 
advantage:set environment easily£¬data and code are almost sepreted ,no need to write plenty of code¡£
disadvantage£ºwhen write excel,have to know python and sikuli API¡£



usage
.\jre\bin\java.exe  -jar excelsikuli.jar sikuli_IF.xlsx :will excute all sheets in sikuli_IF.xlsx
.\jre\bin\java.exe  -jar excelsikuli.jar sikuli_IF.xlsx sheet1 :will excute  sheet1 in sikuli_IF.xlsx
.\jre\bin\java.exe  -jar excelsikuli.jar sikuli_IF.xlsx sheet1,sheet2 :will excute  sheet1 and sheet2 in sikuli_IF.xlsx

tips
vnc fullscreen dsiplay .\\bin\\VNC-Viewer-5.2.0-Windows-64bit.exe FullScreen=1
vnc color level .\\bin\\VNC-Viewer-5.2.0-Windows-64bit.exe ColorLevel=rgb222 ( if need to use the images from someone's environment,must be aware of product big version,pixel,color level)

python and sikuli api
refer to sikuli_IF.xlsx excel comment
http://doc.sikuli.org/
https://github.com/sikuli/sikuli
http://www.w3cschool.cc/python/python-tutorial.html


©À©¤bin                         //vnc.exe ide.bat
©À©¤image                       //search image
©¦  ©À©¤com                      
©¦  ©À©¤user                     
©¦  ©¦  ©¸©¤dereck

©À©¤img                        //screen shot
©À©¤jre                        //jre1.7 
©¦  ©À©¤bin
©¦  ©¦  ©À©¤client
©¦  ©¦  ©À©¤dtplugin
©¦  ©¦  ©À©¤plugin2
©¦  ©¦  ©¸©¤server
©¦  ©¸©¤lib                     
©¦      ©À©¤applet
©¦      ©À©¤cmm
©¦      ©À©¤deploy
©¦      ©¦  ©¸©¤jqs
©¦      ©À©¤ext
©¦      ©À©¤fonts
©¦      ©À©¤i386
©¦      ©À©¤images
©¦      ©¦  ©¸©¤cursors
©¦      ©À©¤management
©¦      ©À©¤security
©¦      ©À©¤servicetag
©¦      ©¸©¤zi
©¦          ©À©¤Africa
©¦          ©À©¤America
©¦          ©¦  ©À©¤Argentina
©¦          ©¦  ©À©¤Indiana
©¦          ©¦  ©À©¤Kentucky
©¦          ©¦  ©¸©¤North_Dakota
©¦          ©À©¤Antarctica
©¦          ©À©¤Asia
©¦          ©À©¤Atlantic
©¦          ©À©¤Australia
©¦          ©À©¤Etc
©¦          ©À©¤Europe
©¦          ©À©¤Indian
©¦          ©À©¤Pacific
©¦          ©¸©¤SystemV
©À©¤lib                           //java sikuli lib
©¦  ©À©¤Microsoft.VC90.CRT
©¦  ©¸©¤Microsoft.VC90.OPENMP
©¸©¤logs                         //logs
