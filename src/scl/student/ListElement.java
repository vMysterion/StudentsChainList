package scl.student;

public class ListElement<T> {

	private ListElement<T> next;
	private T content;
	
	public ListElement(T content, ListElement<T> next) {
		this.content = content;
		this.next = next;
	}
	
	public ListElement() {
	}
	
	public ListElement<T> getNext() {
		return next;
	}
	
	public T getContent() {
		return content;
	}
	
	public void setContent(T content) {
		this.content = content;
	}
	
	public void setNext(ListElement<T> next) {
		this.next = next;
	}
}
