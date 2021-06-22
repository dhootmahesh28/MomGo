package com.swacorp.crew.utils;
/**
 * This class creates objects to represent a data table on a web page
 */

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DataTable {
        public WebElement table;
        /*
        Ways to find elements
        */
        private static By rowTag = By.xpath("./tbody/tr");
        private static By headerTag = By.xpath("//thead/tr[1]/th"); //thead/tr[1]/th
        private static By cellTag = By.xpath("./td");
        private static By thTag = By.xpath("./th");
        private static String innerText = "innerText";
        By buttonTag = By.tagName("button");


        public DataTable(){
        }

        public DataTable(WebElement table){
            this.table = table;
        }

        public By getRowTag() {
            return rowTag;
        }

        public By getHeaderTag() {
            return headerTag;
        }

        public By getCellTag() {
            return cellTag;
        }

        public By getThTag() {
        return thTag;
    }


        /**
         * To set the table
         * @param table The table to set
         */
        public void setTable (WebElement table){
            this.table = table;
        }

        /**
         * The list of the rows
         * @return a List of row WebElement
         */
        public List<WebElement> getRowsList(){
            return table.findElements(rowTag);
        }


        /**
         * The size of the table (number of rows)
         * @return the table size
         */
        public int tableSize(){
            return table.findElements(rowTag).size();
        }


        /**
         * The List of table header cells
         * @return a List of cells WebElement
         */
        public List<WebElement> getHeaderList(){
            return  table.findElements(headerTag);
        }


        /**
         * The cells List of a column
         * @param columnName The name of the column to list
         * @return a WebElements (cells) List of the column
         */
        public List<WebElement> getColumnList(String columnName){
            List<WebElement> cells = new ArrayList<>();
            List<WebElement> rows = this.getRowsList();
            int columnIndex = this.findColumnIndex(columnName);

            for(WebElement row : rows){
                cells.add(row.findElements(cellTag).get(columnIndex));
            }

            return cells;
        }


        /**
         * The cells List of a column
         * @param columnName The name of the column to list
         * @return a String (cells) List of the column
         */
        public List<String> getColumnListOfString(String columnName){
            List<String> cells = new ArrayList<>();
            List<WebElement> rows = this.getRowsList();
            int columnIndex = this.findColumnIndex(columnName);

            for(WebElement row : rows){
                cells.add(row.findElements(cellTag).get(columnIndex).getText());
            }

            return cells;
        }


        /**
         * The cells List of a column
         * @param columnName The name of the column to list
         * @return a String (cells) List of the column
         */
        public List<String> getColumnListOfString(String columnName, String strTagName){
        List<String> cells = new ArrayList<>();
        List<WebElement> rows = this.getRowsList();
        int columnIndex = this.findColumnIndex(columnName);
        By cellTagName = By.xpath("./" + strTagName);
        for(WebElement row : rows){
            cells.add(row.findElements(cellTagName).get(columnIndex).getText());
        }

        return cells;
    }



    /**
         * The List of table header titles in a List
         * @return a List of string titles
         */
        public List<String> getHeaderTitlesList(){
            List <WebElement> headerCells = this.getHeaderList();
            int columnCount = headerCells.size();
            List<String> headerTitles = new ArrayList<>();
            for (int i = 0; i < columnCount; i++) {
                headerTitles.add(headerCells.get(i).getText());
            }
            return headerTitles;
        }


        /**
         * Find a single row (a list of cells) by item name in the table and item column index
         * @param itemName
         * @param itemColumnIndex
         * @return a list with the row cells, a empty list if item name not has been found
         */
        public List<WebElement> getCellsFromRow(String itemName, int itemColumnIndex){
            List<WebElement> rowCells = new ArrayList<>();
            if(itemColumnIndex >= 0){
                List<WebElement> rows = this.getRowsList();
                for(int j = 0; j < rows.size(); j++){
                    //The list of the cells row
                    rowCells = rows.get(j).findElements(cellTag);//tr[1]
                    String currentItem = rowCells.get(itemColumnIndex).getText();//tr[1]/td[2]
                    currentItem = trimInitialSpaces(currentItem);
                    //If the content of the cell, in the corresponding column, equal the column name, return the row
                    if(currentItem.equalsIgnoreCase(itemName)){
                        return rowCells;
                    }
                }
                rowCells.clear();
                return rowCells;
            }
            return rowCells;
        }


        /**
         * Find a single row (a list of cells) by item name in the table and item column name
         * @param itemName
         * @param itemColumn
         * @return a list with the row cells, a empty list if item name not has been found
         */
        public List<WebElement> getCellsFromRow(String itemName, String itemColumn){
            int itemColumnIndex = this.findColumnIndex(itemColumn);
            return this.getCellsFromRow(itemName, itemColumnIndex);
        }


        /**
         * Find a rows by item name in the table
         * @param itemName
         * @param itemColumn
         * @return a list with the rows, a empty list if item name not has been found
         */
        public List<WebElement> findRowList(String itemName, String itemColumn){
            List<WebElement> rows = new ArrayList<>();
            int itemColumnIndex = this.findColumnIndex(itemColumn);
            if(itemColumnIndex >= 0)
            {
                List<WebElement> allRows = this.getRowsList();
                for(WebElement row : allRows){
                    List<WebElement> cells = row.findElements(cellTag);
                    //If the content of the cell in the corresponding column equal the column name, return the row
                    if(cells.get(itemColumnIndex).getText().equalsIgnoreCase(itemName)){
                        rows.add(row);
                    }
                }
            }
            return rows;
        }


        /**
         * The number of columns
         * @return the number of columns
         */
        public int columnsNumber(){
            return this.getHeaderList().size();
        }


        /**
         * Find the index of the column by name (starting from 0)
         * @param columnName the name of the column
         * @return (-1) if column not found, (>=0) if the column found
         */
        public int findColumnIndex(String columnName){
            List<WebElement> headers = this.getHeaderList();
            int itemColumnIndex = -1;
            for(int i = 0; i < headers.size(); i++){
                WebElement th = headers.get(i);
                if(th.getAttribute(innerText).contains(columnName)){
                    itemColumnIndex = i;
                    break;
                }
            }
            return itemColumnIndex;
        }


        /**
         * Looks for a cell by item column and data column name
         * @param itemName the string to search in the table
         * @param itemColumn the column in which to search the item
         * @param dataColumn the column in which to collect the data
         * @return a WebElement represent a cell, (null) if item name not has been found
         */
        public WebElement getCell(String itemName, String itemColumn, String dataColumn){
            //Find the data columns index
            int itemColumnIndex = this.findColumnIndex(itemColumn);
            int dataColumnIndex = this.findColumnIndex(dataColumn);
            return this.getCell(itemName, itemColumnIndex, dataColumnIndex);
        }


        /**
         * Looks for a cell by item column index and data column index
         * @param itemName the string to search in the table
         * @param itemColumnIndex the column index in which to search the item
         * @param dataColumnIndex the column index in which to collect the data
         * @return a WebElement represent a cell, (null) if item name not has been found
         */
        public WebElement getCell(String itemName, int itemColumnIndex, int dataColumnIndex){
            //Find the row
            List<WebElement> cells = this.getCellsFromRow(itemName, itemColumnIndex);
            if ((!cells.isEmpty()) && (dataColumnIndex >= 0)){
                return cells.get(dataColumnIndex);
            }
            return null;
        }


        /**
         * Looks for a button in a cell by item column index and data column index
         * @param itemName the string to search in the table
         * @param itemColumn the column index in which to search the item
         * @param dataColumn the column index in which to collect the data
         * @return a WebElement represent a button, (null) if item name not has been found
         */
        public WebElement getCellButton(String itemName, String itemColumn, String dataColumn){
            WebElement cell = this.getCell(itemName, itemColumn, dataColumn);
            return cell.findElement(buttonTag);
        }


        /**
         * This metod trim initial spaces of a string
         * @param toBeCut
         * @return
         */
        public String trimInitialSpaces(String toBeCut) {
            if (! toBeCut.equals("")) {
                while (Character.isSpaceChar(toBeCut.charAt(0))) {
                    toBeCut = toBeCut.substring(1, toBeCut.length());
                }
            }
            return toBeCut;
        }


        public int getRowNumBasedOnValue(String itemName, int itemColumnIndex){
        List<WebElement> rowCells;
        if(itemColumnIndex >= 0) {
            List<WebElement> rows = this.getRowsList();
            for (int j = 0; j < rows.size(); j++) {
                //The list of the cells row
                rowCells = rows.get(j).findElements(cellTag);//tr[1]
                String currentItem = rowCells.get(itemColumnIndex).getText();//tr[1]/td[2]
                currentItem = trimInitialSpaces(currentItem);
                //If the content of the cell, in the corresponding column, equal the column name, return the row
                if (currentItem.equalsIgnoreCase(itemName)) {
                    return j;
                }
            }
            for (int j = 0; j < rows.size(); j++) {
                //The list of the cells row
                rowCells = rows.get(j).findElements(getThTag());//tr[1]
                String currentItem = rowCells.get(itemColumnIndex).getText();//tr[1]/td[2]
                currentItem = trimInitialSpaces(currentItem);
                //If the content of the cell, in the corresponding column, equal the column name, return the row
                if (currentItem.equalsIgnoreCase(itemName)) {
                    return j;
                }
            }
            }
            return -1;
        }


        public String getFullContentOfTable(){

            List<WebElement> rowsList = getRowsList();
            StringBuilder strText = new StringBuilder();
            for (WebElement row : rowsList) {
                strText.append("#" + row.getText());
            }
            return strText.substring(1);
        }



}//end class
