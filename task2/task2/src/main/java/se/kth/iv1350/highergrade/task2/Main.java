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
     * The class adapted from Random using inheritance.
     *
     */
    public static class RandomFiftyFiftyInheritance extends Random {
        RandomFiftyFiftyInheritance(){
            super();
        }
        RandomFiftyFiftyInheritance(long seed){
            super(seed);
        }

        /**
        * The method to generate a boolean result, that 50 percent of it is true and 50 percent is false.
        * @return true/false A boolean that has 50 percent to be true and 50 percent to be false.
        */
        public boolean fiftyFifty() {
            return nextDouble() > 0.5;
        }
    }

    /**
     * The class adapted from Random using composition.
     *
     */
    public static class RandomFiftyFiftyComposition {
        private Random rand;

        RandomFiftyFiftyComposition(long seed) {
            this.rand = new Random(seed);
        }

        /**
        * The method to generate a boolean result, that 50 percent of it is true and 50 percent is false.
        * @return true/false A boolean that has 50 percent to be true and 50 percent to be false.
        */
        public boolean fiftyFifty() {
            return this.rand.nextDouble() > 0.5;
        }
    }
}