
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

## Documentação da API

### Receita
#### Retorna uma lista paginada das receitas cadastradas.

```http
  GET /receitas
```

| Parâmetros (Opcionais)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `page` | `Integer` | **Opcional**. Define qual página será exibida. |
| `linesPerPage` | `Integer` | **Opcional**. Define quantos registros serão exibidos. |
| `direction` | `String` | **Opcional**. Define se teremos uma ordenação ASC ou DESC. |
| `orderBy` | `String` | **Opcional**. Define campo usado para ordenação. |

#
#### Retorna uma receita de acordo com o ID informado.

```http
  GET /receitas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você quer. |

#
#### Adiciona uma nova receita

```http
  POST /receitas
```

| Parâmetro   | Tipo        | Descrição                                   |
| :---------- | :---------  | :------------------------------------------ |
| `data`      | `LocalDate` | **Obrigatório**. Informa a data da receita. |
| `descricao` | `String`    | **Obrigatório**. Informa o tipo da receita. |
| `valor`     | `BigDecimal`| **Obrigatório**. Informa o valor da receita.|

**Body**
```json
{
    "data" : "2022-07-10T08:30:00Z",
    "descricao" : "Pix aniversário",
    "valor" : 170.0
}
```
**Return - Status 201 Created**
```json
{
    "id": 3,
    "descricao": "Pix aniversário",
    "valor": 170.0,
    "data": "2022-07-10"
}
```
#
```http
  PUT /receitas/${id}
```

| Parâmetro   | Tipo        | Descrição                                   |
| :---------- | :---------  | :------------------------------------------ |
| `data`      | `LocalDate` | **Obrigatório**. Informa a data da receita. |
| `descricao` | `String`    | **Obrigatório**. Informa o tipo da receita. |
| `valor`     | `BigDecimal`| **Obrigatório**. Informa o valor da receita.|

**Body**
```json
{
    "data" : "2022-07-10T08:30:00Z",
    "descricao" : "Pix aniversário",
    "valor" : 200.0
}
```
**Return - Status 200 OK**
```json
{
    "id": 3,
    "descricao": "Pix aniversário",
    "valor": 200.0,
    "data": "2022-07-10"
}
```
#
```http
  DELETE /receitas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você deseja deletar. 

**Return - Status 204 No Content**
#

### Despesa
#### Retorna uma lista paginada das despesas cadastradas.

```http
  GET /despesas
```

| Parâmetros (Opcionais)   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `page` | `Integer` | **Opcional**. Define qual página será exibida. |
| `linesPerPage` | `Integer` | **Opcional**. Define quantos registros serão exibidos. |
| `direction` | `String` | **Opcional**. Define se teremos uma ordenação ASC ou DESC. |
| `orderBy` | `String` | **Opcional**. Define campo usado para ordenação. |

#
#### Retorna uma despesa de acordo com o ID informado.

```http
  GET /despesas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você quer. |

#
#### Adiciona uma nova despesa

```http
  POST /despeas
```

| Parâmetro   | Tipo        | Descrição                                   |
| :---------- | :---------  | :------------------------------------------ |
| `data`      | `LocalDate` | **Obrigatório**. Informa a data da despesa. |
| `descricao` | `String`    | **Obrigatório**. Informa o tipo da despesa. |
| `valor`     | `BigDecimal`| **Obrigatório**. Informa o valor da despesa.|

**Body**
```json
{
    "data" : "2022-07-10T08:30:00Z",
    "descricao" : "Pizza",
    "valor" : 60.0
}
```
**Return - Status 201 Created**
```json
{
    "id": 5,
    "descricao": "Pizza",
    "valor": 60.0,
    "data": "2022-07-10"
}
```
#
```http
  PUT /despesas/${id}
```

| Parâmetro   | Tipo        | Descrição                                   |
| :---------- | :---------  | :------------------------------------------ |
| `data`      | `LocalDate` | **Obrigatório**. Informa a data da despesa. |
| `descricao` | `String`    | **Obrigatório**. Informa o tipo da despesa. |
| `valor`     | `BigDecimal`| **Obrigatório**. Informa o valor da despesa.|

**Body**
```json
{
    "data" : "2022-07-10T08:30:00Z",
    "descricao" : "Pizza",
    "valor" : 70.0
}
```
**Return - Status 200 OK**
```json
{
    "id": 5,
    "descricao": "Pizza",
    "valor": 70.0,
    "data": "2022-07-10"
}
```
#
```http
  DELETE /despesas/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` | **Obrigatório**. O ID do item que você deseja deletar. 

**Return - Status 204 No Content**
