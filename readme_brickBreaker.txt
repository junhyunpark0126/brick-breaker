Name: Jun Hyun Park
PennKey: andrewpp
Recitation: Section 219


***************************************************************

How to Run Program

(You will be running the Game.java class with one command line argument)

1. Compile
2. Type into command line : "java Game brickBreakerMap.txt"
3. View Running Program
4. Play

***************************************************************

***************************************************************

Additional Features of the Program

1. EXTRA CREDIT : Score Feature
2. Ball bounces in different direction depending on if ball hits right or left side
of platform
3. Platform does not go out of the boundaries of the map

***************************************************************

***************************************************************

Description of Each File and Its Purpose

1.brickBreakerMap.txt

Text file that contains number of bricks, width, height, and each brick's x and y 
position and health. Gets read into Map.java.

2. Game.java

Class that acts as the overall encompassing Brick Breaker game. Responsible for 
updating game and restarting the game.

3. Map.java

Class that acts as the map for the game. Responsible for controlling collisions
and is where most of the other classes interact upon.

4. Brick.java

Object that represents the brick in Brick Breaker. Collisions and health handled

5. Platform.java

Object that represents the platform in Brick Breaker. Collisions and movement
handled

6. Ball.java

Object that represents the ball in Brick Breaker. Collisions, movement, 
lives remaining, score all handled

***************************************************************