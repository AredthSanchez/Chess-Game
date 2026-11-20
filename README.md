# Chess Move Validator

A Java console application that checks whether each chess piece can move from its starting square to a selected target square.

## Features

- Six distinct piece types: pawn, rook, bishop, knight, king, and queen.
- White and black piece colors, enum-based columns, and validated board coordinates.
- Abstract classes, inheritance, and movement interfaces.
- Custom exceptions and input prompts that retry invalid selections.
- Generated Javadoc in [`docs/`](docs/index.html).

This validates individual movement geometry. It does not implement a complete chess match, occupied-square rules, check/checkmate, castling, promotion, en passant, or two-square pawn starts. A successful validation does not update the piece's stored position. This would be implemented upon as I keep working on the project.

## Requirements and running

Use JDK 17 or newer. No external libraries are required.

```sh
./build.sh
java -cp out chess.app.ChessMoveValidator
```

Enter six different piece types, each piece's color and starting coordinates, followed by one target square. The program reports whether each selected piece can reach that target.

## Structure

- `src/chess/app/ChessMoveValidator.java`: console entry point and input handling.
- `src/chess/board/`: board coordinate validation.
- `src/chess/enums/`: piece types, colors, and columns.
- `src/chess/figures/`: piece implementations and shared hierarchy.
- `src/chess/interfaces/`: movement and coordinate contracts.
- `src/chess/exceptions/`: invalid piece, coordinate, and movement errors.
- `docs/`: generated API documentation, rebuilt from this source.

## Verification

```sh
./build.sh
javac --release 17 -cp out -d out tests/ChessMovementTest.java
java -cp out ChessMovementTest
```

The check covers representative moves for all six pieces, invalid moves/coordinates, and duplicate-piece detection. It is a focused smoke test, not a complete chess-rules test suite.

## Rebuild API documentation

The checked-in docs were generated with JDK 25:

```sh
javadoc --no-fonts -author -version -d docs -sourcepath src -subpackages chess -doctitle 'Chess Move Validator API' -windowtitle 'Chess Move Validator API'
```

For a JDK that does not support `--no-fonts`, omit that option. Open `docs/index.html` in a browser or serve the repository locally with `python3 -m http.server 8000`.

## Contributors

The original application credits **Aredth Sanchez, Sofia Vargas, and Ben Silva**. These credits remain in the source. This repository replaces assignment-number folder names and historical assignment comments with project-specific documentation.
