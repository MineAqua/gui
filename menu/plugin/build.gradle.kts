plugins {
    id("com.github.johnrengelman.shadow") version("7.1.2")
    id("gui.common-conventions")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    implementation(project(":gui-menu-api"))
    implementation(project(":gui-item-skull-api"))
    implementation(project(":gui-item-api"))
}