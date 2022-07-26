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

public class Row {
    private int ID;
    private int attrNum;
    
    private ArrayList<Cell> cells;
    
    public Row(int i, int a){
        cells = new ArrayList<>();
        ID = i;
        attrNum = a;
    }
    
    public void delRow(){
        for (int i = 0; i < cells.size(); i++){
            Cell c = cells.get(i);
            c = null;
        }
        cells.clear();
        //loop the cells to del all the references to them  DONE?
    }
    
    public void addCell(Attribute a, int ind){
        Object obj;
        //String s1 = a.getO().getClass().toString();
        //String s2 = new Integer("1").getClass().toString();
        if (a.getO().getClass().toString().equals(new Integer("1").getClass().toString()))
            obj = new Integer("0");
        else if (a.getO().getClass().toString().equals(new Double("2.1").getClass().toString()))
            obj = new Double("0.0");
        else if (a.getO().getClass().toString().equals(new Character('c').getClass().toString()))
            obj = new Character('e');
        else if (a.getO().getClass().toString().equals(new Boolean(true).getClass().toString()))
            obj = new Boolean(true);
        else if (a.getO().getClass().toString().equals(new Color(0xCCFFCC).getClass().toString()))
            obj = new Color(0x000000);
        else
            obj = new String("Empty");
        Cell c = new Cell(ind, obj, a);
        cells.add(c);
    }
    
    public void delCell(int ind){
        cells.remove(ind);
    }
    
    public Cell getCell(int cId){
        return cells.get(cId);
    }
    
    public int getAttrNum(){
        return attrNum;
    }
    
    public void setAttrNum(int n){
        if (n >= 0)
            attrNum = n;
    }
    
    public int getID(){
        return ID;
    }
}

