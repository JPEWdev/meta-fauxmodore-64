SUMMARY = "Open-source 6502 cross assembler"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f67e705f4eb5064027b8c40ccfdb43e"

# They move old versions to a different folder
MIRRORS =+ "http://www.floodgap.com/retrotech/xa/dists/ http://www.floodgap.com/retrotech/xa/dists/unsupported/"

SRC_URI = "http://www.floodgap.com/retrotech/xa/dists/${BPN}-${PV}.tar.gz \
           file://0001-Fix-makefile.patch \
           "
SRC_URI[sha256sum] = "63c12a6a32a8e364f34f049d8b2477f4656021418f08b8d6b462be0ed3be3ac3"

BBCLASSEXTEND = "native"

B = "${S}"

EXTRA_OEMAKE += "LD='${CCLD}'"

do_install() {
    oe_runmake DESTDIR=${D} PREFIX=${prefix} install
}
