package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Chess.ChessExcepection;
import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		System.out.println("Players, you are ready?");
		Scanner sc = new Scanner (System.in);
		ChessMatch chessMatch = new ChessMatch();
		List<ChessPiece> captured = new ArrayList<>();
				
		while (!chessMatch.getCheckMate()) {
			try {
				UI.clearScreen(); 
				UI.printMatch(chessMatch, captured);
				System.out.println();
				System.out.print("Souce:");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean [][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);	
				System.out.println();
				System.out.println(" Target:");
				ChessPosition target = UI.readChessPosition(sc);
	
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				if (capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if(chessMatch.getPrometed() != null) {
					System.out.println("Enter piece for promotion (B/N/R/Q)");
					String type = sc.nextLine().toUpperCase();
					while(	!type.equals("B") && !type.equals("R") && !type.equals("N") && !type.equals("Q")) {
						System.out.println("Invalid value! Enter piece for promotion (B/N/R/Q)");
					    type = sc.nextLine().toUpperCase();
					}

					chessMatch.replacePromotedPiece(type);
				}
			}
			catch(ChessExcepection e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);
	}
		
}
