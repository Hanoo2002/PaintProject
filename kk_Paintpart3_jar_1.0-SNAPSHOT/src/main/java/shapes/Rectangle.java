/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Samsung
 */
public class Rectangle extends AbstractShapeClass {

    private int length;
    private int width;

    public Rectangle(java.awt.Point position, int length, int width) {
        super(position);
        this.length = length;
        this.width = width;

    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void draw(java.awt.Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawRect(getPosition().x, getPosition().y, this.width, this.length);
        canvas.setColor(getFillColor());
        canvas.fillRect(getPosition().x, getPosition().y, this.width, this.length);

    }

//    @Override
//    public boolean contains(Point point) {
//        //boolean inX = (point.x < (getPosition().x + width ));
//        //boolean inY = (point.y < (getPosition().y + length));
//        //return inX&&inY;
//        java.awt.Rectangle r = new java.awt.Rectangle(getPosition().x, getPosition().y, this.width, this.length);
//        return r.contains(point);
//    }
    @Override
    public boolean contains(Point point) {
        Point position2 = new Point(this.getPosition().x + width, this.getPosition().y);
        Point position3 = new Point(this.getPosition().x, this.getPosition().y + length);
        Point position4 = new Point(this.getPosition().x + width, this.getPosition().y + length);
        float A1 = area(this.getPosition(), point, position3);
        float A2 = area(this.getPosition(), point, position2);
        float A3 = area(position3, point, position4);
        float A4 = area(position2, point, position4);
        System.out.println("a1 =" + A1 + "a2 =" + A2 + "a3 =" + A3 + "a4 =" + A4);
        System.out.println("sum of areas: " + (A1 + A2 + A3 + A4));
        System.out.println("length: " + getDistance(this.getPosition(), position3));
        System.out.println("width: " + getDistance(this.getPosition(), position2));
        float areaofrect = (float) (getDistance(this.getPosition(), position3) * getDistance(this.getPosition(),position2));
        System.out.println("total area: " + areaofrect);
        if ((int) areaofrect == (int) (A1 + A2 + A3 + A4)) {
            return true;
        }
        return false;

    }

    public static float area(Point A, Point B, Point C) {
        float area = (A.x * (B.y - C.y) + B.x * (C.y - A.y) + C.x * (A.y - B.y)) / 2.0f;
        return Math.abs(area);
    }

    float getDistance(Point a, Point b) {
        float tall = (float) Math.sqrt(Math.pow((a.y - b.y), 2) + Math.pow((a.x - b.x), 2));
        return tall;
    }

    @Override
    public void moveTo(Point point) {
        setPosition(getDraggingPoint());
    }

    @Override
    public void setResizeEdges() {
        emptyResize();
        Point upLeft = new Point(getPosition().x - 5, getPosition().y - 5);
        Point upRight = new Point(upLeft.x + width, upLeft.y);
        Point downLeft = new Point(upLeft.x, upLeft.y + length);
        Point downRight = new Point(upLeft.x + width, upLeft.y + length);
        shapes.DraggingRectangle rect1 = new shapes.DraggingRectangle(upLeft, 10, 10);
        shapes.DraggingRectangle rect2 = new shapes.DraggingRectangle(upRight, 10, 10);
        shapes.DraggingRectangle rect3 = new shapes.DraggingRectangle(downLeft, 10, 10);
        shapes.DraggingRectangle rect4 = new shapes.DraggingRectangle(downRight, 10, 10);
        addResizePoint(rect1);
        addResizePoint(rect2);
        addResizePoint(rect3);
        addResizePoint(rect4);
    }

    @Override
    public void resize(Point newPt, DraggingRectangle movingSquare) {
        int movedX, movedY, ptX, ptY;
        Point newPosition;
        ArrayList<DraggingRectangle> vertices = getResizePoint();
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(movingSquare)) {
                switch (i) {
                    case 0:
                        ptX = newPt.x;
                        ptY = newPt.y;
                        movedX = -ptX + getPosition().x;
                        movedY = -ptY + getPosition().y;
                        width += movedX;
                        length += movedY;
                        setPosition(newPt);
                        break;
                    case 1:
                        ptX = getPosition().x + width;
                        ptY = getPosition().y;
                        movedX = newPt.x - ptX;
                        movedY = -newPt.y + ptY;
                        width += movedX;
                        length += movedY;
                        newPosition = new Point(getPosition().x, getPosition().y - movedY);
                        setPosition(newPosition);
                        break;
                    case 2:
                        ptX = getPosition().x;
                        ptY = getPosition().y + length;
                        movedX = -newPt.x + ptX;
                        movedY = newPt.y - ptY;
                        width += movedX;
                        length += movedY;
                        newPosition = new Point(getPosition().x - movedX, getPosition().y);
                        setPosition(newPosition);
                        break;
                    case 3:
                        ptX = getPosition().x + width;
                        ptY = getPosition().y + length;
                        movedX = newPt.x - ptX;
                        movedY = newPt.y - ptY;
                        width += movedX;
                        length += movedY;
                        break;
                    default:
                        break;
                }

            }
        }

    }

}
