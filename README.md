<div align="center"><img src="path/to/your/sudoku_game_logo.png"></div>
<h1 align="center">Sudoku Game</h1>
<p align="center"><strong>Description</strong>
<br>A console-based Sudoku game implementation in Java, featuring a server-client architecture for multiplayer gameplay.</p>
<br/>
<h2>About</h2>
- Provides a classic 9x9 Sudoku puzzle.
<br/>
- Supports multiplayer mode with a server managing the game state and clients interacting with it.
<br/>
- Includes a Sudoku puzzle generator and solver for validating moves.
<br/>
- Utilizes a simple text-based interface for interaction.

<h2>Key Features</h2>
- Sudoku Logic: Generates Sudoku puzzles and manages game logic.
<br/>
- Multiplayer Support: Allows multiple players to connect and play using a client-server model.
<br/>
- Move Validation: Ensures that all player moves are valid and adhere to Sudoku rules.
<br/>
- Dynamic Puzzle Generation: Creates a unique Sudoku puzzle each time the server starts.
<br/>
- Console Interface: Provides a user-friendly text-based interface for game interactions.

<h2>Technologies Used</h2>
- Java
<br/>
- Networking (Sockets)
<br/>
- Syncrhonization
<br/>
- Object-Oriented Programming

<h2>Installation</h2>

1. Clone the repository: `git clone https://github.com/your-username/sudoku-game.git`
2. Navigate to the project directory: `cd sudoku-game`
3. Compile the Java files: `javac Sudoku.java`, `javac Server.java`, `javac Client.java`
4. Start the server: `java Server <PORT_NUMBER>`
5. In separate terminals, start each client: `java Client <PORT_NUMBER>`

<h2>Usage</h2>
- On the client console, type 'show' to see the current state of the Sudoku board.
<br/>
- To make a move, enter 'update i j num' where i and j are row and column indices (0-8), and num is the number to place (1-9).
<br/>
- Follow the server and client console instructions for interactive gameplay.

<h2>Credits</h2>

- Author: Thomas Dinopoulos
- Co-Author: Doosan Baik

</p>
