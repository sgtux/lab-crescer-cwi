FROM alpine:latest
WORKDIR /app
COPY files /app
RUN apk add --update nodejs npm
ENV NODE_PORT 80
ENV TZ=America/Sao_Paulo
CMD ["npm", "start"]