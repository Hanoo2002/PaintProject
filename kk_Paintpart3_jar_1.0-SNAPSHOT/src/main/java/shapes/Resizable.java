/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package shapes;

import java.awt.Point;

/**
 *
 * @author Samsung
 */
public interface Resizable {
    public abstract void setResizeEdges();

    public abstract void resize(Point newPt,DraggingRectangle movingSquare);
    
}
