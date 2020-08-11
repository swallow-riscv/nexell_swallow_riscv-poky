#!/bin/sh
GADGET_DIR=/sys/kernel/config/usb_gadget/g1

#Mount ConfigFS and create Gadget
if ! [ -d "${GADGET_DIR}" ]; then
        mkdir ${GADGET_DIR}
fi

#Set default Vendor and Product IDs for now
echo 0x18d1 > ${GADGET_DIR}/idVendor
echo 0x0003 > ${GADGET_DIR}/idProduct

#Create English strings and add random deviceID
if ! [ -d "${GADGET_DIR}/strings/0x409" ]; then
        mkdir ${GADGET_DIR}/strings/0x409
fi

echo NEXELL > ${GADGET_DIR}/strings/0x409/manufacturer
cat /etc/hostname > ${GADGET_DIR}/strings/0x409/product

#Create gadget configuration
if ! [ -d "${GADGET_DIR}/configs/c.1" ]; then
        mkdir ${GADGET_DIR}/configs/c.1
        mkdir ${GADGET_DIR}/configs/c.1/strings/0x409
fi
echo "Conf 1" > ${GADGET_DIR}/configs/c.1/strings/0x409/configuration

#Create Mass FunctionFS function
#And link it to the gadget configuration
if ! [ -d "${GADGET_DIR}/functions/mass_storage.ms0" ]; then
        mkdir ${GADGET_DIR}/functions/mass_storage.ms0
	echo 1 > ${GADGET_DIR}/functions/mass_storage.ms0/lun.0/removable
	echo /dev/mmcblk0p3 > ${GADGET_DIR}/functions/mass_storage.ms0/lun.0/file
	ln -s ${GADGET_DIR}/functions/mass_storage.ms0 ${GADGET_DIR}/configs/c.1/mass_storage.ms0
fi

if [ "`cat ${GADGET_DIR}/UDC`" == "20d00000.dwc2otg" ]; then
        echo "" > ${GADGET_DIR}/UDC
fi
echo 20d00000.dwc2otg > /sys/kernel/config/usb_gadget/g1/UDC
