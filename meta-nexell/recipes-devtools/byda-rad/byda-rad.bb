SUMMARY = "Byda Radar"
DESCRIPTION = "Excute Byda Radar apps on bootup."
SECTION = "base"

RDEPENDS_${PN} = "byda-rad"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://byda_rad_run;md5=3eef4080d787ebb6fec681ae46e4cd68"

INHIBIT_DEFAULT_DEPS = "1"

# As the recipe doesn't inherit systemd.bbclass, we need to set this variable
# manually to avoid unnecessary postinst/preinst generated.
python __anonymous() {
    if not bb.utils.contains('DISTRO_FEATURES', 'sysvinit', True, False, d):
        d.setVar("INHIBIT_UPDATERCD_BBCLASS", "1")
}

inherit update-rc.d

SRC_URI = "file://byda_rad_run \
	   file://byda_rad_standby_v4 \
	   file://byda_rad.sh"

INITSCRIPT_NAME = "byda_rad.sh"
INITSCRIPT_PARAMS = "start 40 S ."

S = "${WORKDIR}"

do_install () {
    # Only install the script if 'sysvinit' is in DISTRO_FEATURES
    # THe ulitity this script provides could be achieved by systemd-vconsole-setup.service
    if ${@bb.utils.contains('DISTRO_FEATURES','sysvinit','true','false',d)}; then
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/byda_rad.sh ${D}${sysconfdir}/init.d/
    fi

    install -d ${D}${bindir}

    install -m 0755 ${WORKDIR}/byda_rad_run ${D}${bindir}
    install -m 0755 ${WORKDIR}/byda_rad_standby_v4 ${D}${bindir}
}

ALLOW_EMPTY_${PN} = "1"
