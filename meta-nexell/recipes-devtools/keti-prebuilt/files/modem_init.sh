dmesg -n 1                                      # dmesg disable
ifconfig condor up                              # network interface active
/usr/bin/condor_drv_test write 0 2c FF
/usr/bin/condor_drv_test write 0 13c 03FA0600
/usr/bin/condor_drv_test write 0 68c 3F236218
/usr/bin/condor_drv_test chan 0 184 184                # WAVE channel setting
/usr/bin/condor_drv_test set 36 10                     # Datarate, txpower setting
/usr/bin/condor_drv_test addr set 00:12:34:56:78:02    # MAC address setting
/usr/bin/condor_drv_test layer 1                       # IP layer active
ifconfig condor 2.2.2.2 netmask 255.255.255.0          # IP, netmask setting
