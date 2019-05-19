package scl.run;

import scl.student.*;
import scl.writer.*;
import scl.reader.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private String menu =""
			+ "Students Chain List\n"
			+ "L  - Empty the list\n"
			+ "ZN - Sort by name and print list\n"
			+ "ZM - Sort by matriculation number and print list\n"
			+ "SN - Search by name\n"
            + "SM - Search by matriculation number \n"
            + "N  - Insert student\n"
            + "S  - Save data\n"
            + "E  - End programm\n";
	
	private BufferedReader br;
	private Student[] data;
	private ChainList nameList;
	private ChainList numberList;
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
			System.out.println("Error creating reader - "+e1.getMessage());
		} catch (IOException e) {
			System.out.println("Error creating writer - "+e.getMessage());
		}
		try {
			data = sr.readFile();
		} catch (IOException e) {
			System.out.println("Error reading data from file - "+e.getMessage());
			data = new Student[0];
		} catch(IllegalArgumentException iae) {
			System.out.println("Error importing data from file - "+iae.getMessage()+"\nQuitting Program");
			System.exit(0);
		}
		
		nameList = new ChainList(data, false);
		numberList = new ChainList(data, true);
	}
	
	public static void main(String[] args) {
		new Main().run();
	}
	
	public void run() {
		while(loop) {
			nameList.reset();
			numberList.reset();
			System.out.println(menu);
			System.out.print(">>> ");
			String input = null;
			try {
				input = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			execute(input);
		}
	}
	
	private void execute(String command) {
		switch(command.toUpperCase()) {
		case "L": empty(); break;
		case "ZN": sortName(); break;
		case "ZM": sortMN(); break;
		case "SN": searchName(); break;
		case "SM": searchMN(); break;
		case "N": insert(); break;
		case "S": save(); break;
		case "E": exit(); break;
		}
	}
	
	private void empty() {
		numberList.empty();
		nameList.empty();
		data=new Student[0];
	}
	
	private void printList(ChainList cl) {
		System.out.println("\n- - - Studentes - - -");
		while(cl.hasNext()) {
			Student current = cl.next();
			System.out.println(current.getName()+" - "+current.getMatriculationNumber());
		}
		System.out.println();
		cl.reset();
	}
	
	private void sortName() {
		printList(nameList);
	}
	
	private void sortMN() {
		printList(numberList);
	}
	
	private void searchName() {
		System.out.print("\nName: ");
		String sName="";
		try {
			sName=br.readLine();

		} catch(IOException ioe) {
			System.out.println("Error reading input - "+ioe.getMessage());
		}
		List<Student> students = search(sName);
		if(students.isEmpty()) {
			System.out.println("No Students found.");
			return;
		}
		for(int i=0;i<students.size();i++) {
			printStudent(students.get(i));
		}
		nameList.reset();
		System.out.println();
	}
	
	private void printStudent(Student student) {
		if(student==null) {
			System.out.println("No student found");
		}else {
			System.out.println(student.getName()+" - "+student.getMatriculationNumber());
		}
	}
	
	private void searchMN() {
		System.out.print("\nMatriculation number: ");
		String mn="";
		int mNumber=0;
		try {
			mn=br.readLine();
			mNumber=Integer.parseInt(mn);
		} catch(IOException ioe) {
			System.out.println("Error reading input - "+ioe.getMessage());
		} catch(NumberFormatException nfe) {
			System.out.println("Number must be an integer!");
		}
		Student student = search(mNumber);
		if(student == null) {
			System.out.println("No Students found.");
			return;
		}
		printStudent(student);
		nameList.reset();
		System.out.println();
	}
	
	private Student search(int mNumber) {
		while(nameList.hasNext()) {
			Student current = nameList.next();
			if(current.getMatriculationNumber()==mNumber) {
				return current;
			}
		}
		nameList.reset();
		return null;
	}
	
	private List<Student> search(String Name) {
		List<Student> students = new ArrayList<Student>();
		while(nameList.hasNext()) {
			Student current = nameList.next();
			if(current.getName().toLowerCase().equals(Name.toLowerCase())) {
				students.add(current);
			}
		}
		nameList.reset();
		return students;
	}
	
	
	private void insert() {
		String name = "";
		String mn = "";
		int number = 0;
		boolean loop = true;
		while(loop) {
			System.out.print("\nName: ");
			try {
				name = br.readLine();
				loop = false;
			} catch(IOException ioe) {
				System.out.println("Error reading input - "+ioe.getMessage());
			}
		}
		loop = true;
		while(loop) {
			System.out.print("Matriculation Number: ");
			try {
				mn = br.readLine();
				number = Integer.parseInt(mn);
				if(!(checkMnumber(number))) {
					System.out.print("matriculation number already exists\n");	
				}else {
				
				loop = false;}
			
			} catch(IOException ioe) {
				System.out.println("Error reading input - "+ioe.getMessage());
			} catch(NumberFormatException nfe) {
				System.out.println("Number must be an integer!");
			}
		}
		System.out.println();
		Student student = new Student(name, number);
		addToArray(student);
		nameList.add(student);
		numberList.add(student);
	}
	
	private void addToArray(Student student) {
		Student[] ndata = new Student[data.length+1];
		for(int i=0;i<data.length;i++) {
			ndata[i] = data[i];
		}
		ndata[ndata.length-1] = student;
		data = ndata;
	}
		
	
	private boolean checkMnumber(int mn) {
		for(int i=0;i<data.length;i++) {
			if(data[i].getMatriculationNumber()==mn) {
				return false;
			}
		}
		return true;
	}
	
	private void save() {
		try {
			sw.writeData(data);
		} catch (IOException e) {
			System.out.println("Error writing to file - "+e.getMessage());
		}
	}
	
	private void exit() {
		loop = false;
	}
}
