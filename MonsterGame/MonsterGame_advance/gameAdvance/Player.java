package gameAdvance;

import gameAdvance.Monsters.Monster;

public class Player {

	//final variables since i will not change tits value
	private static final int numberOfInitialCards = 4;
	private int numberOfCardsAlive = numberOfInitialCards;
	private  String name;
	private boolean hasLost = false;
	private int health = 100;
	private boolean isAttacking;
	private final Monster[] playerCards = new Monster[numberOfInitialCards];

	//this will allow me to not have null index so only alive will be passed and array will always have the needed size
	private int aliveIndexCounter;
	private Monster[] cardsAlive;


	public Player(String name) {
		this.name = name;
	}


	public Monster[] getPlayerCards() {
		return playerCards;
	}

	public Monster[] getCardsAlive() {
		return cardsAlive;
	}

	//use this to random index in random class
	public int getAliveIndexCounter() {
		return aliveIndexCounter;
	}

	public String getName() {
		return name;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public int getHealth() {
		return health;
	}

	public boolean isHasLost() {
		return hasLost;
	}

	public void setHasLost(boolean hasLost) {
		this.hasLost = hasLost;
	}

	public void setAttacking(boolean attacking) {
		isAttacking = attacking;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void decreaseCardsAlive() {
		numberOfCardsAlive--;
	}

	public boolean hasNoCards() {
		return numberOfCardsAlive == 0;
	}

	public void updateAliveCards() {
		//need to create a new one everytime this runs otherwise will not be able to update
		aliveIndexCounter = 0;
		numberOfCardsAlive = 0;

		//count how many are alive
		for (Monster playerCard : playerCards) {
			if (!playerCard.isDead()) {
				//need to ensure numberofcardalive being passed has size of array is actually correct
				numberOfCardsAlive++;
			}
		}

		//here i can create array with correct size
		cardsAlive = new Monster[numberOfCardsAlive];

		// Fill array with alive
		for (Monster playerCard : playerCards) {
			if (!playerCard.isDead()) {
				cardsAlive[aliveIndexCounter++] = playerCard;
			}
		}

	}

	public void updatePlayerCardsState(Monster currentMonster){
		if(currentMonster.isDead()){
			this.decreaseCardsAlive();
			this.updateAliveCards();
			if(this.hasNoCards()){
				this.setHasLost(true);
			}
		}
	}

	public void decreaseHealth(int damage){
		if(health - damage <= 0){
			health = 0;
			hasLost = true;
		}else{
			health -= damage;
		}
	}

	public void showAliveMonsters(){
		for(Monster monsterAlive : cardsAlive){
			System.out.println(monsterAlive.getId() + " " + monsterAlive.getName() + " has " + monsterAlive.getCurrentHealth() +
					" HP");
		}
	}


}
