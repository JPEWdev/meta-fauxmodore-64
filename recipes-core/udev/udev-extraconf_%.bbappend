FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# Mount FAT file systems so that they can be accessed by weston
MOUNT_GROUP = "weston"
