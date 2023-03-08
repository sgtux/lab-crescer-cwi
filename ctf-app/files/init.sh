#!/bin/bash

echo "* Starting SSH Server"
service ssh start

echo "* Starting Telnet Server"
service openbsd-inetd start

mkdir -p /var/run/apache2

HOST_IP=`hostname -i`
echo ServerName $HOST_IP >> /etc/apache2/apache2.conf 
source /etc/apache2/envvars

echo "* Starting Apache HTTP Server"

exec /usr/sbin/apache2 -D "FOREGROUND"