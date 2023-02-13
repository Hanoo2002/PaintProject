/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package frontend;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author ADMIN
 */
public class Jsonreadwrite {

    String filename;
    private final frontend.DrawingEngine brush;

    Jsonreadwrite(String filename, frontend.DrawingEngine brush) {
        this.filename = filename;
        this.brush = brush;
    }

    void save() {
        try {
            File file = new File(this.filename);
            FileWriter fileWriter = new FileWriter(file);
            shapes.Shape[] allShapes = brush.getShapes();
            fileWriter.write("[");
            for (int i = 0; i < allShapes.length; i++) {
                JSONObject shape = new JSONObject();
                if (allShapes[i] instanceof shapes.StraightLine) {
                    shapes.StraightLine casting = (shapes.StraightLine) allShapes[i];
                    shape.put("type", "line");
                    shape.put("xposition1", casting.getPosition().x);
                    shape.put("yposition1", casting.getPosition().y);
                    shape.put("xposition2", casting.getPosition2().x);
                    shape.put("yposition2", casting.getPosition2().y);
                    shape.put("fillcolor", casting.getFillColor().toString());
                    shape.put("bordercolor", casting.getColor().toString());
                    fileWriter.write(shape.toString());
                } else if (allShapes[i] instanceof shapes.Oval) {
                    shapes.Oval casting = (shapes.Oval) allShapes[i];
                    shape.put("type", "circle");
                    shape.put("xposition1", casting.getPosition().x);
                    shape.put("yposition1", casting.getPosition().y);
                    shape.put("horzradius", casting.getHorizontalradius());
                    shape.put("vertradius", casting.getVerticalradius());
                    shape.put("fillcolor", casting.getFillColor().toString());
                    shape.put("bordercolor", casting.getColor().toString());
                    fileWriter.write(shape.toString());
                } else if (allShapes[i] instanceof shapes.Rectangle) {
                    shapes.Rectangle casting = (shapes.Rectangle) allShapes[i];
                    shape.put("type", "rectangle");
                    shape.put("xposition1", casting.getPosition().x);
                    shape.put("yposition1", casting.getPosition().y);
                    shape.put("length", casting.getLength());
                    shape.put("width", casting.getWidth());
                    shape.put("fillcolor", casting.getFillColor().toString());
                    shape.put("bordercolor", casting.getColor().toString());
                    fileWriter.write(shape.toString());
                } else if (allShapes[i] instanceof shapes.Triangle) {

                    shapes.Triangle casting = (shapes.Triangle) allShapes[i];
                    shape.put("type", "Triangle");
                    shape.put("xposition1", casting.getPosition().x);
                    shape.put("yposition1", casting.getPosition().y);
                    shape.put("xposition2", casting.getPosition2().x);
                    shape.put("yposition2", casting.getPosition2().y);
                    shape.put("xposition3", casting.getPosition3().x);
                    shape.put("yposition3", casting.getPosition3().y);
                    shape.put("fillcolor", casting.getFillColor().toString());
                    shape.put("bordercolor", casting.getColor().toString());
                    fileWriter.write(shape.toString());
                } else {
                }
                fileWriter.write(",\n");
            }
            fileWriter.write("]");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(PaintFrontend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void read() {
        
        JSONParser parser = new JSONParser();
        FileReader read;
       
        try {
            read = new FileReader(this.filename);
            Object obj = parser.parse(read);
            JSONArray ja = (JSONArray) obj;
            shapes.Shape[] allShapes = brush.getShapes();

            for (int i = 0; i < allShapes.length; i++) {
                brush.removeShape(allShapes[i]);
            }
            System.out.println(ja.toJSONString());
            System.out.println(ja.size());
            for (int i = 0; i < ja.size(); i++) {
                JSONObject shape = new JSONObject();
                shape = (JSONObject) ja.get(i);
                String type = (String) shape.get("type");
                if (type.equalsIgnoreCase("line")) {
                    System.out.println("line");
                    long positionX = (long) shape.get("xposition1");
                    long positionY = (long) shape.get("yposition1");
                    long position2x = (long) shape.get("xposition2");
                    long position2y = (long) shape.get("yposition2");
                    Point center1 = new Point((int) positionX, (int) positionY);
                    Point center2 = new Point((int) position2x, (int) position2y);
                    shapes.StraightLine line = new shapes.StraightLine(center1, center2);
                    Color c = colorconverter(shape.get("bordercolor").toString());
                    line.setColor(c);
                    System.out.println(c);
                    Color c2 = colorconverter(shape.get("fillcolor").toString());
                    line.setFillColor(c2);
                    System.out.println(c2);
                    brush.addShape(line);
                    

                } else if (type.equalsIgnoreCase("circle")) {
                    long positionX = (long) shape.get("xposition1");
                    long positionY = (long) shape.get("yposition1");
                    long horizontalradiusValue = (long) shape.get("horzradius");
                    long verticalradiusValue = (long) shape.get("vertradius");
                    Point center = new Point((int) positionX, (int) positionY);
                    shapes.Oval circle = new shapes.Oval(center, (int) horizontalradiusValue, (int) verticalradiusValue);
                    Color c = colorconverter(shape.get("bordercolor").toString());
                    circle.setColor(c);
                    System.out.println(shape.get("bordercolor").toString());
                    Color c2 = colorconverter(shape.get("fillcolor").toString());
                    circle.setFillColor(c2);
                    System.out.println(shape.get("fillcolor").toString());
                    brush.addShape(circle);
                    
                } else if (type.equalsIgnoreCase("rectangle")) {
                    long positionX = (long) shape.get("xposition1");
                    long positionY = (long) shape.get("yposition1");
                    long length = (long) shape.get("length");
                    long width = (long) shape.get("width");
                    Point center = new Point((int) positionX, (int) positionY);
                    shapes.Rectangle rectangle = new shapes.Rectangle(center, (int) length, (int) width);
                    Color c = colorconverter(shape.get("bordercolor").toString());
                    rectangle.setColor(c);
                    System.out.println(c);
                    Color c2 = colorconverter(shape.get("fillcolor").toString());
                    System.out.println(c2);
                    rectangle.setFillColor(c2);
                    brush.addShape(rectangle);
                 
                } else if (type.equalsIgnoreCase("triangle")) {
                    System.out.println("triangle");
                    long position1X = (long) shape.get("xposition1");
                    long position1Y = (long) shape.get("yposition1");
                    long position2X = (long) shape.get("xposition2");
                    long position2Y = (long) shape.get("yposition2");
                    long position3X = (long) shape.get("xposition3");
                    long position3Y = (long) shape.get("yposition3");
                    Point center1 = new Point((int) position1X, (int) position1Y);
                    Point center2 = new Point((int) position2X, (int) position2Y);
                    Point center3 = new Point((int) position3X, (int) position3Y);
                    shapes.Triangle triangle = new shapes.Triangle(center1, center2, center3);
                    Color c = colorconverter(shape.get("bordercolor").toString());
                    System.out.println(shape.get("bordercolor").toString());
                    triangle.setColor(c);
                    Color c2 = colorconverter(shape.get("fillcolor").toString());
                    System.out.println(shape.get("fillcolor").toString());
                    triangle.setFillColor(c2);
                    brush.addShape(triangle);
                   
                } else {
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PaintFrontend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PaintFrontend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PaintFrontend.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Color colorconverter(String rgb) {
        System.out.println(rgb);
        String arr[] = rgb.split("=");

        String red = getsubtext(arr[1]);
        String green = getsubtext(arr[2]);
        String blue = getsubtext(arr[3]);
        Color c = new Color(Integer.parseInt(red), Integer.parseInt(green), Integer.parseInt(blue));
        return c;
    }

    String getsubtext(String color) {
        String s = "";
        int i = 0;
        while (i < 3 && color.length() >= i) {
            if (Character.isDigit(color.charAt(i))) {
                s = s + color.charAt(i);
                i++;
            } else {
                break;
            }
        }
        System.out.println(s);

        return s;
    }

}
