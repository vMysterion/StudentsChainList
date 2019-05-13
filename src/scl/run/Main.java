package scl.run;

public class Main {
	
	String menu =""
			+ "Students Chain List\n"
			+ "L  - Empty the list\n"
			+ "ZN - Sort by name and print list\n"
			+ "ZM - Sort by matriculation number and print list\n"
			+ "";
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		System.out.println(menu);
	}
	
}
