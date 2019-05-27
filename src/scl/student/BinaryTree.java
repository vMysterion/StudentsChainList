package scl.student;

import java.util.ArrayList;
import java.util.List;

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
	
	private Student swapElements(TreeElement before, TreeElement element, TreeElement swapElement, TreeElement beforeSwap, int dir, boolean right) {
		
		TreeElement eR = element.getRight();
		TreeElement eL = element.getLeft();
		
		TreeElement sR = swapElement.getRight();
		TreeElement sL = swapElement.getLeft();
		
		if(dir == 1) {
			before.setRight(swapElement);
		} else if(dir == -1) {
			before.setLeft(swapElement);
		}
		
		swapElement.setRight(eR);
		swapElement.setLeft(eL);
		
		if(right) {
			beforeSwap.setLeft(element);
		} else {
			beforeSwap.setRight(element);
		}
		
		element.setLeft(sL);
		element.setRight(sR);
		
		return element.getContent();

	}
	
	private List getEssentialElements(TreeElement element, int mn) {
		
			boolean loop = true;
			TreeElement before = element;
			int dir = 0;
			while(loop) {
				if(element == null) {
					return null;
				}
				if(mn > element.getContent().getMatriculationNumber()) {
					dir = 1;
					element = element.getRight();
				} else if(mn < element.getContent().getMatriculationNumber()) {
					dir = -1;
					element = element.getLeft();
				}
				
				if(mn == element.getContent().getMatriculationNumber()) {
					loop = false;
				}
				
				if(loop) {
					before = element;
				}
			}
			List essentials = new ArrayList();
			essentials.add(before);
			essentials.add(element);
			essentials.add(dir);
			return essentials;
	}
	
	private List getEssentialElements(TreeElement element, String name) {
		
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
		List essentials = new ArrayList();
		essentials.add(before);
		essentials.add(element);
		essentials.add(dir);
		return essentials;
	}
	
	private Student remove(TreeElement before, TreeElement element, int dir) {
		if(element.hasRight()) {
			boolean onlyRight = true;
			TreeElement swapElement = element.getRight();
			TreeElement beforeSwap = element;
			while(swapElement.hasLeft()) {
				if(swapElement.hasLeft()) {
					beforeSwap = swapElement;
				}
				swapElement = swapElement.getLeft();
				onlyRight = false;
			}
			
			if(onlyRight) {
				if(element.hasLeft()) {
					swapElement.setLeft(element.getLeft());
				}
				before.setRight(swapElement);
				return element.getContent();
			//Is leaf - no more sons
			} else if(!swapElement.hasLeft() && !swapElement.hasRight()) {
				Student removed = this.swapElements(before, element, swapElement, beforeSwap, dir, true);
				beforeSwap.setLeft(null);
				return removed;
			//Swap Elements then pull right sons on previous spot of swapElement
			} else if(swapElement.hasRight()) {
				Student removed = this.swapElements(before, element, swapElement, beforeSwap, dir, true);
				beforeSwap.setLeft(element.getRight());
				return removed;
			}
		} else if(element.hasLeft()) {
			boolean onlyLeft = true;
			TreeElement swapElement = element.getLeft();
			TreeElement beforeSwap = element;
			while(swapElement.hasRight()) {
				if(swapElement.hasLeft()) {
					beforeSwap = swapElement;
				}
				element.getRight();
				onlyLeft = false;
			}
			if(onlyLeft) {
				if(element.hasRight()) {
					swapElement.setLeft(element.getRight());
				}
				before.setLeft(swapElement);
				return element.getContent();
			//Is leaf - no more sons
			} else if(!swapElement.hasLeft() && !swapElement.hasRight()) {
				Student removed = this.swapElements(before, element, swapElement, beforeSwap, dir, false);
				beforeSwap.setRight(null);
				return removed;
			//Swap Elements then pull right sons on previous spot of swapElement
			} else if(swapElement.hasRight()) {
				Student removed = this.swapElements(before, element, swapElement, beforeSwap, dir, false);
				beforeSwap.setRight(element.getLeft());
				return removed;
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
	
	public Student remove(int mn) {
		TreeElement element = this.getRoot();
		if(!element.hasLeft() && !element.hasRight()) {
			this.root = null;
			return element.getContent();
		} else {
			List essentials = this.getEssentialElements(element, mn);
			TreeElement before = (TreeElement)essentials.get(0);
			element = (TreeElement)essentials.get(1);
			int dir = (int)essentials.get(2);
			
			return this.remove(before, element, dir);
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
