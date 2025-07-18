if ($null -ne $env:JAVA_HOME) {
    Write-Host '...building...'
    javac `@serverArgs

    Write-Host '...running Server...'
    java `@serverRunArgs
} else {
    Write-Host '...please setting the JAVA_HOME!'
}
