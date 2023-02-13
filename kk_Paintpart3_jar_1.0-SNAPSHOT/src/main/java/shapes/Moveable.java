/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shapes;

import java.awt.Point;

/**
 *
 * @author Samsung
 */
public interface Moveable {
    public void setDraggingPoint(Point point);
    public Point getDraggingPoint();
    public boolean contains(Point point);
    public void moveTo(Point point);
}
