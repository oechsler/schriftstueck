class ConfigRepositoryImpl : ConfigRepository {

    private val configPath: Path = Paths.get("${System.getProperty("user.home")}/.snug")

    private val configFile: File = File("$configPath/config.json")

    private val serializer = Json { prettyPrint = true }

    override fun retrieve(): Config? {
        if (!configFile.exists())
            return null

        val configString = configFile.readText()
        return serializer.decodeFromString<Config>(configString)
    }

    override fun create(config: Config) {
        Files.createDirectories(configPath)
        configFile.createNewFile()

        val configString = serializer.encodeToString(config)

        configFile.writeText(configString)
    }

    override fun delete() {
        if (!configFile.exists())
            return

        configFile.delete()
    }

}
