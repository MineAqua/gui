plugins {
    id("gui.publishing-conventions")
    id("io.papermc.paperweight.userdev") version "1.7.2"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

paperweight.reobfArtifactConfiguration.set(io.papermc.paperweight.userdev.ReobfArtifactConfiguration.MOJANG_PRODUCTION)

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")

    api(project(":gui-menu-api"))
}