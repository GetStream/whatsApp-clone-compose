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

subprojects {
  apply(from = "$rootDir/spotless/spotless.gradle")

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += listOf(
      "-Xskip-prerelease-check",
      "-Xopt-in=kotlin.RequiresOptIn",
      "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
      "-Xopt-in=com.google.accompanist.pager.ExperimentalPagerApi",
      "-Xopt-in=androidx.compose.material3.ExperimentalMaterial3Api",
      "-Xopt-in=androidx.lifecycle.compose.ExperimentalLifecycleComposeApi"
    )
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}
