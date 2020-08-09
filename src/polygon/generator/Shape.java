package polygon.generator;

import java.util.ArrayList;
public class Shape {
private ArrayList<Point>points;
private ArrayList<Line>lines;
Shape(ArrayList<Point> arr1)
{
    if(arr1.size()>2){
	this.points=arr1;
	this.lines=new ArrayList<Line>();
	for(int i=0;i<arr1.size()-1;i++)
	{
		lines.add(new Line(points.get(i),points.get(i+1)));
	}
	lines.add(new Line(points.get(points.size()-1),points.get(0)));
    }
}


public int[] getXArray(){
    int x[]=new int[points.size()];
    
    for(int i=0;i<points.size();i++){
        x[i]=points.get(i).getX();
    }
    return x;
}

public int[] getYArray(){
    int y[]=new int[points.size()];
    
    for(int i=0;i<points.size();i++){
        y[i]=points.get(i).getY();
    }
    return y;
}

public void setPoint(Point p)
{
	points.add(p);
}

public Point getPoint(int i)
{
	return points.get(i);
}

public ArrayList<Point> getPoints()
{
	return points;
}

public int getNoVer()
{
    return points.size();
}
public double getArea()
{
	double area;
	double sum1=0,sum2=0;
	for(int i=0;i<lines.size();i++)
	{
		sum1=sum1+(lines.get(i).getpoint1().getX()*lines.get(i).getpoint2().getY());
	}
	for(int i=0;i<lines.size();i++)
	{
		sum2=sum2+(lines.get(i).getpoint1().getY()*lines.get(i).getpoint2().getX());
	}
   return Math.abs(sum1-sum2)/2.0;
}

public String getString()
{
	String str="";
	int k=1;
	for(int i=0;i<lines.size();i++)
	{
		str=str+"Length of line "+(k+i)+" is "+String.valueOf(lines.get(i).getLength()+" px\n");
	}
	str=str+"Area of Polygon is "+String.valueOf(getArea()+" sq. px\n");
	return str;
}
}
