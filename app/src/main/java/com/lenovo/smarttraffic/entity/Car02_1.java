package com.lenovo.smarttraffic.entity;

import java.util.List;

public class Car02_1 {

    /**
     * code : 0
     * serverinfo : [{"uv":3520,"hum":66,"rain":"当天和次日有雨","pm25":198,"co2":2649,"tem":27},{"uv":793,"hum":94,"rain":"三天之内有雨","pm25":7,"co2":8438,"tem":15},{"uv":4106,"hum":9,"rain":"三天之内有雨","pm25":2,"co2":5668,"tem":24},{"uv":3789,"hum":57,"rain":"三天之内有雨","pm25":177,"co2":8427,"tem":4},{"uv":4220,"hum":70,"rain":"三天之内没雨","pm25":35,"co2":4078,"tem":9},{"uv":2654,"hum":47,"rain":"三天之内没雨","pm25":124,"co2":210,"tem":29}]
     */

    private int code;
    private List<ServerinfoBean> serverinfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ServerinfoBean> getServerinfo() {
        return serverinfo;
    }

    public void setServerinfo(List<ServerinfoBean> serverinfo) {
        this.serverinfo = serverinfo;
    }

    public static class ServerinfoBean {
        /**
         * uv : 3520
         * hum : 66
         * rain : 当天和次日有雨
         * pm25 : 198
         * co2 : 2649
         * tem : 27
         */

        private int uv;
        private int hum;
        private String rain;
        private int pm25;
        private int co2;
        private int tem;

        public int getUv() {
            return uv;
        }

        public void setUv(int uv) {
            this.uv = uv;
        }

        public int getHum() {
            return hum;
        }

        public void setHum(int hum) {
            this.hum = hum;
        }

        public String getRain() {
            return rain;
        }

        public void setRain(String rain) {
            this.rain = rain;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public int getCo2() {
            return co2;
        }

        public void setCo2(int co2) {
            this.co2 = co2;
        }

        public int getTem() {
            return tem;
        }

        public void setTem(int tem) {
            this.tem = tem;
        }
    }
}
