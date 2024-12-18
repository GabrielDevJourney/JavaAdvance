package academy.mindswap;

import academy.mindswap.field.Field;

public class Main {

	public static void main(String[] args) {

		int gameWidth = 100;
		int gameHeight = 30;
		int delay = 100;

		while (true) {
			Game game = new Game(gameHeight, gameWidth, delay);
			try {
				game.start();
				if(!game.restart){
					break;
				}
				Field.stopScreen();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}
	}
}