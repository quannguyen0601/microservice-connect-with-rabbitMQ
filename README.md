Learning how to use RabbitMQ to connect two service.

**Run RabbitMQ**
 * The simplest way is using Docker compose for running development environment.
 ````
version: '3.1'
services:
  rabbit:
    image: rabbitmq:3-management
    environment:
       RABBITMQ_DEFAULT_USER: user
       RABBITMQ_DEFAULT_PASS: password
    ports:
      - "15672:15672"
      - "5672:5672"
    
 ````
 **Explain** 
 
 This project aims to demonstrate how to using RabbitMQ in Spring boot project. In addition, 
 I try to create multiple modules in this project and make a request & response queue in RabbitMQ.
 
* Direct Exchange
* Topic Exchange
* Fanout Exchange




