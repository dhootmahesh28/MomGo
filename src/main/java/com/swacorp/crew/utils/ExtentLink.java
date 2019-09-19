package com.swacorp.crew.utils;

import com.aventstack.extentreports.markuputils.Markup;

/**
 * Created by x219949 on 8/30/2018.
 */
public class ExtentLink implements Markup {

    private String urlLink;
    private String textLink;

    public void setLink(String linkText, String linkUrl) {
        this.textLink = linkText;
        this.urlLink = linkUrl;
    }

    @Override
    public String getMarkup() {
        String lhs = "<a href=" + urlLink + ">";
        String rhs = "</a>";
        return lhs + textLink + rhs;
    }

    @Override
    public String toString() {
        return this.textLink;
    }

}
