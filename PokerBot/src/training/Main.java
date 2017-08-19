package training;
import java.sql.*;
import domain_model.*;

public class Main {

	public static void main(String args[]) {
		OddsBucket odds = new OddsBucket();
		odds.setName("SebastianAloneTest");
		odds.setDesc("Sebastian Test");
		odds.create();
	}
}
