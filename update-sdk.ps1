function Get-SdkUri {
  param( [string]$Name, [string]$Version )
  "https://repo1.maven.org/maven2/org/firstinspires/ftc/$Name/$Version/$Name-$Version.aar"
}

$Version = $args[0]

rm -r libs\sdk | Out-Null
mkdir libs\sdk | Out-Null

$('Hardware', 'FtcCommon', 'RobotCore') | ForEach {
  $Uri = Get-SdkUri $_ $Version
  $TempFile = New-TemporaryFile

  $ProgressPreference = 'SilentlyContinue'
  Invoke-WebRequest -Uri $(Get-SdkUri $_ $Version) -OutFile $TempFile | Out-Null

  Expand-Archive $TempFile -DestinationPath "$($TempFile).d" -Force
  mv "$($TempFile).d\classes.jar" "libs\sdk\$($_)-$Version.jar"
}
