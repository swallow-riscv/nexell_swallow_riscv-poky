postprocess_function() {
    cd ${IMAGE_ROOTFS}

    sed -i 's/z6:6:respawn:\/sbin\/sulogin/\#z6:6:respawn:\/sbin\/sulogin/g' etc/inittab
    sed -i 's/h0:12345:respawn:\/sbin\/getty -L console 0 vt100/\#h0:12345:respawn:\/sbin\/getty -L console 0 vt100/g' etc/inittab

    echo "1:2345:respawn:/sbin/mingetty --autologin root ttyS0" >> etc/inittab
}

ROOTFS_POSTPROCESS_COMMAND += "postprocess_function;"