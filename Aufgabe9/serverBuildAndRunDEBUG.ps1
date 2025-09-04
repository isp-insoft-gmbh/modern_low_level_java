if ($null -ne $env:JAVA_HOME) {
    Write-Host '...building...'
    javac `@serverArgsDebug

    Write-Host '...running Server...'
    jdb `@serverRunArgs
} else {
    Write-Host '...please setting the JAVA_HOME!'
}
