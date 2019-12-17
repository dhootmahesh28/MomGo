package com.swacorp.crew.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataReader {

    public HashMap<String, HashMap<Integer, HashMap<String, String>>> masterData = new HashMap<String, HashMap<Integer, HashMap<String, String>>>();
    public List<String[]> readFile(String fileName) throws IOException {
        List<String[]> result = new ArrayList<String[]>();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        try {
            // Read first line
            line = br.readLine();

            // Run through following lines
            while (line != null) {
                // Break line into entries using comma
                //String line = br.readLine();
                String[] items = line.split(",");
                try {
                    if (!(items[1].equalsIgnoreCase("TRUE"))) {
                        result.add(items);
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                    // Caught errors indicate a problem with data format -> Print warning and continue
                    //System.out.println("Invalid line: " + line);
                    throw e;
                }
                line = br.readLine();
            }
            return result;
        } finally {
            br.close();
        }
    }


/*    Usage:
            masterData.get("TC1").get(1).get("FirstName")
            masterData.get("TC2").get(1).get("EmployeeName")*/
    //convert List<String[]> to Hashmap<TC,HashMap<Iteration. HashMap<String,String>>>
    public HashMap<String, HashMap<Integer, HashMap<String, String>>> readTestDataMaster(List<String[]> listCsvRows) {
        boolean nextDataSet = false;
        String[] headerRowData = null;
        String testCaseName = null;

        for (int rowNumber = 0; rowNumber<listCsvRows.size(); rowNumber++ ){
            int nextRow;
            String rowData[];
            String nextData[];
            int iteration = 1;
            rowData = listCsvRows.get(rowNumber);

            //Check if the first three headers are TestCaseName, SkipData and Iteration
            if (rowData[0].equalsIgnoreCase("TestCaseName") && rowData[1].equalsIgnoreCase("SkipData") && rowData[2].equalsIgnoreCase("Iteration")){
                HashMap<String, String> data = new HashMap<String, String>();
                HashMap<Integer, HashMap<String, String>> itrData = new HashMap<Integer, HashMap<String, String>>();
                nextRow = rowNumber+1;
                headerRowData = rowData;
                nextData = listCsvRows.get(nextRow);
                testCaseName = nextData[0];
                for (int i=3; i< rowData.length ; i++){ //3 because first 3 headers are static.
                    data.put(rowData[i],nextData[i]);
                }
                itrData.put(iteration,data);
                iteration++;
                rowNumber = nextRow;
                masterData.put(testCaseName, itrData);
            }
            //TODO: Implement the lgic for Iteration wise testdata into Map.
            /*else{
                nextRow = rowNumber+1;
                nextData = listCsvRows.get(nextRow);
                for (int i=3; i< rowData.length ; i++){
                    data.put(headerRowData[i],nextData[i]);
                }
                itrData.put(iteration,data);
                iteration++;
                rowNumber = nextRow;
            }*/

        }
 return masterData;
}

/*    public static void main(String args[]) throws IOException {
        System.out.println("TestDataReader");
        TestDataReader TestDataReader = new TestDataReader();
     //  List<String[]> strings = TestDataReader.readFile("C:\\Users\\x257093\\Desktop\\Work\\gitRepo\\qmo-crew-automation\\src\\test\\TestData\\testdata.csv");
        List<String[]> strings = TestDataReader.readFile(System.getProperty("user.dir")+"\\src\\test\\TestData\\testdata.csv");
        TestDataReader.readTestDataMaster(strings);
        System.out.println(strings);

        System.out.println( System.getProperty("user.dir"));
    }*/
}
