package application.Objects;



public class Node {
    protected Node parentNode;
    protected int x;
    protected int y;
    protected int f;
    protected int g;
    protected int h;
    
    public Node(int x,int y,int g,int h, Node parentNode) {
        this.x =x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.f = g + h;
        this.parentNode = parentNode; //
    }
}
