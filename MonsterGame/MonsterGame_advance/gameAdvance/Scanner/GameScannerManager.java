package gameAdvance.Scanner;

import gameAdvance.Game;
import gameAdvance.Enums.DamageDecision;
import gameAdvance.Enums.GameMode;
import gameAdvance.Monsters.Monster;
import gameAdvance.Player;

import java.util.Scanner;

public class GameScannerManager {

	static Scanner gameScanner = new Scanner(System.in);
	Game game;

	public GameScannerManager(Game game) {
		this.game = game;
	}

	public static GameMode handleGameModeOptions() {
		int userChoice = 0;

		do {

			GameMode.displayModeOptions();

			if (gameScanner.hasNextInt()) {
				userChoice = gameScanner.nextInt();
				gameScanner.nextLine();

				//check user choice and available ones
				return GameMode.decisionOfMode(userChoice);

			}

		} while (true);

	}

	public static String handleDamageDecision(String currentPlayerName) {
		int userChoice = 0;

		do {

			System.out.println(currentPlayerName + " do you want to take damage yourself?");
			DamageDecision.displayDecisionOptions();

			if (gameScanner.hasNextInt()) {
				userChoice = gameScanner.nextInt();
				gameScanner.nextLine();

				//check user choice and available ones
				return DamageDecision.getDamageDecision(userChoice);

			}

		} while (true);
	}

	public static int handleMonsterChoice(Player player) {
		//my scanner needs to grab player alive monsters and display them
		//but the display must be based on enums
		//so for each monster alive in monster get enum info

		int userChoice = 0;
		do {

			System.out.println(player.getName() + " choose your monster for this round!");
			player.showAliveMonsters();

			if (gameScanner.hasNextInt()) {
				userChoice = gameScanner.nextInt();
				gameScanner.nextLine();

				//compare choice with available options

				//? i need return what is selected here and in game handle the l
				//? logic, this needs to be handle in the turn handler since is called
				//?every round
				return userChoice;
			}

		} while (true);
	}
}
