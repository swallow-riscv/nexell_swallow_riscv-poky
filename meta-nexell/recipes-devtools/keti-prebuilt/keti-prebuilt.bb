DESCRIPTION = "KETI Condor Modem Driver Control Application & Utility"
SECTION = "Utils"
LICENSE = "Closed"
LIC_FILES_CHKSUM = " \
  file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6 \
"

SRC_URI = " \
    file://modem_init.sh \
    file://condor_drv_test \
    "

S = "${WORKDIR}"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/modem_init.sh ${D}${bindir}
    install -m 0755 ${WORKDIR}/condor_drv_test ${D}${bindir}
}

FILES_${PN} = "${bindir} ${base_sbindir}"
