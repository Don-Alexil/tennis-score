# 🎾 Tennis Score 🎾

# Subject

The goal is to implement a simple tennis score computer.

The scoring system consist in one game, divided by points :

* Each player starts a game with 0 point.
* If the player wins the 1st ball, he will have 15 points. 2nd balls won : 30 points. 3rd ball won : 40points.
* If a player have 40 points and wins the ball, he wins the game, however there are special rules.
* If both players have 40 points the players are “deuce”.
* If the game is in deuce, the winner of the ball will have advantage
* If the player with advantage wins the ball he wins the game
* If the player without advantage wins the ball they are back at “deuce”.

# 🧠 Analyse 🧠 
## Naive Method - all in one method 
[Naive Code](./src/main/java/ro/alexil/tennis/TennisGameNaive.java)

Pro : 
 - deliver the solution rapidly, the code works
 - if the codebase is small and we won't change often

Cons: 
 - no code readability 
 - difficult to maintain, test and change due to complex conditional logic  
 
## State machine method - separation in small testable pieces   

[State machine Code](./src/main/java/ro/alexil/tennis/TennisGame.java)

Pro: 
 - maintainability and testability 
 - separation of concerns 
 - avoid complex conditional logic 
 - encapsulation of behaviour

Cons: 
 - slightly harder to debug for simple issues
 - instead of a class with few conditions, we have multiple state classes 
 - increase of the code size for a simple game 

# Prerequisites

* Maven 3.9.9
* Java 21

# ⚒️ Install & Run ⚒️

1. *mvn test* - run the unit tests 
