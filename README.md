# Wallet
 Payment Card Platform
 
## Tech Stack: 
* Spring Boot 1.4.2.RELEASE
* Spring 4.3.4.RELEASE
* Spring security
* Tomcat Embed 8.5.6
* Maven 3
* Java 8
* JSP
* Hibernate

## Set up from GitHub
```bash
git clone git@github.com:margot-bonilla/wallet.git
cd wallet
mvn spring-boot:run
```
## Set up from .zip
```bash
unzip file.zip
cd wallet
mvn spring-boot:run
```

# Run the application
```bash
mvn spring-boot:run
```

## Create .jar
```bash
mvn clean
```
```bash
mvn compile
``` 

```bash
mvn package
``` 

```bash
java -jar target/wallet-0.0.1-SNAPSHOT.jar
``` 

## Notes/Assumptions
The card model is just a CRUD, to develop a real card model I would include a more complete database model (p.e. card type, currency, etc)

## Help
Display project dependencies
```bash
mvn dependency:tree
```