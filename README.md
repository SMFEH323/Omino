# Omino

Omino is a Java-based game that incorporates elements of classic tile-matching puzzle games. Players manipulate different shapes, or "polyominos," by moving and rotating them as they fall into a grid. The objective is to fill rows to clear them and score points, similar to the well-known game Tetris.

The project is structured around several key classes:
- `Block`: Represents the smallest unit of the game grid.
- `Board`: Manages the game grid and active pieces.
- `Game`: Handles the game logic and state management.
- `Piece`, `Polyomino`: Define the shapes used in the game, including their configurations and orientations.
- `Action`: Enumerates possible player actions such as moving or rotating pieces.

## Installation

Clone this repository and compile the Java files to start playing Omino.

```bash
git clone https://github.com/SMFEH323/omino.git
cd omino
javac *.java
java Game
```

## Usage

Run the `Game` class to start the game. Use keyboard inputs (specified within the game) to control the pieces.

## Contributing
Contributions are welcome! If you'd like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.

## Contact
For any inquiries, please reach out via email at elhawaryseif@gmail.com.
