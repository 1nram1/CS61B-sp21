package gh2;

// TODO: uncomment the following import once you're ready to start this portion
import deque.Deque;
// TODO: maybe more imports
import deque.ArrayDeque;
import deque.LinkListDeque;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdAudio;


//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {

        int capacity = (int) Math.round( SR / frequency) ;
        buffer=new LinkListDeque<Double>();
            for(int i =0 ; i< capacity ;i++) {
                buffer.addLast(0.0);
            }

    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {


        int size = buffer.size();
        for (int i = 0 ; i< size ;i++) {
            buffer.removeFirst();
            buffer.addLast(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double removeitem = buffer.removeFirst();
        double newdouble = (removeitem + buffer.get(0)) / 2 * DECAY;
        buffer.addLast(newdouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.get(0) ;
    }
}

