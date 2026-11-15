Set-Location "$PSScriptRoot/.."
$env:RUN_DB_TESTS='true'
$env:DB_URL='jdbc:postgresql://localhost:5432/crossdock_test'
$env:DB_USER='crossdock'
if (-not $env:DB_PASSWORD) { $env:DB_PASSWORD='crossdock_demo_2026' }
mvn test
exit $LASTEXITCODE
