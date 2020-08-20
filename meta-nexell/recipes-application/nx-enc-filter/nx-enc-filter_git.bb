DESCRIPTION = "nexell camera encode application with filter framework."

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://Makefile.am;md5=109ed2266c06e5f684141b2094d41db8"

SRC_URI = " \
	file://enc-filter \
"
S = "${WORKDIR}/enc-filter"

PV = "NEXELL"
PR = "0.1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "nx-v4l2 nx-video-api"

inherit autotools pkgconfig

EXTRA_OECONF = " \
     '--prefix=${STAGING_DIR_HOST}' \
     "

do_configure() {
	cd ${S}
    NOCONFIGURE=true ./autogen.sh
    oe_runconf ${EXTRA_OECONF}
}

do_compile() {
	cd ${S}
    oe_runmake clean
    oe_runmake \
		AM_CPPFLAGS+="$(WARN_CFLAGS) \
		-I. \
		-I${STAGING_INCDIR} \
		"
}

do_install() {
	cd ${S}
    install -d ${D}${bindir}
    oe_runmake install DESTDIR=${D}
}

INSANE_SKIP_${PN} = "installed-vs-shipped"
FILES_${PN} = "${bindir}"
ALLOW_EMPTY_${PN} = "1"
