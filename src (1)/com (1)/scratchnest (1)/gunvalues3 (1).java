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

public class gunvalues3 {
    private final  StringProperty Epc;
    private final  StringProperty name;  
    private final  StringProperty email;   
    private final  IntegerProperty totalattendance;
    private final  StringProperty lastattendance;
    private final  SimpleIntegerProperty subtotal2;
    
    
    public gunvalues3( String Epc, String name, String email,String lastattendance,Integer totalattendance) {
     
        this.Epc = new SimpleStringProperty(Epc);
        this.name = new SimpleStringProperty(name);       
        this.email=new SimpleStringProperty(email);
        this.lastattendance=new SimpleStringProperty(lastattendance);
        this.totalattendance = new SimpleIntegerProperty(totalattendance);
        this.subtotal2=new SimpleIntegerProperty();
        NumberBinding multiplication=Bindings.multiply(this.totalattendance,100);
        NumberBinding divide=Bindings.divide(multiplication, 365);
        this.subtotal2Property().bind(divide);
       
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
    public String getemail() {
        return email.get();
    }
    public void setemail(String value){
        email.set(value);
    }
    public StringProperty emailProperty() {
        return email;
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
    public String getlastattendance() {
        return lastattendance.get();
    }
    public void setlastattendance(String value){
      lastattendance.set(value);
    }
    public StringProperty lastattendanceProperty() {
        return lastattendance;
    }
    public IntegerProperty getsubtotal2(){
        return subtotal2;
    }
    public void setsubtotal2(Integer value){
       subtotal2.set(value);
    }
    public IntegerProperty subtotal2Property() {
        return subtotal2;
    }
}

      

