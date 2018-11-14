postprocess_function() {
	cd ${IMAGE_ROOTFS}

        sed -i 's/z6:6:respawn:\/sbin\/sulogin/\#z6:6:respawn:\/sbin\/sulogin/g' etc/inittab
	sed -i 's/h0:12345:respawn:\/sbin\/getty -L console 0 vt100/\#h0:12345:respawn:\/sbin\/getty -L console 0 vt100/g' etc/inittab

	echo "1:2345:respawn:/sbin/mingetty --autologin root ttyS0" >> etc/inittab

	cd ${IMAGE_ROOTFS}
	ln -sf bin/busybox linuxrc
	rm -rf run
	ln -sf tmp run

	#---bootscript.sh create---
	echo "#!/bin/sh" > ./etc/bootscript.sh
	echo "mount -t devtmpfs none /dev" >> etc/bootscript.sh
	echo "mount -t proc none /proc" >> etc/bootscript.sh
	echo "mount -t sysfs none /sys" >> etc/bootscript.sh

	#ping permission change
	echo "inet:x:3003:root"    >> etc/group
	echo "net_raw:x:3004:root" >> etc/group
}

ROOTFS_POSTPROCESS_COMMAND += "postprocess_function;"
