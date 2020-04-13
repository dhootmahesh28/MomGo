package com.swacorp.tsr.enums;

public class EnumCssTransactionReport {

    public enum Function{
        LOGINCRW("LOGINCRW"),
        ACKNOTIF("ACKNOTIF");

        private String function;

        Function(String function) {
            this.function = function;
        }

        public String getValue() {
            return function;
        }
    }

    public enum Reason{
        LGN("LGN"),
        ING("ING");
        private String reason;
        Reason(String reason) {
            this.reason = reason;
        }
        public String getValue() {
            return reason;
        }
    }

}


