# LPOO_53 - Guardians of The Galaxy


## Planned Features

### Specific Elements

- Player's Spaceship
- Enemies' Spaceship's
- Boss Enemies
- Asteroids
- Powerup items
- Inventory
- Collectibles that improve player's score
- Health bar
- Bullets
- Bullets indicator
- Score indicator
- Game menu:
    - Play
    - Scores
    - Configure player's look
### Controls and Actions
- Player's movement using keyboard, vertically and horizontally with some limits.
- Enemies' movement
- Enemies' random generation
- Shooting bullets
- Combined Powerups
- Controlling powerups with mouse

## Game mockup

![Current mockup of the game](images/game_mockup.png)

## Design
### Structure
#### Model view controller
- Model holds all the data for the game
- View is responsible to display the data stored in the model
- Controller is responsible to update the information in the model according to user interaction and game states

### Design patterns in features

#### Preliminary analysis on Power ups

- State is the design pattern that will be the foundation to this set of features. According to what power up is being used, the player will have different states
- However, if powerups are used at the same time, we could adapt a Composite pattern to determine how the following actions would be applied. A function inside the composite class could mathematically calculate the powerup effects and its color.
