package scl.run;

import scl.student.*;
import scl.writer.*;
import scl.reader.*;
import java.io.*;

public class Main {
	
	private String menu =""
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
	
	private BufferedReader br;
	private Student[] data;
	private StudentReader sr;
	private StudentWriter sw;
	private boolean loop;
	
	public Main() {
		loop = true;
		br = new BufferedReader(new InputStreamReader(System.in));
		try {
			sr = new StudentReader();
			sw = new StudentWriter();
		} catch (FileNotFoundException e1) {
			System.out.println("Error creating reader - "+e.getMessage());
		} catch (IOException e) {
			System.out.println("Error creating writer - "+e.getMessage());
		}
		try {
			data = sr.readFile();
		} catch (IOException e) {
			System.out.println("Error reading data from file - "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		while(loop) {
			System.out.println(menu);
			System.out.print(">>> ");
			String input = null;
			try {
				input = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			execute(input);
		}
	}
	
	private void execute(String command) {
		switch(command) {
		case "L": empty(); break;
		case "ZN": sortName(); break;
		case "ZM": sortMN(); break;
		case "SN": searchName(); break;
		case "SM": searchMN(); break;
		case "N": insert(); break;
		case "LN": removeName(); break;
		case "LM": removeMN(); break;
		case "MN": changeName(); break;
		case "MM": changeMN(); break;
		case "S": save(); break;
		case "E": exit(); break;
		}
	}
	
	private void empty() {
		
	}
	
	private void sortName() {
		
	}
	
	private void sortMN() {
		
	}
	
	private void searchName() {
		
	}
	
	private void searchMN() {
		
	}
	
	private void insert() {
		
	}
	
	private void removeName() {
		
	}
	
	private void removeMN() {
		
	}
	
	private void changeName() {
		
	}
	
	private void changeMN() {
		
	}
	
	private void save() {
		
	}
	
	private void exit() {
		loop = false;
	}
}
