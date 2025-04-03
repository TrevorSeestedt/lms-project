@echo off
echo Creating output directory...
if not exist out mkdir out
echo Ensuring JSON directories exist...
if not exist json mkdir json

echo Checking template files...
if exist usersExample.json (
    echo Template user file found.
) else (
    echo ERROR: usersExample.json not found!
    pause
    exit /b 1
)

if exist coursesExample.json (
    echo Template course file found.
) else (
    echo ERROR: coursesExample.json not found!
    pause
    exit /b 1
)

echo Compiling Java files...
javac -d out -cp "./lib/json-simple-1.1.1.jar;./lib/junit-platform-console-standalone-1.9.2.jar" src/*.java
echo Running application...
java -cp "out;./lib/json-simple-1.1.1.jar;./lib/junit-platform-console-standalone-1.9.2.jar" src.CourseUI
pause 