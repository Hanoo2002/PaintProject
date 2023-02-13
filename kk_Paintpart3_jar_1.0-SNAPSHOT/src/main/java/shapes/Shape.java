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
public interface Shape {

    public void setPosition(java.awt.Point position);
    public java.awt.Point getPosition();
    public void setColor(java.awt.Color color);
    public java.awt.Color getColor();
    public void setFillColor(java.awt.Color color);
    public java.awt.Color getFillColor();
    public void draw(java.awt.Graphics canvas);
}
