package com.lenovo.smarttraffic.bean;

import java.util.List;

public class InfoBean {

    /**
     * code : 0
     * serverinfo : {"registetime":"2017-08-06 13:37:52","sex":"女","name":"李四","mobile":"15961733591","carlist":[{"carno":"辽A12335","brand":"benchi"},{"carno":"辽A2341","brand":"baoma"}],"id":"320211198012030502","userid":0}
     */

    private int code;
    private ServerinfoBean serverinfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServerinfoBean getServerinfo() {
        return serverinfo;
    }

    public void setServerinfo(ServerinfoBean serverinfo) {
        this.serverinfo = serverinfo;
    }

    public static class ServerinfoBean {
        /**
         * registetime : 2017-08-06 13:37:52
         * sex : 女
         * name : 李四
         * mobile : 15961733591
         * carlist : [{"carno":"辽A12335","brand":"benchi"},{"carno":"辽A2341","brand":"baoma"}]
         * id : 320211198012030502
         * userid : 0
         */

        private String registetime;
        private String sex;
        private String name;
        private String mobile;
        private String id;
        private int userid;
        private List<CarlistBean> carlist;

        public String getRegistetime() {
            return registetime;
        }

        public void setRegistetime(String registetime) {
            this.registetime = registetime;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public List<CarlistBean> getCarlist() {
            return carlist;
        }

        public void setCarlist(List<CarlistBean> carlist) {
            this.carlist = carlist;
        }

        public static class CarlistBean {
            /**
             * carno : 辽A12335
             * brand : benchi
             */

            private String carno;
            private String brand;

            public String getCarno() {
                return carno;
            }

            public void setCarno(String carno) {
                this.carno = carno;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }
        }
    }
}
