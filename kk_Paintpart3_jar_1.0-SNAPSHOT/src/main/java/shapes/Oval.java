/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.Point;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import java.util.ArrayList;

/**
 *
 * @author Samsung
 */
public class Oval extends AbstractShapeClass {

    private int horizontalradius;
    private int verticalradius;

    public Oval(java.awt.Point position, int horizontalradius, int verticalradius) {
        super(position);
        this.horizontalradius = horizontalradius;
        this.verticalradius = verticalradius;
    }

    @Override
    public void draw(java.awt.Graphics canvas) {
        int centerX = getPosition().x - horizontalradius;
        int centerY = getPosition().y - verticalradius;
        canvas.setColor(getColor());
        canvas.drawOval(centerX, centerY, 2 * horizontalradius, 2 * verticalradius);
        canvas.setColor(getFillColor());
        canvas.fillOval(centerX, centerY, 2 * horizontalradius, 2 * verticalradius);
    }

    public int getHorizontalradius() {
        return horizontalradius;
    }

    public int getVerticalradius() {
        return verticalradius;
    }

    @Override
    public boolean contains(Point point) {
        double check = checkpoint(this.getPosition().x, this.getPosition().y, point.x, point.y, horizontalradius, verticalradius);
        if (check <= 1) {

            return true;
        }
        return false;
    }

    double checkpoint(double h, double k, double x, double y, double a, double b) {
        double p = ((double) Math.pow((x - h), 2) / (double) Math.pow(a, 2)) + ((double) Math.pow((y - k), 2) / (double) Math.pow(b, 2));
        return p;
    }

    @Override
    public void moveTo(Point point) {
        setPosition(getDraggingPoint());
    }

    @Override
    public void setResizeEdges() {
        emptyResize();
        int centerX = getPosition().x - horizontalradius;
        int centerY = getPosition().y - verticalradius;
        Point upLeft = new Point(centerX, centerY);
        Point upRight = new Point(upLeft.x + 2 * horizontalradius, upLeft.y);
        Point downLeft = new Point(upLeft.x, upLeft.y + 2 * verticalradius);
        Point downRight = new Point(upLeft.x + 2 * horizontalradius, upLeft.y + 2 * verticalradius);
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
        int centerX, centerY, dx, dy;
        ArrayList<DraggingRectangle> vertices = getResizePoint();
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(movingSquare)) {
                switch (i) {
                    case 0:
                        centerX = getPosition().x - horizontalradius;
                        centerY = getPosition().y - verticalradius;

                        dx = -newPt.x + centerX;
                        dy = -newPt.y + centerY;
                        if (horizontalradius + dx < 0) {
                            centerX = getPosition().x + horizontalradius;
                            dx = newPt.x - centerX;
                        }
                        if (verticalradius + dy < 0) {
                            centerY = getPosition().y + verticalradius;
                            dy = newPt.y - centerY;
                        }
                        horizontalradius += dx;
                        verticalradius += dy;

                        break;
                    case 1:
                        centerX = getPosition().x + horizontalradius;
                        centerY = getPosition().y - verticalradius;
                        dx = newPt.x - centerX;
                        dy = -newPt.y + centerY;
                        if (horizontalradius + dx < 0) {
                            centerX = getPosition().x - horizontalradius;
                            dx = -newPt.x + centerX;
                        }
                        if (verticalradius + dy < 0) {
                            centerY = getPosition().y + verticalradius;
                            dy = newPt.y - centerY;
                        }
                        horizontalradius += dx;
                        verticalradius += dy;
                        break;
                    case 2:
                        centerX = getPosition().x - horizontalradius;
                        centerY = getPosition().y + verticalradius;
                        dx = -newPt.x + centerX;
                        dy = newPt.y - centerY;
                        if (horizontalradius + dx < 0) {
                            centerX = getPosition().x + horizontalradius;
                            dx = newPt.x - centerX;;
                        }
                        if (verticalradius + dy < 0) {
                            centerY = getPosition().y - verticalradius;
                            dy = -newPt.y + centerY;
                        }
                        horizontalradius += dx;
                        verticalradius += dy;
                        break;
                    case 3:
                        centerX = getPosition().x + horizontalradius;
                        centerY = getPosition().y + verticalradius;
                        dx = newPt.x - centerX;
                        dy = newPt.y - centerY;
                        if (horizontalradius + dx < 0) {
                            centerX = getPosition().x - horizontalradius;
                            dx = -newPt.x + centerX;
                        }
                        if (verticalradius + dy < 0) {
                            centerY = getPosition().y - verticalradius;
                            dy = -newPt.y + centerY;
                        }
                        horizontalradius += dx;
                        verticalradius += dy;
                        break;
                    default:
                        break;
                }

            }
        }

    }

}
