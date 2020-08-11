#!/bin/sh

rmdir /sys/kernel/config/usb_gadget/g1/configs/c.1/strings/0x409
rm /sys/kernel/config/usb_gadget/g1/configs/c.1/mass_storage.ms0
rmdir /sys/kernel/config/usb_gadget/g1/configs/c.1
rmdir /sys/kernel/config/usb_gadget/g1/functions/mass_storage.ms0
rmdir /sys/kernel/config/usb_gadget/g1/strings/0x409
