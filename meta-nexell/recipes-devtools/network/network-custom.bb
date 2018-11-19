DESCRIPTION = "Nexell network setting"
SECTION = "Utils"
LICENSE = "Closed"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \
        file://interfaces \
"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${sysconfdir}
    install -m 0755 ${S}/interfaces ${D}${sysconfdir}
}

FILES_${PN} = "${sysconfdir}"