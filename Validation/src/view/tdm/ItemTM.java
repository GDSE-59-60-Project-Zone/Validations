package view.tdm;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/
public class ItemTM {
    private String code;
    private String itemName;
    private int itemQty;
    private double itemPrice;

    public ItemTM() {
    }

    public ItemTM(String code, String itemName, int itemQty, double itemPrice) {
        this.code = code;
        this.itemName = itemName;
        this.itemQty = itemQty;
        this.itemPrice = itemPrice;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
