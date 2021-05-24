fun KoinApplication.apply() {
    val script = module(createdAtStart = true) {
        single<ScriptService> { ScriptServiceImpl() }
    }
    modules(script)
}