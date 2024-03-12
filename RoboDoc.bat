@echo off
chcp 65001>nul

set rodocPath=c:\_Sync\Vyucba\Materialy\@Robot\RoboDoc
set rodocName=RoboDoc
set javaPath=c:\Program Files\Java\jdk-1.8\bin

call c:\_jEdit\findJava.bat
echo javaPath: %javaPath%

if not exist "%rodocPath%\%rodocName%.class" (
	echo Compiling %rodocName%.
	"%javaPath%\java" -Dfile.encoding=UTF8 -jar "c:\_jEdit\compiler.jar" "%rodocPath%\compile-%rodocName%.txt" "%javaPath%" -q
)

if not exist "%rodocPath%\%rodocName%.class" (
	echo Failed…
	pause>nul
	goto :eof
)

"%javaPath%\java" -Dfile.encoding=UTF8 -jar "c:\_jEdit\launcher.jar" "%rodocPath%\launch-%rodocName%.txt" "%javaPath%" -q

echo.
echo Finished.
echo.

if not "-nowait" == "%1" (
	echo Press any key.
	@pause>nul
)