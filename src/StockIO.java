
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author emoha
 */
public class StockIO {

    //attributes
    private String fileName;

    //constructors
    public StockIO() {
        fileName = "Stocks.txt";
    }

    public StockIO(String fileName) {
        setFileName(fileName);
    }

    //behaviors
    public ArrayList<Stock> getData() {
        // create arraylist
        ArrayList<Stock> data = new ArrayList<Stock>();
        //load the array list with the data from the file
        try {
            BufferedReader inFile = new BufferedReader(new FileReader(fileName));
            String inputLine = "";
            StringTokenizer tokens;
            
            //get first line
            inputLine = inFile.readLine();
            //cycle through the file and read line by line
            while(inputLine != null){
                tokens = new StringTokenizer( inputLine, ",");
                String company = tokens.nextToken();
                double shares = Double.parseDouble(tokens.nextToken());
                double pPrice = Double.parseDouble(tokens.nextToken());
                double cPrice = Double.parseDouble(tokens.nextToken());
                // create a stock object and add it to the array
                Stock stk = new Stock(company, shares, pPrice, cPrice);
                data.add(stk);
                
                // read the next line in the file
                inputLine = inFile.readLine();
                        
            }
            //close the file
            inFile.close();
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, Unable to write to the file: " + ex.getMessage(),
                    fileName, JOptionPane.ERROR_MESSAGE);
        }
        //return the arraylist
        return data;
    }

    public void saveData(ArrayList<Stock> data) {
        //write csv data

        try {
            // create a path to the file
            BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));

            // write the stocks by cycling through the data
            for (int i = 0; i < data.size(); i++) {
                Stock stk = data.get(i);
                outFile.write(stk.getCompanyName() + ",");
                outFile.write("" + stk.getNumberOfShares() + ",");
                outFile.write("" + stk.getPurchasePrice() + ",");
                outFile.write("" + stk.getCurrentPrice());
                outFile.newLine(); // enter key to drop to the next line
            }
            outFile.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error, Unable to write to the file: " + ex.getMessage(),
                    fileName, JOptionPane.ERROR_MESSAGE);
        }
    }

    //Getters and Setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        if (fileName.length() > 0) {
            this.fileName = fileName;
        } else {
            this.fileName = "Stocks.txt";
        }
    }

}
