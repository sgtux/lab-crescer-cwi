FROM ubuntu:20.04
ARG DEBIAN_FRONTEND=noninteractive
COPY files/init.sh /root/init.sh
RUN apt update && apt install telnetd openssh-server apache2 -y && \
    useradd -m -s /bin/bash -p $(openssl passwd -1 qwertyuiop) crescer && \
    a2enmod proxy proxy_http ssl rewrite && \
    a2dissite 000-default.conf && \
    a2ensite default-ssl.conf && \
    chmod +x /root/init.sh
CMD ["/root/init.sh"]