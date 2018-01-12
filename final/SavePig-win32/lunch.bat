set Path=%Path%;./dll

"%java_home%\bin\java" -classpath ".;digpig.jar;lib/org.eclipse.swt_3.2.1.v3235e.jar;lib/org.eclipse.swt.win32.win32.x86_3.2.1.v3235.jar;lib/org.eclipse.jface_3.2.1.M20060908-1000.jar;%java_home%\lib\rt.jar;%java_home%\lib\jsse.jar;%java_home%\lib\jce.jar%java_home%\lib\charsets.jar;%java_home%\lib\ext\dnsns.jar;%java_home%\lib\ext\localedata.jar;%java_home%\lib\ext\QTJava.jar;%java_home%\lib\ext\sunjce_provider.jar;%java_home%\lib\ext\sunpkcs11.jar" like.digpig.main.DigPigMain

REM exit