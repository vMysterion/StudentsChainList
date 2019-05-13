package scl.run;

public class Main {
	
	String menu =""
			+ "Students Chain List\n"
			+ "L  - Empty the list\n"
			+ "ZN - Sort by name and print list\n"
			+ "ZM - Sort by matriculation number and print list\n"
			+ "SN - Search by name\n"
            + "SM - Search by matriculation number \n"
            + "N  - Insert student\n"
            + "LN - Remove student by name\n"
            + "LM - Remove student by matriculation number\n"
            + "MN - Change name\n"
            + "MM - Change matriculation number\n"
            + "S  - Save data\n"
            + "E  - End programm\n";
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		System.out.println(menu);
	}
	
}
