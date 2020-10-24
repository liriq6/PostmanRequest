package Client;

import java.util.Random;

public class RandomController {
    public int randomInt(int i) {

        Random random = new Random();
        int upperbound = i;
        int int_random = random.nextInt(upperbound)+10;
        return int_random;
    }

}
