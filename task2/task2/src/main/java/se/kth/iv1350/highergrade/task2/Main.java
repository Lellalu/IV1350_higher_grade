package se.kth.iv1350.highergrade.task2;

import java.util.Random;

/**
 * The main class to execute the inheritance class and the composition class.
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        long seed = 42;
        RandomFiftyFiftyInheritance inheritance = new RandomFiftyFiftyInheritance(seed);
        RandomFiftyFiftyComposition composition = new RandomFiftyFiftyComposition(seed);

        if (inheritance.fiftyFifty()) {
            System.out.println("RandomFiftyFiftyInheritance generated true");
        }

        if (composition.fiftyFifty()) {
            System.out.println("RandomFiftyFiftyComposition generated true");
        }
    }

    /**
     * The RandomFiftyFiftyInheritance class to execute the inheritance class and the composition class.
     *
     */
    public static class RandomFiftyFiftyInheritance extends Random {
        RandomFiftyFiftyInheritance(){
            super();
        }
        RandomFiftyFiftyInheritance(long seed){
            super(seed);
        }

        public boolean fiftyFifty() {
            return nextDouble() > 0.5;
        }
    }

    public static class RandomFiftyFiftyComposition {
        private Random rand;

        RandomFiftyFiftyComposition(long seed) {
            this.rand = new Random(seed);
        }

        public boolean fiftyFifty() {
            return this.rand.nextDouble() > 0.5;
        }
    }
}