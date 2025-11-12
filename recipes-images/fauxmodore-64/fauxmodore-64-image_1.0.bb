SUMMARY = "Fauxmodore-64 main image"

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

inherit image features_check

REQUIRED_DISTRO_FEATURES = "wayland"

IMAGE_INSTALL = "\
    avahi-daemon \
    kernel-modules \
    networkmanager \
    packagegroup-base-extended \
    packagegroup-core-boot \
    udev-extraconf \
    vice \
    vice-launcher \
    weston \
    weston-init \
    "
