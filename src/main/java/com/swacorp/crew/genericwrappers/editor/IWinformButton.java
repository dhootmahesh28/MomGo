package com.swacorp.crew.genericwrappers.editor;

import com.hp.lft.sdk.GeneralLeanFtException;
import com.swacorp.crew.utils.ReportStatus;
import org.apache.log4j.Logger;
import com.swacorp.crew.pages.constants.MessageConstants;

public interface IButton<T extends com.hp.lft.sdk.winforms.Button> {

    public static final Logger LOGGER = Logger.getLogger(IEditor.class);

    default void btnClick(T obj) throws GeneralLeanFtException {
        try {
            obj.click();
            LOGGER.info(MessageConstants.MSG_CLICKED_SUCCESSFULLY +obj.getDisplayName()+ MessageConstants.OBJECT_TYPE +obj.getClass());
        }catch(Exception e){
            ReportStatus.setReportMsg(MessageConstants.MSG_CLICKED_UNSUCCESSFUL +obj.getObjectName()+ MessageConstants.OBJECT_TYPE +obj.getClass()+"Error: "+e.getMessage());
            LOGGER.error(e);
        }
    }
}
