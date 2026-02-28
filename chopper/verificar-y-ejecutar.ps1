# Script de Verificación y Ejecución - Chopper Backend
# Este script verifica los requisitos y ejecuta la aplicación

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  VERIFICADOR CHOPPER BACKEND" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

$errores = 0

# 1. Verificar Java
Write-Host "[1/4] Verificando Java..." -ForegroundColor Yellow
try {
    $javaVersion = & java -version 2>&1 | Select-Object -First 1
    Write-Host "  ✓ Java encontrado: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "  ✗ Java NO encontrado" -ForegroundColor Red
    Write-Host "    Instala Java 17+ desde: https://adoptium.net/" -ForegroundColor Yellow
    $errores++
}

# 2. Verificar PostgreSQL
Write-Host "[2/4] Verificando PostgreSQL..." -ForegroundColor Yellow
try {
    $pgService = Get-Service -Name "postgresql*" -ErrorAction SilentlyContinue | Select-Object -First 1
    if ($pgService) {
        if ($pgService.Status -eq "Running") {
            Write-Host "  ✓ PostgreSQL está ejecutándose" -ForegroundColor Green
        } else {
            Write-Host "  ✗ PostgreSQL está instalado pero NO está ejecutándose" -ForegroundColor Red
            Write-Host "    Inicia el servicio desde Services (services.msc)" -ForegroundColor Yellow
            $errores++
        }
    } else {
        Write-Host "  ⚠ No se pudo detectar PostgreSQL (puede estar instalado de otra forma)" -ForegroundColor Yellow
        Write-Host "    Verifica manualmente que PostgreSQL esté ejecutándose" -ForegroundColor Yellow
    }
} catch {
    Write-Host "  ⚠ No se pudo verificar PostgreSQL" -ForegroundColor Yellow
}

# 3. Verificar JAR
Write-Host "[3/4] Verificando JAR compilado..." -ForegroundColor Yellow
$jarPath = ".\target\chopper-0.0.1-SNAPSHOT.jar"
if (Test-Path $jarPath) {
    $jarSize = (Get-Item $jarPath).Length / 1MB
    Write-Host "  ✓ JAR encontrado: $($jarSize.ToString('0.00')) MB" -ForegroundColor Green
} else {
    Write-Host "  ✗ JAR NO encontrado en: $jarPath" -ForegroundColor Red
    Write-Host "    Ejecuta: .\mvnw.cmd clean package -DskipTests" -ForegroundColor Yellow
    $errores++
}

# 4. Verificar base de datos (intentar conexión)
Write-Host "[4/4] Verificando base de datos chopper_backend..." -ForegroundColor Yellow
try {
    $env:PGPASSWORD = "postgres"
    $dbCheck = & psql -U postgres -d chopper_backend -c "SELECT 1;" 2>&1
    if ($LASTEXITCODE -eq 0) {
        Write-Host "  ✓ Base de datos 'chopper_backend' existe y es accesible" -ForegroundColor Green
    } else {
        Write-Host "  ✗ No se pudo conectar a 'chopper_backend'" -ForegroundColor Red
        Write-Host "    Crea la BD con: CREATE DATABASE chopper_backend;" -ForegroundColor Yellow
        Write-Host "    Luego ejecuta: psql -U postgres -d chopper_backend -f database_schema.sql" -ForegroundColor Yellow
        $errores++
    }
    $env:PGPASSWORD = $null
} catch {
    Write-Host "  ⚠ psql no está en el PATH, no se pudo verificar la BD" -ForegroundColor Yellow
    Write-Host "    Verifica manualmente que exista 'chopper_backend'" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "========================================" -ForegroundColor Cyan

if ($errores -eq 0) {
    Write-Host "✓ TODAS LAS VERIFICACIONES PASARON" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "Iniciando aplicación en 3 segundos..." -ForegroundColor Yellow
    Write-Host "La aplicación estará disponible en: http://localhost:8080" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "Endpoints principales:" -ForegroundColor White
    Write-Host "  • http://localhost:8080/cliente/traer" -ForegroundColor Gray
    Write-Host "  • http://localhost:8080/producto/traer" -ForegroundColor Gray
    Write-Host "  • http://localhost:8080/venta/traer" -ForegroundColor Gray
    Write-Host ""
    Write-Host "Presiona Ctrl+C para detener la aplicación" -ForegroundColor Yellow
    Write-Host ""
    Start-Sleep -Seconds 3

    & java -jar $jarPath
} else {
    Write-Host "✗ ENCONTRADOS $errores ERROR(ES)" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "Por favor, corrige los errores antes de ejecutar." -ForegroundColor Yellow
    Write-Host "Consulta INSTRUCCIONES.md para más detalles." -ForegroundColor Yellow
}

