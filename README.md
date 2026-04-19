# Group 1 - UDP Client–Server System

## Overview

This project demonstrates a UDP-based Client–Server communication system developed in Java.
It simulates real network communication where multiple clients send requests to a server, and the server processes them based on defined permissions and returns responses.

The system also implements basic access control, file operations, and command execution logic, making it a simple model of distributed network interaction.

## Core Idea

The server listens for incoming UDP packets from multiple clients and processes commands such as:

- READ → Read files from server storage
- WRITE → Write content to server files (admin only)
- EXECUTE → Simulated execution of commands (admin only)

Access control is based on the client’s IP address, where one designated IP acts as admin, while others have restricted permissions.


## Project Structure

- UDPServer.java → Main server that listens for client requests
- RequestHandler.java → Handles and processes all incoming commands
- UDPClientSender.java → Sends commands to the server
- UDPClientReceiver.java → Receives and displays server responses

## System Architecture


### Server Side
- Listens on UDP port 4444
- Accepts requests from multiple clients
- Processes commands using RequestHandler
- Performs file operations inside server_files folder
- Controls access based on client IP

### Client Side
- Connects to server using IP and port
- Sends commands as text messages
- Receives responses from server
- Supports continuous interaction loop



## Access Control

### - Admin Client:
- Full permissions: READ, WRITE, EXECUTE
  
### - Other Clients:

- Only allowed: READ
- Restricted from modifying or executing commands

### Contributors
- Afron → UDPServer implementation & server logic
- Rinesa → Request handling & command processing logic
- Abdurrahman → Client receiver implementation
- Adna → Client sender implementation


## Conclusion

This project demonstrates how UDP sockets can be used to build a lightweight distributed system with multiple clients, server-side processing, and basic security mechanisms. It simulates real-world network communication principles used in modern distributed applications.

