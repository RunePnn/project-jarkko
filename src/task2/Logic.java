package task2;
import java.util.Random;

import task2.Weapon;

public class Logic {
	private Weapon playerWeapon;
	private Weapon comWeapon;
	private Score playerScore;
	private Score comScore;
	private Random rng;
	private boolean play;
	
	
	public Logic() {
		super();
		this.playerWeapon = Weapon.EMPTY;
		this.comWeapon = Weapon.EMPTY;
		this.playerScore = new Score();
		this.comScore = new Score();
		this.rng = new Random();
		this.play = true;
	}
	
	public void getInput (int keycodeInput) {
		switch (keycodeInput) {
		case 16:
			this.setPlayerWeapon(Weapon.KIVI);
			break;
		case 2:
			this.setPlayerWeapon(Weapon.SAKSET);
			break;
		case 8:
			this.setPlayerWeapon(Weapon.PAPERI);
			break;
		case 4:
			this.setPlayerWeapon(Weapon.EMPTY);
			this.resetScore();
			break;
		case 32:
			this.setPlayerWeapon(Weapon.EMPTY);
			this.play = false;
			break;
		default:
			this.setPlayerWeapon(Weapon.EMPTY);
			break;
		}
	}
	
	
	
	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public void setPlayerWeapon (Weapon weapon) {
		this.playerWeapon = weapon;
	}
	
	public Weapon getComWeapon() {
		return comWeapon;
	}

	public Weapon getPlayerWeapon() {
		return playerWeapon;
	}

	public void setComWeapon () {
		int random = this.rng.nextInt(3);
		
		switch (random) {
			case 0:
				this.comWeapon = Weapon.KIVI;
				break;
			case 1:
				this.comWeapon = Weapon.SAKSET;
				break;
			case 2:
				this.comWeapon = Weapon.PAPERI;
				break;
			default:
				this.comWeapon = Weapon.EMPTY;
				break;
		}
	}
	
	
	public int play () {
		this.setComWeapon();
		if (this.playerWeapon == Weapon.EMPTY || this.comWeapon == Weapon.EMPTY) {
			return -1;
		}
		
		if (this.getPlayerWeapon() == this.getComWeapon()){
			return 2;
		} else if (this.getPlayerWeapon() == Weapon.KIVI) {
			if (this.getComWeapon() == Weapon.SAKSET) {
				return 1;
			} else if (this.getComWeapon() == Weapon.PAPERI) {
				return 0;
			}
		} else if (this.getPlayerWeapon() == Weapon.SAKSET) {
			if (this.getComWeapon() == Weapon.KIVI) {
				return 0;
			} else if (this.getComWeapon() == Weapon.PAPERI) {
				return 1;
			}
		} else if (this.getPlayerWeapon() == Weapon.PAPERI) {
			if (this.getComWeapon() == Weapon.KIVI) {
				return 1;
			} else if (this.getComWeapon() == Weapon.SAKSET) {
				return 0;
			}
		}
		
		return -1;
	}
	
	public void resetScore() {
		this.playerScore.resetScore();
		this.comScore.resetScore();
	}
	
	public Score getPlayerScore() {
		return this.playerScore;
	}
	
	public Score getComScore() {
		return this.comScore;
	}
}
