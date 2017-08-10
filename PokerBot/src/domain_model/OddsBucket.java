package domain_model;
import java.util.*;
																																																																																																																																																																							
public class OddsBucket {
	private String id;
	private String name;
	private String desc;
	
	public String getId() {return this.id;}
	public String getName() {return this.name;}
	public String getDesc() {return this.desc;}
	
	public void setId(String s) {this.id = s;}
	public void setName(String s) {this.name = s;}
	public void setDesc(String s) {this.desc = s;}
	
	public boolean save() {
		return false;
	}
	
	public Set<OddsBucket> retrieveAll() {																							
		return new TreeSet<OddsBucket>();
	}
	
	public boolean retrieve(String id) {
		return false;
	}
}
