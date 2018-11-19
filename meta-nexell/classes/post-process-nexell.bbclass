postprocess_function() {
	cd ${IMAGE_ROOTFS}

        sed -i 's/z6:6:respawn:\/sbin\/sulogin/\#z6:6:respawn:\/sbin\/sulogin/g' etc/inittab
	sed -i 's/h0:12345:respawn:\/sbin\/getty -L console 0 vt100/\#h0:12345:respawn:\/sbin\/getty -L console 0 vt100/g' etc/inittab

	echo "1:2345:respawn:/sbin/mingetty --autologin root ttyS0" >> etc/inittab

	cd ${IMAGE_ROOTFS}
	ln -sf bin/busybox linuxrc
	rm -rf run
	ln -sf tmp run

	# #---bootscript.sh create---
	# echo "#!/bin/sh" > ./etc/bootscript.sh
	# echo "mount -t devtmpfs none /dev" >> etc/bootscript.sh
	# echo "mount -t proc none /proc" >> etc/bootscript.sh
	# echo "mount -t sysfs none /sys" >> etc/bootscript.sh

	#ping permission change
	echo "inet:x:3003:root"    >> etc/group
	echo "net_raw:x:3004:root" >> etc/group

	#busybox-mdev automount
	echo "mmcblk[0-9]p[0-9] 0:0 660 */etc/mdev/mdev-mount.sh" >> etc/mdev.conf
	echo "/dev/mmcblk0p2       /run/media/mmcblk0p2          auto       defaults,sync,noauto  0  0" >> etc/fstab

        #network
        cp -a ${BASE_WORKDIR}/../../../riscv-poky/meta-nexell/recipes-devtools/network/files/interfaces etc/network/
}

ROOTFS_POSTPROCESS_COMMAND += "postprocess_function;"
