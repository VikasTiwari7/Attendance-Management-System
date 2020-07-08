package com.scratchnest;

import com.fazecast.jSerialComm.SerialPort;
import com.jfoenix.controls.JFXButton;
import static com.scratchnest.ArmsTabViewController.con;
import com.scratchnest.fxml.gunvalues;
import com.thingmagic.ReadExceptionListener;
import com.thingmagic.ReadListener;
import com.thingmagic.Reader;
import com.thingmagic.ReaderException;
import com.thingmagic.SimpleReadPlan;
import com.thingmagic.TagProtocol;
import com.thingmagic.TagReadData;
import connectivity.dbcon;
import connectivity.onlinedbcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.util.HashSet;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class ArmsTabViewController implements Initializable {
    @FXML
    private TableView<gunvalues> details;
    @FXML
    private TableColumn<gunvalues, String> tepc;
    private ObservableList<gunvalues> data;
    private ObservableList<gunvalues1> data1;
    private ObservableList<gunvalues3> data2;   
    @FXML
    private BarChart<String ,Double> piechart;     
    @FXML
    private Label readerConnectionStatusLabel;    
    @FXML
    private Label readerConnectionStatusLabel1;    
    protected static Reader rfidReader = null;    
    volatile static StringProperty tag = new SimpleStringProperty();    
    protected static SerialPort rfidReaderSerialPort = null;    
    protected static AudioInputStream audioInputStream;    
    protected  static Clip clip; 
    @FXML
    private TableColumn<gunvalues, String> tdate;
    @FXML
    private TableColumn<gunvalues, String> tname;   
    @FXML
    private TableColumn<gunvalues, String> tatt;   
    public static Connection con = dbcon.getconnect();
    public static Connection con1 = onlinedbcon.getconnect();
    @FXML
    private TableView<gunvalues1> person;
    @FXML
    private TableColumn<gunvalues1, String> pid;
    @FXML
    private TableColumn<gunvalues1, String> pname;   
    @FXML
    private TableColumn<gunvalues1, String> pemail;
    @FXML
    private TableColumn<gunvalues1, Integer> pattendance;
    @FXML
    private TableView<gunvalues3> history;
    @FXML
    private TableColumn<gunvalues3,String> hname;
    @FXML
    private TableColumn<gunvalues3, String> hepc;
    @FXML
    private TableColumn<gunvalues3, String> hemail;
    @FXML
    private TableColumn<gunvalues3, String> hlastattendance;
    @FXML
    private TableColumn<gunvalues3, String> htotalattendance;
    @FXML
    private TextField psearch;
    @FXML
    private TextField hsearch;
    @FXML
    private ToggleGroup monitoringServiceStartStopToggleGroup1;
    @FXML
    private JFXButton admin;
    @FXML
    private Label countrow;
    @FXML
    private Label countrow1;
    @FXML
    private Tab hh;
    @FXML
    private VBox countrow2;
   
@Override
public void initialize(URL url, ResourceBundle rb) {          
                   
        TableColumn numberCol = new TableColumn("Serial Number");
        numberCol.setMinWidth(20);
        numberCol.setCellValueFactory(new Callback<CellDataFeatures<gunvalues, gunvalues>, ObservableValue<gunvalues>>() {
            @Override public ObservableValue<gunvalues> call(CellDataFeatures<gunvalues, gunvalues> p) {
                return new ReadOnlyObjectWrapper(p.getValue());
            }
        });
        numberCol.setCellFactory(new Callback<TableColumn<gunvalues, gunvalues>, TableCell<gunvalues, gunvalues>>() {
            @Override public TableCell<gunvalues, gunvalues> call(TableColumn<gunvalues, gunvalues> param) {
                return new TableCell<gunvalues, gunvalues>() {
                    @Override protected void updateItem(gunvalues item, boolean empty) {
                            super.updateItem(item, empty);

                            if (this.getTableRow() != null && item != null) {
                                setText(this.getTableRow().getIndex()+1+"");
                            } 
                            else {
                                setText("");
                            }
                    }
                };
            }
        });
        numberCol.setSortable(false);
        details.getColumns().add(0,numberCol);
        TableColumn numberCol1 = new TableColumn("Serial Number");
        numberCol1.setMinWidth(20);
        numberCol1.setCellValueFactory(new Callback<CellDataFeatures<gunvalues1, gunvalues1>, ObservableValue<gunvalues1>>() {
            @Override public ObservableValue<gunvalues1> call(CellDataFeatures<gunvalues1, gunvalues1> p) {
                 return new ReadOnlyObjectWrapper(p.getValue());
            }
        });
        numberCol1.setCellFactory(new Callback<TableColumn<gunvalues1, gunvalues1>, TableCell<gunvalues1, gunvalues1>>() {
            @Override public TableCell<gunvalues1, gunvalues1> call(TableColumn<gunvalues1, gunvalues1> param) {
                return new TableCell<gunvalues1, gunvalues1>() {
                    @Override protected void updateItem(gunvalues1 item, boolean empty) {
                                                     super.updateItem(item, empty);

                                                     if (this.getTableRow() != null && item != null) {
                                                               setText(this.getTableRow().getIndex()+1+"");
                                                    } 
                                                    else {
                                                               setText("");
                                                    }
                                          }
                               };
                     }
            });
           numberCol1.setSortable(false);
           person.getColumns().add(0,numberCol1);
           numberCol.setStyle("-fx-alignment:center");
           numberCol1.setStyle("-fx-alignment:center");
           TableColumn numberCol2 = new TableColumn("Serial Number");
           numberCol2.setMinWidth(20);
           numberCol2.setCellValueFactory(new Callback<CellDataFeatures<gunvalues3, gunvalues3>, ObservableValue<gunvalues3>>() {
                     @Override public ObservableValue<gunvalues3> call(CellDataFeatures<gunvalues3, gunvalues3> p) {
                                return new ReadOnlyObjectWrapper(p.getValue());
                     }
           });
           numberCol2.setCellFactory(new Callback<TableColumn<gunvalues3, gunvalues3>, TableCell<gunvalues3, gunvalues3>>() {
                     @Override public TableCell<gunvalues3, gunvalues3> call(TableColumn<gunvalues3, gunvalues3> param) {
                                return new TableCell<gunvalues3, gunvalues3>() {
                                          @Override protected void updateItem(gunvalues3 item, boolean empty) {
                                                    super.updateItem(item, empty);

                                                    if (this.getTableRow() != null && item != null) {
                                                                setText(this.getTableRow().getIndex()+1+"");
                                                    } else {
                                                                 setText("");
                                                    }
                                          }
                                };
                      }
            });
           numberCol2.setSortable(false);
           history.getColumns().add(0,numberCol2);
           numberCol2.setStyle("-fx-alignment:center");
           TableColumn subtotal= new TableColumn("percentage");
           subtotal.setCellValueFactory(new PropertyValueFactory<gunvalues,Integer>("subtotal") );
           details.getColumns().add(subtotal);
           subtotal.setStyle("-fx-alignment:center");
           TableColumn subtotal1= new TableColumn("percentage");
           subtotal1.setCellValueFactory(new PropertyValueFactory<gunvalues1,Integer>("subtotal1") );
           person.getColumns().add(subtotal1);
           subtotal1.setStyle("-fx-alignment:center");
           TableColumn subtotal2= new TableColumn("percentage");
           subtotal2.setCellValueFactory(new PropertyValueFactory<gunvalues3,Integer>("subtotal2") );
           history.getColumns().add(subtotal2);
           subtotal2.setStyle("-fx-alignment:center");
           
           BarGraph();
           startRfidReaderService();        
           tag.setValue("NOT READING");
           readerConnectionStatusLabel1.textProperty().bind(tag);         

}
//Stop RFID Reader Service using Stop Button
@FXML
public void stopServiceFromUiButton(ActionEvent event){
           try{
                     rfidReader.stopReading();
                     rfidReader.destroy();
                     rfidReader = null;
                     readerConnectionStatusLabel.textProperty().unbind();
                     readerConnectionStatusLabel.setText("Disconnected");
           }
           catch(Exception e){
                     System.out.println("Stop Service Exception: " + e);
           }
}    
// Start RFID Reader Service using Start Button
@FXML
public void startServiceFromUiButton(ActionEvent event){
           try{
                    if( (rfidReader == null) || (!rfidReader.hasContinuousReadStarted))
                    {
                        startRfidReaderService();
                    }
            }
           catch(Exception e){
                    System.out.println("Exception: " + e);
           }
}  
    
// Start RFID Reader Service
private void startRfidReaderService(){         
       
           rfidReaderConnectService connectService = new rfidReaderConnectService();
        
           rfidReaderReadingService readingService = new rfidReaderReadingService();
        
           connectService.restart();
      
           connectService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                     @Override
                     public void handle(WorkerStateEvent event) {
                               int[] antennaList = {2,3};
                               try {
                                          rfidReader.paramSet("/reader/region/id", Reader.Region.IN);
                                          SimpleReadPlan plan = new SimpleReadPlan(antennaList, TagProtocol.GEN2, true);
                                          rfidReader.paramSet("/reader/read/plan", plan);
                                          rfidReader.paramSet("/reader/gpio/outputList", antennaList);
                                          readerConnectionStatusLabel.textProperty().unbind();
                                          readerConnectionStatusLabel.textProperty().bind(readingService.messageProperty());
                                          readingService.restart();
                               } catch (Exception ex) {
                                          System.out.println("Reader Setting Exception: " + ex);
                                }
                     }
           });

           readerConnectionStatusLabel.textProperty().bind(connectService.messageProperty());
}

    @FXML
    private void adminlogin(ActionEvent event) {
    }
