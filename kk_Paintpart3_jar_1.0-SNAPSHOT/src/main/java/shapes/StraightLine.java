/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Samsung
 */
public class StraightLine extends AbstractShapeClass {

    private java.awt.Point position2;

    public void setPosition2(Point position2) {
        this.position2 = position2;
    }

    public StraightLine(java.awt.Point position, java.awt.Point position2) {
        super(position);
        this.position2 = position2;
    }

    public Point getPosition2() {
        return position2;
    }

    @Override
    public void draw(java.awt.Graphics canvas) {
        canvas.setColor(getColor());
        Graphics2D g2 = (Graphics2D) canvas;
        g2.setStroke(new BasicStroke(4));
        canvas.drawLine(getPosition().x, getPosition().y, position2.x, position2.y);
    }

    @Override
    public boolean contains(Point point) {
        double distance1 = getDistance(point, getPosition());
        double distance2 = getDistance(point, position2);
        System.out.println(distance1);
        System.out.println(distance2);
        System.out.println(getDistance(getPosition(), position2));
        if ((int)(distance1 + distance2) == (int)getDistance(getPosition(), position2)) {
            return true;
        } else {
            return false;
        }
    }

    double getDistance(Point a, Point b) {
        double tall = Math.sqrt(Math.pow((a.y - b.y), 2) + Math.pow((a.x - b.x), 2));
        return tall;
    }

    @Override
    public void moveTo(Point point) {
        int movedX=getDraggingPoint().x-this.getPosition().x;
        int movedY=getDraggingPoint().y-this.getPosition().y;
        this.setPosition(getDraggingPoint());
         position2.x=position2.x+movedX;
         position2.y=position2.y+movedY;
    }

    @Override
    public void setResizeEdges() {
        emptyResize();
        shapes.DraggingRectangle rect1 = new shapes.DraggingRectangle(getPosition(), 10, 10);
        shapes.DraggingRectangle rect2 = new shapes.DraggingRectangle(getPosition2(), 10, 10);
        addResizePoint(rect1);
        addResizePoint(rect2);
    }

    @Override
    public void resize(Point newPt,DraggingRectangle movingSquare) {
                ArrayList<DraggingRectangle> vertices = getResizePoint();

        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(movingSquare)) {
                switch (i) {
                    case 0:
                        setPosition(newPt);
                        break;
                    case 1:
                        setPosition2(newPt);
                        break;
                    default:
                        break;
                }

            }
        }
    }

   
}
