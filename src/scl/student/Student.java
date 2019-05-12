package scl.student;

public class Student {

	private int mn;
	private String name;
	
	public Student(String name, int mn) {
		this.mn = mn;
		this.name = name;
	}
	
	public int getMatriculationNumber() {
		return this.mn;
	}
	
	public String getName() {
		return this.name;
	}
	
}
