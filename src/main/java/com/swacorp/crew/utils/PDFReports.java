package com.swacorp.tsr.utils.pdf;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDFReports {
    ArrayList<String> pdfContentLines = new ArrayList<String>();
    String[] pdfLines;
    String[] tags = {"TRIP TO PULL", "SPLIT TRIP","ASSIGNED PORTION","OPEN TIME PORTION","OPEN TIME PORTION" };


    public PDFReports(String pdfPath) throws Exception{


        try (PDDocument document = PDDocument.load(new File(pdfPath))) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                pdfLines = pdfFileInText.split("\\r?\\n");
            }
        }
    }

    public ArrayList<String> readBetweenTags(String startTag, String endTag){
        int i=0;
        boolean startReading = false;
        boolean stopReading = false;
        for (String line : pdfLines) {
            if (line.equalsIgnoreCase(endTag)){
                stopReading = true;
                break;
            }

            if (startReading && !stopReading){
                pdfContentLines.add(i,line);
                i++;
            }

            if (line.equalsIgnoreCase(startTag)){
                startReading = true;
            }
        }
        return pdfContentLines;
    }

    public ArrayList<String> readBetweenTags(String startTag, int startIndex, String endTag, int endIndex, String ignorPat, String searchPat){
        int i=0;
        int countStartIndex = 0;
        int countEndIndex = 0;
        boolean startReading = false;
        boolean stopReading = false;
        Pattern ignorePattern = Pattern.compile(ignorPat);
        Pattern searchPattern = Pattern.compile(searchPat);
        for (String line : pdfLines) {
            Matcher ignoreMatches = ignorePattern.matcher(line);
            Matcher searchMatches = searchPattern.matcher(line);
            if (!ignoreMatches.find() | searchMatches.find()){
                if (line.equalsIgnoreCase(endTag)){
                    countEndIndex++;

                    if (countEndIndex == endIndex) {
                        stopReading = true;
                        break;
                    }
                }

                if (startReading && !stopReading){
                    pdfContentLines.add(i,line);
                    i++;
                }

                if (line.equalsIgnoreCase(startTag)){
                    countStartIndex++;
                    if (countStartIndex == startIndex) {
                        startReading = true;
                    }
                }
            }
        }
        return pdfContentLines;
    }


}
