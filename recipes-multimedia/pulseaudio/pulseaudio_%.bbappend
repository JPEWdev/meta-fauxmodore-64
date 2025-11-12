do_install:append:raspberrypi5() {
    # The audio output on raspberrypi 5 has significant latency when in a
    # combined sync and using time based scheduling. Also ignore dB values
    # from ALSA, as they can cause headphone volume to be way too loud
    sed -i \
        -e 's/^load-module module-udev-detect/load-module module-udev-detect tsched=0 ignore_dB=1/' \
        ${D}${sysconfdir}/pulse/default.pa
}
