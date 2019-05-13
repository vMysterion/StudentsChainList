package scl.student;

public class ListElement {

	private ListElement next;
	private Student content;
	
	public ListElement(Student content, ListElement next) {
		this.content = content;
		this.next = next;
	}
	
	public ListElement() {
	}
	
	public ListElement getNext() {
		return next;
	}
	
	public Student getContent() {
		return content;
	}
	
	public void setContent(Student content) {
		this.content = content;
	}
	
	public void setNext(ListElement next) {
		this.next = next;
	}
}
