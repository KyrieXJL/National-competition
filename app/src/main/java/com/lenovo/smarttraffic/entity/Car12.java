package com.lenovo.smarttraffic.entity;

import java.util.List;

public class Car12 {

    /**
     * code : 0
     * serverinfo : [{"date":"2020-03-08","image":"null","titile":"夫子庙","content":"内容5"},{"date":"2020-05-21","image":"null","titile":"天安门","content":"内容2"},{"date":"2020-05-21","image":"null","titile":"夫子庙","content":"内容2"},{"date":"2020-06-01","image":"http://106.14.2.80:8080/ts/images/timg.jpg ","titile":"夫子庙","content":"内容3"},{"date":"2020-06-01","image":"http://106.14.2.80:8080/ts/images/timg.jpg ","titile":"长城","content":"内容1"}]
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
         * date : 2020-03-08
         * image : null
         * titile : 夫子庙
         * content : 内容5
         */

        private String date;
        private String image;
        private String titile;
        private String content;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitile() {
            return titile;
        }

        public void setTitile(String titile) {
            this.titile = titile;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
