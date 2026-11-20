# Chess Game

A Java chess project with an interactive Swing graphical interface and piece-specific movement validation.

## Features

- Interactive Java Swing chess interface with an 8-by-8 board.
- Six piece types: pawn, rook, bishop, knight, king, and queen.
- Collections and generics to organize chess pieces and game data.
- Lambda functions for graphical interface event handling.
- Abstract classes, inheritance, interfaces, enums, and custom exceptions.
- Generated HTML API documentation with Javadoc.

## Included source snapshot

The checked-in source and supplied ZIP archives contain the console movement-validation snapshot. The Swing interface, collections/generics integration, and GUI event handlers described above are not present in these files yet. No new implementation code was added in this documentation update.

## Run the included source

Requires JDK 17 or newer, with no external libraries.

```sh
./build.sh
java -cp out chess.app.ChessMoveValidator
```

Enter six different piece types, their colors and starting coordinates, followed by a target square. This snapshot validates movement geometry only; it does not implement a complete chess match or update piece positions after validation.

## Files

- `src/`: Java source, organized by application, board, pieces, enums, interfaces, and exceptions.
- `docs/`: generated Javadoc for the checked-in source.
- `tests/ChessMovementTest.java`: representative movement and input-validation checks.
- `build.sh`: Java 17 build script.
- `archives/Chess-Game-Java-Source.zip`: complete original supplied Java source archive.
- `archives/Chess-Game-Javadoc.zip`: complete original supplied HTML Javadoc archive.

Archive contents are preserved as supplied, including their internal names and contributor credits.

## Verification

```sh
./build.sh
javac --release 17 -cp out -d out tests/ChessMovementTest.java
java -cp out ChessMovementTest
```

## Contributors

Original source credits Aredth Sanchez, Sofia Vargas, and Ben Silva. Those credits remain intact.
