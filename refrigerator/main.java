package refrigerator;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    private static String filepath = "obj";
    private static Scanner myObj = new Scanner(System.in);  // Create a Scanner object

    public static ArrayList createNewFridge() throws IOException {
        ArrayList<foodItem> fridge = new ArrayList<foodItem>();
        return fridge;
    }

    public static ArrayList openFridge() throws IOException {
        ArrayList<foodItem> fridge = (ArrayList<foodItem>) ObjectIO.ReadObjectFromFile(filepath);
        return fridge;
    }

    public static void addToFridge(ArrayList<foodItem> fridge) {
        while (true) {
            System.out.print("Enter Food Item Name: ");
            String foodItemName = myObj.next();  // Read user input

            System.out.print("Enter Expiry Date: ");
            //int datestring = Integer.parseInt(myObj.next());
            Date datestring = Date.valueOf(myObj.next()); //converting string into sql date

            fridge.add(new foodItem(foodItemName, datestring));
            //SQLiteTest.addUser(foodItemName, datestring);
            System.out.println("Add More Items?: Y / N");
            if (myObj.nextLine().equalsIgnoreCase("Y")) {

            } else if (myObj.next().equalsIgnoreCase("N")) {
                ObjectIO.WriteObjectToFile(fridge, filepath);
                break;
            }
        }
    }

    public static void editFridge(ArrayList<foodItem> fridge) {
        if (myObj.next().equalsIgnoreCase("Y")) {
            for (foodItem f : fridge) {
                System.out.println(f.getItemName() + " " + f.getExpiryDate());
                System.out.println("Is this the Item?: Y / N");
                if (myObj.next().equalsIgnoreCase("Y")) {
                    System.out.println("Edit Name or Expiry Date?: N / E");
                    if (myObj.next().equalsIgnoreCase("N")) {
                        System.out.print("Enter Food Item Name: ");
                        String foodItemName = myObj.next();
                    } else if (myObj.next().equalsIgnoreCase("E")) {
                        System.out.print("Enter Expiry Date: ");
                        Date datestring = Date.valueOf(myObj.next());//converting string into sql date
                    } else if (myObj.next().equalsIgnoreCase("N")) {
                        System.out.println(f.getItemName() + " " + f.getExpiryDate());
                    }
                }
            }
        }
    }

    public static void displayFridgeContents(ArrayList<foodItem> fridge) {
        for (foodItem f : fridge) {
            System.out.println(f.getItemName() + " " + f.getExpiryDate());
        }
    }

    public static void main (String[] args) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("Create New Fridge or Open Existing?: N / O");
        String answer = myObj.next();
        if (answer.equalsIgnoreCase("N")) {
            ArrayList<foodItem> fridge = createNewFridge();
            addToFridge(fridge);

            System.out.println("Display Fridge Contents?: Y / N");
            if (myObj.next().equalsIgnoreCase("Y")) {
                displayFridgeContents(fridge);
            }
        }
        else if (answer.equalsIgnoreCase("O")) {
            ArrayList<foodItem> fridge = openFridge();

            System.out.println("Edit Fridge Contents?: Y / N");
            if (myObj.next().equalsIgnoreCase("Y")) {
                System.out.println("Add Items to Fridge?: Y / N");
                if (myObj.next().equalsIgnoreCase("Y")) {
                    addToFridge(fridge);
                }
                else if (myObj.next().equalsIgnoreCase("N")) {
                    System.out.println("Edit Items in Fridge?: Y / N");
                    if (myObj.next().equalsIgnoreCase("Y")) {
                        editFridge(fridge);
                    }
                }
            }
            System.out.println("Display Fridge Contents?: Y / N");
            if (myObj.next().equalsIgnoreCase("Y")) {
                displayFridgeContents(fridge);
            }
        }
    }
}