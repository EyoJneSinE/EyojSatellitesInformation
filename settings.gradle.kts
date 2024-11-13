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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "EyojSatellittesInformation"
include(":app")
include(":core")
include(":eyojnavigation")
include(":feature")
include(":feature:main")
include(":feature:onboarding")
include(":feature:main:satellitecommunicator")
include(":core:common")
include(":feature:main:satellites")
include(":feature:main:satellitedetail")
