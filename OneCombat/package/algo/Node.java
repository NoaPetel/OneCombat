package algo;

import java.util.ArrayList;
import java.util.List;

import system.physics.FlatVector;

public class Node implements Comparable<Node> {
    private final int x;
    private final int y;
    private int g;
    private int h;
    private Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.g = 0;
        this.h = 0;
        this.parent = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getF() {
        return g + h;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(getF(), other.getF());
    }
    public static ArrayList<FlatVector> toVector(List<Node> nodeList) {
        ArrayList<FlatVector> vectorList = new ArrayList<>();

        for (Node node : nodeList) {
            FlatVector flatVector = new FlatVector(node.x-31, node.y-7);//Valeurs magiques
            vectorList.add(flatVector);
        }

        return vectorList;
    }

	public static boolean sameNode(Node CurrentNode, Node goalNode) {
		return (CurrentNode.x == goalNode.x && CurrentNode.y == goalNode.y);
	}

	
}