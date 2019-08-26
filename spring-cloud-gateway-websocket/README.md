# Spring Cloud Gateway Load Balanced Websocket Demo

This project represents a bare-bones example of how to set up a load balanced websocket server utilizing Spring Boot Starter Websocket, and Spring Cloud Gateway, as well as a Message Broker to facilitate messages between multiple server instances.

The project uses Eureka for service discovery, however it is possible to manually specify a hostname and port in `RouteLocationConfiguration.java` rather than the service name for routing HTTP and Websocket requests through the gateway. Specifying instances by host and port like this however may preclude you from being able to effectively loadbalance requests.

The provided message broker is RabbitMQ, however it's possible to swap out the RabbitMQ server for the provided ActiveMQ server.

## Project Structure
- `eureka`
    - Spring Cloud Netflix Eureka - provides service discovery for enabled clients. This allows our applications to find each other by their `spring.application.name` property and the port they are configured to run on. With this our services can make requests to discoverable services with `http://my-service-name` rather than needing to know the hostname and port of the service
- `gateway`
    - Spring Cloud Gateway - provides routing and load balancing for HTTP and Websocket connections through a single host and port (http://localhost:8080 as configured), and uses service discovery to route requests by service name.
- `server`
    - Our example application which is serving a websocket endpoint for our client to connect to. This application has two profiles configured, a default profile running on port 8081 and a second profile on port 8082.
- `example.html`
    - Our example "client" to connect to our websocket server.

## Requirements
- Docker
- Web browser

## Running
### Using Docker Compose:
- Run `docker-compose up -d` to build and start the containers
  - Access Eureka console via http://localhost:8761 or health via http://localhost:8761/actuator/health
  - Access Gateway and application endpoints through http://localhost:8080, or health via http://localhost:8080/actuator/health
    - NOTE: There is no default route
- open `example.html` in a websocket compatible browser

During startup be patient as eureka attempts to resolve location and port, as well as health for each of the discovery enabled applications (gateway, server [app1], and server [app2]). This could take some time for the discovery server and clients to begin accepting requests.

I've added a timed method to respond to the same websocket topic which the client is subscribed to to simulate push notifications from the server, either in response to a system action or in response to another user's action.

### Demonstration of the project running
![Websocket demonstration](https://github.com/jmlw/demo-projects/blob/master/spring-cloud-gateway-websocket/ws-demo-1.gif?raw=true)

### Demonstration with second user in another window
![Websocket demonstration with second user](https://github.com/jmlw/demo-projects/blob/master/spring-cloud-gateway-websocket/ws-demo-2.gif?raw=true)
