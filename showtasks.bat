call runcrud.bat

if "%ERRORLEVEL%" == "0" goto fail

start "" "C:\Program Files\Mozilla Firefox\firefox.exe " http://localhost:8080/crud/v1/task/getTasks

:fail
echo.
echo There were errors