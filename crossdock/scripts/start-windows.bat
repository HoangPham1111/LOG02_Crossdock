@echo off
cd /d "%~dp0.."
docker compose up --build -d
if errorlevel 1 goto fail
echo Open http://localhost:8080 when app startup completes.
docker compose logs -f app
exit /b 0
:fail
echo Please check Docker Desktop and read docs/01_CAI_DAT_VA_CHAY.md
pause
exit /b 1
