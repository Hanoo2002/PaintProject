/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import java.awt.Color;
import javax.swing.JColorChooser;

/**
 *
 * @author Samsung
 */
public class Helpers {

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static Color launchColorPanel(String title) {
        return JColorChooser.showDialog(null, title, Color.BLACK);
    }

    public static double DistanceSquareToLine(double x, double y,
            double x1, double y1, double x2, double y2) {
        double d1 = x1 - x2;
        double d2 = y1 - y2;
        double d = d1 * d1 + d2 * d2;
        if (d <= 0) {
            return 0;
        }
        double n = x * y1 - x1 * y + x1 * y2 - x2 * y1 + x2 * y - x * y2;
        return n * n / d;
    }

}
