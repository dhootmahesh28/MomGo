package com.swacorp.crew.utils;

import com.swacorp.decryptpassword.DecryptPlainPassword;
import com.swacorp.util.dbutil.DBConnector;
import com.swacorp.util.dbutil.DBQueryImplementation;
import com.swacorp.util.properties.ProjectProperties;
import org.apache.log4j.Logger;
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
    private DBConnector dbConnector;
    public static final Logger LOGGER = Logger.getLogger(ResultUploadToALM.class);

    public ResultUploadToALM() {
        dbConnector = new DBConnector();
    }

    public void uploadResultToALM(String uploadResultstoDB) {
        LOGGER.info("::::::Ready to upload Test Results to ALM::::::");
        ResultUploadToALM resultUploadToALM = new ResultUploadToALM();
        if (uploadResultstoDB != null && ("Yes".equalsIgnoreCase(uploadResultstoDB) || "True".equalsIgnoreCase(uploadResultstoDB))) {
            if (System.getProperty("ALMCycleId") != null) {
                resultUploadToALM.uploadResults();
                LOGGER.info("::::::Test Results uploaded to ALM::::::");
            } else
                LOGGER.error("Enter ALMCycle ID to update the automation run results in the respective ALM cycle");
        }
    }

    public void uploadResults() {
        Connection connection;
        String dbPassword;
        DBQueryImplementation dbQueryImplementation;
        try {
            ProjectProperties.load();
            dbPassword = DecryptPlainPassword.plainPassword(ProjectProperties.dbPassKey, ProjectProperties.dbEncryptedPassword);
            connection = dbConnector.init(ProjectProperties.dbClass, ProjectProperties.dbURL, ProjectProperties.dbUserName, dbPassword);
            dbQueryImplementation = new DBQueryImplementation(connection);
            String[] cycleID = ProjectProperties.almCycleId.split(",");
            for (int i = 0; i < cycleID.length; i++) {
                setAlmCycleId(cycleID[i]);
                dbQueryImplementation.uploadJBehaveResultsToDB();
            }
        } catch (ParseException | ClassNotFoundException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException | SQLException | NoSuchPaddingException | IOException | IllegalBlockSizeException e) {
            LOGGER.error(e);
        } finally {
            dbConnector.close();
        }
    }

    private static void setAlmCycleId(String data){
        ProjectProperties.almCycleId = data;
    }
}