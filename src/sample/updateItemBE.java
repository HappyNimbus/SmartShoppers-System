package sample;

import java.sql.Connection;
import java.sql.Statement;

public class updateItemBE {
    public static String updateName(String newName, String oldName, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";

        if(newName.equals("")){
            msg = "Fill in Name to update";
        }
        else{
            String newNameChange = "UPDATE items SET name = '" + newName + "' WHERE name = '" + oldName + "'" + "AND store = '" + store + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newNameChange);
                msg = "Name Change Successful";

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return msg;
    }
    public static String updateCat(String newCat, String oldName, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";
        if(newCat.equals("")){
            msg = "Fill in catagory to update";
        }
        else{
            String newChange = "UPDATE items SET catagory = '" + newCat + "' WHERE name = '" + oldName + "'" + " AND store = '" + store + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                msg = "Category Change Successful";

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return msg;
    }
    public static String updatePrice(String newPrice, String oldName, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";

        if(newPrice.equals("")){
            msg = "Fill in price to update";
        }
        else{
            String newChange = "UPDATE items SET price = '" + newPrice + "' WHERE name = '" + oldName + "'"+ " AND store = '" + store + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                msg = "Price Change Successful";

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return msg;
    }

    public static String updateSize(String newL, String newW, String newH, String oldName, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";
        if(newL.equals("") || newW.equals("") || newH.equals("")){
           msg = "Fill in size to update";
        }
        else{
            String newChange = "UPDATE items SET size = '" + newL + "x" + newW + "x" + newH + "' WHERE name = '" + oldName + "'"+ " AND store = '" + store + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                msg = "Size Change Successful";

            }catch(Exception e){
                e.printStackTrace();
            }

        }
        return msg;
    }
    public static String updateIsle(String newIsle, String oldName, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";
        if(newIsle.equals("")){
            msg = "Fill in isle to update";
        }
        else{
            String newChange = "UPDATE items SET isle = '" + newIsle + "' WHERE name = '" + oldName + "'" + " AND store = '" + store + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                msg = "Isle Change Successful";

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return msg;
    }
    public static String updateDesc(String newDecs, String oldName, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";
        if(newDecs.equals("")){
            msg = "Fill in description to update";
        }
        else{
            String newChange = "UPDATE items SET description = '" + newDecs + "' WHERE name = '" + oldName + "'" + " AND store = '" + store + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                msg = "Description Change Successful";

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return msg;
    }

    public static String updateAva(String newAva, String oldName, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";
        if(newAva.equals("")){
            msg = "Select availability to update";
        }
        else{
            String newChange = "UPDATE items SET avalibilty = '" + newAva + "' WHERE name = '" + oldName + "'" + " AND store = '" + store + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                msg = "Availability Change Successful";

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return msg;
    }

    public static String updateSale(String newSale, String oldName, String store){
        DBUtils connectNow = new DBUtils();
        Connection connectDB = connectNow.getConnection();
        String msg = "";

        if(newSale.equals("")){
            msg = "Select sale to update";
        }
        else{
            String newChange = "UPDATE items SET sale = '" + newSale + "' WHERE name = '" + oldName + "'" + " AND store = '" + store + "'";

            try{
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(newChange);
                msg = "Sale Change Successful";

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return msg;
    }
}
