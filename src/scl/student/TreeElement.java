package scl.student;

public class TreeElement {

	private TreeElement left;
	private TreeElement right;
	private Student content;
	
	public TreeElement(Student content, TreeElement left, TreeElement right) {
		this.setContent(content);
		this.setLeft(left);
		this.setRight(right);
	}
	
	public boolean hasLeft() {
		if(left == null) return false;
		return true;
	}
	
	public boolean hasRight() {
		if(right == null) return false;
		return true;
	}

	public TreeElement getRight() {
		return right;
	}

	public void setRight(TreeElement right) {
		this.right = right;
	}

	public TreeElement getLeft() {
		return left;
	}

	public void setLeft(TreeElement left) {
		this.left = left;
	}

	public Student getContent() {
		return content;
	}

	public void setContent(Student content) {
		this.content = content;
	}
	
}
