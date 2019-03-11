SUMMARY = "NEXELL RISCV apps"
DESCRIPTION = "The riscv set of packages required by NEXELL"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

NEXELL_LIBS = " \
nx-allocator \
nx-scaler \
nx-v4l2 \
"

RDEPENDS_${PN} = " \
    helloworld-nexell \
    android-tools-nexell \
    bash-completion \
    ${NEXELL_LIBS} \
    testsuite \
    mtd-utils \
    ffmpeg \
"
