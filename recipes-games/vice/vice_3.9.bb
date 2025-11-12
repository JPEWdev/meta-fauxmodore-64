SUMMARY = "Versatile Commodore Emulator"
HOMEPAGE = "http://vice-emu.sourceforge.net"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"

SRC_URI = "\
    ${SOURCEFORGE_MIRROR}/vice-emu/${BPN}-${PV}.tar.gz \
    file://0001-SDL-Add-option-to-save-settings-when-menu-exits.patch;striplevel=2 \
    "
SRC_URI[sha256sum] = "40202b63455e26b87ecc63eb5a52322c6fa3f57cab12acf0c227cf9f4daec370"

inherit autotools-brokensep pkgconfig gtk-icon-cache features_check

DEPENDS = " \
    bison-native \
    dos2unix-native \
    glib-2.0-native \
    xa-native \
    \
    libsdl2 \
    libsdl2-image \
    "

PACKAGECONFIG ??= "\
    ${@bb.utils.filter('DISTRO_FEATURES', 'pulseaudio ipv6', d)} \
    fastsid resid evdev zlib curl rs232"
PACKAGECONFIG[pulseaudio] = "--with-pulse,--without-pulse,pulseaudio,pulseaudio-server"
PACKAGECONFIG[fastsid] = "--with-fastsid,--without-fastsid,,"
PACKAGECONFIG[resid] = "--with-resid,--without-resid,,"
PACKAGECONFIG[cpuhistory] = "--enable-cpuhistory,--disable-cpuhistory,,"
PACKAGECONFIG[evdev] = "--with-evdev,--without-evdev,libevdev,"
PACKAGECONFIG[zlib] = "--with-zlib,--without-zlib,zlib,"
PACKAGECONFIG[curl] = "--with-curl,--without-curl,curl,"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,,"
PACKAGECONFIG[rs232] = "--enable-rs232,--disable-rs232,,"

EXTRA_OECONF = " \
    --without-oss \
    --disable-pdf-docs \
    --libdir=${libdir} \
"

do_configure() {
    for d in resid resid-dtv; do
        cd ${S}/src/$d
        echo "Configuring $d..."
        ACLOCAL="$ACLOCAL" autoreconf -Wcross -Wno-obsolete --verbose --install --force ${EXTRA_AUTORECONF} $acpaths || die "autoreconf execution failed."
        echo "Configuring $d done"
    done
    cd ${S}
    rm -f ./configure
    ACLOCAL="$ACLOCAL" autoreconf -Wcross -Wno-obsolete --verbose --install --force ${EXTRA_AUTORECONF} $acpaths || die "autoreconf execution failed."
    oe_runconf
}

INSANE_SKIP = "buildpaths"
