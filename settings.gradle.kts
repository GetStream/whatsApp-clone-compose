@file:Suppress("UnstableApiUsage")
pluginManagement {
  includeBuild("build-logic")
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
  }
}
rootProject.name = "WhatsAppCloneCompose"
include(":app")
include(":core:designsystem")
include(":core:navigation")
include(":core:model")
include(":core:network")
include(":core:database")
include(":core:data")
include(":core:uistate")
include(":features:camera")
include(":features:chats")
include(":features:status")
include(":features:calls")
include(":features:video")
include(":benchmark")
