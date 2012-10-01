package coinFlipV1.gitmad.app;

import java.util.Random;

public class Coin {
	private String result;
	
	public Coin(){
		result = "not set";
	}

	public String getResult() {
		return result;
	}
	
	public String flip(){
		Random rand = new Random();
		Double flip = rand.nextDouble();

		if (flip < 0.5)
			this.result = "heads";
		else
			this.result = "tails";

		return this.result;
	}
}
