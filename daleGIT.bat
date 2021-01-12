@echo off
echo 1. PULL
echo 2. PUSH
echo 3. Salir
echo.
set/p op= Elija una opcion:
if %op%== 1 (goto :1)
if %op%== 2 (goto :2)
if %op%== 3 (goto :3)

:1
cls
git pull origin main
pause
exit

:2
cls
set/p name= El commit entre comillas:
git pull origin main
git add .
git commit -m %name%
git push origin main
pause
exit