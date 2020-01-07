package com.swacorp.crew.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.hp.lft.sdk.winforms.Window;
import com.swacorp.crew.pages.common.BasePage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.swacorp.crew.pages.common.Constant;

// This class will be a wrapper to ReportUtil.
public class callReportUtil {
    private ReportUtil report;

    callReportUtil(){
        createNewReportInstance();
    }

    private void createNewReportInstance() {
        report = new ReportUtil();

    }

    public ReportUtil getReportInstance() {
        if (report != null) {
            return report;
        }else
            return null;
    }
}
