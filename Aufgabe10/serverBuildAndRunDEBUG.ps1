if ($null -ne $env:JAVA_HOME) {
	Write-host '...cleaning...'
	if (Test-Path -Path 'mods') {
		Write-Host '..remove mods..'
		Remove-Item mods -Recurse -Force -ProgressAction SilentlyContinue
	}

    Write-Host '...building...'
    javac `@serverArgsDebug

    Write-Host '...running Server for Debug...'
    jdb `@serverRunArgs
} else {
    Write-Host '...please setting the JAVA_HOME!'
}
