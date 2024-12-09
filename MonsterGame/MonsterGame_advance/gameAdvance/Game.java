package gameAdvance;

import gameAdvance.HelperClasses.*;
import gameAdvance.Enums.GameMode;
import gameAdvance.Monsters.Monster;
import gameAdvance.Monsters.MonsterFactory;
import gameAdvance.Obstacles.Fairy;
import gameAdvance.Obstacles.Witch;
import gameAdvance.Scanner.GameScannerManager;

public class Game {
	//trying to use last class subject since players will always be 2 why not set it
	private final Player[] players = new Player[2];
	private int roundTrackingCounter = 0;
	private final TurnHandler turnHandler = new TurnHandler();
	private GameMode mode;
	private static final Supernatural[] obstacles = new Supernatural[2];


	public Game() {
		this.players[0] = new Player("Player 1");
		this.players[1] = new Player("Player 2");
		obstacles[0] = new Fairy();
		obstacles[1] = new Witch();
		initializePlayersHands();
	}

	public static Supernatural[] getObstacles() {
		return obstacles;
	}

	//*METHODS

	private void initializePlayersHands() {
		for (Player player : players) {
			dealCards(player);
			//initialize cardsAlive with all new cards
			player.updateAliveCards();
		}
	}

	//*DEAL CARDS
	private void dealCards(Player player) {
		Monster[] currentPlayerHand = player.getPlayerCards();
		for (int i = 0; i < currentPlayerHand.length; i++) {
			//choose a rancom enum type each time
			int randomIndex = Random.randomTypeMonstersIndex();
			if (currentPlayerHand[i] == null) {
				currentPlayerHand[i] = MonsterFactory.createRandomMonster(randomIndex);
			}
		}
	}


	public void start() {
		mode = GameScannerManager.handleGameModeOptions();
		GameConsole.printGameStart(mode.getDescription());
		playGame();
	}

	private void playGame() {
		Player player1 = players[0];
		Player player2 = players[1];

		//keep playing while neither has lost all cards so have a variable for both
		//&& cuz need oly one to stop game
		while (!player1.hasNoCards() && !player2.hasNoCards() && !player1.isHasLost() && !player2.isHasLost()) {

			playRound(player1, player2);
			roundTrackingCounter++;
		}

		endGame(player1, player2);

	}

	private void playRound(Player player1, Player player2) {

		GameConsole.printRoundStart(roundTrackingCounter);

		switch (mode) {
			case BOT_VS_BOT -> {
				handleGameBotVsBot(player1, player2);
			}
			case PLAYER_VS_BOT -> {
				handleGamePlayerVsBot(player1, player2);
			}
			case PLAYER_VS_PLAYER -> {
				handleGamePlayerVsPlayer(player1, player2);
			}
		}

		GameConsole.printRoundEnd();

		//!terminal info
			/*GameConsole.printRoundInfo(roundTrackingCounter, player2.getName(), player1.getName(),
					monsterPlayer2.getName(), monsterPlayer1.getName(),
					monsterPlayer2.getCurrentHealth(), monsterPlayer1.getCurrentHealth());*/
	}


	//no need for attacker player itself be passed since I am passing his selected monster and that is where i will
	// grab damage to deal from
	private void handleGameBotVsBot(Player player1, Player player2) {
		Supernatural obstacle = roundObstacle();

		if (obstacle != null && roundTrackingCounter % 2 == 0) {

			turnHandler.handleTurnWithObstacleBotVsBot(obstacle, player1, player2);

		} else {
			if (roundTrackingCounter % 2 == 0) {

				turnHandler.handleBotTurnBotVsBot(player1, player2);

			} else {

				turnHandler.handleBotTurnBotVsBot(player2, player1);
			}
		}
	}

	private void handleGamePlayerVsBot(Player player1, Player player2) {

		Supernatural obstacle = roundObstacle();

		player1.setName("USER");
		player2.setName("BOT");

		if (obstacle != null) {
			System.out.println("Obstacle for this round is " + obstacle.getName());
			turnHandler.handleTurnWithObstaclePlayervsBot(obstacle,player1,player2);
		} else {

			if (roundTrackingCounter % 2 == 0) {

				System.out.println("No obstacle playing!");

				player1.setAttacking(true);
				player2.setAttacking(false);

				turnHandler.handleBotVsPlayerTurn(player1, player2);

			} else {
				player1.setAttacking(false);
				player2.setAttacking(true);

				turnHandler.handleBotVsPlayerTurn(player1, player2);
			}
		}
	}

	private void handleGamePlayerVsPlayer(Player attacker, Player defense) {

		Supernatural obstacle = roundObstacle();

		attacker.setName("USER_Gabriel");
		defense.setName("USER_Test");

		if (obstacle != null) {
			System.out.println("Obstacle for this round is " + obstacle.getName());
			turnHandler.handleTurnWithObstaclePlayerVsPlayer(obstacle, attacker, defense);
		} else {
			System.out.println("No obstacle playing!");
			if (roundTrackingCounter % 2 == 0) {

				attacker.setAttacking(true);
				defense.setAttacking(false);

				turnHandler.handlePlayerVsPlayerTurn(attacker, defense);

			} else {
				attacker.setAttacking(false);
				defense.setAttacking(true);

				turnHandler.handlePlayerVsPlayerTurn(defense, attacker);
			}
		}
	}

	private void endGame(Player player1, Player player2) {
		if (player1.isHasLost()) {
			GameConsole.announceWinner(player2.getName(), player1.getName());
		} else {
			GameConsole.announceWinner(player1.getName(), player2.getName());
		}
	}

	private Supernatural roundObstacle() {
		return Generator.generateRandomObstacle();
	}
}
