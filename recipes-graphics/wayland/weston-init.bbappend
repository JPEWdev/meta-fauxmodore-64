FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

USERADD_PARAM:${PN} = "--home /home/weston --shell /bin/sh --user-group -G video,input,render,seat,wayland,audio weston"
