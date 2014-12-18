public class SpecialCrushes {
	private Board board;
	private int width;
	private int height;

	public SpecialCrushes(Board board) {
		this.board = board;
		width = board.getWidth();
		height = board.getHeight();
	}

	public void crushByColorBomb(Piece lokum, Piece colorbomb) {

		if (colorbomb.getType() == 5 && (lokum instanceof StrippedLokum)) {
			int n = 0;
			try {
				n = board.removedByUsingColorBomb((Lokum) lokum,
						(Lokum) colorbomb);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.setScore(Score.usingStrippedColorBombCalculation(n));
			board.handleScoreModification();
			return;

		} else if (colorbomb.getType() == 5 && (lokum instanceof WrappedLokum)) {
			try {
				board.removedByUsingColorBomb((Lokum) lokum, (Lokum) colorbomb);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.setScore(Score.usingWrappedColorBombCalculation());
			board.handleScoreModification();
			return;

		} else if (colorbomb.getType() == 5
				&& (lokum instanceof ColorBombLokum)) {
			try {
				board.clear();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.setScore(Score
					.usingDoubleColorBombCalculation(height * width));
			board.handleScoreModification();
			return;

		} else if (colorbomb.getType() == 5 && (lokum instanceof BasicLokum)) {
			int n = 0;
			try {
				n = board.removedByUsingColorBomb((Lokum) lokum,
						(Lokum) colorbomb);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.setScore(Score.usingColorBombCalculation(n));
			board.handleScoreModification();
			return;
		} else if (lokum.getType() == 5 && (colorbomb instanceof StrippedLokum)) {
			int n = 0;
			try {
				n = board.removedByUsingColorBomb((Lokum) colorbomb,
						(Lokum) lokum);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.setScore(Score.usingStrippedColorBombCalculation(n));
			board.handleScoreModification();
			return;
		} else if (lokum.getType() == 5 && (colorbomb instanceof WrappedLokum)) {
			try {
				board.removedByUsingColorBomb((Lokum) colorbomb, (Lokum) lokum);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.setScore(Score.usingWrappedColorBombCalculation());
			board.handleScoreModification();
			return;
		} else if (lokum.getType() == 5 && (colorbomb instanceof BasicLokum)) {
			int n = 0;
			try {
				n = board.removedByUsingColorBomb((Lokum) colorbomb,
						(Lokum) lokum);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.setScore(Score.usingColorBombCalculation(n));
			board.handleScoreModification();
		}

	}

	public void crushByStrippedAndWrapped(Piece firstPressed,
			Piece secondPressed) {
		int x = secondPressed.getPosX();
		int y = secondPressed.getPosY();
		if (x == 0 && y == 0) {
			try {
				for (int i = 0; i < 2; i++) {
					board.removeColumn((Lokum) board.getArrayOfPieces()[0][i]);
					board.removeRow((Lokum) board.getArrayOfPieces()[i][0]);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			board.setScore(Score.usingWrappedAndStrippedCalculation(width));
			board.handleScoreModification();
			return;
		} else if (x > 0 && x < width - 1 && y == 0) {
			try {
				for (int i = -1; i < 2; i++) {
					board.removeColumn((Lokum) board.getArrayOfPieces()[0][x
							+ i]);
					if (i >= 0) {
						board.removeRow((Lokum) board.getArrayOfPieces()[i][0]);
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			board.setScore(Score.usingWrappedAndStrippedCalculation(width));
			board.handleScoreModification();
			return;
		} else if (x == width - 1 && y == 0) {
			try {
				for (int i = 0; i < 2; i++) {
					board.removeColumn((Lokum) board.getArrayOfPieces()[0][width
							- 1 - i]);
					board.removeRow((Lokum) board.getArrayOfPieces()[i][0]);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			board.setScore(Score.usingWrappedAndStrippedCalculation(width));
			board.handleScoreModification();
			return;
		} else if (x == 0 && y > 0 && y < height - 1) {
			try {
				for (int i = -1; i < 2; i++) {
					board.removeRow((Lokum) board.getArrayOfPieces()[y + i][x]);
					if (i >= 0) {
						board.removeColumn((Lokum) board.getArrayOfPieces()[y][x
								+ i]);
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			board.setScore(Score.usingWrappedAndStrippedCalculation(width));
			board.handleScoreModification();
		} else if (x == 0 && y == height - 1) {
			try {
				for (int i = 0; i < 2; i++) {
					board.removeColumn((Lokum) board.getArrayOfPieces()[0][i]);
					board.removeRow((Lokum) board.getArrayOfPieces()[height - i
							- 1][0]);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			board.setScore(Score.usingWrappedAndStrippedCalculation(width));
			board.handleScoreModification();
		} else if (x > 0 && x < width - 1 && y == height - 1) {
			try {
				for (int i = -1; i < 2; i++) {
					board.removeColumn((Lokum) board.getArrayOfPieces()[0][x
							+ i]);
					if (i >= 0) {
						board.removeRow((Lokum) board.getArrayOfPieces()[height
								- 1 - i][0]);
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			board.setScore(Score.usingWrappedAndStrippedCalculation(width));
			board.handleScoreModification();
		} else if (x == width - 1 && y == height - 1) {
			try {
				for (int i = 0; i < 2; i++) {
					board.removeColumn((Lokum) board.getArrayOfPieces()[0][width
							- 1 - i]);
					board.removeRow((Lokum) board.getArrayOfPieces()[height - 1
							- i][0]);
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			board.setScore(Score.usingWrappedAndStrippedCalculation(width));
			board.handleScoreModification();
			return;
		} else if (x == width - 1 && y > 0 && y < height - 1) {
			try {
				for (int i = -1; i < 2; i++) {
					board.removeRow((Lokum) board.getArrayOfPieces()[y + i][x]);
					if (i >= 0) {
						board.removeColumn((Lokum) board.getArrayOfPieces()[y][x
								- i]);
					}
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			board.setScore(Score.usingWrappedAndStrippedCalculation(width));
			board.handleScoreModification();
			return;
		} else {
			try {
				for (int i = -1; i < 2; i++) {
					board.removeRow((Lokum) board.getArrayOfPieces()[y + i][x]);
					board.removeColumn((Lokum) board.getArrayOfPieces()[y][x
							+ i]);
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			board.setScore(Score.usingWrappedAndStrippedCalculation(width));
			board.handleScoreModification();
			return;
		}
	}

	public void crushByStripped(Piece firstPressed, Piece secondPressed) {
		if ((firstPressed instanceof StrippedLokum)
				&& (secondPressed instanceof BasicLokum)) {
			if (board.checkSequenceDown(firstPressed)
					|| board.checkSequenceUp(firstPressed)
					|| board.checkSequenceHorizontalMiddle(firstPressed)
					|| board.checkSequenceVerticalMiddle(firstPressed)
					|| board.checkSequenceLeft(firstPressed)
					|| board.checkSequenceRight(firstPressed)) {
				if (((StrippedLokum) firstPressed).isVertical()) {
					try {
						board.removeColumn((Lokum) firstPressed);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					board.setScore(Score.usingStrippedCalculation(height));
					board.handleScoreModification();
				} else {
					try {
						board.removeRow((Lokum) firstPressed);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					board.setScore(Score.usingStrippedCalculation(width));
					board.handleScoreModification();
				}
				return;
			}
		} else if ((secondPressed instanceof StrippedLokum)
				&& (firstPressed instanceof BasicLokum)
				&& firstPressed.getType() == secondPressed.getType()) {
			if (((StrippedLokum) secondPressed).isVertical()) {
				try {
					board.removeColumn((Lokum) secondPressed);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				board.setScore(Score.usingStrippedCalculation(height));
				board.handleScoreModification();
			} else {
				try {
					board.removeRow((Lokum) secondPressed);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				board.setScore(Score.usingStrippedCalculation(width));
				board.handleScoreModification();
			}
			return;
		}
	}

	public void crushByWrapped(Piece firstPressed, Piece secondPressed) {
		if ((firstPressed instanceof WrappedLokum)
				&& (secondPressed instanceof BasicLokum)) {
			if (board.checkSequenceDown(firstPressed)
					|| board.checkSequenceUp(firstPressed)
					|| board.checkSequenceHorizontalMiddle(firstPressed)
					|| board.checkSequenceVerticalMiddle(firstPressed)
					|| board.checkSequenceLeft(firstPressed)
					|| board.checkSequenceRight(firstPressed)) {
				try {
					board.removeRange((Lokum) firstPressed, 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				board.setScore(Score.usingWrappedCalculation());
				board.handleScoreModification();
				return;
			}
		} else if ((secondPressed instanceof WrappedLokum)
				&& (firstPressed instanceof BasicLokum)) {
			if (board.checkSequenceDown(secondPressed)
					|| board.checkSequenceUp(secondPressed)
					|| board.checkSequenceHorizontalMiddle(secondPressed)
					|| board.checkSequenceVerticalMiddle(secondPressed)
					|| board.checkSequenceLeft(secondPressed)
					|| board.checkSequenceRight(secondPressed)) {
				try {
					board.removeRange((Lokum) secondPressed, 1);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				board.setScore(Score.usingWrappedCalculation());
				board.handleScoreModification();
				return;
			}
		}
	}
	public void crushByDoubleWrapped(Piece firstPressed, Piece secondPressed){
		if ((secondPressed instanceof WrappedLokum)
				&& (firstPressed instanceof WrappedLokum)){
			try {
				board.removeRange((Lokum) secondPressed, 2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.setScore(Score.usingDoubleWrappedCalculation());
			board.handleScoreModification();
		}
	}
	public void crushByDoubleStripped(Piece firstPressed, Piece secondPressed){
		if ((secondPressed instanceof StrippedLokum)
				&& (firstPressed instanceof StrippedLokum)){
			try {
				board.removeColumn((Lokum) secondPressed);
				board.removeRow((Lokum) firstPressed);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			board.setScore(Score.usingDoubleStrippedCalculation(width));
			board.handleScoreModification();
		}
	}
}
