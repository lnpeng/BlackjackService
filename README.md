# BlackjackService
Blackjack web service is a SOAP-based web service that deals cards and evaluates hands.

# Description
Blackjack web service provides web methods to shuffle a deck of cards, deal a card from the deck and evaluate a hand of cards. The Blackjack web service uses an `HttpSession` object to maintain a unique deck of cards for each client application. Several clients can use the service at the same time, but web method calls made by a specific client use only the deck of cards stored in that client’s session.

# Getting Started
## Prerequisites
- JDK 1.8
- GlassFish Server 4.1
- JAX-WS 2.2.6

## Installing
```
git clone git@github.com:lnpeng/BlackjackService.git
cd BlackjackService
```

## Running the tests
### Test the Blackjack web service in Web Browser
- Type the following URL into a web brower.
```
http://localhost:8080/Blackjack1/BlackjackService?Tester
```
- ![Test](https://github.com/lnpeng/BlackjackService/blob/master/Screen%20Shot%202018-12-04%20at%204.51.46%20PM.png)

## Deployment
Deploy the war file to [GlassFish](https://javaee.github.io/glassfish/) Server.

# Build
* [Ant](https://ant.apache.org/) - Automating software build process.
