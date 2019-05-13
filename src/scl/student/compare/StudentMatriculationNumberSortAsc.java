package scl.student.compare;

import java.util.Comparator;

import scl.student.Student;

public class StudentMatriculationNumberSortAsc implements Comparator<Student> {

	@Override
	public int compare(Student first, Student second) {
		return first.getMatriculationNumber() - second.getMatriculationNumber();
	}
	
}
