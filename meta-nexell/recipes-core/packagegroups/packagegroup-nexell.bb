SUMMARY = "NEXELL RISCV apps"
DESCRIPTION = "The riscv set of packages required by NEXELL"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = " \
    helloworld-nexell \
    android-tools-nexell \
    bash-completion \
"