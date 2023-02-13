/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Samsung
 */
public class DraggingRectangle implements Shape,Moveable {

    private java.awt.Point position;
    private java.awt.Color borderColor = Color.BLACK;
    private java.awt.Color fillColor=Color.BLACK;
    private java.awt.Point draggingPoint;
    private final int length;
    private final int width;

        public DraggingRectangle(java.awt.Point position, int length, int width) {
        this.position=position;
        this.length = length;
        this.width = width;

    }
    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public void setColor(java.awt.Color color) {
        borderColor = color;
    }

    @Override
    public java.awt.Color getColor() {
        return borderColor;
    }

    @Override
    public void setFillColor(java.awt.Color color) {
        fillColor = color;
    }

    @Override
    public java.awt.Color getFillColor() {
        return fillColor;
    }

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getColor());
        canvas.drawRect(getPosition().x, getPosition().y, this.length , this.width);
        canvas.setColor(getFillColor());
        canvas.fillRect(getPosition().x, getPosition().y, this.length , this.width);    }

    @Override
    public void setDraggingPoint(Point point) {
        this.draggingPoint=point;
    }

    @Override
    public Point getDraggingPoint() {
        return draggingPoint;
    }

     @Override
    public boolean contains(Point point) {
        //boolean inX = (point.x < (getPosition().x + width ));
        //boolean inY = (point.y < (getPosition().y + length));
        //return inX&&inY;
         java.awt.Rectangle r = new java.awt.Rectangle(getPosition().x, getPosition().y,this.length ,this.width);
         return r.contains(point);
    }

    @Override
    public void moveTo(Point point) {
        setPosition(point);
    }

   
    
}
