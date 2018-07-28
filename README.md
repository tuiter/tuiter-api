[![Build Status](https://travis-ci.org/corrigeai/corrigeai-api.svg?branch=master)](https://travis-ci.org/corrigeai/corrigeai-api)
# corrigeai-api

To run this application as a simple docker container you just need to have
docker installed in your machine an follow two simple steps:

1 - `docker pull gmonteiro/corrigeai-api`

2 - `docker container run -d --name qma-test -p 3000:3000 corrigeai`

After that, you only have to wait a few minutes for the application to go up 
inside the container and it's ready, it will be available on your host's port 3000.

Notes: For this release the Corrigeai team wants to maintain refined control over 
a database, in this way, the application will always be connecting to our 
database when using that docker image.