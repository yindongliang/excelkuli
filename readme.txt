technical point��read excel file��invoke python script to use sikuli 
advantage:set environment easily��data and code are almost sepreted ,no need to write plenty of code��
disadvantage��when write excel,have to know python and sikuli API��



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


����bin                         //vnc.exe ide.bat
����image                       //search image
��  ����com                      
��  ����user                     
��  ��  ����dereck

����img                        //screen shot
����jre                        //jre1.7 
��  ����bin
��  ��  ����client
��  ��  ����dtplugin
��  ��  ����plugin2
��  ��  ����server
��  ����lib                     
��      ����applet
��      ����cmm
��      ����deploy
��      ��  ����jqs
��      ����ext
��      ����fonts
��      ����i386
��      ����images
��      ��  ����cursors
��      ����management
��      ����security
��      ����servicetag
��      ����zi
��          ����Africa
��          ����America
��          ��  ����Argentina
��          ��  ����Indiana
��          ��  ����Kentucky
��          ��  ����North_Dakota
��          ����Antarctica
��          ����Asia
��          ����Atlantic
��          ����Australia
��          ����Etc
��          ����Europe
��          ����Indian
��          ����Pacific
��          ����SystemV
����lib                           //java sikuli lib
��  ����Microsoft.VC90.CRT
��  ����Microsoft.VC90.OPENMP
����logs                         //logs
