# On Air Entertainment


## Technologies to use

Solution based on the following technologies:
- Scala
- Akka Actors
- Circe used for serialization
- Akka HTTP can be used for websockets
Other options:


## Coding task

Create an application for a Lucky Numbers game.

Game will include following minimal functionality:
- a method / service that generates a random number from 0 to 999999
- a method / service that accepts number of players, e.g. 5 and generates a random number for each player & a bot player
- a method / service that for each player & bot player constructs a game result with the following rules:
  - counts occurrences of each digit in given number, e.g. for number 447974 there are 4 - 3 times, 7 - 2 times, 9 - one time
  - calculates result for each digit by formula 10^(times-1) * digit, e.g. in number 447974 it will be 10 * 10 * 4 for 4, 10 * 7 for 7, 9 for 9  
  - summarizes all digit result, e.g. for number 447974 it will be 10 * 10 * 4 + 10 * 7 + 9 = 479
- a method / service that calculates winning list:
  - all results that are below bot player aren't included into result list
  - all winners should be sorted by position
  
## Requests

Endpoint for playing game
```
ws://127.0.1.1:8008/play
```


- play message to websocket
```
{
  "players": 3
}
```
- results message from websocket
```
[
  {
    "position": 1,
    "result": 108,
    "number": 773308
  },
  {
    "position": 2,
    "result": 79,
    "number": 982606
  },
  {
    "position": 3,
    "result": 51,
    "number": 440128
  }
]
```
- response of bad request
```
{
  "code": 400,
  "message": "Bad Request"
}
```
