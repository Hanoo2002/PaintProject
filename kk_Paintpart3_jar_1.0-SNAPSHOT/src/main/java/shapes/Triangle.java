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
public class Triangle extends AbstractShapeClass {

    private java.awt.Point position2;
    private java.awt.Point position3;

    private int[] xPoints;
    private int[] yPoints;

    public Triangle(java.awt.Point position, java.awt.Point position2, java.awt.Point position3) {
        super(position);
        this.position2 = position2;
        this.position3 = position3;

    }

    public Point getPosition2() {
        return position2;
    }

    public Point getPosition3() {
        return position3;
    }

    public void setPosition2(Point position2) {
        this.position2 = position2;
    }

    public void setPosition3(Point position3) {
        this.position3 = position3;
    }

    public int[] getxPoints() {
        return xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    @Override
    public void draw(java.awt.Graphics canvas) {
        canvas.setColor(getColor());
        xPoints = new int[]{this.getPosition().x, this.getPosition2().x, this.getPosition3().x};
        yPoints = new int[]{this.getPosition().y, this.getPosition2().y, this.getPosition3().y};
        canvas.drawPolygon(xPoints, yPoints, 3);
        canvas.setColor(getFillColor());
        canvas.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public boolean contains(Point point) {
        int checkX = point.x;
        int checkY = point.y;
        int x1 = this.xPoints[0];
        int x2 = this.xPoints[1];
        int x3 = this.xPoints[2];
        int y1 = this.yPoints[0];
        int y2 = this.yPoints[1];
        int y3 = this.yPoints[2];
        float A = area(x1, y1, x2, y2, x3, y3);

        /* Calculate area of triangle PBC */
        float A1 = area(checkX, checkY, x2, y2, x3, y3);

        /* Calculate area of triangle PAC */
        float A2 = area(x1, y1, checkX, checkY, x3, y3);

        /* Calculate area of triangle PAB */
        float A3 = area(x1, y1, x2, y2, checkX, checkY);

        /* Check if sum of A1, A2 and A3 is same as A */
        return (A == A1 + A2 + A3);
    }

    @Override
    public void moveTo(Point point) {

        int movedX = getDraggingPoint().x - this.getPosition().x;
        int movedY = getDraggingPoint().y - this.getPosition().y;
        setPosition(getDraggingPoint());
        position2.x = position2.x + movedX;
        position2.y = position2.y + movedY;
        position3.x = position3.x + movedX;
        position3.y = position3.y + movedY;
    }

    private float area(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (float) Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
    }

    @Override
    public void setResizeEdges() {
        emptyResize();
        shapes.DraggingRectangle rect1 = new shapes.DraggingRectangle(getPosition(), 10, 10);
        shapes.DraggingRectangle rect2 = new shapes.DraggingRectangle(getPosition2(), 10, 10);
        shapes.DraggingRectangle rect3 = new shapes.DraggingRectangle(getPosition3(), 10, 10);
        addResizePoint(rect1);
        addResizePoint(rect2);
        addResizePoint(rect3);
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
                    case 2:
                        setPosition3(newPt);
                        break;
                    default:
                        break;
                }

            }
        }
    }

   

}