//Connect RFID Reader
private static class rfidReaderConnectService extends Service<Void> {        
           String finalMessage = null;
           @Override
           protected Task createTask() {  
                     return new Task<Void>() { 
                                @Override
                               protected Void call() throws Exception {
                                          String connectedSerialPort = rfidSerialPortDetection();
                                          if(connectedSerialPort.equals("Reader Not Connected")){
                                                finalMessage = "Disconnected";
                                            }
                                          else{
                                                    try{
                                                               rfidReader = Reader.create("tmr:///" + connectedSerialPort);
                                                               rfidReader.connect();
                                                               System.out.println(rfidReader.paramGet("/reader/version/model").toString());
                                                               finalMessage = "Connected";
                                                    }
                                                    catch(Exception e){
                                                               finalMessage = "Not able to connect";
                                                    }
                                           }
                                           updateMessage(finalMessage);

                                          return null;
                               }
                     };
           }
 }
//Reader Reading Service Start
public class rfidReaderReadingService extends Service<Void> {

           @Override
           protected Task createTask() {

                     return new Task<Void>() {

                               @Override
                               protected Void call() throws Exception {
                     
                                    try{
                                        ReadListener tagPrintListener = new ReadListener(){

                                        HashSet<String> seenTags = new HashSet<String>();
                                        @Override
                                        public void tagRead(Reader r, TagReadData tr){
                                            String t = tr.epcString();
                                                if(!seenTags.contains(t))
                                                {
                                                    seenTags.add(t);                                  
                                                    System.out.println("New tag"+""+t);
                                                    Per_Day_Update(t);   
                                                    Daily_Record_Updation(); 
                                                    UpdateCount(t);  
                                                    LocalHistoryUpdate(); 
                                                     // per_day_update records saves in Online Database
                                                    Task<Void> onlineperdayupdate=new Task<Void>() 
                                                    {  
                                                        @Override
                                                        public Void call() {
                                                            try  
                                                            {
                                                                System.out.println("0000");
                                                                java.sql.Timestamp date= new java.sql.Timestamp(new java.util.Date().getTime());   
                                                                String query = "SELECT ID,Name,Attendance_Count from Overall_Person_Info_Table where Id='"+t+"' ";                                             
                                                                PreparedStatement ps = con1.prepareStatement("INSERT INTO per_day_update_table(ID,Attendance_Time,Name,Attendance_Count) values(?,?,?,?) ");                                       
                                                                Statement st = con1.createStatement();
                                                                ResultSet rs = st.executeQuery(query);                                                          
                                                                while(rs.next())
                                                                {
                                                                    String epc = rs.getString("ID");                                                                                                 
                                                                    String name = rs.getString("Name");
                                                                    String ta = rs.getString("Attendance_Count");
                                                                    ps.setString(1,epc);
                                                                    ps.setTimestamp(2, date);
                                                                    ps.setString(3,name);
                                                                    ps.setString(4, ta);
                                                                    ps.executeUpdate();     
                                                                }
                                                            }     
                                                            catch (Exception e)
                                                            {
                                                                System.err.println("Got an exception2 "+e);
                                                            }
                                                            return null;
                                                        }
                                                    };      
                                                    //Updation of online dataabase
                                                    Task<Void> onlinedatabaseupdate=new Task<Void>(){
                                                        @Override
                                                        public Void call() {
                                                        try  
                                                        {
                                                            Statement pstmt = con1.createStatement();
                                                            String sql="UPDATE  Overall_Person_Info_Table  SET Attendance_Count=Attendance_Count+1 where ID='"+t+"' ";
                                                            String sql1="UPDATE per_day_update_table  SET Attendance_Count=Attendance_Count+1 where ID='"+t+"' ";
                                                            String sql2="UPDATE  Overall_Person_Info_Table  SET Attendance_Count=1 where Attendance_Count>365";          
                                                            String sql3="UPDATE  per_day_update_table  SET Attendance_Count=1 where Attendance_Count>365 ";
                                                            String sql4="UPDATE Overall_Person_Info_Table set percentage =  Attendance_Count* 100/ 365 ";
                                                            String sql5="UPDATE per_day_update_table set Percentage =  Attendance_Count* 100/ 365 ";                                                                                                
                                                            pstmt.addBatch(sql);
                                                            pstmt.addBatch(sql1);
                                                            pstmt.addBatch(sql2);
                                                            pstmt.addBatch(sql3);
                                                            pstmt.addBatch(sql4);
                                                            pstmt.addBatch(sql5);
                                                            pstmt.executeBatch();    
                                                        }     
                                                        catch (Exception e)
                                                        {
                                                            System.err.println("Got an exception3 "+e);
                                                        }
                                                        return null;
                                                    }
                                                };                              
                                                try{
                                                    Thread t1=new Thread(onlineperdayupdate);
                                                    t1.start();
                                                    Thread t2=new Thread(onlinedatabaseupdate);
                                                    t2.start();
                                                }
                                                catch(Exception e)
                                                {
                                                    System.out.println(e);
                                                } 
                                            }
                                            Platform.runLater(new Runnable() {
                                                @Override
                                                public void run() {
                                                    tag.setValue(t);
                                                    try{ 
                                                        Local_DB_PersonInfo();  
                                                        
                                                    }
                                                    catch(Exception e)
                                                    {
                                                        System.out.println("Runnable Exception"+e);
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    rfidReader.addReadListener(tagPrintListener);
                                    ReadExceptionListener exceptionListener = new ReadExceptionListener(){
                                        @Override
                                        public void tagReadException(com.thingmagic.Reader r, ReaderException re){
                                            updateMessage("Disconnected");                   
                                        }
                                    };
                                    rfidReader.addReadExceptionListener(exceptionListener);
                                    rfidReader.startReading();
                                        updateMessage("Connected");
                                    }
                                    catch(Exception e){
                                        System.out.println("Reader Exception: " + e);
                                        updateMessage("Disconnected");
                                    }
                                    return null;
                            }
                               
                    };
                     
            }
           
}

    // Serial Port detection
    private static String rfidSerialPortDetection(){

        SerialPort[] serialPortList = SerialPort.getCommPorts();
        String finalSelectedPort = null; //final reader com port

        for(int i = 0; i < serialPortList.length; i++){
            if(serialPortList[i].toString().substring(0, 3).equals("M6E"))
            {
                finalSelectedPort = serialPortList[i].getSystemPortName();
                rfidReaderSerialPort = serialPortList[i];
                return finalSelectedPort;
            }
        }
            finalSelectedPort = "Reader Not Connected";
            return finalSelectedPort;
        }
    //Data is updated in database
    public void Per_Day_Update(String EPC) {        
    
        try { 
           
            String query = "SELECT ID,Name,Attendance_Count from  overall_person_info_table  where ID='"+EPC+"'";

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);
           
            PreparedStatement ps = con.prepareStatement("INSERT INTO per_day_update_table (ID,Name,Attendance_Time,Attendance_Count) values(?,?,?,?) ");

            while(rs.next())
            {
                //getting values from Person_Info table in database
                String epc = rs.getString("ID");           
                String name = rs.getString("Name");
                //for getting System Date
                java.sql.Timestamp date= new java.sql.Timestamp(new java.util.Date().getTime());
                //Saves in per_day_update_table in database
                String ta = rs.getString("Attendance_Count");               
                ps.setString(1,epc);
                ps.setString(2,name);
                ps.setTimestamp(3,date);
                ps.setString(4, ta);
                ps.executeUpdate();
            }
        }
        catch (Exception e)
        {
         System.err.println("Got an exception! "+e);        

        }
    }
    //Populating Daily_Record tableview in UI
    public void Daily_Record_Updation(){
        try{
            data = FXCollections.observableArrayList();
               ResultSet rss = (ResultSet) con.createStatement().executeQuery("SELECT * FROM  per_day_update_table where DATE(Attendance_Time)=CURDATE()");
               while (rss.next()) {
                   data.add(new gunvalues(rss.getString(1),rss.getString(2),rss.getString(3),rss.getInt(4)));  
                }

            tepc.setCellValueFactory(new PropertyValueFactory<>("epc"));
            tdate.setCellValueFactory(new PropertyValueFactory<>("date"));
            tname.setCellValueFactory(new PropertyValueFactory<>("name"));
            tatt.setCellValueFactory(new PropertyValueFactory<>("total"));
            //set Items to tableview
            details.setItems(data);
        }
        catch(Exception e){}
        }
    //Updation of Local_Database
    public void UpdateCount(String EPC){
        try{
            Statement pstmt = con.createStatement();
            //Multiple Queries
            String sql="UPDATE  overall_person_info_table  SET Attendance_Count=Attendance_Count+1 where ID='"+EPC+"' ";
            String sql1="UPDATE per_day_update_table  SET Attendance_Count=Attendance_Count+1 where ID='"+EPC+"' ";
            String sql2="UPDATE  overall_person_info_table  SET Attendance_Count=1 where Attendance_Count>365";
           
            String sql3="UPDATE  per_day_update_table  SET Attendance_Count=1 where Attendance_Count>365 ";
            String sql4="UPDATE Overall_Person_Info_Table set Percentage =  Attendance_Count* 100/ 365  ";
            String sql5="UPDATE per_day_update_table set Percentage =  Attendance_Count* 100/ 365 ";
             // Add Batch for adding multiple queries in statement 
            pstmt.addBatch(sql);
            pstmt.addBatch(sql1);
            pstmt.addBatch(sql2);
            pstmt.addBatch(sql3);
            pstmt.addBatch(sql4);
              pstmt.addBatch(sql5);
           //executing batch
            pstmt.executeBatch();
        }
        catch(Exception e){}
    }
    //Showing BarGraph in UI
    public void BarGraph(){
       
        XYChart.Series<String,Double> series=new XYChart.Series<>();
        try{

            String sql="select Name,Attendance_Count from  Overall_Person_Info_Table  ";
            ResultSet rsss=con.createStatement().executeQuery(sql);
                    
            while(rsss.next())
            {
                series.getData().add(new XYChart.Data<>(rsss.getString(1),rsss.getDouble(2)));
            }
            piechart.getData().add(series);
        }
        catch(Exception e){}
        }
    //Popualte Person_Info tableview in UI
    public void Local_DB_PersonInfo() {
        try{
            data1 = FXCollections.observableArrayList();
            ResultSet rss = (ResultSet) con.createStatement().executeQuery("SELECT ID,Name,Email,Attendance_Count from  overall_person_info_table ");
            while (rss.next()) {
                data1.add(new gunvalues1(rss.getString(1),rss.getString(2),rss.getString(3),rss.getInt(4)));  
            }
        }
        catch (Exception e) {
               System.out.println(e+"...."+"5");
        }
        pid.setCellValueFactory(new PropertyValueFactory<>("Epc"));
        pname.setCellValueFactory(new PropertyValueFactory<>("name"));          
        pemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        pattendance.setCellValueFactory(new PropertyValueFactory<>("totalattendance"));
        //set Items to tableview
        person.setItems(data1);
        //Search Filter in Person_Info
        FilteredList<gunvalues1> filtereddata=new FilteredList<>(data1,b -> true);
        psearch.textProperty().addListener((observable,oldValue,newValue) ->{
            filtereddata.setPredicate(gunvalues1 ->{
                if(newValue==null ||newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter=newValue.toLowerCase();

                if( gunvalues1.getEpc().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                else if (gunvalues1.getname().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } 
                else 
                    return false;
            });  
        });
        SortedList<gunvalues1> sorteddata=new SortedList<>(filtereddata);
        sorteddata.comparatorProperty().bind(person.comparatorProperty());
        person.setItems(sorteddata);
    }
    //Populate History Tableview from Local_Database  in UI
    public void LocalHistoryUpdate() { 
        try {
            data2 = FXCollections.observableArrayList();   
            ResultSet rss = (ResultSet) con.createStatement().executeQuery("select per_day_update_table.ID,per_day_update_table.Name,overall_person_info_table.Email, per_day_update_table.Attendance_Time,overall_person_info_table.Attendance_Count from per_day_update_table  INNER JOIN  overall_person_info_table using(ID)");
            while (rss.next()) {
                data2.add(new gunvalues3(rss.getString(1),rss.getString(2),rss.getString(3),rss.getString(4),rss.getInt(5))); 
            }
            }
            catch (Exception e) 
            {
               System.out.println(e+"...."+"6");
            }
            
            hepc.setCellValueFactory(new PropertyValueFactory<>("Epc"));
            hname.setCellValueFactory(new PropertyValueFactory<>("name"));           
            hemail.setCellValueFactory(new PropertyValueFactory<>("email"));         
            hlastattendance.setCellValueFactory(new PropertyValueFactory<>("lastattendance"));
            htotalattendance.setCellValueFactory(new PropertyValueFactory<>("totalattendance"));
            //set Items to tableview
            history.setItems(data2);
            //Search Filter in History
            FilteredList<gunvalues3> filtereddata=new FilteredList<>(data2,b -> true);
            hsearch.textProperty().addListener((observable,oldValue,newValue) ->{
                filtereddata.setPredicate(gunvalues3 ->{
                    if(newValue==null ||newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter=newValue.toLowerCase();

                    if(gunvalues3.getEpc().toLowerCase().indexOf(lowerCaseFilter) !=-1){
                        return true;
                    }
                    else if (gunvalues3.getname().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    } else 
                    return false;
               });  
            });

            SortedList<gunvalues3> sorteddata=new SortedList<>(filtereddata);
            sorteddata.comparatorProperty().bind(history.comparatorProperty());
            history.setItems(sorteddata);
    }
    
}