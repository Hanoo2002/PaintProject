/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import shapes.Shape;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JPanel;
import shapes.DraggingRectangle;
/**
 *
 * @author Samsung
 */
public class DrawingShape extends JPanel implements DrawingEngine {

    public ArrayList<shapes.Shape> shapes;
    public ArrayList<shapes.DraggingRectangle> squares;
    private shapes.AbstractShapeClass movingShape;
    private shapes.AbstractShapeClass movingShape2;
    private shapes.DraggingRectangle movingSquare;
    public boolean flag = false;

    public DrawingShape() {
        shapes = new ArrayList<>();
        squares = new ArrayList<>();
        ClickListener cListener = new ClickListener();
        this.addMouseListener(cListener);

        DragListener dListner = new DragListener();
        this.addMouseMotionListener(dListner);
    }

    @Override
    public void paintComponent(Graphics canvas) {
        super.paintComponent(canvas);
        for (shapes.Shape s : shapes) {
            s.draw(canvas);
        }
        for (shapes.Shape s : squares) {
            s.draw(canvas);
        }
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        refresh(null);

    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        deletesquares();
        refresh(null);
    }

    /**
     *
     * @return
     */
    @Override
    public shapes.Shape[] getShapes() {
        shapes.Shape[] allShapes = new shapes.Shape[shapes.size()];
        allShapes = shapes.toArray(allShapes);
        return allShapes;
    }

    @Override
    public void refresh(Graphics canvas) {
        repaint();
    }
    void deletesquares() {
        squares.removeAll(squares);
        refresh(null);
    }

    private class ClickListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent evt) {
            Point currentPoint = evt.getPoint();
            boolean flag = false;
            for (shapes.Shape s : shapes) {
                
                shapes.AbstractShapeClass currentShape = (shapes.AbstractShapeClass) s;
                ArrayList<DraggingRectangle> points = currentShape.getResizePoint();
                if (currentShape.contains(currentPoint)) {
                    flag = true;
                    movingShape = currentShape;
                    movingSquare=null;
                    movingShape2 = null;
                    movingShape.setResizeEdges();
                    deletesquares();
                    for (DraggingRectangle r : points) {
                        squares.add(r);
                        refresh(null);
                    }
                }
                for (DraggingRectangle r : squares) {
                    if (r.contains(currentPoint)) {
                        movingShape2 = movingShape;
                        movingSquare = r;
                        movingShape = null;
                        return;
                    }
                }
                if (!flag) {
                    deletesquares();
                    movingShape = null;
                    movingSquare = null;
                    movingShape2 = null;
                    
                }
            }
        }
    }

    private class DragListener extends MouseMotionAdapter {

        @Override
        public void mouseDragged(MouseEvent evt) {
            Point newPoint = evt.getPoint();
            deletesquares();
            if (movingShape != null) {
                
                ArrayList<DraggingRectangle> points = movingShape.getResizePoint();
                movingShape.setDraggingPoint(newPoint);
                movingShape.moveTo(movingShape.getDraggingPoint());
                movingShape.setResizeEdges();//;addsquares(currentShape);
                for (DraggingRectangle r : points) {
                    squares.add(r);
                }
            }
            else if (movingShape2 != null) {
                refresh(null);
                movingShape2.resize(newPoint,movingSquare);
               
            }
            refresh(null);
        }

    }

}
