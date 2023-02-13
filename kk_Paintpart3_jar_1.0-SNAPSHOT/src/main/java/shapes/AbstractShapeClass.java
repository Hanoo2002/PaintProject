/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;


/**
 *
 * @author Samsung
 */
public abstract class AbstractShapeClass implements Shape,Moveable,Resizable {

    private java.awt.Point position;
    private java.awt.Color borderColor = Color.BLACK;
    private java.awt.Color fillColor=Color.BLACK;
    private java.awt.Point draggingPoint;
    private ArrayList<DraggingRectangle> draggingSquares;
    public AbstractShapeClass(java.awt.Point position)
    {
        this.position=position;
        draggingSquares=new ArrayList<>();

    }    
    @Override
    public final void setPosition(java.awt.Point position) {
        this.position = position;
    }

    @Override
    public java.awt.Point getPosition() {
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
    public abstract void draw(Graphics canvas);

    @Override
    public void setDraggingPoint(Point point) {
        this.draggingPoint=point;
    }

    @Override
    public Point getDraggingPoint() {
        return draggingPoint;
    }

    @Override
    public abstract boolean contains(Point point);
 
    @Override
    public abstract void moveTo(Point point);
    
    public void addResizePoint(DraggingRectangle r){
        draggingSquares.add(r);
    }
    
    public void emptyResize()
    {
        draggingSquares.removeAll(draggingSquares);
    }
    
    public ArrayList<DraggingRectangle> getResizePoint()
    {
     return draggingSquares;
    }
    
    @Override
    public abstract void setResizeEdges();

    @Override
    public abstract void resize(Point newPt,DraggingRectangle movingSquare);
    
    
}
