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

public class Table {
    public String name;
    private int index;
    
    private ArrayList<Row> rows;
    private ArrayList<Attribute> attributes;  //Make this private?
    
    public Table(String n, int x){
        name = new String(n);
        index = new Integer(x);
        rows = new ArrayList<>();
        attributes = new ArrayList<>();
    }
    
    public void deleteT(){
        for (int i = 0; i < rows.size(); i++){
            rows.get(i).delRow();
            Row r = rows.get(i);
            r = null;
        }
        rows.clear();
        //run a loop to delete all the rows  DONE?
        for (int i = 0; i < attributes.size(); i++){
            Attribute a = attributes.get(i);
            a = null;
        }
        attributes.clear();
        //run a loop to delete all the attributes  DONE?
    }
    
    public int getIndex(){
        return index;
    }

    public int rowNum(){
        return rows.size();
    }
    
    public int attrNum(){
        return attributes.size();
    }
    
    public Attribute getAttr(int index){
        for (int i = 0; i < attributes.size(); i++)
            if (attributes.get(i).getIndex() == index)
                return attributes.get(i);
        return null;
    }
    
    public Row getRow(int index){
        for (int i = 0; i < rows.size(); i++)
            if (rows.get(i).getID() == index)
                return rows.get(i);
        return null;
    }
    
    public Row getRowInOrder(int num){
        return rows.get(num);
    }
    
    public Attribute getAttrInOrder(int num){
        return attributes.get(num);
    }
    
    public void addRow(){
        Row r;
        if (rows.isEmpty()){
            r = new Row(0, attributes.size());
            rows.add(r);
            for (int i = 0; i < attributes.size(); i++)
                r.addCell(attributes.get(i), i);
        }
        else{
            r = new Row(rows.get(rows.size() - 1).getID() + 1, attributes.size());
            rows.add(r);
            for (int i = 0; i < attributes.size(); i++)
                r.addCell(attributes.get(i), i);
        }
    }
    
    public void delRow(int i){
        int det = 0;
        for (int j = 0; j < rows.size(); j++){
            if (rows.get(j).getID() == i){
                rows.get(j).delRow();
                det = j;
            }
        }
        rows.remove(det);
    }
    
    public void delRowInOrder(int num){
        rows.get(num).delRow();
        rows.remove(num);
    }
    
    public void addAttr(Attribute at){
        //Attribute atr = new Attribute(at.getName(), at.getO(), at.getIndex());
        if (at.getIndex() < 0){
            Attribute a = new Attribute(null, null, -1, -1, null, null);
            attributes.add(a);
            for (int i = 0; i < rows.size(); i++){
                rows.get(i).setAttrNum(rows.get(i).getAttrNum() + 1);
                rows.get(i).addCell(a, rows.get(i).getAttrNum());
            }
            a = null;
        }
        else{
            attributes.add(at);
            for (int i = 0; i < rows.size(); i++){
                rows.get(i).setAttrNum(rows.get(i).getAttrNum() + 1);
                rows.get(i).addCell(at, rows.get(i).getAttrNum());
            }
            //atr = null;
        }
        //loop the rows, add new empty cells under the new attribute  DONE?
    }
    
    public void delAttrInOrder(int ind){
        for (int i = 0; i < rows.size(); i++){
            rows.get(i).delCell(attributes.get(ind).getIndex());
            rows.get(i).setAttrNum(rows.get(i).getAttrNum() - 1);
        }
        attributes.remove(attributes.get(ind));
        //loop the rows list, remove the cells under this attribute
        //ALSO loop the attributes list in the table, re-set the indexes
        //When looping the rows, re-set the indexes of their cells as well!
        //DONE?
    }
    
    public void delAttr(int ind){
        for (int i = 0; i < attributes.size(); i++){
            if (attributes.get(i).getIndex() == ind){
                delAttrInOrder(i);
                break;
            }
        }
    }
}

