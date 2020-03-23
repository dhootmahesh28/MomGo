package com.swacorp.tsr.enums;

public class EnumRosa {

    public enum Status{
        SystemProcessing("System Processing"),
        RequestFailure("Request Failure");

        private String status;

        Status(String status) {
            this.status = status;
        }

        public String status() {
            return status;
        }
    }

    public enum User{
        FO("FO"),
        FA("FA");

        private String user;

        User(String user) {
            this.user = user;
        }

        public String user() {
            return user;
        }
    }
}


