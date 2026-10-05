$ErrorActionPreference = 'Stop'
$lintVersion = '14.3.0'
$lintDirectory = Join-Path $PSScriptRoot '.tools'
New-Item -ItemType Directory -Path $lintDirectory -Force | Out-Null
$lintJar = Join-Path $lintDirectory "checkstyle-$lintVersion-all.jar"
if (-not (Test-Path -LiteralPath $lintJar)) {
    Invoke-WebRequest "https://github.com/checkstyle/checkstyle/releases/download/checkstyle-$lintVersion/checkstyle-$lintVersion-all.jar" -OutFile $lintJar
}
$javaFiles = @(Get-ChildItem -LiteralPath (Join-Path $PSScriptRoot 'src') -Recurse -Filter '*.java' | ForEach-Object { $_.FullName })
& java -jar $lintJar -c (Join-Path $PSScriptRoot 'checkstyle.xml') $javaFiles
exit $LASTEXITCODE
