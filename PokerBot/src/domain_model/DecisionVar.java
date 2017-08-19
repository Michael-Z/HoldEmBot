package domain_model;

import java.sql.*;

/*
 * This class specifies DecisionVar as an entity that can be
 * saved into and retrieved from a sql database
 */

public abstract class DecisionVar {
	protected int id;
	protected int decisionVarId;
	protected Connection conn;
	protected Statement stat;
	
	public int getId() {return this.id;}
	public int getDecisionVarId() {return this.decisionVarId;}
	
	public void setId(int i) {this.id = i;}
	public void setDecisionVarId(int i) {this.decisionVarId = i;}

	
	protected void createConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/Peralta/PokerBot.db");
		    stat = conn.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	
	public abstract boolean create();
	
	public abstract boolean update(int id);
	
	public abstract boolean read(int id);
	
	public abstract boolean delete(int id);
	
	public boolean equals(DecisionVar dv) {
		return this.decisionVarId == dv.decisionVarId;
	}
}
