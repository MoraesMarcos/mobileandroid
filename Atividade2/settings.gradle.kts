// Caminho: /settings.gradle.kts

pluginManagement {
    repositories {
        google()
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
    versionCatalogs {
        // Renomeado para evitar colisão com outro catálogo "libs"
        create("coreLibs") {
            from(files("gradle/libs.versions.toml"))
            // Se precisar importar mais TOMLs, use a MESMA chamada:
            // from(files("gradle/libs.versions.toml", "gradle/extra.versions.toml"))
        }
    }
}

rootProject.name = "Programacao"
include(":app")
