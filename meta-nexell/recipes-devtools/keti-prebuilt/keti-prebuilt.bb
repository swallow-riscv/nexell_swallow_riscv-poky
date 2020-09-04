DESCRIPTION = "KETI Condor Modem Driver Control Application & Utility"
SECTION = "Utils"
LICENSE = "Closed"
LIC_FILES_CHKSUM = "file://modem_init.sh;md5=901ef320dc94e40c46b92c62ea729b77"

# As the recipe doesn't inherit systemd.bbclass, we need to set this variable
# manually to avoid unnecessary postinst/preinst generated.
python __anonymous() {
    if not bb.utils.contains('DISTRO_FEATURES', 'sysvinit', True, False, d):
        d.setVar("INHIBIT_UPDATERCD_BBCLASS", "1")
}

inherit update-rc.d

SRC_URI = " \
    file://modem_init.sh \
    file://condor_drv_test \
    file://keti_modem.sh"

INITSCRIPT_NAME = "keti_modem.sh"
INITSCRIPT_PARAMS = "start 41 S ."

S = "${WORKDIR}"

do_install() {
    # Only install the script if 'sysvinit' is in DISTRO_FEATURES
    # THe ulitity this script provides could be achieved by systemd-vconsole-setup.service
    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
        install -d ${D}${sysconfdir}/init.d/
        install -m 0755 ${WORKDIR}/keti_modem.sh ${D}${sysconfdir}/init.d/
    fi
    install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/modem_init.sh ${D}${bindir}
    install -m 0755 ${WORKDIR}/condor_drv_test ${D}${bindir}
}

ALLOW_EMPTY_${PN} = "1"
