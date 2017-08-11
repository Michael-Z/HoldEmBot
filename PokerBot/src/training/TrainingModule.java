package training;
import decision_engine.*;
import java.util.*;

public class TrainingModule {
	
	public static List<Strategy> createNewGen(int numStrat) {
		
		for (int i = 0; i < numStrat; i++) {
		}
		return new LinkedList<Strategy>();
	}
	
	public static List<Strategy> applyPressure(List<Strategy> gen) {
		//simHand(gen);
		//^from poker engine
		//returns the fittest strategies
		return gen;
	}
	
	public static Strategy randomCrossOver(Strategy s1, Strategy s2) {
		//TODO randomly select half of decisions from s1 and combine with s2
		return s1;
	}
	
	
	public static List<Strategy> crossOver(List<Strategy> fittestStrats, int numNewGen) {
		List<Strategy> newGen = new LinkedList<Strategy>();
	
		for (int i = 0; i < numNewGen; i++) {
			int j = (int) Math.floor(Math.random() * 4);
			int k = j;
			while (k == j) {
				k = (int) Math.floor(Math.random() * 4);
			}
			newGen.add(randomCrossOver(fittestStrats.get(j), fittestStrats.get(k)));	
		}
		return newGen;
	}
}
