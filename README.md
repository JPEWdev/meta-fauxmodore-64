![Fauxmodore 64 Logo](images/logo.png)

OpenEmbedded Layer for building a Commodore 64 emulator

# Getting Started

The images can be built using `bitbake-setup`:

```
$ bitbake-setup init https://raw.githubusercontent.com/JPEWdev/meta-fauxmodore-64/refs/heads/main/fauxmodore-64.conf.json
```

After this, source the environment as prompted by `bitbake-setup`:

```
. PATH/TO/BUILD/build/init-build-env
```

If you are building for a Raspberry Pi, the license for the Synaptics firmware
needs to be accepted to prevent build errors:

```
echo 'LICENSE_FLAGS_ACCEPTED += "synaptics-killswitch"' >> conf/local.conf
```

Finally, the image can be built with:

```
bitbake fauxmodore-64-image
```

# Hardware

This layer is configured to be used with an original Commodore 64 keyboard
attached to a [C64USB](https://github.com/JPEWdev/c64usb) adapter which allows
reading the keyboard and Joystick ports as USB input devices.

The Single Board Computer can then be embedded in the empty case to give the
"feel" of an original Commodore 64, but with the modern conveniences of an
emulator (fast forward "warp" is particularly nice for long disk load times).

If you would like to see an example of how this can be done, see
[My Blog](https://wattissoftware.blogspot.com/search/label/Commodore%2064)

While the emulator will run fine on most hardware, I'd recommend a Raspberry Pi
5, since the extra CPU power allows it to warp faster (up to 7x in my
experience).
