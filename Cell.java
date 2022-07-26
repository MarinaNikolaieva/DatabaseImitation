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
import java.awt.Color;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cell {
    private int ID;  //COLUMN number, the ROW number is contained in row's array
    private Object value;
    private Attribute attr;
    
    public Cell(int i, Object val, Attribute a){
        if (i >= 0)
            ID = i;
        attr = a;
        //String at = a.getO().getClass().toString();
        //String v = val.getClass().toString();
        if (a.getO().getClass().toString().equals(val.getClass().toString()))
            value = val;
    }
    
    public Object getVal(){
        if (attr.getTypeEntered().equals("Color")){
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(value.toString());
            ArrayList<Integer> found = new ArrayList<>();
            while (m.find())
                found.add(Integer.parseInt(m.group()));
            Color c = new Color(found.get(0), found.get(1), found.get(2));
            String res = String.format(c.getRed() + ", " + c.getGreen() + ", " + c.getBlue());
            return res;
        }
        return value;
    }
    
    public int getID(){
        return ID;
    }
    
    public void setVal(Object v){
        if (v.getClass().toString().equals(attr.getO().getClass().toString()))
            value = v;
    }
}

