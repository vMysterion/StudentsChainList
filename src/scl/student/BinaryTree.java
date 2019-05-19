package scl.student;

public class BinaryTree {

	private TreeElement root;
	private boolean sort;
	
	public BinaryTree(Student[] data, boolean sort) {
		this.sort = sort;
		create(data);
	}
	
	public void create(Student[] data) {
		for(Student s : data) {
			this.add(s);
		}
	}
	
	public void empty() {
		root = null;
	}
	
	public TreeElement getRoot() {
		return this.root;
	}
	
	public void add(Student student) {
		
		TreeElement element = new TreeElement(student, null, null);
		
		if(root == null) {
			root = element;
			return;
		}
		
		TreeElement current = root;
		int result = 0;
		boolean loop = true;
		while(loop) {
			Student comp = current.getContent();
			if(sort) {
				if(student.getMatriculationNumber() <= comp.getMatriculationNumber()) {
					result = -1;
				} else {
					result = 1;
				}
			} else {
				if(student.getName().compareTo(comp.getName()) <= 0) {
					result = -1;
				} else {
					result = 1;
				}
			}
			
			TreeElement next = null;
			if(result == -1) {
				next = current.getLeft();
			} else if(result == 1) {
				next = current.getRight();
			} else {
				throw new RuntimeException("shit happens");
			}
			
			if(next == null) {
				loop = false;
			} else {
				current = next;
			}
		}
		
		if(result == -1) {
			current.setLeft(element);
		} else if(result == 1) {
			current.setRight(element);
		} 
		
	}
	
}
