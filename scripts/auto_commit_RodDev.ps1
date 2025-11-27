<#
Script: auto_commit_RodDev.ps1
Propósito: Cambiar a la rama RodDev (crearla si hace falta), sincronizar con origin, añadir todos los cambios,
hacer un commit con el mensaje "Cambios de última hora en código" (si no hay cambios crea un commit vacío) y pushear a origin/RodDev.

Uso: Abre PowerShell, sitúate en la raíz del repositorio o ejecuta este script desde la raíz del proyecto.
Ejemplo:
    cd 'C:\Users\Asgard\Documents\ProyectosAndroid\Aplicacion_Mobile'
    pwsh -NoProfile -ExecutionPolicy Bypass -File .\scripts\auto_commit_RodDev.ps1

Notas:
- Si el remoto "origin" no existe lo añadirá apuntando a: https://github.com/ClaudioFranciscoDelgadoGallardo/Aplicacion_Mobile.git
- Si hay conflictos al hacer pull --rebase, el script se detendrá y te indicará los archivos en conflicto.
- Si necesitas forzar un push por reescritura de historial (por ejemplo, después de --amend), revisa y usa:
# git push --force-with-lease origin RodDev
#>

param(
    [string]$RepoPath = 'C:\Users\Asgard\Documents\ProyectosAndroid\Aplicacion_Mobile',
    [string]$RemoteName = 'origin',
    [string]$RemoteUrl = 'https://github.com/ClaudioFranciscoDelgadoGallardo/Aplicacion_Mobile.git',
    [string]$Branch = 'RodDev',
    [string]$CommitMessage = 'Cambios de última hora en código'
)

function Run-Git([string]$args) {
    Write-Host "\n> git $args" -ForegroundColor Cyan
    $proc = Start-Process git -ArgumentList $args -NoNewWindow -PassThru -Wait -RedirectStandardOutput -RedirectStandardError
    $out = $proc.StandardOutput.ReadToEnd()
    $err = $proc.StandardError.ReadToEnd()
    if ($out) { Write-Host $out }
    if ($err) { Write-Host $err -ForegroundColor Yellow }
    return @{ Out = $out; Err = $err; ExitCode = $proc.ExitCode }
}

# Moverse al repo
Set-Location -Path $RepoPath
Write-Host "Working directory: $(Get-Location)" -ForegroundColor Green

# Comprobar remote origin
$remoteCheck = Run-Git "remote get-url $RemoteName"
if ($remoteCheck.ExitCode -ne 0 -or -not $remoteCheck.Out) {
    Write-Host "Remote '$RemoteName' no encontrado. Se añadirá: $RemoteUrl" -ForegroundColor Yellow
    Run-Git "remote add $RemoteName $RemoteUrl"
} else {
    Write-Host "Remote '$RemoteName' -> $($remoteCheck.Out.Trim())" -ForegroundColor Green
}

# Traer refs remotas
Run-Git "fetch $RemoteName"

# Intentar cambiar a la rama o crear tracking si existe en remoto
$sw = Run-Git "switch $Branch"
if ($sw.ExitCode -ne 0) {
    Write-Host "switch local falló, intentando trackear desde remoto..." -ForegroundColor Yellow
    $sw2 = Run-Git "switch --track $RemoteName/$Branch"
    if ($sw2.ExitCode -ne 0) {
        Write-Host "No existe en remoto, creando nueva rama local $Branch" -ForegroundColor Yellow
        Run-Git "checkout -b $Branch"
    }
}

Run-Git "branch --show-current"

# Hacer pull --rebase si el branch existe remotamente
$pull = Run-Git "pull --rebase $RemoteName $Branch"
if ($pull.ExitCode -ne 0) {
    Write-Host "Pull falló o hay conflictos. Revisa el estado y resuelve conflictos manualmente." -ForegroundColor Red
    Run-Git "status --porcelain"
    exit 1
}

# Comprobar cambios
$status = (git status --porcelain)
if ($status -and $status.Trim() -ne '') {
    Write-Host "Cambios detectados. Añadiendo y commiteando..." -ForegroundColor Green
    Run-Git "add -A"
    $commit = Run-Git "commit -m \"$CommitMessage\""
    if ($commit.ExitCode -ne 0) {
        Write-Host "Commit falló. Comprueba mensajes de error." -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "No hay cambios. Se creará un commit vacío con el mensaje solicitado." -ForegroundColor Yellow
    Run-Git "commit --allow-empty -m \"$CommitMessage\""
}

# Push al remoto (establecer upstream la primera vez)
$push = Run-Git "push -u $RemoteName $Branch"
if ($push.ExitCode -ne 0) {
    Write-Host "Push falló. Si reescribiste historial y necesitas forzar, considera: git push --force-with-lease $RemoteName $Branch" -ForegroundColor Red
    exit 1
}

Write-Host "Push completado." -ForegroundColor Green
Run-Git "log -1 --pretty=fuller"
Run-Git "show --name-only HEAD"

Write-Host "Operación finalizada. Verifica en GitHub: https://github.com/ClaudioFranciscoDelgadoGallardo/Aplicacion_Mobile/tree/$Branch" -ForegroundColor Cyan

