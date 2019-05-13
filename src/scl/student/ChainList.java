package scl.student;

public class ChainList {
	
	private ListElement head;
	private ListElement tail;
	private ListElement current;
	private ListElement last;
	private boolean sort; //true number - false name
	
	public ChainList(Student[] data, boolean sort) {
		this.sort = sort;
	}
	
	@Deprecated
	public void create(Student[] data) {
		for(int i=0;i<data.length;i++) {
			ListElement element = new ListElement();
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
	
	public Student next() {
		ListElement next = current;
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
		reset();
		return size;
	}
	
	public boolean hasNext() {
		if(current.getNext() != null) return true;
		return false;
	}
	
	public Student get(int index) {
		return getElement(index).getContent();
	}
	
	private ListElement getElement(int index) {
		for(int i=0;i<index;i++) {
			next();
		}
		return current;
	}
	
	public void removeIndex(int index) {
		ListElement before = null;
		ListElement after = null;
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
	
	public int remove(String name) {
		int size = size();
		int removed = 0;
		for(int i=0;i<size;i++) {
			Student comp = next();
			if(name.equals(comp.getName())) {
				removeIndex(i);
				removed++;
			}
		}
		return removed;
	}
	
	public int remove(int mn) {
		int size = size();
		int removed = 0;
		for(int i=0;i<size;i++) {
			Student comp = next();
			if(mn == comp.getMatriculationNumber()) {
				removeIndex(i);
				removed++;
			}
		}
		return removed;
	}
	
	public void add(Student student) {
		if(head == null && tail == null && current == null) {
			ListElement element = new ListElement(student, null);
			head = element;
			tail = element;
			current = element;
			return;
		}
		
		ListElement element = new ListElement(student, null);
		int size = size();
		int result = 1;
		int index = -1;
		boolean loop = hasNext();
		while(loop) {
			Student comp = next();
			Student curr = element.getContent();
			if(sort) {
				if(curr.getMatriculationNumber() < comp.getMatriculationNumber()) {
					result = -1;
				} else if(curr.getMatriculationNumber() == comp.getMatriculationNumber()) {
					result = 0;
				}
			} else {
				if(curr.getName().compareTo(comp.getName()) < 0) {
					result = -1;
				} else if(curr.getName().equals(comp.getName())) {
					result = 0;
				}
			}
			loop = hasNext();
			if(result <=0) {
				loop = false;
			}
			index++;
		}
		
		if(result < 1) {
			ListElement after = getElement(index);
			element.setNext(after);
			if(size>1) {
				ListElement before = getElement(index-1);
				before.setNext(element);
			} 
			if(index == 0) {
				head = element;
			}
		} else {
			ListElement before = tail;
			before.setNext(element);
			tail = element;
		}
	}
}
