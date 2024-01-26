
# Localizar Pet - API

Essa API tem como objetivo receber informações de um dispositivo que recebe latitude, longitude, id e
data/hora, que transmite a as coordenadas de um animal perdido, retornando um endereço completo.


## Tecnologias


Desenvolvimento: Java e Spring

Testes de Integração: JUnit e Cucumber

API Externa: Position Stack

## Request

#### Endpoint: /localizar-pet
#### Método: POST


```
{
	"id": "123",
	"latitude": 40.763841,
	"longitude": -73.972972,
	"dateTime": "2024-01-22T12:00:00"
}
```


## Response

```
{
	"country": "United States",
	"region": "New York",
	"locality": "New York",
	"neighbourhood": "Midtown East",
	"label": "Apple Store, New York, NY, USA"
}
```





## Rodando os testes de integração

Para rodar os testes, encontre o diretório test-integration e utilize a seguinte classe:

```bash
  RunnerTest.class
```

Os reports estarão no seguinte diretório:

```bash
  /target/cucumber-reports/cucumber.json
```


