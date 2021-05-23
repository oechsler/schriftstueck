class ApplyCommand: CliktCommand(name = "apply", help = "Evaluate and apply a resource script"), KoinComponent {

    private val scriptService: ScriptService by inject()

    private val scriptPath by argument().path(mustExist = true, canBeDir = false)

    override fun run() {
        try {
            val script = scriptService.loadFromPath(scriptPath, Script::class)
            script.apply()
        } catch (ex: ScriptCompileException) {
            echo("${ex.message}\n", err = true)
            ex.reports.forEach {
                echo(it, err = true)
            }
        } catch (ex: ScriptRuntimeException) {
            echo(ex.message, err = true)
        }
    }

}
