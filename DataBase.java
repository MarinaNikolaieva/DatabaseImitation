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

public class DataBase {
    public String name;
    public String descript;
    private int index;
    
    private ArrayList<Table> tables;  //HashMap maybe?
    
    public DataBase(String n, String d, int ind){  //create an basic database - activates when the program is started - Constructor
        name = n;
        descript = d;
//        if (0 == GUI.basesSize())
//            index = 0;
//        else if (GUI.getLastBase().getIndex() == ind){
//            index = GUI.getLastBase().getIndex() + 1;
//        }
//        else
//            index = ind;
        index = ind;
        tables = new ArrayList<>();
    }
    
    public Table getTable(int index){
        for (int i = 0; i < tables.size(); i++)
            if (tables.get(i).getIndex() == index)
                return tables.get(i);
        return null;
    }
    
    public Table getTableInOrder(int num){
        if (num >= tables.size())
            return null;
        return tables.get(num);
    }
    
    public void delT(int index){
        for (int i = 0; i < tables.size(); i++)
            if (tables.get(i).getIndex() == index)
                tables.remove(i);
    }
    
    public void addT(Table t){
        tables.add(t);
    }
    
    public int getIndex(){
        return index;
    }
    
    public void delDB(){  //clear database, activated remotely
        for (int i = 0; i < tables.size(); i++){
            Table t = tables.get(i);
            t.deleteT();
            t = null;
        }
        tables.clear();
        //run the loop to delete all tables  DONE?
        for (int i = 0; i < GUI.basesSize(); i++){
            if (GUI.getBase(i).getIndex() == index)
                GUI.delDB(i);
        }
    }
    
    public int tableNum(){
        return tables.size();
    }
    
    public void change (String n, String d){
        if (!n.equals(""))
            name = n;
        if (!d.equals(""))
            descript = d;
    }
    
//    public void addTable(String name){  //NEEDED redo - doesn't get anything. Call the constructor instead  DONE?
//        Table t = new Table(name, tables.size());
//        t.addAttr(new Attribute("БазовеІм'я", "Integer", 0, 0, null, null));
//        t.addRow();
//        tables.add(new Table(name, tables.size()));
//    }
    
//    public void delTable(Table t){
//        t.deleteT();
//        for (int i = 0; i < tables.size(); i++){
//            if (tables.get(i).getIndex() == t.getIndex())
//                tables.remove(i);
//        }
//        //loop the tables. Reset the indexes of all that left  DONE?
//        //This has to be done BEFORE deleting!
//    }
    
    public Table union(int a, int b){
        String n = getTable(a).name + " + " + getTable(b).name;
        Table tab = new Table(n, tables.size());
        for (int i = 0; i < getTable(a).attrNum(); i++){
            tab.addAttr(getTable(a).getAttr(i));
        }
        int tem = getTable(a).attrNum();
        for (int i = 0; i < getTable(b).attrNum(); i++){
            String aName = getTable(b).getAttr(i).name;
            String ob = getTable(b).getAttr(i).getTypeEntered();
            String lv = getTable(b).getAttr(i).getLowVal();
            String uv = getTable(b).getAttr(i).getUpVal();
            Attribute atr;
            if (lv != null)
                atr = new Attribute(aName, ob, tem, 1, lv, uv);
            else
                atr = new Attribute(aName, ob, tem, 0, null, null);
            tab.addAttr(atr);
            tem++;
            //tab.getAttr(tab.attrNum() - 1).setIndex(tab.attrNum() - 1);
        }
        for (int i = 0; i < tab.attrNum(); i++){  //checking for name duplicates
            for (int j = i + 1; j < tab.attrNum(); j++){
                if (tab.getAttr(i).name.equals(tab.getAttr(j).name))
                    tab.getAttr(j).name = tab.getAttr(j).name + "new";
            }
        }
        int c = 0;
        //NEEDED! Add rows to tab table. Row num = tables.get(a).rows.size() * tables.get(b).rows.size()  DONE
        int temp = getTable(a).rowNum() * getTable(b).rowNum();
        for (int i = 0; i < temp; i++)
            tab.addRow();
        for (int i = 0; i < getTable(a).rowNum(); i++){  //wat???
            for (int j = 0; j < getTable(b).rowNum(); j++){
                for (int k = 0; k < getTable(a).attrNum(); k++){
                    //tab.rows.get(c).addCell(tab.attributes.get(k), k);
                    tab.getRowInOrder(c).getCell(k).setVal(getTable(a).getRowInOrder(i).getCell(k).getVal());
                }
                for (int k = 0; k < tables.get(b).attrNum(); k++){
                    //tab.rows.get(c).addCell(tab.attributes.get(tables.get(a).attrNum() + k), tables.get(a).attrNum() + k);
                    tab.getRowInOrder(c).getCell(getTable(a).attrNum() + k).setVal(getTable(b).getRowInOrder(j).getCell(k).getVal());
                }
                c++;
            }
        }
        tables.add(tab);
        return tab;
        //create a new table
        //add it to the database array
        //unite the attributes (checking for duplicates!)
        //transfer/copy the rows & cells
        //DONE?
    }
}
