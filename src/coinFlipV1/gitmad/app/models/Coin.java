package coinFlipV1.gitmad.app.models;

import java.util.Random;

/**
 * Model to encapsulate the coin
 */
public class Coin {
    public static boolean flip(){
        Random rand = new Random();
        Double flip = rand.nextDouble();

        if (flip < 0.5) {
            return true;
        }
        else {
            return false;
        }
    }
}
