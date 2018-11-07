CorrigeAi Backend
===========

Overview
--------

> CorrigeAi aims to bring a collaborative network of students who need help in the write essays and another students and teachers who can offer this help.

## CorrigeAi Backend

> This is the default API application for the CorrigeAi platform. It is a RESTfull API written in Spring Boot framework. It leverages common popular libraries such as Spring Mongo using as default MongoDB database and JWT and Bcrypt.

## Build Status

Travis

[![Build Status](https://travis-ci.org/corrigeai/corrigeai-api.svg?branch=master)](https://travis-ci.org/corrigeai/corrigeai-api)

## API Documentation

<a target="_blank" href="https://corrigeai-api.herokuapp.com/swagger-ui.html">Access swagger reference here</a>

# Demo

Check out the [**live demo**](https://corrigeai.herokuapp.com) deployment!

## Building from source

1. Ensure you have 

   ```java``` installed - goto http://www.oracle.com/technetwork/java/javase/downloads/jdk10-downloads-4416644.html to download installer for your OS.    
   ```maven``` installed - goto https://maven.apache.org/download.cgi to download latest version of maven.

1. Clone this repository to your local filesystem (default branch is 'master')

1. To download the dependencies
   ```
    mvn install
   ```

1. To run the application, run the following command on the project root folder

   ```
    mvn spring-boot:run
   ```

   Note: If you see a warning similar to the one shown below on running `Disabling contextual LOB creation as createClob() method threw error`, see this question https://stackoverflow.com/questions/4588755/disabling-contextual-lob-creation-as-createclob-method-threw-error

   ```
    java.lang.reflect.InvocationTargetException: null
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
        at java.base/java.lang.reflect.Method.invoke(Method.java:564) ~[na:na]
        at org.hibernate.engine.jdbc.env.internal.LobCreatorBuilderImpl.useContextualLobCreation(LobCreatorBuilderImpl.java:113) [hibernate-core-5.2.17.Final.jar:5.2.17.Final]
        at org.hibernate.engine.jdbc.env.internal.LobCreatorBuilderImpl.makeLobCreatorBuilder(LobCreatorBuilderImpl.java:54) [hibernate-core-5.2.17.Final.jar:5.2.17.Final]
        at org.hibernate.engine.jdbc.env.internal.JdbcEnvironmentImpl.<init>(JdbcEnvironmentImpl.java:271) [hibernate-core-5.2.17.Final.jar:5.2.17.Final]
   ```
## Running maven tasks

### Running the tests

You just need to run the following command:

`mvn test`

# Run this application as docker container

To run this application as a simple docker container you just need to have
docker installed in your machine an follow two simple steps:

1 - `docker pull gmonteiro/corrigeai-api`

2 - `docker container run -d --name corrigeai -p 3000:3000 corrigeai-api`

After that, you only have to wait a few seconds for the application to go up 
inside the container and it's ready, it will be available on your host's port 3000.

> Notes: For this release the Corrigeai team wants to maintain refined control over 
a database, in this way, the application will always be connecting to our 
database when using that docker image.