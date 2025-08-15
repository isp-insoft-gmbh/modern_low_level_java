if ($null -eq $env:JAVA_HOME) {
    Write-Host '..setting first the JAVA_HOME!!!'
    Exit-PSSession
}

Write-host '...cleaning...'
if (Test-Path -Path 'mods') {
    Write-Host '..remove mods..'
    Remove-Item mods -Recurse -Force -ProgressAction SilentlyContinue
}

if (Test-Path -Path 'jar') {
    Write-Host '..remove jar..'
    Remove-Item jar -Recurse -Force -ProgressAction SilentlyContinue
}

if (Test-Path -Path 'documentation') {
    Write-Host '..remove documentation..'
    Remove-Item documentation -Recurse -Force -ProgressAction SilentlyContinue
}

if (Test-Path -Path 'customjre') {
    Write-Host '..remove customjre..'
    Remove-Item customjre -Recurse -Force -ProgressAction SilentlyContinue
}

if (Test-Path -Path 'RachelCsvParser') {
    Write-Host '..remove old CLI App..'
    Remove-Item RachelCsvParser -Recurse -Force -ProgressAction SilentlyContinue
}

if (Test-Path -Path 'RachelCsvParserServer') {
    Write-Host '..remove old Server App..'
    Remove-Item RachelCsvParserServer -Recurse -Force -ProgressAction SilentlyContinue
}

Write-Host '...create keystore...'
if (!(Test-Path -Path 'keys.jks')) {
    Start-Process -NoNewWindow -FilePath keytool -ArgumentList '-genkeypair', '-alias jarkey', '-keyalg Ed25519', '-keystore keys.jks', '-validity 365', '-storepass pupupu', '-keypass pupupu', '-dname "cn=Normen Rachel, ou=CS, o=isp-insoft GmbH, c=DE"' -Wait
} else {
    Write-Host '...keyfile still exist...nothing to do...'
}

Write-Host '...create documentation...'
javadoc `@docArgs

Write-Host '...building...'
javac `@libArgs
javac `@cliArgs
javac `@serverArgs

Write-Host '..create jar''s...'
jar `@libJarArgs
jar `@cliJarArgs
jar `@serverJarArgs

Write-Host '..signing jar''s...'
jarsigner -verbose -keystore keys.jks -storepass pupupu -keypass pupupu jar/de.rachel.cli.jar jarkey
jarsigner -verbose -keystore keys.jks -storepass pupupu -keypass pupupu jar/de.rachel.lib.jar jarkey
jarsigner -verbose -keystore keys.jks -storepass pupupu -keypass pupupu jar/de.rachel.server.jar jarkey

Write-Host '...verifying jars''s...'
# java -XshowSettings is returned de for Language, but i has to use en for this call
# but i don't know why this is nessesary, the certs was build on the same system
# without this option....
# where came this nessesary for verifiy from?
jarsigner -verify "-J-Duser.language=en" -verbose -certs jar/de.rachel.cli.jar
jarsigner -verify "-J-Duser.language=en" -verbose -certs jar/de.rachel.lib.jar
jarsigner -verify "-J-Duser.language=en" -verbose -certs jar/de.rachel.server.jar

#Write-Host '...creating custom jre...'
#jlink `@cliLinkArgs

Write-Host '...creating CLI App...'
jpackage `@jpackArgs
Write-Host '...creating Server App...'
jpackage `@serverJpackArgs

Write-Host '####### WIN INFO#########"'
Write-Host 'Under run the Setup and follow the instruktions'
Write-Host 'To uninstall it use your Software Dashboard'
Write-Host '#########################"'
Write-Host '####### LINUX INFO#######"'
Write-Host 'Under Linux you can install the Package with "sudo dpkg -i <packagefilename>"'
Write-Host 'and uninstall with "sudo dpkg -r <packagename>"'

#Write-Host '..running from jars...'
#java `@runArgs
