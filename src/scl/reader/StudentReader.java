package scl.reader;

import java.io.*;
import java.util.*;
import scl.student.Student;

public class StudentReader {

	private File file;
	private BufferedReader br;
	
	public StudentReader() throws FileNotFoundException, IllegalArgumentException {
		file = new File("students/students.txt");
		br = new BufferedReader(new FileReader(file));
	}
	
	public Student[] readFile() throws IOException {
		List<String> names = new ArrayList<String>();
		List<Integer> numbers = new ArrayList<Integer>();
		while(br.ready()) {
			String in = br.readLine();
			String[] token = in.split(",");
			int mn = 0;
			try {
				mn = Integer.parseInt(token[1]);
			} catch(NumberFormatException nfe) {
				
			}
			names.add(token[0]);
			numbers.add(mn);
		}
		Student[] students = new Student[names.size()];
		for(int i=0;i<students.length;i++) {
			Student student = new Student(names.get(i), numbers.get(i));
			for(int j=0;j<i;j++) {
				if(students[j].equals(student)) throw new IllegalArgumentException("Student in save file with Matriculation number "+student.getMatriculationNumber()+" exists multiple times.");
			}
			students[i] = student;
		}
		
		return students;
	}
	
}
