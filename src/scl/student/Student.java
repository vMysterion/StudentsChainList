package scl.student;

public class Student implements Comparable<Student> {

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
	
	@Override
	public boolean equals(Object o) {
		Student that = (Student)o;
		if(this.mn == that.getMatriculationNumber()) return true;
		return false;
	}
	
	@Override
	public int compareTo(Student that) {
		return this.mn - that.getMatriculationNumber();
	}
	
}
