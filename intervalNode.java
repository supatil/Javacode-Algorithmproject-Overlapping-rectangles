/**
 */
class intervalNode<T extends Comparable<T>> {

	public T rectno;
	public T xlow;
	public T ylow;
	public T xhigh;
	public T yhigh;
	public String color;
	public static final String BLACK = "Black";
    public static final String RED = "Red";

    intervalNode<T> parent;
    intervalNode<T> left;
    intervalNode<T> right;
   
	
    intervalNode(T xlow){
        this.xlow = xlow;
        }
    
    intervalNode()
    {
        color = BLACK;
        parent = null;
        left = null;
        right = null;
        xhigh = null;
        xhigh=null;
        ylow = null;
        rectno=null;
    }	
}

