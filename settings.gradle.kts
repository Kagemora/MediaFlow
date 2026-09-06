pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MediaFlow"
include(":app")

include(":core:common")
include(":core:database")
include(":core:datastore")
include(":core:model")
include(":core:navigation")
include(":core:ui")
include(":core:network")

include(":feature:auth:api")
include(":feature:auth:impl")
include(":feature:feed:api")
include(":feature:feed:impl")
include(":feature:market:api")
include(":feature:market:impl")
include(":feature:video:api")
include(":feature:video:impl")
