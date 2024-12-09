package game;

public interface Strikeable {

	//all strikeable will be able to be suffer damage and must therefore be able to update health
	void sufferHit(int damage);

	void updateCurrentHealth(int damage);
}
