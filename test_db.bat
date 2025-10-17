@echo off
echo Testing database setup...
curl -X POST http://localhost:8080/auth/test -H "Content-Type: application/json"
echo.
pause

