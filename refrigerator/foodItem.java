package refrigerator;

import java.io.Serializable;
import java.sql.Date;

public class foodItem implements Serializable {
    private String itemName;
    private Date expiryDate;

    foodItem (String itemName,Date expiryDate) {
        this.itemName = itemName;
        this.expiryDate = expiryDate;
    }

    public String getItemName() {
        return this.itemName;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public void setItemName() {
        this.itemName = itemName;
    }

    public void setExpiryDate() {
        this.expiryDate = expiryDate;
    }
}