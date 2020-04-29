package com.swacorp.crew.pages.constants;

public class EnumRosa {

    public enum Bidline{
        HARDLINE("Hard Line"),
        BLANKLINE("Blank Line");

        private String bidline;

        Bidline(String bidline) {
            this.bidline = bidline;
        }

        public String getValue() {
            return bidline;
        }
    }

    public enum Category{
        RECURRENT("Recurrent");

        private String category;

        Category(String category) {
            this.category = category;
        }

        public String getValue() {
            return category;
        }
    }

    public enum Aircraft{
        ALL737("737 All");

        private String aircraft;

        Aircraft(String aircraft) {
            this.aircraft = aircraft;
        }

        public String getValue() {
            return aircraft;
        }
    }

    public enum Cycle{
        ALL("All"),
        SIXMONTHS("6/12 MONTH"),
        TWELVEMONTHS("AQP 12 Month");

        private String cycle;

        Cycle(String cycle) {
            this.cycle = cycle;
        }

        public String getValue() {
            return cycle;
        }
    }

    public enum Status{
        SYSTEMPROCESSING("System Processing"),
        REQUESTFAILURE("Request Failure"),
        COMMITTED("Committed");

        private String status;

        Status(String status) {
            this.status = status;
        }

        public String getValue() {
            return status;
        }
    }

    public enum Position{
        CAPTAIN("Captain"),
        FO("FO");

        private String position;

        Position(String position) {
            this.position = position;
        }

        public String getValue() {
            return position;
        }
    }
}


