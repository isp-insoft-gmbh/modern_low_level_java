# setting some Paths
$JDKPATHLINUX='/usr/lib/jvm/jdk-24.0.1'
$JDKPATHWINDOWS='C:\Users\Public\D\jdk-24.0.1'

# for Timemesure
$startTime = Get-Date

Write-Host '...setting the JAVA ENVs...'
if ($IsLinux) {
    if (Test-Path -Path $JDKPATHLINUX) {
        $env:JAVA_HOME = $JDKPATHLINUX
        $env:JAVA_HOME
        $env:PATH = $env:JAVA_HOME + "/bin:" + $env:PATH
        $env:PATH
    } else {
        Write-Host '...JDK Path '$JDKPATHLINUX' not found'
        Exit
    }

} else {
    if (Test-Path -Path $JDKPATHWINDOWS) {
        $env:JAVA_HOME = $JDKPATHWINDOWS
        $env:JAVA_HOME
        $env:PATH = $env:JAVA_HOME + "\bin:" + $env:PATH
        $env:PATH
    } else {
        Write-Host '...JDK Path '$JDKPATHWINDOWS' not found'
        Exit
    }
}