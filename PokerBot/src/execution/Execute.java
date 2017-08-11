package execution;
import domain_model.*;
import decision_engine.*;

public class Execute {


//loop:
	//run data_acquisition
	//figure out current state
	//use inputs from domain_model, goes through strategy
	//act
	public static void main(String[] args) {
		//State s = acquireData();
		State s = new State();
		//DecisionTree dt = retrieveFromDataBase(DecisionTree);
		//OR.... Strategy str = retrieveFromDataBase(Strategy);
		DecisionTree dt = new DecisionTree(s);
		Strategy str = new Strategy(dt);
		boolean isPrinted = false;
		while (true) {
			s.update();
			if (s.isTurnToAct() && !isPrinted) {
				try {
					System.out.println(str.decide());
				} catch (Exception e) {
					System.out.println("Decision error");
				}
				isPrinted = true;
			}
		}
	}
}
