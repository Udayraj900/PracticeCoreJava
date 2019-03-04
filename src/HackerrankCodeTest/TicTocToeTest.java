package HackerrankCodeTest;

import java.util.Scanner;

public class TicTocToeTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		TicTacToe tacToe = new TicTacToe();
		Scanner sc = new Scanner(System.in);
		int x = 0, y = 0;
		do {
			System.out.println(tacToe.player == tacToe.X ? "Player X turn" : "Player O turn");
			x = sc.nextInt();
			y = sc.nextInt();

			tacToe.putSign(x, y);
			System.out.println(tacToe.toString());
			System.out.println("-------------------------------");
			tacToe.displayWinner();
		} while (tacToe.isEmpty);
	}
}

class TicTacToe {
	public static final int X = 1, O = -1;
	public static final int EMPTY = 0;

	public int player = X;
	private int[][] board = new int[3][3];
	public boolean isEmpty = false;

	public void putSign(int x, int y) {
		if (x < 0 || x > 2 || y < 0 || y > 2) {
			System.out.println("Invalid board Position...");
			return;
		}
		if (board[x][y] != EMPTY) {
			System.out.println("Board Position already Occupied...");
			return;
		}

		board[x][y] = player;
		player = -player;
	}

	public boolean isWin(int player) {
		return ((board[0][0] + board[0][1] + board[0][2] == player * 3)
				|| (board[1][0] + board[1][1] + board[1][2] == player * 3)
				|| (board[2][0] + board[2][1] + board[2][2] == player * 3)
				|| (board[0][0] + board[1][0] + board[2][0] == player * 3)
				|| (board[1][0] + board[1][1] + board[1][2] == player * 3)
				|| (board[2][0] + board[2][1] + board[2][2] == player * 3)
				|| (board[0][0] + board[1][1] + board[2][2] == player * 3)
				|| (board[2][0] + board[1][1] + board[0][2] == player * 3));
	}

	public void displayWinner() {
		if (isWin(X)) {
			System.out.println("X wins...");
			isEmpty = false;
		} else if (isWin(O)) {
			System.out.println("O wins...");
			isEmpty = false;
		} else {
			if (!isEmpty) {
				System.out.println("Tie...");
			}
		}
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		isEmpty = false;
		for(int i=0;i<3;i++) {
			for(int j = 0;j<3;j++) {
				switch(board[i][j]) {
				case X: s.append(" X "); break;
				case O: s.append(" O "); break;
				case EMPTY: s.append("   "); isEmpty = true; break;
				
				}
				if(j<2) s.append("|");
			}
			if(i<2)
				s.append("\n-----------\n");
		}
		return s.toString();
	}
}
