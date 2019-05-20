package scl.run;

import scl.student.*;
import scl.writer.*;
import scl.reader.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private BinaryTree nameList;
	private BinaryTree numberList;
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
		
		nameList = new BinaryTree(data, false);
		numberList = new BinaryTree(data, true);
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
		case "SN": searchName();break;
		case "SM": searchNumber();break;
		case "N": insert(); break;
		case "S": save(); break;
		case "E": exit(); break;
		case "Z": printArray();break;
		}
	}
	
	
	//WARUM BALLERT DER NULL IN DIE ARRAYLIST?????
	private void searchName() {
		String name=null;
		boolean loop = true;
		while(loop) {
			System.out.print("Name: ");
			try {
				name = br.readLine();
				loop = false;
			} catch(IOException ioe) {
				System.out.println("Error reading input - "+ioe.getMessage());
			}
		}
		ArrayList<TreeElement> studenten = new ArrayList<TreeElement>();;
		TreeElement s=binaryNameSearch(numberList.getRoot(), name);
		studenten.add(s);
		loop = true;
		while(s!=null) {
				if(s.hasLeft()) {
					s=binaryNameSearch(s.getLeft(), name);
				}else if(s.hasRight()) {
					s=binaryNameSearch(s.getRight(), name);
				}else loop=false;
			
				studenten.add(s);
		}
		
		if(studenten.size()==0) {
			System.out.println("No student with name: "+name+" found");
		}else {
			for(TreeElement e: studenten) {
				if(e!=null) {
					printStudent(e.getContent());
				}
			}
		}
	}
	

	
	private void searchNumber() {
		int mNr=0;
		boolean loop = true;
		while(loop) {
			System.out.print("Matriculation Number: ");
			try {
				mNr = Integer.parseInt(br.readLine());
				loop = false;
			} catch(IOException ioe) {
				System.out.println("Error reading input - "+ioe.getMessage());
			} catch(NumberFormatException nfe) {
				System.out.println("Number must be an integer!");
			}
		}
		
		TreeElement s=binaryNumberSearch(numberList.getRoot(), mNr);
		if(s == null) {
			System.out.println("No student with mNR: "+mNr+" found");
		}else {
			printStudent(s.getContent());
		}
	}

	
	private TreeElement binaryNumberSearch(TreeElement next,int mNr) {
		if(next==null) {
			return null;
		}else if(mNr==next.getContent().getMatriculationNumber()) {
			return next;
		}else if(mNr<=next.getContent().getMatriculationNumber()) {
			return binaryNumberSearch(next.getLeft(),mNr);
		}else {
			return binaryNumberSearch(next.getRight(),mNr);
		}
	}
	
	private TreeElement binaryNameSearch(TreeElement next,String name) {
		if(next==null) {
			return null;
		}else if(next.getContent().getName().compareTo(name)==0) {
			return next;
		}else if(next.getContent().getName().compareTo(name)==-1) {
			return binaryNameSearch(next.getLeft(),name);
		}else {
			return binaryNameSearch(next.getRight(),name);
		}
	}
	
	
	private void printArray() {
		for(Student s :data) {
			printStudent(s);
		}
	}

	private void empty() {
		numberList.empty();
		nameList.empty();
		data=new Student[0];
	}
	
	private boolean printTree(TreeElement next) {
		TreeElement left = next.getLeft();
		TreeElement right = next.getRight();
		boolean returned = false;
		boolean printed = false;
		
		if(next.hasLeft()) {
			 returned = printTree(left);
		} else {
			printStudent(next.getContent());
			printed = true;
		}
		
		if(returned & !printed) {
			printStudent(next.getContent());
		} 
		
		if(next.hasRight()) {
			printTree(right);
		}
		
		return true;
	}
	
	private void sortName() {
		printTree(nameList.getRoot());
	}
	
	private void sortMN() {
		printTree(numberList.getRoot());
	}
	
	
	private void printStudent(Student student) {
		if(student==null) {
			System.out.println("No student found");
		}else {
			System.out.println(student.getName()+" - "+student.getMatriculationNumber());
		}
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
	
	private void removeFromArray(Student student) {
		for(int i=0;i<data.length;i++) {
			if(data[i].equals(student)) {
				data[i] = data[data.length-1];
			}
		}
		Student[] ndata = new Student[data.length-1];
		for(int i=0;i<data.length;i++) {
			ndata[i] = data[i];
		}
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
