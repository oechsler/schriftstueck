@SnugDsl
class MountBuilder private constructor() {

    companion object {

        fun mounts(init: MountBuilder.() -> Unit): MountBuilder {
            return MountBuilder().apply(init)
        }

    }

    private var mounts = setOf<Mount>()

    private fun mount(mount: Mount) {
        val mutableSet = mounts.toMutableSet()
        mutableSet.add(mount)
        mounts = mutableSet.toSet()
    }

    // Short-hands for mount types
    val volume = MountType.VOLUME
    val config = MountType.CONFIG
    val secret = MountType.SECRET
    // END: Short-hands for mount types

    // Syntax for mounting a volume
    infix fun String.to(path: PathBuilder): VolumeWithPath {
        return VolumeWithPath(this, path)
    }

    infix fun VolumeWithPath.with(permission: PermissionBuilder) {
        mount(
            Mount(
                name,
                volume.value,
                path.toString(),
                permission.toString()
            )
        )
    }
    // END: Syntax for mounting a volume

    // Syntax for mounting any storage type
    infix fun String.from(type: MountType): StorageWithType {
        return StorageWithType(this, type)
    }

    infix fun StorageWithType.to(path: PathBuilder): StorageWithTypeAndPath {
        return StorageWithTypeAndPath(this, path)
    }

    infix fun StorageWithTypeAndPath.with(permission: PermissionBuilder) {
        val name = storageWithType.name
        val type = storageWithType.type

        mount(
            Mount(
                name,
                type.value,
                path.toString(),
                permission.toString()
            )
        )
    }
    // END: Syntax for mounting any storage type

    fun toMounts(): Set<Mount> {
        return mounts
    }

}