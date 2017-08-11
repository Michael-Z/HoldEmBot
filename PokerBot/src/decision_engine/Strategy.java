package decision_engine;
import domain_model.State;
import utils.*;

public class Strategy {
	private DecisionTree dt;
	private int profit;
	public Strategy(DecisionTree dt) {
		this.dt = dt;
	}
	
	public int profit() {return this.profit;}
	
	public Action decide() throws Exception{
			return dt.decide();
	}
	//TODO should have decision tree, randomizer, mutator, combiner
}
