package scl.writer;

import java.io.*;

import scl.student.Student;

public class StudentWriter {

	private File file;
	private PrintWriter pw;
	
	public StudentWriter() throws IOException {
		file = new File("students/students.txt");
		if(!file.exists()) file.createNewFile();
		pw = new PrintWriter(new FileWriter(file), true);
	}
	
	public void writeData(Student[] data) {
		for(int i=0;i<data.length;i++) {
			pw.println(data[i].getName()+","+data[i].getMatriculationNumber());
		}
		pw.close();
	}
	
}
