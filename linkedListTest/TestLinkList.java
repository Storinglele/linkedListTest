package linkedListTest;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestLinkList {
	LinkListImpl linkList = new LinkListImpl();

	@Test
	public void add() {
		Node node = new Node(1);
		linkList.add(node);
	}

	@Test
	public void isExists() {

		Node node1 = new Node(1);
		linkList.add(node1);
		System.out.println(linkList.isExists(1));
		Node node2 = new Node(10);
		linkList.add(node2);
		System.out.println(linkList.isExists(10));
		for (int i = 0; i < 10; i++) {
			Node nodeTwo = new Node(new Random().nextInt(100));
			System.out.println("第i次" + i + "此次加入的值是：" + nodeTwo.getValue());
			linkList.add(nodeTwo);
			System.out.println(linkList.isExists(1));
			System.out.println(linkList.getIevel());
		}
		linkList.display();
		System.out.println();
		System.out.println("测试完成");

	}
}
