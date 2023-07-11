### AP-Tank-Stars-Game-
This is an AP Group Project in which we made a Tank fight simulation game similar to Tank Stars available on google play and app store

### Description
This is the LibGDX version of the TankStars game, which executes its basic functions and gameplay. The game allows two players to play turn by turn, starting by choosing their tanks and then moving, aiming and firing at their opponent's tank to decrease their health. The tanks also have fuel, which is limited and the fuel bar keeps on decreasing as the tank moves. The game is won when one player is defeated by reducing their health to zero. TankStars games involves the features of pausing, resuming and restarting a game at any given time, and also saving the current game (such that all the current data related to health, fuel, positions etc. is saved) so that it can be played later.

This project involves the major concepts of Object Oriented Programming, with relation between classes, their objects and functions being implemented. Serialization has been implemented to save and store the games, as well as concepts of design patterns.

### Screens
- ChooseTankScreen.java: This screen allows both the players to choose any tank of their choice from the available option(Abrams, Helios and Coalition).
- GameOverScreen.java: This screen pops up when the health bar of any player touches zero. This screen indicates that the current game has ended and gives users the option to restart the current game or go to the home screen of the game.
-MainMenuScreen.java: This screen displays three buttons: New Game, Resume and Exit. The new game button starts a new game for the user, the resume button allows users to choose from a list of saved games to load and exot button exits the application
- InGameScreen.java: This is the screen which is displayed when the game is in progress. It has multiple options for the users: Pause game, Save game, etc.
- ResumeScreen.java: This screen displays the list of all saved games for the user to choose from.
- LoadGameScreen.java: This is the loading screen which shows up while the game loads.
- PauseScreen.java: This is the in-game pause screen.
- HomePage.java: This screen displays the game icon and if clicked upon, proceeds to load the game.

### Entities
- Tank.java: This is the tank entity of the game. This is the main entity which moves on a terrain, shoots and aims.
- Weapon.java: This is the entity which is attached to the tank. The tank can aim and shoot usin weapons
- GameObjects: These are the entities apart from the tank and weapon, like the terrain, the scenery, etc.

### How To Play
- Left Arrow: Moves tank to the left
- Right Arrow: Moves tank to the right
- Up Arrow: To move the aim upwards
- Down Arrow: To move the aim downwards
- M Key: To Increase the shooting power
- N Key: To decrease the shooting power
- Spacebar: To fire bullet from the tank
