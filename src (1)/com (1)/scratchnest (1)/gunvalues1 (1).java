/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scratchnest;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class gunvalues1 {
    private  StringProperty Epc;
    private  StringProperty name;
    private  IntegerProperty totalattendance;
    private final SimpleIntegerProperty subtotal1;     
     
    public gunvalues1( String Epc, String name,String email,Integer totalattendance) {
        this.Epc = new SimpleStringProperty(Epc);
        this.name = new SimpleStringProperty(name);
        this.totalattendance = new SimpleIntegerProperty(totalattendance);
        this.subtotal1=new SimpleIntegerProperty();
        NumberBinding multiplication=Bindings.multiply(this.totalattendance,100);
        NumberBinding divide=Bindings.divide(multiplication, 365);
        this.subtotal1Property().bind(divide);
      
    }
    public String getEpc() {
        return Epc.get();
    }
    public void setEpc(String value){
        Epc.set(value);
    }     
    public StringProperty EpcProperty() {
        return Epc;
    }
    public String getname() {
        return name.get();
    }
    public void setname(String value){
        name.set(value);
    }
    public StringProperty nameProperty() {
        return name;
    }
    public Integer gettotalattendance() {
        return totalattendance.get();
    }
    public void settotalattendance(Integer value){
        totalattendance.set(value);
    }
    public IntegerProperty totalattendanceProperty() {
        return totalattendance;
    }
    public IntegerProperty getsubtotal1(){
        return subtotal1;
    }
    public void setsubtotal1(Integer value){
       subtotal1.set(value);
    }
    public IntegerProperty subtotal1Property() {
        return subtotal1;
    }
    
}
