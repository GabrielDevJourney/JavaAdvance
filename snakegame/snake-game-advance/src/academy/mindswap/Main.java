package academy.mindswap;

import academy.mindswap.field.Field;

public class Main {

	public static void main(String[] args) {

		while (true) {
			Game game = new Game(100, 30, 100);
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
//todo fix message not appearing