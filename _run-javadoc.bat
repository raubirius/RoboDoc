@echo off
chcp 65001
"C:\Program Files\Java\jdk-1.8\bin\javadoc.exe" -use -author -version -package -source 1.8 -noindex -notree -nohelp -exclude podpora apacheAntZIP -link https://docs.ORACLE.com/javase/8/docs/api/ -d robodoc -encoding UTF-8 -docencoding UTF-8 knižnica -notimestamp -nodeprecatedlist -Xdoclint:-html --allow-script-in-comments -stylesheetfile stylesheet.css
rem pause