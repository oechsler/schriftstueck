@SnugDsl
class ResourcesBuilder private constructor() : Script {

    companion object {

        fun resources(init: ResourcesBuilder.() -> Unit): ResourcesBuilder {
            return ResourcesBuilder().apply(init)
        }

    }

    private var resourceGroup = setOf<Script>()

    fun volume(name: String, block: VolumeBuilder.() -> Unit) {
        addScriptRoot(VolumeBuilder.volume(name, block))
    }

    fun deployment(name: String, block: DeploymentBuilder.() -> Unit) {
        this.addScriptRoot(DeploymentBuilder.deployment(name, block))
    }

    fun loadBalancer(name: String, block: LoadBalancerBuilder.() -> Unit) {
        this.addScriptRoot(LoadBalancerBuilder.loadBalancer(name, block))
    }

    private fun addScriptRoot(script: Script) {
        val mutableSet = this.resourceGroup.toMutableSet()
        mutableSet.add(script)
        this.resourceGroup = mutableSet
    }

    override fun apply() {
        resourceGroup.forEach{
            it.apply()
        }
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }

}