package com.swacorp.tsr.enums;

public class EnumWaitConstants {
    public enum WaitDuration{
        ONE_SEC(1000),
        TWO_SEC(2000),
        TWHREE_SEC(3000),
        FIVE(5000),
        TEN(10000),
        FIFTEEN(15000),
        TWENTY(20000),
        THIRTY(30000),
        FORTYFIVE(45000),
        ONE_MIN(60000),
        FIFTEEN_MIN(900000);

        private int status;

        WaitDuration(int status) {
            this.status = status;
        }

        public int status() {
            return status;
        }
    }

}
