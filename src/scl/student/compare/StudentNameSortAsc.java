package scl.student.compare;

import java.util.Comparator;

import scl.student.Student;

public class StudentNameSortAsc implements Comparator<Student> {

	@Override
	public int compare(Student first, Student second) {
		return first.getName().compareTo(second.getName());
	}

}
