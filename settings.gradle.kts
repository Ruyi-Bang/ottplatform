import org.gradle.api.initialization.resolve.RepositoriesMode

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven {
            name = "GitHubPackagesPlugins"
            url = uri(System.getenv("PUBLISH_URL") ?: "https://maven.pkg.github.com/Ruyi-Bang/packages")
            credentials {
                username = System.getenv("GPR_USER")
                password = System.getenv("GPR_TOKEN")
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven {
            name = "GitHubPackagesRuyiBang"
            url = uri(System.getenv("PUBLISH_URL") ?: "https://maven.pkg.github.com/Ruyi-Bang/packages")
            credentials {
                username = System.getenv("GPR_USER")
                password = System.getenv("GPR_TOKEN")
            }
            content { includeGroup("org.ruyibang") }
        }
    }
    versionCatalogs {
        create("libs") {
            from("org.ruyibang:ottplatform.sharedlibraryversions:0.+")
        }
    }
}

rootProject.name = "ottplatform"
