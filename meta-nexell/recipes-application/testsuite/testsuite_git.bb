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

	#adc
	cd ${S}/adc
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#gpio
	cd ${S}/gpio
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#i2c
	cd ${S}/i2c
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#mmc
	cd ${S}/mmc
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#pwm
	cd ${S}/pwm
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#spi
	cd ${S}/spi
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#uart
	cd ${S}/uart
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#watchdog
	cd ${S}/watchdog
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#cpu
	cd ${S}/cpu
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#sfr
	cd ${S}/sfr
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} CC="$CC" clean
	oe_runmake CROSS_COMPILE=${TARGET_PREFIX} INCLUDES="-I${STAGING_INCDIR}" LDFLAGS="-L${STAGING_LIBDIR}" CC="$CC"

	#main
	cd ${S}/main
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

	#adc_test
	install -m 0755 ${S}/adc/adc-test ${D}${bindir}

	#gpio-test
	install -m 0755 ${S}/gpio/gpio-test ${D}${bindir}

	#i2c-test
	install -m 0755 ${S}/i2c/i2c-test ${D}${bindir}

	#mmc-test
	install -m 0755 ${S}/mmc/mmc-test ${D}${bindir}

	#pwm-test
	install -m 0755 ${S}/pwm/pwm-test ${D}${bindir}

	#spi-test
	install -m 0755 ${S}/spi/spi-test ${D}${bindir}

	#uart-test
	install -m 0755 ${S}/uart/uart-test ${D}${bindir}

	#watchdog-test
	install -m 0755 ${S}/watchdog/watchdog-test ${D}${bindir}

	#cpuinfo-test
	install -m 0755 ${S}/cpu/cpuinfo-test ${D}${bindir}

	#sfr-test
	install -m 0755 ${S}/sfr/sfr-test ${D}${bindir}

	#test-main
	install -m 0755 ${S}/main/test-main ${D}${bindir}
}

INSANE_SKIP_${PN} = "ldflags"
FILES_${PN} = "${bindir} ${libdir}"
ALLOW_EMPTY_${PN} = "1"
