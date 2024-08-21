## Comandos:
Gerar certificado para o servidor apache2.
```sh
openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out certificate.pem
```