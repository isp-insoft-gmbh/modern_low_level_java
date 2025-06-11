Write-Host '...building...'
javac `@libArgs
javac `@cliArgs

Write-Host '..running...'
java `@runArgs

Write-Host '..create jar''s..'
jar `@libJarArgs