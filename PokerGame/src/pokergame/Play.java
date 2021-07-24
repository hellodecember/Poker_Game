/**
 * Name: Autumn Arnold
 * Date: 5/1/2020
 * Project: Poker Game
 */

package pokergame;


public class Play {
	public static void main(String[] args) {
		PokerGame pokerGame;
		
		if(args.length > 0) {
			pokerGame = new PokerGame(Integer.parseInt(args[0]));
		}
		else {
			pokerGame = new PokerGame();
		}
		
		pokerGame.play();
	}
}
