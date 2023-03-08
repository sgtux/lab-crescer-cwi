FROM ubuntu:20.04
ARG DEBIAN_FRONTEND=noninteractive
COPY files/* /root/
RUN apt update && apt install telnetd openssh-server apache2 -y && \
    useradd -m -s /bin/bash -p $(openssl passwd -1 qwertyuiop) crescer && \
    chmod +x /root/init.sh
CMD ["/root/init.sh"]