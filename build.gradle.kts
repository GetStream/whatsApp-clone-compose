buildscript {
  repositories {
    google()
    maven("https://plugins.gradle.org/m2/")
  }
  dependencies {
    classpath(Dependencies.androidGradlePlugin)
    classpath(Dependencies.kotlinGradlePlugin)
    classpath(Dependencies.kotlinSerializationPlugin)
    classpath(Dependencies.ksp)
    classpath(Dependencies.spotlessGradlePlugin)
    classpath(Dependencies.hiltPlugin)
  }
}
