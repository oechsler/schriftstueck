@SnugDsl
open class StorageBuilder constructor(val name: String, private val storageClass: StorageClass): Script {

    lateinit var size: Size

    fun toStorage(): Storage {
        return Storage(name, size.toString(), storageClass.value)
    }

    override fun apply() {
        val serializer = Json { prettyPrint = true }
        println(serializer.encodeToString(toStorage()))
    }

    override fun rollback() {
        TODO("Not yet implemented")
    }

}