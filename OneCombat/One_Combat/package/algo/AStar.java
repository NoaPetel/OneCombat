package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    private final int[][] grid;
    private final int width;
    private final int height;
    private final List<Node> path;
    private final PriorityQueue<Node> openSet;
    private final boolean[][] closedSet;

    public AStar(int[][] grid) {
        this.grid = grid;
        this.width = grid.length;
        this.height = grid[0].length;
        this.path = new ArrayList<>();
        this.openSet = new PriorityQueue<>();
        this.closedSet = new boolean[width][height];
    }

    public List<Node> findPath(int x, int y, int xGoal, int yGoal) {
        Node startNode = new Node(x, y);
        Node goalNode = new Node(xGoal, yGoal);
        
        int essaiMax = 200;
        int compteur = 0;
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            compteur += 1;
            if (Node.sameNode(currentNode, goalNode)) {
                constructPath(currentNode);
                break;
            }
            if(compteur > essaiMax) {
            	break;
            }

            closedSet[currentNode.getX()][currentNode.getY()] = true;

            for (Node neighbor : getNeighbors(currentNode)) {
                if (closedSet[neighbor.getX()][neighbor.getY()])
                    continue;

                int tentativeG = currentNode.getG() + 1;

                if (!openSet.contains(neighbor) || tentativeG < neighbor.getG()) {
                    neighbor.setParent(currentNode);
                    neighbor.setG(tentativeG);
                    neighbor.setH(manhattanDistance(neighbor, goalNode));

                    if (!openSet.contains(neighbor))
                        openSet.add(neighbor);
                }
            }
        }

        return path;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int x = node.getX();
        int y = node.getY();

        if (isValidCoordinate(x - 1, y))
            neighbors.add(new Node(x - 1, y));

        if (isValidCoordinate(x + 1, y))
            neighbors.add(new Node(x + 1, y));

        if (isValidCoordinate(x, y - 1))
            neighbors.add(new Node(x, y - 1));

        if (isValidCoordinate(x, y + 1))
            neighbors.add(new Node(x, y + 1));

        return neighbors;
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height && grid[x][y] != 0;
    }

    private int manhattanDistance(Node node, Node goalNode) {
        return Math.abs(node.getX() - goalNode.getX()) + Math.abs(node.getY() - goalNode.getY());
    }

    private void constructPath(Node currentNode) {
        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }
        Collections.reverse(path);
    }
}



