/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ITLMPack;

/**
 *
 * @author Marina Nik
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;

public class Attribute {
    //There will be no attribute Type editing! Remove and recreate it if you need!
    public String name;
    private Object type;
    private int index;
    private Color LI;
    private Color UI;
    private String typeName;
    
    private ArrayList<String> allowedTypes = new ArrayList<String>(Arrays.asList("Integer", "Boolean", "Double", "Character", "String", "Color"));
    
    public Attribute(String n, String ob, int i, int inter, String LL, String UL){
        name = n;
        
        Object obj = new Object();
        if (!"".equals(ob)){
            if (allowedTypes.contains(ob)){
                if (ob.equals("Integer"))
                    obj = new Integer("0");
                else if (ob.equals("Double"))
                    obj = new Double("0.0");
                else if (ob.equals("Character"))
                    obj = new Character('e');
                else if (ob.equals("Boolean"))
                    obj = new Boolean(true);
                else if (ob.equals("Color"))
                    obj = new Color(0x000000);
                else
                    obj = new String("Empty");
                typeName = ob;
            }
            else{
                obj = new String("Empty");
                typeName = "String";
            }
            if (ob.equals("Color")){
                Color newColL;
                Color newColU;
                if (inter == 1){
                    newColL = Color.decode(LL);
                    LI = newColL;
                    newColU = Color.decode(UL);
                    UI = newColU;
                    if (isLesser(UI, LI)){
                        LI = newColU;
                        UI = newColL;
                    }
                }
                else{
                    LI = null;
                    UI = null;
                }
            }
            //NEEDED! Finish this part DONE?
            else{
                LI = null;
                UI = null;
            }
        }
        type = obj;
        index = i;
    }
    
    public String getType(int ind){
        return allowedTypes.get(ind);
    }
    
    public String getTypeEntered(){
        return typeName;
    }
    
    public int typeCount(){
        return allowedTypes.size();
    }
    
    public int getIndex(){
        return index;
    }
    
    public boolean isLesser(Color test, Color meas){
        boolean lesserFull = (test.getRed() < meas.getRed()) && (test.getGreen() < meas.getGreen()) && (test.getBlue() < meas.getBlue());
        boolean lesserRed = (test.getRed() < meas.getRed()) && (test.getGreen() <= meas.getGreen()) && (test.getBlue() <= meas.getBlue());
        boolean lesserGreen = (test.getRed() <= meas.getRed()) && (test.getGreen() < meas.getGreen()) && (test.getBlue() <= meas.getBlue());
        boolean lesserBlue = (test.getRed() <= meas.getRed()) && (test.getGreen() <= meas.getGreen()) && (test.getBlue() < meas.getBlue());
        boolean equalFull = (test.getRed() == meas.getRed()) && (test.getGreen() == meas.getGreen()) && (test.getBlue() == meas.getBlue());
        if (lesserFull || lesserRed || lesserGreen || lesserBlue || equalFull)
            return true;
        else
            return false;
    }
    
    public String getLowVal(){
        if (LI == null)
            return null;
        String res = String.format(LI.getRed() + ", " + LI.getGreen() + ", " + LI.getBlue());
        return res;
    }
    
    public Color getLowColVal(){
        if (LI == null)
            return null;
        return LI;
    }
    
    public void setLowVal(Color col){
        if (col == null)
            LI = null;
        else if (getO().toString().equals("Color")){
            try{
                LI = col;
                if (isLesser(UI, LI)){
                    col = LI;
                    LI = UI;
                    UI = col;
                }
            }
            catch (Exception ex){
                System.out.printf("\nInvalid data format! Value setting failed!");
            }
        }
    }
    
    public String getUpVal(){
        if (UI == null)
            return null;
        String res = String.format(UI.getRed() + ", " + UI.getGreen() + ", " + UI.getBlue());
        return res;
    }
    
    public Color getUpColVal(){
        if (UI == null)
            return null;
        return UI;
    }
    public void setUpVal(Color col){
        if (col == null)
            UI = null;
        else if (getO().toString().equals("Color")){
            try{
                UI = col;
                if (isLesser(UI, LI)){
                    col = UI;
                    UI = LI;
                    LI = col;
                }
            }
            catch (Exception ex){
                System.out.printf("\nInvalid data format! Value setting failed!");
            }
        }
    }
    
    public Object getO(){
        return type;
    }
}

