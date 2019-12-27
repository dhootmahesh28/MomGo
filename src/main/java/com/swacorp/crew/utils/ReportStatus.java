package com.swacorp.crew.utils;


public class ReportStatus {

        private static int status;
        private static String reportMsg;

        public static int getStatus() {
            return status;
        }

        public static void setStatus(int status) {
            status = status;
        }
        public static String getReportMsg() {
            return reportMsg;
        }

        public static void setReportMsg(String reptMsg) {
            reportMsg = reptMsg;
        }

        public static void reset(){
            setStatus(-1);
            setReportMsg("--Reset--");
        }
}
