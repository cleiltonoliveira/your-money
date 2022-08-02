
# YOUR MONEY
Your Money is an app to help people control their financial lives. The goal is to provide you with a way to organize your income and expenses your way.  
It is being developed as an artifact of Alura's backend programming challenge.  
Read more about [here](https://www.alura.com.br/challenges/back-end-4/semana-01-implementando-api-rest)
## Requirements
- Java Development Kit >=11;

## Project structure
I tried to apply in the project structure some [Clean Architecture](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) practices aiming to develop an easily testable code, with loosely coupled modules and that supports the inclusion of new features efficiently.

The project has the following modules:
* `domain`: Owns all application domain objects;
* `usecases`: It has the entire business rule of the service;
* `persistence`: Implementation of the data layer where the data access interfaces defined by the use case layer are implemented;
* `web`: Layer that exposes API endpoints as a web service;
* `application`: Module that connects all other modules of the architecture and starts the application.

### Diagram of dependencies between modules

<img src="assets/diagram-arch.png" alt="Architecture" width="500" height="500"/>