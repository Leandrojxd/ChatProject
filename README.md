# Chat Application Project

## Objective
The main objective of this project is to design, develop, and implement a chat application that allows users to:
- Register using a username.
- Create or join chat rooms.
- Send and receive text messages within rooms or between users.

The application should ensure confidentiality of messages and authentication of users.

---

## Basic Requirements
1. User registration and login using a username.
2. Users can create chat rooms or join existing ones.
3. Users can send and receive messages within a chat room.
4. User authentication.
5. Message confidentiality.
6. Track registered and logged-in users.
7. Ensure message integrity and reception.

---

## Expected Outcomes
By completing this project, the following goals should be achieved:

1. Complete Application Development: Implement a functional chat application enabling user communication via chat rooms.
2. Network Security Concepts: Integrate security protocols (e.g., TLS, SSL, SHA-256) to ensure message confidentiality and user authentication.
3. Client-Server Architecture: Understand and implement client-server architecture, handling user management and message routing.
4. Event-Driven Architecture: Use a message queue system (event-bus) to manage communication events.
5. Socket Programming: Employ socket programming to handle data transmission between client and server, justifying the chosen protocol.
6. Simple User Interface: Develop a simple, effective user interface for both the client and server using JavaFX.
7. Logging and Data Persistence: Implement logging functionality and manage data persistence in a MongoDB database.

---

## Technologies Used
- Programming Language: Java is utilized for both the server and client applications, providing a robust platform for network applications.
- Database: MongoDB is used for data persistence, allowing efficient storage and retrieval of user data and chat messages.
- Libraries:
    - Spring Framework: Used for building the server, facilitating dependency injection and RESTful web services.
    - Jackson: For handling JSON serialization and deserialization, enabling seamless data exchange between client and server.
- Networking:
    - TCP Sockets: Employed for reliable communication between the client and server.
    - MQTT: A lightweight messaging protocol utilized for message passing, particularly for real-time communication.
    - Mosquitto: An MQTT broker used to manage the message distribution efficiently.
- Security:
    - SHA-256: Used for hashing messages to ensure their integrity before transmission.
    - RSA: RSA (Rivest-Shamir-Adleman) es un algoritmo de cifrado asimétrico que utiliza un par de claves: una clave pública y una clave privada, garantizando así la confidencialidad de los mensajes. Cuando un remitente desea enviar un mensaje seguro, utiliza la clave pública del destinatario para cifrarlo, convirtiéndolo en un mensaje cifrado que solo puede ser descifrado por el destinatario utilizando su clave privada. Este proceso asegura que, aunque el mensaje cifrado sea interceptado, no podrá ser leído sin acceso a la clave privada correspondiente. La clave pública se puede compartir abiertamente, lo que permite una comunicación segura sin comprometer la privacidad del destinatario. De esta manera, RSA proporciona un método robusto para proteger la confidencialidad de la información en diversas aplicaciones.
- Logging: Implemented using the Log4j library to maintain logs of application events and user activities.
- Design Patterns: The Singleton pattern is applied to manage unique instances of key components throughout the application.
- Port: The server operates on port 5000 to handle incoming client connections.



## Installation and Setup

2. Navigate to the project directory:
   bash
   cd chat-application

3. Set up MongoDB and ensure it is running on your local machine.
   docker run --name chatcapstone -p 27017:27017 -d mongo
   Make sura that you have the collections: ChatRoom and User.

4. Configure the application properties to connect to the MongoDB instance.

5. Install mosquitto service for you Operating System in case of ubuntu and run the service
   Use this command: sudo apt update && sudo apt install mosquitto mosquitto-clients

6. Run and compile Server, from carpet Chat Server , remember use mvn install

7. Run and compile ChatClient, from carpet ChatClient mvn install

---
