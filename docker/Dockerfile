FROM openjdk:8-jdk-alpine

RUN apk add --no-cache git && \
    apk add --no-cache maven

ENV HOME /root

# Define working directory.
WORKDIR /root

# Cloning and install dependencies
RUN \
  git clone https://github.com/corrigeai/corrigeai-api.git && \
  (cd corrigeai-api && mvn install)

# Define working directory.
WORKDIR /root/corrigeai-api
  
CMD ./mvnw spring-boot:run
