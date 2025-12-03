Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "TESTING MICROSERVICIOS LEVELUP" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

Write-Host "=== [1/7] Testing Auth Service (8081) ===" -ForegroundColor Green
try {
    $response = Invoke-RestMethod -Uri 'http://localhost:8081/api/auth/health' -Method GET -TimeoutSec 5
    Write-Host "OK Auth Service" -ForegroundColor Green
    Write-Host "Response: $response" -ForegroundColor Gray
    Write-Host ""
} catch {
    Write-Host "FAILED Auth Service" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Yellow
    Write-Host ""
}

Write-Host "=== [2/7] Testing User Service (8082) ===" -ForegroundColor Green
try {
    $response = Invoke-RestMethod -Uri 'http://localhost:8082/api/usuarios/health' -Method GET -TimeoutSec 5
    Write-Host "OK User Service" -ForegroundColor Green
    Write-Host "Response: $response" -ForegroundColor Gray
    Write-Host ""
} catch {
    Write-Host "FAILED User Service" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Yellow
    Write-Host ""
}

Write-Host "=== [3/7] Testing Product Service (8083) ===" -ForegroundColor Green
try {
    $response = Invoke-RestMethod -Uri 'http://localhost:8083/api/productos/health' -Method GET -TimeoutSec 5
    Write-Host "OK Product Service" -ForegroundColor Green
    Write-Host "Response: $response" -ForegroundColor Gray
    Write-Host ""
} catch {
    Write-Host "FAILED Product Service" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Yellow
    Write-Host ""
}

Write-Host "=== [4/7] Testing Order Service (8084) ===" -ForegroundColor Green
try {
    $response = Invoke-RestMethod -Uri 'http://localhost:8084/api/ordenes/health' -Method GET -TimeoutSec 5
    Write-Host "OK Order Service" -ForegroundColor Green
    Write-Host "Response: $response" -ForegroundColor Gray
    Write-Host ""
} catch {
    Write-Host "FAILED Order Service" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Yellow
    Write-Host ""
}

Write-Host "=== [5/7] Testing Notification Service (8085) ===" -ForegroundColor Green
try {
    $response = Invoke-RestMethod -Uri 'http://localhost:8085/api/notificaciones/health' -Method GET -TimeoutSec 5
    Write-Host "OK Notification Service" -ForegroundColor Green
    Write-Host "Response: $response" -ForegroundColor Gray
    Write-Host ""
} catch {
    Write-Host "FAILED Notification Service" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Yellow
    Write-Host ""
}

Write-Host "=== [6/7] Testing File Service (8086) ===" -ForegroundColor Green
try {
    $response = Invoke-RestMethod -Uri 'http://localhost:8086/api/archivos/health' -Method GET -TimeoutSec 5
    Write-Host "OK File Service" -ForegroundColor Green
    Write-Host "Response: $response" -ForegroundColor Gray
    Write-Host ""
} catch {
    Write-Host "FAILED File Service" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Yellow
    Write-Host ""
}

Write-Host "=== [7/7] Testing API Gateway (8080) ===" -ForegroundColor Green
try {
    $response = Invoke-RestMethod -Uri 'http://localhost:8080/actuator/health' -Method GET -TimeoutSec 5
    Write-Host "OK API Gateway" -ForegroundColor Green
    Write-Host "Response: $response" -ForegroundColor Gray
    Write-Host ""
} catch {
    Write-Host "FAILED API Gateway" -ForegroundColor Red
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Yellow
    Write-Host ""
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "TESTING COMPLETO" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""
