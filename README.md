Trade Processor Application
=======

This is a producer/consumer project that, given a `POST` call to the `/api/trade-message` endpoint with a `JSON` payload structured as follows,
```
{
  "userId": "John",
  "currencyFrom": "EUR",
  "currencyTo": "GBP",
  "amountSell": 1000,
  "amountBuy": 747.10,
  "rate": 0.7471,
  "timePlaced" : "14-JAN-15 10:27:44",
  "originatingCountry" : "FR"
}
```
stores it in a database (in this case I used derby but it's a matter of changing the datasource properties to migrate to another database) and exposes aggregated informations to clients.

The comunication from the endpoint and the other system elements is achieved through a bus that decouples the components. For this scenario I used a google [AsyncEventBus](http://docs.guava-libraries.googlecode.com/git/javadoc/com/google/common/eventbus/AsyncEventBus.html) but the architecture can be adapted to brokers like RabbitMQ, ActiveMQ, HornetQ...

Finally the front end is a simple angular.js application that shows the informations gathered and also shows some aggregated informations:
  1. The nations generating trade messages and the amount of trade messages for each nation;
  2. The amount sold by each user;
  3. The amount bougth by each user.

This project is composed by three modules:
 - market-trade-commons: contains the common facilities used among the project;
 - market-trade-middleware: service layer that is in charge of the database managing, wrapping into an applicative layer that permits to decouple the parts;
 - market-trade-webapp: frontier project that manages all the http endpoints exposed by the application, plus a simple GUI that summarize what happens in the system.

Building & Deploy
===
Using maven, after a project build, the `market-trade-webapp.war` file can be uplodaded to any container or (I used this approach during development) you can enter the market-trade-webapp submodule directory, where jetty is configured to be started using the `mvn jetty:run` goal.

After the boot the application will be avaible on `localhost:8081/market-trade-webapp/`.

If there is any issue, feel free to write down what happens!
