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
		}
		
		nameList = new ChainList(data, false);
		numberList = new ChainList(data, true);
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
		printStudent(search(sName));
		nameList.reset();
		System.out.println();
	}
	
	private void printStudent(Student student) {
		if(student==null) {
			System.out.println("No student found");
		}else {
			System.out.println(student.getName()+" - "+student.getMatriculationNumber());
		}
		System.out.println();
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
		printStudent(search(mNumber));
		nameList.reset();
	}
	
	/* sortiert zn nach lowercase uppercase?
	 * man könnte mat nummer einlesen auslager ist in 3+methoden
	 * evtl. in chainlist rein?
	 */
	private Student search(int mNumber) {
		while(nameList.hasNext()) {
			Student current = nameList.next();
			if(current.getMatriculationNumber()==mNumber) {
				return current;
			}
		}
		return null;
	}
	
	private Student search(String Name) {
		while(nameList.hasNext()) {
			Student current = nameList.next();
			if(current.getName().toLowerCase().equals(Name.toLowerCase())) {
				return current;
			}
		}
		return null;
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
				loop = false;
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
	
	private void removeName() {
		String name="";
		System.out.print("Name: ");
		try {
			name=br.readLine();
		} catch(IOException ioe1) {
			System.out.println("Error reading input - "+ioe1.getMessage());
		}
		Student student = search(name);
		nameList.reset();
		if(student==null) {
			System.out.println("No student found");
		}else {
			nameList.remove(name);
			numberList.remove(name);
			nameList.reset();
			numberList.reset();
			System.out.println("Student removed\n");
		}
		
	}
	
	private void removeMN() {
		int mNumber=0;
		System.out.print("Matriculation number: ");
		try {
			mNumber=Integer.parseInt(br.readLine());
		} catch(IOException ioe1) {
			System.out.println("Error reading input - "+ioe1.getMessage());
		}catch(NumberFormatException nfe) {
			System.out.println("Number must be an integer!");
		}
		Student student = search(mNumber);
		nameList.reset();
		if(student==null) {
			System.out.println("No student found");
		}else {
			nameList.remove(mNumber);
			numberList.remove(mNumber);
			nameList.reset();
			numberList.reset();
			System.out.println("Student removed\n");
		}
	}
	
	private void changeName() {
		String input="";
		Student student=null;
		boolean loop = true;
		while(loop) {
			System.out.print("Choose student:\n"
								+"n - by name\n"
								+"m - by matriculation number\n"
								+">>> ");
			try {
				input = br.readLine();
			} catch(IOException ioe) {
				System.out.println("Error reading input - "+ioe.getStackTrace());
				System.out.println("Error reading input - "+ioe.getMessage());
			}
			if(input.toLowerCase().equals("n")) {	
				loop=false;
				String name="";
				System.out.print("Name: ");
				try {
					name=br.readLine();
				} catch(IOException ioe1) {
					System.out.println("Error reading input - "+ioe1.getMessage());
				} 
				student = search(name);
				nameList.reset();
				if(student==null) {
					System.out.println("No student found");
				}else {
					System.out.print("New name: ");
					try {
						String newName=br.readLine();
						int mn=student.getMatriculationNumber();
						nameList.remove(name);
					//	nameList.reset();
						nameList.add(new Student(newName,mn));
						numberList.remove(name);
					//	numberList.reset();
						numberList.add(new Student(newName,mn));
				
					} catch(IOException ioe2) {
						System.out.println("Error reading input - "+ioe2.getMessage());
					}	
				}
			
			}else if(input.toLowerCase().equals("m")) {
				loop=false;
				String mn="";
				int mNumber=0;
				System.out.print("Matriculation number: ");
				try {
					mn=br.readLine();
					mNumber=Integer.parseInt(mn);
				} catch(IOException ioe1) {
					System.out.println("Error reading input - "+ioe1.getMessage());
				} catch(NumberFormatException nfe) {
					System.out.println("Number must be an integer!");
				}
				student = search(mNumber);
				nameList.reset();
				if(student==null) {
					System.out.println("No student found");
				}else {
					System.out.println("New name: ");
					try {
						String newName=br.readLine();
						nameList.remove(mNumber);
						nameList.add(new Student(newName,mNumber));
						numberList.remove(mNumber);
						numberList.add(new Student(newName,mNumber));
					} catch(IOException ioe2) {
						System.out.println("Error reading input - "+ioe2.getMessage());
					}	
				}
			}else {
				System.out.println("Error reading input");
			}
		}
		}	
		
	
	
	private void changeMN() {
		String input="";
		Student student=null;
		boolean loop = true;
		while(loop) {
			System.out.print("Choose student:\n"
								+"n - by name\n"
								+"m - by matriculation number\n"
								+">>> ");
			try {
				input = br.readLine();
			} catch(IOException ioe) {
				System.out.println("Error reading input - "+ioe.getStackTrace());
				System.out.println("Error reading input - "+ioe.getMessage());
			}
			if(input.toLowerCase().equals("n")) {	
				loop=false;
				String name="";
				System.out.print("Name: ");
				try {
					name=br.readLine();
				} catch(IOException ioe1) {
					System.out.println("Error reading input - "+ioe1.getMessage());
				} 
				student = search(name);
				nameList.reset();
				if(student==null) {
					System.out.println("No student found");
				}else {
					System.out.print("New matriculation number: ");
					try {
						int newMN=Integer.parseInt(br.readLine());
						String oldName=student.getName();
						numberList.remove(name);
						numberList.add(new Student(oldName,newMN));
					} catch(IOException ioe2) {
						System.out.println("Error reading input - "+ioe2.getMessage());
					}catch(NumberFormatException nfe) {
						System.out.println("Number must be an integer!");
					}	
				}
			
			}else if(input.toLowerCase().equals("m")) {
				loop=false;
				String mn="";
				int mNumber=0;
				System.out.print("Matriculation number: ");
				try {
					mn=br.readLine();
					mNumber=Integer.parseInt(mn);
				} catch(IOException ioe1) {
					System.out.println("Error reading input - "+ioe1.getMessage());
				} catch(NumberFormatException nfe) {
					System.out.println("Number must be an integer!");
				}
				student = search(mNumber);
				nameList.reset();
				if(student==null) {
					System.out.println("No student found");
				}else {
					System.out.print("New matriculation number:");
					try {
						int newMN=Integer.parseInt(br.readLine());
						String oldName=student.getName();
						numberList.remove(mNumber);
						numberList.add(new Student(oldName,newMN));
						
					} catch(IOException ioe2) {
						System.out.println("Error reading input - "+ioe2.getMessage());
					}catch(NumberFormatException nfe) {
						System.out.println("Number must be an integer!");
					}	
				}
			}else {
				System.out.println("Error reading input");
			}
		}
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
