SUMMARY = "A ramdisk image just capable of allowing a device to boot."

IMAGE_FEATURES = ""
IMAGE_ROOTFS_SIZE = "16384"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
export IMAGE_BASENAME = "core-image-riscv-initramfs"

IMAGE_LINGUAS = ""

# NOTE we must use cpio.gz here as this is what mkbootimg requires
IMAGE_FSTYPES_forcevariable = "cpio.gz"

# We don't need depmod data here
KERNELDEPMODDEPEND = ""
USE_DEPMOD = "0"
PACKAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_BOOTSTRAP_INSTALL} ${CORE_IMAGE_EXTRA_INSTALL}"
PACKAGE_INSTALL += " busybox e2fsprogs bash packagegroup-nexell "

inherit extrausers
EXTRA_USERS_PARAMS = "\
    useradd -P nexell nexell; \
    "

inherit core-image
