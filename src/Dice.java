import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int dicecount ;
    int min=1;
    int max=6;

    public Dice(int dicecount) {
        this.dicecount = dicecount;
    }

    public int rolldice()
    {
        int totalsum =0;
        int diceused=0;
        while(diceused<dicecount)
        {
           totalsum += ThreadLocalRandom.current().nextInt(min, max+1);
           diceused++;
        }

        return totalsum;
    }
}
