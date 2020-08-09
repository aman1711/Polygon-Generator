package polygon.generator;


public class Line {
	
private Point p1,p2;
	
Line(Point p1,Point p2)
{
	this.p1=p1;
	this.p2=p2;
}
public Point getpoint1()
{
	return p1;
}
public Point getpoint2()
{
	return p2;
}
public double getLength()
{
	double d1=(double)p1.getX()-p2.getX();
	double d2=(double)p1.getY()-p2.getY();
	double d3=Math.sqrt(Math.pow(d1, 2)+Math.pow(d2, 2));
	return d3;
}

public static Point instersection(Line l1,Line l2){
    
    int a1,b1,c1,a2,b2,c2;
    Point p1,p2;
    p1=l1.getpoint1();
    p2= l1.getpoint2();
    
    a1=p2.getY()-p1.getY();
    b1=p1.getX()-p2.getX();
    c1=(p1.getX()*a1)+(p1.getY()*b1);
    
    p1=l2.getpoint1();
    p2= l2.getpoint2();
    
    a2=p2.getY()-p1.getY();
    b2=p1.getX()-p2.getX();
    c2=(p1.getX()*a2)+(p1.getY()*b2);
    
    
    int x,y;
    double d;
    d=(a1*b2-a2*b1);
    x=(int) ( (b2*c1-b1*c2)/d);
    y=(int) ((a1*c2-a2*c1)/d);
        
    return new Point(x,y);
    
}

public static boolean isOnLine(Point p,Line l){
    
    Point p1,p2;
    p1=l.getpoint1();
    p2= l.getpoint2();
    
    if(Math.abs(p.getDistance(p1)+p.getDistance(p2)-p1.getDistance(p2))<=5){
        return true;
    }else return false;
    
}

public static boolean isInInter(Line l1,Line l2){
    
    Point p= Line.instersection(l1, l2);
    
    return Line.isOnLine(p, l1)&&Line.isOnLine(p, l2);
    
}

public static boolean isInInter(Point p1,Point p2,Point p3,Point p4){
    Line l1=new Line(p3,p4);
    Line l2=new Line(p1,p2);
    
    return Line.isInInter(l1, l2);
    
}

}
