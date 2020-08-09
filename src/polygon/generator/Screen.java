/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polygon.generator;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author hp
 */
public class Screen extends Applet{
    
    private Applet app;
    private int click_no;
    private boolean done;
    private JLabel label;
    private int drag_started;

    private ArrayList <Point> points;
    private Shape shape;
    
    Screen(JLabel label){
        
        this.label=label;
        app=this;
        done=false;
        this.click_no=0;
        this.drag_started=-1;
        points = new ArrayList<>();

        Color col = new Color(224,255,255);
        
        app.setBackground(col);
        app.setVisible(true);
        app.addMouseListener(new ListenForMouse());
        app.addMouseMotionListener(new ListenForMouseDrag());
        
        
    }
    
    public ArrayList<Point> getPoints(){
        return points;
    }    
    
    public int getN(){
        return this.click_no;
    }
    public boolean getD(){
        return this.done;
    }
    public void setD(boolean t){
        this.done=t;
    }
    
        
    public Shape getShape(){
       return shape;
    }
    
    public void setShape(){
        this.setD(true);
        this.shape = new Shape(points);
        this.click_no=points.size();
        this.repaint();
    }
    
    public void clear(){
        this.shape=null;
        this.click_no=0;
        this.done=false;
        points.clear();
        repaint();
        
    }
    
    public void setShape(Shape s){
        
        if(s.getNoVer()<=2) return; 
        this.done=true;
        this.click_no=s.getNoVer();
        this.shape=s;
        this.points=s.getPoints();
        this.repaint();
        
    }
    
    private void drawCircles(Graphics g){
        
            g.setColor(new Color(255,255,185));
            for(int i=0;i<click_no;i++){
    
                g.fillOval(points.get(i).getX()-10,points.get(i).getY()-10,20,20);
  
            }
            Graphics2D g2 = (Graphics2D) g;    
            g2.setStroke(new BasicStroke(1));
            
            g.setColor(new Color(255,0,0));
            for(int i=0;i<click_no;i++){
    
                g.drawOval(points.get(i).getX()-10,points.get(i).getY()-10,20,20);
  
            }
        
    }
    
    @Override
    public void paint(Graphics g){
        
        if(done){
            
            Font font = new Font("Arial",Font.ITALIC,20);
            g.setFont(font);
            
            label.setText("Now you can drag and drop vertexes");
                
            

            int x1[],y1[];
            x1= shape.getXArray();
            y1= shape.getYArray();
            g.setColor(Color.WHITE);
                
            Graphics2D g2 = (Graphics2D) g;    
            g2.setStroke(new BasicStroke(2));
            g2.fillPolygon(x1,y1,click_no);
            
            this.drawCircles(g); 
            
            g2.setStroke(new BasicStroke(2));
            
            g2.setColor(new Color(26,148,149));
            g2.drawPolygon(x1,y1,click_no);
            
            g2.setColor(Color.BLUE);
            
            for(int i=0;i<click_no;i++){
                g2.drawString(Integer.toString(i+1), x1[i]-8, y1[i]-8);
            }
            
        }
        
        else if(this.click_no==0){
            
            
            label.setText("Click on Screen For First Point Of Polygon");
            
            
        }
        
        else if(this.click_no==1){
            
            label.setText("Click on Screen For Second Point Of Polygon");
            
            g.setColor(new Color(255,255,185));
            g.fillOval(points.get(click_no-1).getX()-10,points.get(click_no-1).getY()-10 , 20, 20);
            
            g.setColor(new Color(255,0,0));
            g.drawOval(points.get(click_no-1).getX()-10,points.get(click_no-1).getY()-10 , 20, 20);
            
            g.setColor(new Color(26,148,149));
            g.fillOval(points.get(click_no-1).getX()-2,points.get(click_no-1).getY()-2,4,4);

            
        }
        else{
            
            label.setText("Click on Screen For Next Point Of Polygon");

            this.drawCircles(g);
            g.setColor(new Color(26,148,149));
                
            Graphics2D g2 = (Graphics2D) g;
                
            g2.setStroke(new BasicStroke(2));
            
            
            for(int i=1;i<click_no;i++){
    
                g2.drawLine(points.get(i-1).getX(), points.get(i-1).getY(), points.get(i).getX(), points.get(i).getY());
                
                
            }
            
        }
        
        
        
    }
    
    private class ListenForMouse implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getButton()==1)
            if(!done){
                Point p= new Point(e.getX(),e.getY());
                if(points.size()!=0)
                for(int i=0;i<click_no;i++){
                        if(points.get(i).getDistance(new Point(e.getX(),e.getY()))<=12&&points.size()>2){
                            if(points.size()-i>2){
                                for(int j=i+1; j<click_no-2;j++)
                                    if(Line.isInInter(points.get(j), points.get(j+1), points.get(click_no-1), p)){
                                        JOptionPane.showMessageDialog(app, "Sides of Polygons can't intersect!!");
                                        return;
                                    }      
                                for(int j=0;j<i;j++){
                                    points.remove(0);
                                }
                                setShape();
                            }
                            else
                                JOptionPane.showMessageDialog(app, "Polygon must have at least 3 points");
                            return;
                        }
                        else if(points.get(i).getDistance(new Point(e.getX(),e.getY()))<=12){
                            JOptionPane.showMessageDialog(app, "First create at least 3 points!!");
                            return;
                        }
                        
                }
                
                if(points.size()>2)
                    for(int i=0; i<click_no-2;i++)
                        if(Line.isInInter(points.get(i), points.get(i+1), points.get(click_no-1), p)){
                            JOptionPane.showMessageDialog(app, "Sides of Polygons can't intersect!!");
                            return;
                        }
                
                click_no++;
                points.add(p);
                repaint();
            }
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
            if(e.getButton()==1)
            if(done)
            for(int i=0;i<click_no;i++){
                if(points.get(i).getDistance(new Point(e.getX(),e.getY()))<=12){
                    drag_started=i;
                    points.get(i).setX(e.getX());
                    points.get(i).setY(e.getY());
                    repaint();
                    break;
                }
            }
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            drag_started=-1;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
        
        
    }
    
    private class ListenForMouseDrag implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            
                if(done)
                    if(drag_started!=-1){
                        points.get(drag_started).setX(e.getX());
                        points.get(drag_started).setY(e.getY());
                        repaint();
                    }
        }

        @Override
        public void mouseMoved(MouseEvent e) {}
        
    }
    
}
