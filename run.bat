@echo off
cd /d "%~dp0"

if "%JAVA_HOME%"=="" (
    echo JAVA_HOME is not set.
    echo Please set JAVA_HOME to your JDK folder.
    pause
    exit /b 1
)

echo Compiling...
"%JAVA_HOME%\bin\javac.exe" src\Task.java src\Task_Manager.java src\TaskApp.java src\Main.java

if errorlevel 1 (
    echo.
    echo Compile failed.
    pause
    exit /b 1
)

echo Starting app...
"%JAVA_HOME%\bin\java.exe" -cp src Main

if errorlevel 1 (
    echo.
    echo App failed to start.
    pause
    exit /b 1
)