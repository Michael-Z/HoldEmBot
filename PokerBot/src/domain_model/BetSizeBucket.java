package domain_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BetSizeBucket extends DecisionVar{
	private String name;
	private String desc;
	public BetSizeBucket() {
		super();
	}
	public boolean create() {
		try {
		  createConnection();
          String statement = "insert into OddsBucket(Name, Description) values ('" + name + "', '" + desc + "');";
          stat.executeUpdate(statement);
          stat.close();
          //conn.commit();
          conn.close();
//        stat.executeUpdate("create table OddsBucket (name, description);");
//        PreparedStatement prep = conn.prepareStatement("insert into OddsBucket(Name, Description) values ('Odds 1', 'Odds 1 Desc');");
      } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
      }
      return false;

	}
	
	public boolean update(int id) {
		try {
	          Class.forName("org.sqlite.JDBC");
	          Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/Peralta/PokerBot.db");
	          Statement stat = conn.createStatement();
	          String statement = "update OddsBucket set Name = '" + name + "', Description = '" + desc + "' where Id = " + id + ";";
	          stat.executeUpdate(statement);
	          stat.close();
	          //conn.commit();
	          conn.close();
//	        stat.executeUpdate("create table OddsBucket (name, description);");
//	        PreparedStatement prep = conn.prepareStatement("insert into OddsBucket(Name, Description) values ('Odds 1', 'Odds 1 Desc');");
	      } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	      }
	      return false;
	}
	public boolean read(int id) {
		try {
	          Class.forName("org.sqlite.JDBC");
	          Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/Peralta/PokerBot.db");
	          Statement stat = conn.createStatement();
	          String statement = "select * from OddsBucket where Id = " + id + ";";
	          ResultSet rs = stat.executeQuery(statement);
	          while ( rs.next() ) {
	              id = rs.getInt("Id");
	            //  name = rs.getString("Name");
	            // desc = rs.getString("Description");
	           }
	           rs.close();
	          stat.close();
	          //conn.commit(); 
	          conn.close();
//	        stat.executeUpdate("create table OddsBucket (name, description);");
//	        PreparedStatement prep = conn.prepareStatement("insert into OddsBucket(Name, Description) values ('Odds 1', 'Odds 1 Desc');");
	      } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	      }
	      return false;
	}
	
	public boolean delete(int id) {
		try {
	          Class.forName("org.sqlite.JDBC");
	          Connection conn = DriverManager.getConnection("jdbc:sqlite:/Users/Peralta/PokerBot.db");
	          Statement stat = conn.createStatement();
	          String statement = "delete from OddsBucket where Id = " + id + ";";
	          stat.executeUpdate(statement);
	          stat.close();
	          //conn.commit(); 
	          conn.close();
//	        stat.executeUpdate("create table OddsBucket (name, description);");
//	        PreparedStatement prep = conn.prepareStatement("insert into OddsBucket(Name, Description) values ('Odds 1', 'Odds 1 Desc');");
	      } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	      }
	      return false;
	}
}
