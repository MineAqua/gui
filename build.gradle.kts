plugins {
    java
    `maven-publish`
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(8))
        }
    }

    repositories {
        mavenCentral()
        maven("https://repo.codemc.io/repository/nms/")
        maven("https://repo.unnamed.team/repository/unnamed-public/")
    }

    publishing {
        repositories {
            maven {
                val snapshot = version.toString().endsWith("-SNAPSHOT")
                val typeName = if (snapshot) "snapshots" else "releases"
                name = "unnamed-$typeName"

                val repoUrl = "https://repo.unnamed.team/repository/$name"
                setUrl(repoUrl)

                credentials {
                    username = System.getenv("REPO_USER")
                    password = System.getenv("REPO_PASSWORD")
                }
            }
        }

        publications {
            create<MavenPublication>("maven") {
                from(components["java"])
            }
        }
    }
}
