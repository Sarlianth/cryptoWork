package ie.gmit.sw.ai;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class SimulatedAnnealing {
	
	private SecureRandom r;
	private Playfair pf;
	private Grams g;
 	
	private Key key;
	
	private int temperature;
	private int transitions;
	
	private HashMap<String, Double> nGrams; 
	
	public SimulatedAnnealing(int temperature, int transitions, String cipherText) {
		super();
		r = new SecureRandom();
		this.g = new Grams("4grams.txt");
		this.pf = new Playfair();
		this.pf.setCipherText(cipherText);
		this.key = Key.keyInstance();
		this.temperature = temperature;
		this.transitions = transitions;

	}// construct
	
	public double annealing(String cipherText) throws Throwable {		
		
		nGrams =  (HashMap<String, Double>) g.loadNGrams();		// load our quad grams 
		String parent = key.generateKey();						// generate our key
		String decryptedText = pf.decrypt(parent);				// decrypt text using said key
		double parentScore = g.scoreText(decryptedText);		// score the decrypted text
		double bestScore = parentScore;							// set the preliminary best score
		//System.out.println(bestScore);
		double probability;
		Random rand = new Random();
		
		for(int temp = temperature; temp > 0; temp -= 1) {
			for (int index = transitions; index >= 0; index--) {
				String child = key.shuffleKey(parent);			//  Change the parent key slightly to get child key, 
				decryptedText = pf.decrypt(child);				// decrypt with the child key
				double childScore = g.scoreText(decryptedText);	// Measure the fitness of the deciphered text using the child key	
				double delta = childScore - parentScore;		// get the delta 	
				if(delta >= 0) {								// if the delta is over 0 this key is better
					parent = child;
					parentScore = childScore;

				} else  {
					probability = Math.exp(delta/temp);
					//System.out.println(probability);
					//if(probability > ((rand.nextInt(32767)+1) / 32767)) { // prevent getting stuck
					if(probability > 0.01) {
						parent = child;
						parentScore = childScore;
					}
				}
			
				if(parentScore > bestScore) {
					bestScore = parentScore;
					String bestKey = parent;
					System.out.printf("\nTransition: %d at Temp: %d\nBest Score: %f0.3\tFor Key: %s\nDecrypted message: %s\n", index, temp, bestScore, bestKey, decryptedText);
					if(bestScore >= 550) break;
				}//if p > b	
			}//transitions
			//System.out.println(temp);
			
		}//tempurature
		
		return bestScore;
	}// annealing
	
	
}


