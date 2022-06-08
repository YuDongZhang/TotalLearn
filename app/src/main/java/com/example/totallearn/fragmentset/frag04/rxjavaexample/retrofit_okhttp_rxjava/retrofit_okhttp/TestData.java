package com.example.totallearn.fragmentset.frag04.rxjavaexample.retrofit_okhttp_rxjava.retrofit_okhttp;

import java.util.List;

public class TestData {

    /**
     * dataCount : 26
     * doctorList : [{"doctorFlow":"03408c1815274fc9a7a6a6b38926f10b","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190813/129868fda00d45feb34a3ae3096612b6.png","doctorName":"李铭宇","phone":"18036786918","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"},{"doctorFlow":"07e7ca5dd4bf49cea1c6f95aae706fbc","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190820/065b54adea9742c3b5d1fbd29f7595ca.jpg","doctorName":"房乐","phone":"15262231371","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"},{"doctorFlow":"0c9363f5afbb4ef4b1d3711649bf178b","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190812/6cd27ce3369140f4b928637a44429f47.jpg","doctorName":"马宁福","phone":"18012097575","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"},{"doctorFlow":"8e59188f8d374378a026ca4a375f6a6c","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190815/0a052f35c6f0493d8da415eb0d64a992.jpg","doctorName":"付家旭","phone":"18842612753","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"},{"doctorFlow":"afbb02d0ab364979813796fbf6ee9988","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190812/4a0f134fb3ef4836854eefb21918a1a9.jpg","doctorName":"陈兴宇","phone":"15062991279","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"},{"doctorFlow":"bf5b1f8aadc947529e38ad35eb4c0e7a","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190817/bb28fbed5d10463493c46af3980054b7.jpg","doctorName":"唐伟","phone":"18251060065","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"},{"doctorFlow":"c803a4c843e24895bfe049df5e42536f","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190816/81a2e71956e64228ba29e20366a95d25.jpg","doctorName":"潘驰","phone":"15005268235","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"},{"doctorFlow":"e72c022c20f24b6bbe862a8844125ab0","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190812/c39c53aaff604ddf95efadfa826c98a4.jpg","doctorName":"穆斯塔帕·达毛拉","phone":"18900960961","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"},{"doctorFlow":"e9d840a2612a4c07a84da119d21079a9","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190813/3b57a4c97685497b9c3839434014b3c1.jpg","doctorName":"李洋洋","phone":"18340837992","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"},{"doctorFlow":"f817dd5ccff94497a0dd83767622654a","doctorImg":"https://js.ezhupei.com/pdsciupload/userImages/20190813/871499b383254ca29d88a3b3c144ca8d.jpg","doctorName":"陈明政","phone":"18340838619","qty":"1","sessionNumber":"2019","trainingSpeName":"外科"}]
     * resultId : 200
     * resultType : 交易成功
     */

    public String dataCount;
    public int resultId;
    public String resultType;
    public List<DoctorListBean> doctorList;

    public static class DoctorListBean {
        /**
         * doctorFlow : 03408c1815274fc9a7a6a6b38926f10b
         * doctorImg : https://js.ezhupei.com/pdsciupload/userImages/20190813/129868fda00d45feb34a3ae3096612b6.png
         * doctorName : 李铭宇
         * phone : 18036786918
         * qty : 1
         * sessionNumber : 2019
         * trainingSpeName : 外科
         */

        public String doctorFlow;
        public String doctorImg;
        public String doctorName;
        public String phone;
        public String qty;
        public String sessionNumber;
        public String trainingSpeName;
    }
}
