package application.Objects;

import java.awt.Point;
import java.util.ArrayList;
import application.GamePanel;

public class PathFinding {
	
	ArrayList<Node> openList = new ArrayList<>();
	ArrayList<Node> closeList = new ArrayList<>();
	
	public int calculateH(int startX, int startY, int goalX, int goalY)	{
		return (int)(Math.abs(goalX - startX) + Math.abs(goalY - startY));
	}
	
	public int findNodeWithLeastF(ArrayList<Node> arrList) {
		Node minNode = arrList.get(0);
		int minIndex = 0;
		
		for (int i = 1; i < arrList.size(); i++) {
			if (minNode.f > arrList.get(i).f) {
				minNode = arrList.get(i);
				minIndex = i;
			}
		}
		
		return minIndex;
	}
	
	public int checkList(ArrayList<Node> arrList, Node node) {
		if (arrList.isEmpty())
			return -1;
		
		for (int j = 0; j < arrList.size(); j++) {
			if (arrList.get(j).x == node.x &&
				arrList.get(j).y == node.y &&
				(arrList.get(j).f <node.f))
				return j;
		}		
		return -1;
	}
	
	public ArrayList<Node> doAlgorithmAStar( int startX, int startY, int goalX, int goalY) {
		
		if (startX == goalX && startY == goalY) {		
			return closeList;
		}		
		openList = new ArrayList<>();
		closeList = new ArrayList<>();	
		
		Node startNode = new Node(startX, startY, 0, calculateH(startX, startY, goalX, goalY), null);
		openList.add(startNode);
		
		while (!openList.isEmpty()) {
						
			Node minNode = openList.get(findNodeWithLeastF(openList));
			openList.remove(findNodeWithLeastF(openList));
			closeList.add(minNode);			
			Node[] successors = new Node[4];	
			
		  if (Character.isFree(minNode.x*GamePanel.SQUARE_SIZE , minNode.y*GamePanel.SQUARE_SIZE - 1.25)) 
			    successors[0] = new Node(minNode.x, minNode.y - 1, minNode.g +1, calculateH(minNode.x, minNode.y , goalX, goalY),minNode);	
			
			if (Character.isFree(minNode.x*GamePanel.SQUARE_SIZE + 1.25, minNode.y*GamePanel.SQUARE_SIZE ))
				  successors[1] = new Node(minNode.x + 1, minNode.y, minNode.g + 1, calculateH(minNode.x , minNode.y, goalX, goalY),minNode);
			
			if (Character.isFree(minNode.x*GamePanel.SQUARE_SIZE, minNode.y*GamePanel.SQUARE_SIZE + 1.25))				
			 	  successors[2] = new Node(minNode.x, minNode.y + 1, minNode.g + 1, calculateH(minNode.x, minNode.y , goalX, goalY),minNode);
			
		  if (Character.isFree ((minNode.x*GamePanel.SQUARE_SIZE -  1.25) , minNode.y*GamePanel.SQUARE_SIZE))			
				  successors[3] = new Node(minNode.x - 1, minNode.y, minNode.g + 1, calculateH(minNode.x , minNode.y, goalX, goalY),minNode);	
			
			for (int i = 0; i < successors.length; i++) {
				if (successors[i] == null)
					continue;
				if (successors[i].x == goalX && (successors[i].y == goalY)) {
					System.out.println("Found Target.");
					closeList.add(successors[i]);
					return closeList;
				}				
				if (checkList(openList, successors[i]) != -1)
					continue;								
				int index = checkList(closeList,  successors[i]);				
				if (index != -1)
					continue;
				else 
					openList.add(successors[i]);	
			}	
		}		

		if (closeList.get(closeList.size() - 1).x != goalY ||closeList.get(closeList.size() - 1).y !=  goalX) {
			  closeList.removeAll(closeList);
		}
		
		return closeList;
		
	}
	
	public Point nextStep(Node currentNode) {
		while (currentNode.parentNode.parentNode != null) {
			currentNode = currentNode.parentNode;
		}
		return new Point((int)currentNode.x, (int)currentNode.y);
	}
	
	public Node nextStepVor (Node currenNode) {
		currenNode = currenNode.parentNode;
		return currenNode;
	}
	
}
