package scl.student;

public class ChainList<T> {
	
	private ListElement<T> head;
	private ListElement<T> tail;
	private ListElement<T> current;
	
	public ChainList(T[] data) {
		for(int i=0;i<data.length;i++) {
			ListElement<T> element = new ListElement<T>();
			element.setContent(data[i]);
			element.setNext(null);
			if(i==0) {
				this.head = element;
			}
			current = element;
			if(i!=data.length-1 && i!=0) {
				current.setNext(element);
			}
			if(1==data.length-1) {
				this.tail = element;
			}
		}
	}
	
	public T next() {
		current = current.getNext();
		return current.getContent();
	}
	
	public void reset() {
		this.current = this.head;
	}

	public int size() {
		int size=0;
		while(this.hasNext()) {
			current = current.getNext();
			size++;
		}
		return size;
	}
	
	public boolean hasNext() {
		if(current.getNext() != null) return true;
		return false;
	}
	
}
