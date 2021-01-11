# JavaPacker

Semester project for studies using GoF design patterns - sometimes they are used by force :D


## Getting Started

The goal of the project is to encrypt another JAR executable based on the key provided by the user, save it to a file and create a decryptor and put it into the loader (I used ow2 asm to generate bytecode).
After launching the program and going through all the steps, you will get the default - output.jar in jar file location (configurable) file which is ready to run. Nothing hard! :)

## Configuration

```
#Mon Jan 11 10:08:37 CET 2021
useNullByteName=true
outputPath=output.jar
fakeDirectory=true
inputPath=input.jar
encryptionKey=ABCDEFGHIJKLMNPA
```

Property name  | expected value | Description
------------- | ------------- | -------------
inputPath  | Path to input jar file | Define input jar location
outputPath  | Path to output jar file | Define output jar location
encryptionKey  | 16 bytes(length) key | Key used to AES encryption
useNullByteName  | true/false | Method add \u0000 to the beginning of the class name. This method allows you to hide class names and prevent them from unpacking by popular archivers like WinRar
fakeDirectory  | true/false | https://github.com/x4e/fakedirectory - completely unavailable to anyone using WinRAR, Luyten and every other zip viewer.

## Comments

Such a packer <b>is not a protection you can rely on</b>. It can only come in handy against total noobs. If you already want to use it somewhere to add extra protection to your application - Please obfuscate the loader.
Remember that there are tons of ways to get around this - memory dump, defineClass hook, javaagent ... etc


## Screenshots

![alt text](https://i.imgur.com/jUrEsd8.png)
![alt text](https://i.imgur.com/967U67f.png)

## Credits
x4e - https://github.com/x4e/fakedirectory
