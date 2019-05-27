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
	
	public TreeElement get(String name, TreeElement element) {
		Student student = element.getContent();
		String studName = student.getName();
		if(name.equals(studName)) {
			return element;
		} else if(name.compareTo(studName) > 0) {
			return get(name, element.getRight());
		} else if(name.compareTo(studName) < 0) {
			return get(name, element.getLeft());
		} else {
			return null;
		}
	}
	
	private TreeElement get(int mn, TreeElement element) {
		Student student = element.getContent();
		int studMn = student.getMatriculationNumber();
		if(mn == studMn) {
			return element;
		} else if(mn > studMn) {
			return get(mn, element.getRight());
		} else if(mn < studMn) {
			return get(mn, element.getLeft());
		} else {
			return null;
		}
	}
	
	private Student swapElements(TreeElement before, TreeElement element, TreeElement swapElement, int dir, boolean right) {
		if(right) {
			swapElement.setLeft(element.getLeft());
		} else {
			swapElement.setRight(element.getRight());
		}
		
		if(dir==1) {
			before.setRight(swapElement);
			return element.getContent();
		} else if(dir==-1) {
			before.setLeft(swapElement);
			return element.getContent();
		} else {
			return null;
		}
	}
	
	public Student remove(String name) {
		TreeElement element = this.getRoot();
		
		if(!element.hasLeft() && !element.hasRight()) {
			this.root = null;
			return element.getContent();
		} else {
			boolean loop = true;
			TreeElement before = element;
			int dir = 0;
			while(loop) {
				if(element == null) {
					return null;
				}
				if(name.compareTo(element.getContent().getName()) > 0) {
					dir = 1;
					element = element.getRight();
				} else if(name.compareTo(element.getContent().getName()) < 0) {
					dir = -1;
					element = element.getLeft();
				}
				
				if(name.equals(element.getContent().getName())) {
					loop = false;
				}
				
				if(loop) {
					before = element;
				}
			}
			if(element.hasRight()) {
				TreeElement swapElement = element.getRight();
				TreeElement beforeSwap = element;
				while(swapElement.hasLeft()) {
					if(swapElement.hasLeft()) {
						beforeSwap = swapElement;
					}
					swapElement = swapElement.getLeft();
				}
				
				//Is leaf - no more sons
				if(!swapElement.hasLeft() && !swapElement.hasRight()) {
					return this.swapElements(before, element, swapElement, dir, true);
				//pull right sons on spot of the swapElement
				} else if(swapElement.hasRight()) {
					beforeSwap.setLeft(swapElement.getRight());
					swapElement.getRight().setLeft(swapElement.getLeft());
					return this.swapElements(before, element, swapElement, dir, true);
				}
			} else if(element.hasLeft()) {
				TreeElement swapElement = element.getLeft();
				TreeElement beforeSwap = element;
				while(swapElement.hasRight()) {
					if(swapElement.hasLeft()) {
						beforeSwap = swapElement;
					}
					element.getRight();
				}
				//Is leaf - no more sons
				if(!swapElement.hasLeft() && !swapElement.hasRight()) {
					return this.swapElements(beforeSwap, element, swapElement, dir, false);
				//pull left sons on spot of swapElement
				} else if(swapElement.hasRight()) {
					beforeSwap.setRight(swapElement.getLeft());
					swapElement.getLeft().setRight(swapElement.getRight());
					return this.swapElements(beforeSwap, element, swapElement, dir, false);
				} else {
					return null;
				}
			} else {
				if(dir == 1) {
					before.setRight(null);
					return element.getContent();
				} else if(dir == -1) {
					before.setLeft(null);
					return element.getContent();
				} else {
					return null;
				}
			}
			return null;
		}
		
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
