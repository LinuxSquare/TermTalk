# TermTalk

## About

TermTalk is a Java Application, which allows you to communicate with your friends inside your local network.
It was written as a school-project for the IT-Module 226B.

## How it works
![](mdwiki/subChapter/images/howitworks.png)

1. User1 requests the IP of user2 from the broker-server.
He delivers his IP (in this case 192.168.1.1) and asks for U2

U2 is used as key to get the IP out of the hashmap.

2. The Broker-Server responses the IP of user2 (in this case 192.168.1.2)

3. User1 is connecting to the IP of User2 to communicate via peer-to-peer.

## Server

### Usage
```
cd finish/back-end
mvn install
mvn liberty:start-server
```
Once started the broker-service is reachable under the following link: <http://localhost:9000/>.
TermTalk works with the JSON data-interchange format. Data can be gotten from the broker-server under the link <http://localhost:9000/broker/api/user>.

The Ports are as followed:
  * HTTP: 9000
  * HTTPS: deactivated at the moment due to complicity

### Jobs of the server

The server has to serve multiple functions:
  * __take user-hashkeys from users and return the associated IP address.__
    The IP of a single user can be gotten by attaching the hashkey to the application-address (http://localhost:9000/broker/api/user/<hashkey>)

  * __akknolage new users and save ther IP in a hashmap__
    New users will have to register themselves at the server, so other users can find them.
