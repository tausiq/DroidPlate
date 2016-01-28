Welcome to DroidPlate!
===================

Usage
-
####Step 1: Copy the library 
* Copy **droidplate** folder inside your project 
* Open settings.gradle (Project Settings) and add the following line, 
```include ':droidplate'```
* Add the following line inside **dependencies** in **build.gradle (Module:app)** file
```compile project(':droidplate')```
* Replace the content of **build.gradle (Project: level)** file 
```
 // Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()

        // CONFIG
        maven { url 'https://maven.fabric.io/public' }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.5.0'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files

        // CONFIG
        // https://bitbucket.org/hvisser/android-apt
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'

        // CONFIG
        // https://twittersdk.artifactoryonline.com/twittersdk/public/io/fabric/tools/gradle/
        classpath 'io.fabric.tools:gradle:1.21.2'
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

// CONFIG
project.ext {
    compileSdkVersion = 23
    buildToolsVersion = "23.0.2"
    minSdkVersion = 15
    targetSdkVersion = 23

    supportVersion = "23.1.1"
    annotationsVersion = "3.3.2"
    iconifyVersion = "2.1.0"
    glideVersion = "3.6.1"
    gsmVersion = "8.3.0"
    eventbusVersion = "2.4.0"
    commonslangVersion = "3.4"
}

task clean(type: Delete) {
    delete rootProject.buildDir
}
```  
* Relace **gradle.properties (Project properties)** with the following content
```
# Project-wide Gradle settings.

# IDE (e.g. Android Studio) users:
# Gradle settings configured through the IDE *will override*
# any settings specified in this file.

# For more details on how to configure your build environment visit
# http://www.gradle.org/docs/current/userguide/build_environment.html

# Specifies the JVM arguments used for the daemon process.
# The setting is particularly useful for tweaking memory settings.
# Default value: -Xmx10248m -XX:MaxPermSize=256m
org.gradle.jvmargs=-Xmx2048m -XX:MaxPermSize=512m -XX:+HeapDumpOnOutOfMemoryError -Dfile.encoding=UTF-8

# When configured, Gradle will run in incubating parallel mode.
# This option should only be used with decoupled projects. More details, visit
# http://www.gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects
org.gradle.parallel=true

# Signature
KEY_DEV = DEV
VAL_DEV = "Md. Shahab Uddin"
KEY_GIT_SHA = GIT_SHA
KEY_BUILD_TIME = BUILD_TIME

``` 
* Open **build.gradle (Module:app)** and add the lines
```
apply plugin: 'com.neenbedankt.android-apt'


android {
    compileSdkVersion rootProject.ext.compileSdkVersion
    buildToolsVersion rootProject.ext.buildToolsVersion

    defaultConfig {
        minSdkVersion rootProject.ext.minSdkVersion
        targetSdkVersion rootProject.ext.targetSdkVersion
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

repositories {
    jcenter()
    maven { url 'https://maven.fabric.io/public' }
}


apt {
    arguments {
        androidManifestFile variant.outputs[0]?.processResources?.manifestFile
        // if you have multiple outputs (when using splits), you may want to have other index than 0

        // you should set your package name here if you are using different application IDs
        // resourcePackageName "your.package.name"

        // You can set optional annotation processing options here, like these commented options:
        // logLevel 'INFO'
        // logFile '/var/log/aa.log'
        // RELEASE: make it false
        trace 'true'
    }
}
```

####Step 2:####
Sync gradle 

####Step 3:####
* Create an application class and extends from
```
com.genericslab.droidplate.app.CoreApplication
```
* Add your app in AndroidManifest.xml inside ```<application>``` tag 
* Extend your main theme from **Droid.Theme** 
```
<style name="AppTheme" parent="Droid.Theme">
```
* Replace **colors.xml** with the following content and remove theme colors 
```
    <color name="color.primary">#009688</color>
    <color name="color.primary.dark">#00796B</color>
    <color name="color.primary.light">#B2DFDB</color>
    <color name="color.accent">#536DFE</color>
    <color name="color.button.normal">@color/color.accent</color>
    <color name="color.button.disable">#3f51b5</color>
    <color name="color.button.pressed">#448AFF</color>
    <color name="color.divider">#B6B6B6</color>
```