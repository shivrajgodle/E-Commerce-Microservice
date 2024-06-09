# E-Commerce-Microservice

Microservices


- [x] Config server

Dependancies:-

1. Config Server (to make it as configuration server)


Steps to make Configuration service

-> go to main class and add @EnableConfigServer below SpringBootApplication

-> create application.yml file and add below code.

spring:
  profile: native
  application:
    name: config-server
  cloud:
    config:
      server:
        native:
          search-locations: classpath:/configurations

server:
  port: 8888



- [x] Discovery Server:-

Dependancies:- 

1. Config client (for fetching configuration)
2. Eureka Server (for acting as discovery server)


Steps to make Discovery service

-> go to main class and add @EnableEurekaServer below @SpringBootApplication


-> create application.yml file and add below code.

spring:
  config:
    import: optional:configserver:http://localhost:8888
  application:
    name: discovery-service




-> Also go to the config server resources/configurations folder and create discovery-service.yml file


eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}/${server.port}/eureka/
server:
  port: 8761


-> now go to http://localhost:8761 , here you will find one gui  where all registered services will shown. 


- [x] Customer Service:-


Dependancies:- 

1. Config client (for fetching configuration)
2.  Spring Web 
3. Spring Data MongoDB
4. Validation 
5. Lombok

-> create application.yml file and add below code.

spring:
  config:
    import: optional:configserver:http://localhost:8888
  application:
    name: customer-service

-> Also go to the config server resources/configurations folder and create customer-service.yml file

spring:
  data:
    mongodb:
      username: alibou
      password: alibou
      host: localhost
      port: 27017
      database: customer
      authentication-database: admin
server:
  port: 8090




