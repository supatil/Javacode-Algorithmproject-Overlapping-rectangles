

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class intervaltree<T extends Comparable<T>> {

	private intervalNode<T> Empty = new intervalNode<T>();
	private intervalNode<T> root = Empty;

    public intervaltree() {
        root.left = Empty;
        root.right = Empty;
        root.parent = Empty;
    }
    
    public static void main(String args[]) throws FileNotFoundException{
    	
    	@SuppressWarnings("resource")
    	Scanner s = new Scanner(System.in);

    	
    	//if(!args[0].isEmpty())
        if(true)
    	{
    	String input = args[0];
    		
    	//	String input = "overlap_input.txt"; 
    		/*switch(input)
    		{
    			case "no_overlap_input.txt":
    										s = new Scanner(new File("no_overlap_input.txt"));
    										break;
    			case "overlap_input.txt":
    				                        s = new Scanner(new File("overlap_input.txt"));
    										break;
    			case "no_overlap.txt":
    										s = new Scanner(new File("no_overlap.txt"));
    										break;
    			default: 
    				System.out.println("Input file is invalid..");
    				break;
    		}*/
			s = new Scanner (new File(input));
    	}
    	
        
    	intervaltree<Double> integerBox = new intervaltree<Double>();
    	//Scanner s = new Scanner(new File("overlap_input.txt"));
    	Double[] arr = new Double[5];
    	//arr[1] = s.nextDouble();
    	//int index = 1;
    	boolean searchFlag = false;
    	 while (s.hasNext())
    	 {
    		for (int i = 0; i < 5; i++)
    		{
    		    arr[i]=s.nextDouble();
    		    
    		 //  System.out.println(arr[i]);
    		    
    		}
    		
    		searchFlag = integerBox.checkoverlap(arr[0],arr[1],arr[2],arr[3],arr[4]);
    		if(!searchFlag){
    			System.out.println("Overlap Found");
    			break;
    		}
    		integerBox.insert(arr[0],arr[1],arr[2],arr[3],arr[4]);
    		//System.out.println("Entries inserted" + index);
    	   // index++;
    	
    	 }
    	 
    	 if(searchFlag)
    	 {
    		 System.out.println("No Overlap Found");
    	 }
    	// integerBox.checkoverlap(5.0,2640.30,111712.03,2641.52,111722.15);
  /* intervaltree<Double> integerBox = new intervaltree<Double>();
    	integerBox.insert(1.0,2640.30,111712.03,2641.52,111722.15);
    	integerBox.insert(2.0,2629.30,111712.03,2641.52,111722.15);
    	integerBox.insert(3.0,2619.30,111712.03,2641.52,111722.15);
    	integerBox.insert(4.0,2669.30,111712.03,2641.52,111722.15);
    	integerBox.checkoverlap(5.0,2630.30,111712.03,2631.52,111722.15);*/
    //	integerBox.Delete(new intervalNode<Double>(2629.30));   	
    }

    public void insert(T recno,T xl,T yl,T xh,T yh) {
        insert(recno,new intervalNode<T>(xl),yl,xh,yh);
    }

	private void insert(T recno,intervalNode<T> xl,T yl,T xh,T yh) {
			xl.ylow=yl;
			xl.xhigh=xh;
			xl.yhigh=yh;
			xl.rectno=recno;
		
			intervalNode<T> parent = Empty;
			intervalNode<T> x = root;

			intervalNode<T> y = Empty;
			intervalNode<T> z = xl;

			while (x!=Empty){
				parent = x;
				if (xl.xlow.compareTo(x.xlow) < 0)
				{
					x = x.left;
				}
				else
				{
					x = x.right;
				}
			}
			xl.parent = parent;

			if (parent==Empty)
				{
				root = xl;
				//System.out.println("inserted at root");
				}
			else if (xl.xlow.compareTo(parent.xlow) < 0)
				{
				parent.left = xl;
				//System.out.println("inserted at left ");
				}
			else
				{
				parent.right = xl;
				//System.out.println("inserted at right");
				}
			xl.left = Empty;
			xl.right = Empty;
			xl.color = intervalNode.RED;

			while (z.parent.color == intervalNode.RED){

				if (z.parent == z.parent.parent.left){
					y = z.parent.parent.right;
					if (y.color == intervalNode.RED){
						z.parent.color = intervalNode.BLACK;
						y.color = intervalNode.BLACK;
						z.parent.parent.color = intervalNode.RED;
						z = z.parent.parent;
					}
					else if (z == z.parent.right){
						z = z.parent;
						leftRotate(z);
					}

					else{
						z.parent.color = intervalNode.BLACK;
						z.parent.parent.color = intervalNode.RED;
						rightRotate(z.parent.parent);
					}
				}
				else{
					y = z.parent.parent.left;

					if (y.color == intervalNode.RED)
					{
						z.parent.color = intervalNode.BLACK;
						y.color = intervalNode.BLACK;
						z.parent.parent.color = intervalNode.RED;
						z = z.parent.parent;
					}
					else if (z == z.parent.left)
					{
						z = z.parent;
						rightRotate(z);
					}
					else{
						z.parent.color = intervalNode.BLACK;
						z.parent.parent.color = intervalNode.RED;
						leftRotate(z.parent.parent);
					}
				}
			}
		root.color = intervalNode.BLACK;
	}
//to check overlap of rectangle
//public intervalNode<T> checkoverlap(T key,T yl,T xh,T yh){
public boolean checkoverlap(T rect,T key,T yl,T xh,T yh){

	intervalNode<T> current = root;
	boolean noOverlapflag=true; 
	while (current!=Empty){ 
		if(current.xlow.compareTo(key)<0 && current.xhigh.compareTo(key)<0 && current.xlow.compareTo(xh)<0 && current.xhigh.compareTo(xh)<0 || current.ylow.compareTo(yl)<0 && current.yhigh.compareTo(yl)<0 && current.ylow.compareTo(yh)<0 && current.yhigh.compareTo(yh)<0 || current.xlow.compareTo(key)>0 && current.xhigh.compareTo(key)>0 && current.xlow.compareTo(xh)>0 && current.xhigh.compareTo(xh)>0|| current.ylow.compareTo(yl)>0 && current.yhigh.compareTo(yl)>0 && current.ylow.compareTo(yh)>0 && current.yhigh.compareTo(yh)>0)
		{
			if(current.xlow.compareTo(key)<0)
			{
					current=current.right;
			}
			else
			{
				current=current.left;
			}
			
			
			
		}//System.out.println("No Overlap");
		else
		{
			noOverlapflag = false;
			System.out.println("Overlap found with rectangle: "
		+current.rectno+" And its Points are: "+current.xlow+" "+current.ylow+" "+current.xhigh+" "+current.yhigh+" and Rectangle Number "+rect+" And its Points are: "+key+" "+yl+" "+xh+" "+yh);
		
			break;
		
		}
		
		
		
		
		}
	    
	  /* if(noOverlapflag)
		{
			//System.out.println("No Overlap");
		}*/
		
	
	return noOverlapflag;
}
private void leftRotate(intervalNode<T> x){

	intervalNode<T> y;
	y = x.right;
	x.right = y.left;
	
	if (y.left!=Empty)
	
	{
		y.left.parent = x;
	}
	y.parent = x.parent;

	if (x.parent==Empty)
	{
		root = y;
	}

	else if (x.parent.left == x)
		{
		x.parent.left = y;
		}

	else
		{
		x.parent.right = y;
		}


	y.left = x;
	x.parent = y;
}

private void rightRotate(intervalNode<T> y){

    intervalNode<T> x = y.left;
    y.left = x.right;

    if (x.right!=Empty) 	
    {
    	x.right.parent = y;
    }
    x.parent = y.parent;

    if (y.parent==Empty)
    {
        root = x;
    }
    else if (y.parent.right == y)
    {
    	y.parent.right = x;
    }
    else
    {
    	y.parent.left = x;
    }
    x.right = y;
    y.parent = x;
}
}

