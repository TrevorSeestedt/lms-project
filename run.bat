@echo off
echo Compiling Java files...
javac -cp "./lib/json-simple-1.1.1.jar;./lib/junit-platform-console-standalone-1.9.2.jar" src/*.java
echo Running application...
java -cp ".;./lib/json-simple-1.1.1.jar;./lib/junit-platform-console-standalone-1.9.2.jar" src.CourseUI
pause 