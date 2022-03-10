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
import view.tdm.CustomerTM;
import view.tdm.ItemTM;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public class ItemFormController implements Initializable {


    public TextField txtItemCode;
    public TextField txtItemName;
    public TextField txtItemQty;
    public TextField txtItemPrice;
    public TableView<ItemTM> tblItem;
    public JFXButton btnSaveItem;
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Initializing table columns with TableTM Properties
        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

        //set edit and delete button from images to the table
        tblItem.getColumns().get(3).setCellValueFactory((param) -> {
            ImageView edit = new ImageView("/view/assets/icons/draw.png");
            ImageView delete = new ImageView("/view/assets/icons/trash.png");
            return new ReadOnlyObjectWrapper(new HBox(10, edit, delete));
        });


        //set click event for the Save Comment
        btnSaveItem.setOnMouseClicked(event -> {
            //Gather information from inputs
            saveItem();
        });

        btnSaveItem.setDisable(true);


        //add pattern and text to the map
        //Create a pattern and compile it to use
        Pattern codePattern = Pattern.compile("^(P00-)[0-9]{3,5}$");
        Pattern namePattern = Pattern.compile("^[A-z ]{3,15}$");
        Pattern qtyPattern = Pattern.compile("^[0-9]{1,}$");
        Pattern itemPrice = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");

        map.put(txtItemCode,codePattern);
        map.put(txtItemName,namePattern);
        map.put(txtItemQty,qtyPattern);
        map.put(txtItemPrice,itemPrice);

    }

    private void saveItem() {
        String itemCode = txtItemCode.getText();
        String itemName = txtItemName.getText();
        int itemQty = Integer.parseInt(txtItemQty.getText());
        double itemPrice = Double.parseDouble(txtItemPrice.getText());

        // Add to the Table
        ItemTM itemTM = new ItemTM(itemCode, itemName, itemQty, itemPrice);
        tblItem.getItems().add(itemTM);

        clearAllTexts();
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        validate();
//        TextField = error
//        boolean // validation ok

        //if the enter key pressed
        if (keyEvent.getCode() == KeyCode.ENTER) {
            Object response = validate();
            //if the response is a text field
            //that means there is a error
            if (response instanceof TextField) {
                TextField textField = (TextField) response;
                textField.requestFocus();// if there is a error just focus it
            } else if (response instanceof Boolean) {
                System.out.println("Work");
                saveItem();
            }

        }

    }

    private Object validate() {
        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (!pattern.matcher(key.getText()).matches()){
                //if the input is not matching
                addError(key);
                return key;
            }
            removeError(key);
        }
        return true;
    }



    private void removeError(TextField txtField) {
        txtField.getParent().setStyle("-fx-border-color: green");
        btnSaveItem.setDisable(false);
    }

    private void addError(TextField txtField) {
        if (txtField.getText().length() > 0) {
            txtField.getParent().setStyle("-fx-border-color: red");
        }
        btnSaveItem.setDisable(true);
    }


    public void clearAllTexts() {
        txtItemCode.clear();
        txtItemName.clear();
        txtItemQty.clear();
        txtItemPrice.clear();
        txtItemCode.requestFocus();
        setBorders(txtItemCode,txtItemName,txtItemQty,txtItemPrice);
    }

    //reset border colors to default color
    public void setBorders(TextField... textFields){
        for (TextField textField : textFields) {
            textField.getParent().setStyle("-fx-border-color: rgba(76, 73, 73, 0.83)");
        }
    }


}
