package utils;
/*This enum is used by the decision tree to specify which
 * type of variable it needs to match against in order to 
 * iterate through the tree
 */
public enum VarType {
	STAGE, ODDS, TOTALNUMPLAYERS, POSITION, BLINDSLEFT,
	POTSIZE, PERSONALITIESLEFT, BETSIZE, PREF,
	FLOP, TURN, RIVER, PLAYERISIN
}
