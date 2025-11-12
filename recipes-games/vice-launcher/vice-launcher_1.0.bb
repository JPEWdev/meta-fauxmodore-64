SUMMARY = "VICE Launcher"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "weston-init"

SRC_URI = "\
    file://vice.service.in \
    file://vicerc.in \
    file://hotkeys.vhk \
    file://prepare-data \
    file://vice.pa \
    "

INHIBIT_DEFAULT_DEPS = "1"

inherit systemd features_check

REQUIRED_DISTRO_FEATURES = "systemd"

S = "${UNPACKDIR}"

VICE_DATADIR ?= "/var/lib/vice"

do_compile() {
    for f in vice.service vicerc; do
        sed -e 's^@libexecdir@^${libexecdir}^g' \
            -e 's^@datadir@^${VICE_DATADIR}^g' \
            ${S}/$f.in > ${B}/$f
    done
}

do_install() {
    install -m 755 -d ${D}${systemd_system_unitdir}
    install -m 644 ${B}/vice.service ${D}${systemd_system_unitdir}

    install -m 755 -d ${D}${libexecdir}/vice
    install -m 644 ${B}/vicerc ${D}${libexecdir}/vice/
    install -m 644 ${B}/hotkeys.vhk ${D}${libexecdir}/vice/
    install -m 755 ${S}/prepare-data ${D}${libexecdir}/vice

    install -m 755 -d ${D}${sysconfdir}/pulse/default.pa.d/
    install -m 644 ${S}/vice.pa ${D}${sysconfdir}/pulse/default.pa.d/

    install -m 755 -d ${D}${VICE_DATADIR}
    ln -sf /run/media ${D}${VICE_DATADIR}/media
}

SYSTEMD_SERVICE:${PN} = "vice.service"
RDEPENDS:${PN} = "\
    weston-init \
    vice \
    pulseaudio-module-combine-sink \
    "
