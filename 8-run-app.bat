@echo off
@setlocal enableextensions
@cd /d "%~dp0"

echo Running Application
java -jar book-club-management-system/target/book-club-management-system-1.0-SNAPSHOT.jar

echo Operation Completed!
pause