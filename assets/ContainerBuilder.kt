@SnugDsl
class ContainerBuilder private constructor(val name: String) {

    companion object {

        fun container(name: String, init: ContainerBuilder.() -> Unit): ContainerBuilder {
            return ContainerBuilder(name).apply(init)
        }

    }

    private lateinit var image: Image
    private var ports = setOf<Port>()

    fun image(name: String, tag: String) {
        image = ImageBuilder.image(name, tag).toImage()
    }

    fun ports(block: PortBuilder.() -> Unit) {
        this.ports = PortBuilder.ports(block).toPorts()
    }

    fun toContainer(): Container {
        return Container(name, image, ports)
    }

}

