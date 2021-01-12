@echo off
set/p name= El commit:
git pull origin main
git add .
git commit -m %name%
git push origin main
pause