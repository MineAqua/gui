rootProject.name = "gui"

include(":item:api")

arrayOf("api", "plugin").forEach {
    include(":menu:$it")
}