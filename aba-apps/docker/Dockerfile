FROM iron/java:1.8
MAINTAINER Marq Aideron <marq.aideron@gmail.com>

RUN echo '@edge http://nl.alpinelinux.org/alpine/edge/main' >> /etc/apk/repositories
RUN echo '@community http://nl.alpinelinux.org/alpine/edge/community' >> /etc/apk/repositories

RUN apk update && apk upgrade
RUN apk add bash

RUN rm -rf /var/cache/apk/*