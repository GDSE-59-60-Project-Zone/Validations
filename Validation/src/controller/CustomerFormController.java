package controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import view.tdm.CustomerTM;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initializing table columns with TableTM Properties
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        //set edit and delete button from images to the table
        tblCustomer.getColumns().get(3).setCellValueFactory((param)->{
            ImageView edit = new ImageView("/view/assets/icons/draw.png");
            ImageView delete = new ImageView("/view/assets/icons/trash.png");
            return new ReadOnlyObjectWrapper(new HBox(10,edit,delete));
        });


        //set click event for the Save Comment
        btnSaveCustomer.setOnMouseClicked(event->{
            //Gather information from inputs
            String customerID = txtCusID.getText();
            String customerName = txtCusName.getText();
            String customerAddress = txtCusAddress.getText();
            double customerSalary = Double.parseDouble(txtCusSalary.getText());

            // Add to the Table
            CustomerTM customerTM = new CustomerTM(customerID, customerName, customerAddress, customerSalary);
            tblCustomer.getItems().add(customerTM);
        });


    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        //C00-001
        //01. C00- this prefix is a must
        //02. after C00- only numbers allowed. minimum 3 (0-9) maximum 5
        //reg-ex ^(C00-)[0-9]{3,5}$

        //get the type value
        String typedCustomerID = txtCusID.getText();

        //Create a pattern and compile it to use
        Pattern idPattern = Pattern.compile("^(C00-)[0-9]{3,5}$");

        boolean matches = idPattern.matcher(typedCustomerID).matches(); // return boolean
        if (matches){
            //if true - green
            txtCusID.getParent().setStyle("-fx-border-color: green");
        }else{
            //else - red
            txtCusID.getParent().setStyle("-fx-border-color: red");
        }


    }



}
