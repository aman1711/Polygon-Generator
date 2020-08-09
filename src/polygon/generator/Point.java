package polygon.generator;


public class Point {
private int x,y;

Point(int x,int y)
{
	this.x=x;
    this.y=y;
}

public void setX(int x)
{
	this.x=x;
}

public void setY(int y)
{
	this.y=y;
}

public int getX()
{
	return x;
}
public int getY()
{
	return y;
}
public double getDistance(Point p)
{
	int d1=p.getX()-x;
	d1=(int)Math.pow(d1,2);
	int d2=p.getY()-y;
	d2=(int)Math.pow(d2,2);
	int d3=d2+d1;
	double d;
	d=Math.sqrt(d3);
	return d;
}
}

