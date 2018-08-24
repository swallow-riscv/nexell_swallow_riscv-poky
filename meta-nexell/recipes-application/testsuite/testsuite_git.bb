SUMMARY = "Nexell testsuite - test application"
SECTION = "application"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://Makefile;md5=d5743c4d7fa2b466a875bac2c6176aa1"

SRCREV = "dcd349678b629dfb2bec07f3a2b7f1f3387a4c41"
SRC_URI = "git://git.nexell.co.kr/nexell/linux/apps/testsuite;protocol=git;branch=nexell"

DEPENDS = "nx-allocator nx-scaler nx-v4l2"
#DEPENDS = "nx-video-api nx-scaler"

S = "${WORKDIR}/git"

PV = "NEXELL"
PR = "0.1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_PACKAGE_STRIP = "1"

inherit pkgconfig

ARCH_TYPE = "${@get_kernel_arch(d,"${TARGET_PREFIX}")}"

do_compile() {
	#scaler_test
	cd ${S}/scaler_test_file
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"
	#camera_test
	cd ${S}/cam_test
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"
}

do_install() {
	install -d ${D}${bindir}
	install -d ${D}${libdir}

	#scaler_test
	install -m 0755 ${S}/scaler_test_file/scaler-test-file ${D}${bindir}
	install -m 0755 ${S}/scaler_test_file/input.yuv ${D}${bindir}
	#camera_test
	install -m 0755 ${S}/cam_test/cam_test ${D}${bindir}
}

INSANE_SKIP_${PN} = "ldflags"
FILES_${PN} = "${bindir} ${libdir}"
ALLOW_EMPTY_${PN} = "1"
