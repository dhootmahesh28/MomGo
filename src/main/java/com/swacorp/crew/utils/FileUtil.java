package com.swacorp.crew.utils;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;


public class FileUtil {

    public  static final Logger loggerFileUtil = Logger.getLogger(FileUtil.class);


    /* Get the newest file for a specific extension */
    public File getTheNewestFile(String filePath, String ext) {
        File theNewestFile = null;
        File dir = new File(filePath);
        FileFilter fileFilter = new WildcardFileFilter("*." + ext);
        File[] files = dir.listFiles(fileFilter);

        if (files.length > 0) {
            /** The newest file comes first **/
            Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
            theNewestFile = files[0];
        }
        return theNewestFile;
    }

    public boolean isFileExist(String strFilePath){
        boolean blnFileExist = false;
        try{
            File f = new File(strFilePath);

            if (f.exists())
                blnFileExist = true;
        }catch(Exception ex){
            loggerFileUtil.info("Exception checkThePresenceOfFile : " + strFilePath);
            loggerFileUtil.error(ex);
        }
        return blnFileExist;

    }
}


