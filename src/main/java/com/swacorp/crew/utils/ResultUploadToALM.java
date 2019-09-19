package com.swacorp.crew.utils;

import com.swacorp.decryptpassword.DecryptPlainPassword;
import com.swacorp.util.dbutil.DBConnector;
import com.swacorp.util.dbutil.DBQueryImplementation;
import com.swacorp.util.properties.ProjectProperties;
import org.json.simple.parser.ParseException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;

public class ResultUploadToALM {
    private Connection connection;
    private DBConnector dbConnector;
    private String dbPassword;
    private DBQueryImplementation dbQueryImplementation;

    public ResultUploadToALM() {
        dbConnector = new DBConnector();
    }

    public void uploadResultToALM(String uploadResultstoDB) {
        System.out.println("::::::Ready to upload Test Results to ALM::::::");
        ResultUploadToALM resultUploadToALM = new ResultUploadToALM();
        if (uploadResultstoDB != null && ("Yes".equalsIgnoreCase(uploadResultstoDB) || "True".equalsIgnoreCase(uploadResultstoDB))) {
            if (System.getProperty("ALMCycleId") != null) {
                resultUploadToALM.uploadResults();
                System.out.println("::::::Test Results uploaded to ALM::::::");
            } else
                System.err.println("Enter ALMCycle ID to update the automation run results in the respective ALM cycle");
        }
    }

    public void uploadResults() {
        try {
            ProjectProperties.load();
            dbPassword = DecryptPlainPassword.plainPassword(ProjectProperties.dbPassKey, ProjectProperties.dbEncryptedPassword);
            connection = dbConnector.init(ProjectProperties.dbClass, ProjectProperties.dbURL, ProjectProperties.dbUserName, dbPassword);
            dbQueryImplementation = new DBQueryImplementation(connection);
            String[] cycleID = ProjectProperties.almCycleId.split(",");
            for (int i = 0; i < cycleID.length; i++) {
                ProjectProperties.almCycleId = cycleID[i];
                dbQueryImplementation.uploadJBehaveResultsToDB();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            dbConnector.close();
        }
    }

}
