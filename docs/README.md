# LPOO_53 - Guardians of The Galaxy


## Planned Features

### Specific Elements

- Boss Enemies
- Powerup items
- Inventory
- Health bar
- Bullets indicator
- Score indicator
- Game menu:
  - Play
  - Scores
  - Configure player's look
  
### Controls and Actions
- Enemies' random generation
- Basic Powerups
- Combined Powerups
- Controlling powerups with mouse
- Asteroid actions
- Collectibles that improve player's score
- Apply collision detection to elements

## Implemented Features
### Elements and Views
- Player's Spaceship
- Enemies' Spaceship's
- Bullets
- Asteroids
- Coins

#### Example element views
![Views screenshot](images/screenshots/views.png)

#### UML Diagram

![Elements UML](images/design/elements.png)

### Controls and actions
### Player's movement using keyboard, vertically and horizontally with some limits.
![Player controls](gifs/player.gif)

### Enemies' movement
| ![Down Movement](gifs/enemy1.gif)  |  ![Diagonal Movement](gifs/enemy2.gif)   |
| :---: | :---: |
| ![Circular Movement](gifs/enemy3.gif)   | ![Diagonal Movement](gifs/enemy4.gif)   |

### Shooting bullets
![Bullets](gifs/spawn-bullets.gif)

### Collision detection

## Game mockup

![Current mockup of the game](images/game_mockup.png)

## Structure
#### Model view controller
- Model holds all the data for the game
- View is responsible to display the data stored in the model
- Controller is responsible to update the information in the model according to user interaction and game states

#### Rendering elements
- It is important that we build this part of the structure to be versatile, so that the game could be used with other engines.
- To allow this situation, we created an interface `gui` that has functions like `drawColor` or `drawCharacter`. These will be called by specific Viewers. Example:

![Rendering diagram](images/design/rendering.png)

## Design
### Firing
#### Problem in Context
We wanted to create different ways of firing that could be applied to the spaceships.

We also didn't want to create lots of duplicated or hard to maintain code. The solution needed to allow
the easy creation of different firing methods without causing divergent changes or shotgun surgery.


#### The pattern
To solve this issue, we have applied an adaptation of the [Strategy Pattern](https://refactoring.guru/design-patterns/strategy).
This pattern allows us to extract all the different controls into different classes, each strategy class containing methods only relevant to that specific controller.

#### Implementation
The current implementation is as follows in the UML diagram

![FiringStrategy](images/design/firingStrategy.png)

This pattern is implemented in the following classes:

- [FiringStrategy](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/controller/firing/FiringStrategy.java)
- [StraightBulletStrategy](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/controller/firing/StraightBulletStrategy.java)

### Movement

#### Problem in Context

We wanted to create different ways of movement that could be applied to various elements like 
asteroids, spaceships or bullets.

We also didn't want to create lots of duplicated or hard to maintain code. The solution needed to allow
the easy creation of different movement methods without causing divergent changes or shotgun surgery.

#### The Pattern

To solve this issue, we have applied a [Strategy Pattern](https://refactoring.guru/design-patterns/strategy).

#### Implementation

The current implementation is as follows in the UML diagram.

![Movement](images/design/movementStrategy.png)

This pattern is implemented in the following classes:

- [MovementStrategy](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/controller/movement/MovementStrategy.java)
- [CircularMovement](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/controller/movement/CircularMovement.java)
- [DiagonalBounceMovement](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/controller/movement/DiagonalBounceMovement.java)
- [DiagonalDownLeftMovement](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/controller/movement/DiagonalDownLeftMovement.java)
- [DiagonalDownRightMovement](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/controller/movement/DiagonalDownRightMovement.java)
- [FallDownMovement](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/controller/movement/FallDownMovement.java)
- [MoveUpwardsMovement](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/controller/movement/MoveUpwardsMovement.java)


### Colliders

#### Problem in Context

Elements like asteroids and bullets need to collide with spaceships, so we can know when to
remove health from them. To do this elements need to have associated colliders that have different shapes
to create more believable collisions.

Different shapes have different ways of checking collision with each other which, if not correctly
designed, could degenerate into multiple switch statements.

The idea is to have multiple lines per collider with different widths and relative positions and 
have collision checking using polymorphism.

#### The Pattern

To solve the issue related to the usage of multiple lines per collider, the [Composite Pattern](https://refactoring.guru/design-patterns/composite) was used.

To solve the issue related to the checking of collisions of different shapes, while keeping the benefits of polymorphism
, a variation of the [Visitor Pattern](https://refactoring.guru/design-patterns/visitor) was used. We deviated from
the standard visitor behaviour by making the collider visit the other collider (a self visitor), making the collider
both the visitor and also the visited element. One disadvantage of this pattern is that
whenever we need to create a new collider shape, the Collider class needs to be changed.

#### Implementation

The current implementation is as follows in the UML diagram.

![Colliders](images/design/colliders.png)

This pattern is implemented in the following classes:

- [BodyCollider](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/model/collider/BodyCollider.java)
- [LineCollider](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/model/collider/LineCollider.java)
- [LineCompositeCollider](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/model/collider/LineCompositeCollider.java)

### Bounding Box Creation

#### Problem in Context

Each collider shape has a different way of calculating and creating their bounding boxes. However, this shouldn't be
the colliders' function, as it would violate the Single-Responsibility principle.

#### The Pattern

To solve this issue, the [Factory Pattern](https://refactoring.guru/design-patterns/factory-method) was used.
The factory creates a bounding box based by the type of the object passed through the parameter.

#### Implementation

The current implementation is as follows in the UML diagram.

![BoundingBox](images/design/boundingbox.png)

This pattern is implemented in the following class:

- [BoundingBoxFactory](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/model/collider/BoundingBoxFactory.java)

### Line Composite Collider Shapes

#### Problem in Context

Every time we want to create a Line Composite Collider, a list of Line Colliders must be passed by parameter through
the constructor. This can become quite messy and complicated, with a lot of duplicated code, specially since most of the
elements are represented by shapes like triangles, vertical lines or diamonds.

#### The Pattern

To solve this issue, the [Factory Pattern](https://refactoring.guru/design-patterns/factory-method) was used.

#### Implementation

The current implementation is as follows in the UML diagram.

![LineFactory](images/design/linefactory.png)

This pattern is implemented in the following class:

- [LineCompositeFactory](https://github.com/FEUP-LPOO-2021/lpoo-2021-g53/blob/develop/src/main/java/com/shootemup/g53/model/collider/LineCompositeFactory.java)

### Preliminary analysis on Power ups

- State is the design pattern that will be the foundation to this set of features. According to what power up is being used, the player will have different states


## Known Code Smells and Refactoring Suggestions

### Middle Man
The [SpaceshipController]() is a middle man to [Spaceship](). 

To solve this issue we could simply remove this class, since it just calls the move and fire methods from the second class. 

### Switch Statement
The [DiagonalBounceMovement]() contains switch case statements.

We can solve this issue by replacing the `Direction` with a state class, using a State Pattern. This could be achieved by using the already implemented [DiagonalDownLeft]() and [DiagonalDownRight]() movement classes.

### Shotgun Surgery

Whenever a new collider shape is added, a new abstract method for checking collision with it has to be added to the 
[BodyCollider]() class, which means every sub-class of BodyCollider needs to implement that method.

There is no way to solve this issue without removing the Visitor Pattern which would make for worse organization.

## Testing

## Self-evaluation

