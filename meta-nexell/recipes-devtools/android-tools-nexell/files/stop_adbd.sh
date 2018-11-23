#!/bin/sh

PID_ADBD=`pidof adbd`

rmdir /sys/kernel/config/usb_gadget/g1/configs/c.1/strings/0x409
rm /sys/kernel/config/usb_gadget/g1/configs/c.1/ffs.adb
rmdir /sys/kernel/config/usb_gadget/g1/configs/c.1
rmdir /sys/kernel/config/usb_gadget/g1/functions/ffs.adb
rmdir /sys/kernel/config/usb_gadget/g1/strings/0x409

kill $PID_ADBD
