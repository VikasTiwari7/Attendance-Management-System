/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scratchnest.fxml;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Communication Lab 10
 */
public class gunvalues {

    public  StringProperty epc;
    public  StringProperty date;
    public  StringProperty name;
    public  IntegerProperty total;
    private final SimpleIntegerProperty subtotal;      
     
    public gunvalues(String epc, String date,String name,Integer total )
    {
        this.epc = new SimpleStringProperty(epc);
        this.date = new SimpleStringProperty(date);
        this.name=new SimpleStringProperty(name);        
        this.total=new SimpleIntegerProperty(total);
        this.subtotal=new SimpleIntegerProperty();
        NumberBinding multiplication=Bindings.multiply(this.total,100);
        NumberBinding divide=Bindings.divide(multiplication, 365);
        this.subtotalProperty().bind(divide);
    }   
    public String getepc() {
        return epc.get();
    }
     public void setepc(String value){
        epc.set(value);
    }     
    public StringProperty epcProperty() {
        return epc;
    }      
    public String getname(){
          return name.get();
    }
    public void setname(String value){
        name.set(value);
    }
    public StringProperty nameProperty(){
        return name;
    }
    public String getdate() {
        return date.get();
    }
    public void setdate(String value){
        date.set(value);
    }
    public StringProperty dateProperty() {
        return date;
    }
    public Integer gettotal(){
        return total.get();
    }
    public void settotal(Integer value){
        total.set(value);
    }
    public IntegerProperty totalProperty() {
        return total;
    }
    public IntegerProperty getsubtotal(){
        return subtotal;
    }
    public void setsubtotal(Integer value){
       subtotal.set(value);
    }
    public IntegerProperty subtotalProperty() {
        return subtotal;
    }  
 
}

