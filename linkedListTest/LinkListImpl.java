package linkedListTest;

import java.util.Random;

public class LinkListImpl implements LinkList {

	private Node first;
	private Node end;
	private int level;
	public int size;

	public LinkListImpl() {
		level = 0; // 刚开始只有最下面一层
		first = new Node(null);
		end = new Node(null);
		first.next = end;
		end.prev = first;
		size = 0;

	}

	@Override
	public boolean isExists(Integer value) {
		if (value == null) {
			throw new RuntimeException("参数异常");
		}
		return value.equals(findFrist(value).getValue());
	}

	@Override
	public void add(Node node) {
		if (node == null || node.getValue() == null) {
			throw new RuntimeException("参数异常");
		}
		// 使用跳表结构进行存储，链表的元素排序进行添加
		// 找到插入位置的前一个点n
		Node n = findFrist(node.getValue());

		node.prev = n;
		node.next = n.next;
		n.next.prev = node;
		n.next = node;
		int i = 0;
		// 判断是否增加索引
		Random random = new Random();
		while (random.nextDouble() < 0.5 && level < 5) {
			// 加层数
			if (i >= level) { // 如果当前插入的节点所处的层数大于等于最大的层数，那么就需要增加高度了，因为这里要保证头尾节点的高度是最高的
				// 下面的代码就是在头尾节点的上插入新的节点，以此来增加高度
				Node nf = new Node(null);
				Node ne = new Node(null);
				nf.next = ne;
				ne.prev = nf;
				nf.down = first;
				ne.down = end;
				first.up = nf; // 将头尾节点上移，成为最顶层的节点，这就是为什么每次插入和查询的时候都是最上面开始查询的，因为这里的head默认的就是从最上面开始的
				end.up = ne;
				first = nf;
				end = ne;
				level++; // 最高层数加一
			}
			// 上一层索引上加插入的indexNode
			// 1、先找到上一层离这个节点最近的索引node
			while (n.up == null && n.prev != null) {
				n = n.prev;
			}
			// 找到上面一层
			n = n.up;
			// 在这一层插入该node
			Node indexNode = new Node(node.getValue());
			indexNode.prev = n;
			System.out.println(i);
			indexNode.next = n.next;

			n.next.prev = indexNode;
			n.next = indexNode;
			indexNode.down = node;
			node.up = indexNode;
			node = indexNode;
			// i指针，现在在加入节点的上一层
			i++;
		}
		size++; // 节点加一
	}

	public Node findFrist(Integer value) {
		Node n = first;
		// 从第一层往下找，直到找到最下面离要加的节点最近的节点
		while (true) {
			while (n != null && n.next.getValue() != null && n.next.getValue() <= value) {
				n = n.next;
			}
			if (n.down != null) {
				n = n.down;
			} else {
				break;
			}
		}
		return n;
	}

	public int getIevel() {
		return level;
	}

	public void display() {
		while (level >= 0) {
			Node node = first;
			System.out.println();
			System.out.println("第" + level + "层***********************************");
			while (node != null) {
				System.out.print(node.getValue() + "<------->");
				node = node.next;
			}
			level--;
			if (first.down != null) {
				first = first.down;
			}
		}
	}
}
