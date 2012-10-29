package coinFlipV1.gitmad.app.models;

import java.util.Random;

/**
 * Model to encapsulate the coin
 */
public class Coin {
    private boolean result;

    public Coin(){
        result = false;
    }

    public Coin(boolean result) {
        this.result = result;
    }

    public boolean flip(){
        Random rand = new Random();
        Double flip = rand.nextDouble();

        if (flip < 0.5)
            this.result = true;
        else
            this.result = false;

        return this.result;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
