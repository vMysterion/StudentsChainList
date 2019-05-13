package scl.student;

public class ChainList<T> {
	
	private ListElement<T> head;
	private ListElement<T> tail;
	private ListElement<T> current;
	
	public ChainList(T[] data) {
		create(data);
	}
	
	public void create(T[] data) {
		for(int i=0;i<data.length;i++) {
			ListElement<T> element = new ListElement<T>();
			element.setContent(data[i]);
			element.setNext(null);
			if(i==0) {
				this.head = element;
			}
			if(i!=0) {
				current.setNext(element);
			}
			if(i==data.length-1) {
				this.tail = element;
			}
			current = element;
		}
		reset();
	}
	
	public void empty() {
		head = null;
		tail = null;
		current = null;
	}
	
	public T next() {
		ListElement<T> next = current;
		current = current.getNext();
		return next.getContent();
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
	
	public T get(int index) {
		return getElement(index).getContent();
	}
	
	private ListElement<T> getElement(int index) {
		for(int i=0;i<index;i++) {
			next();
		}
		return current;
	}
	
	public void removeIndex(int index) {
		ListElement<T> before = null;
		ListElement<T> after = null;
		if(index == 0) {
			head = getElement(1);
			return;
		} else if(index == size()) {
			tail = getElement(size()-1);
			return;
		}
		before = getElement(index-1);
		after = getElement(index+1);
		before.setNext(after);
	}
	
	/*
	public int remove(String name) {
		int size = size();
		for(int i=0;i<size;i++) {
			T comp = next();
			if(name.equals("")) {
				
			}
		}
	}
	
	public int remove(int mn) {
		
	}
	*/
}
