# build image 
docker build -t akhmadwildan/eureka-server .

# Push image
docker push akhmadwildan/eureka-server

# create container
docker container create --name eureka-server  -p 8761:8761 akhmadwildan/eureka-server

# start container
docker container start eureka-server