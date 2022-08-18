package linkedListTest;

public class Node {

	private Integer value;
	public Node prev,next,up,down;

	public Node(Integer value){
		this.value = value;
		prev = next = up = down = null;
	}
	public Integer getValue() {
		return value;
	}
}
