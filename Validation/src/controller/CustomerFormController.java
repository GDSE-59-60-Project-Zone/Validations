package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import util.ValidationUtil;
import view.tdm.CustomerTM;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public class CustomerFormController implements Initializable {
    public TextField txtCusID;
    public TextField txtCusName;
    public TextField txtCusAddress;
    public TextField txtCusSalary;
    public TableView<CustomerTM> tblCustomer;
    public JFXButton btnSaveCustomer;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initializing table columns with TableTM Properties
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        //set edit and delete button from images to the table
        tblCustomer.getColumns().get(3).setCellValueFactory((param) -> {
            ImageView edit = new ImageView("/view/assets/icons/draw.png");
            ImageView delete = new ImageView("/view/assets/icons/trash.png");
            return new ReadOnlyObjectWrapper(new HBox(10, edit, delete));
        });


        //set click event for the Save Comment
        btnSaveCustomer.setOnMouseClicked(event -> {
            //Gather information from inputs
            saveCustomer();
        });

        btnSaveCustomer.setDisable(true);

        //add pattern and text to the map
        //Create a pattern and compile it to use
        Pattern idPattern = Pattern.compile("^(C00-)[0-9]{3,5}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern salaryPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");

        map.put(txtCusID,idPattern);
        map.put(txtCusName,namePattern);
        map.put(txtCusAddress,addressPattern);
        map.put(txtCusSalary,salaryPattern);

    }

    private void saveCustomer() {
        String customerID = txtCusID.getText();
        String customerName = txtCusName.getText();
        String customerAddress = txtCusAddress.getText();
        double customerSalary = Double.parseDouble(txtCusSalary.getText());

        // Add to the Table
        CustomerTM customerTM = new CustomerTM(customerID, customerName, customerAddress, customerSalary);
        tblCustomer.getItems().add(customerTM);

        clearAllTexts();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        ValidationUtil.validate(map,btnSaveCustomer);
//        TextField = error
//        boolean // validation ok

        //if the enter key pressed
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response =  ValidationUtil.validate(map,btnSaveCustomer);;
            //if the response is a text field
            //that means there is a error
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");
                saveCustomer();
            }
        }
    }

    public void clearAllTexts() {
        txtCusID.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusSalary.clear();
        txtCusID.requestFocus();
        setBorders(txtCusID,txtCusName,txtCusAddress,txtCusSalary);
    }

    //reset border colors to default color
    public void setBorders(TextField... textFields){
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }


}
