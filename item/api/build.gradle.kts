dependencies {
    api("org.jetbrains:annotations:22.0.0")
    arrayOf("validation", "bukkit").forEach {
        api("team.unnamed.common:commons-$it:2.0.0")
    }

    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
}