package scl.student.compare;

import java.util.Comparator;

import scl.student.Student;

public class StudentMatriculationNumberSortDsc implements Comparator<Student> {

	@Override
	public int compare(Student first, Student second) {
		return second.getMatriculationNumber() - first.getMatriculationNumber();
	}
	
}
