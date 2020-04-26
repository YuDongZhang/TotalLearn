package com.example.totallearn.fragmentset.frag04.f4entity;

import java.util.List;

/**
 * Created by Shinelon on 2019/11/22.
 */

public class JokeEntity {

    public int code;
    public String message;
    public List<ResultBean> result;

    @Override
    public String toString() {
        return "JokeEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result.toString() +
                '}';
    }

    public static class ResultBean {

        public String sid;
        public String text;
        public String type;
        public String thumbnail;
        public String video;
        public Object images;
        public String up;
        public String down;
        public String forward;
        public String comment;
        public String uid;
        public String name;
        public String header;
        public String top_comments_content;
        public String top_comments_voiceuri;
        public String top_comments_uid;
        public String top_comments_name;
        public String top_comments_header;
        public String passtime;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "sid='" + sid + '\'' +
                    ", text='" + text + '\'' +
                    ", type='" + type + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", video='" + video + '\'' +
                    ", images=" + images +
                    ", up='" + up + '\'' +
                    ", down='" + down + '\'' +
                    ", forward='" + forward + '\'' +
                    ", comment='" + comment + '\'' +
                    ", uid='" + uid + '\'' +
                    ", name='" + name + '\'' +
                    ", header='" + header + '\'' +
                    ", top_comments_content='" + top_comments_content + '\'' +
                    ", top_comments_voiceuri='" + top_comments_voiceuri + '\'' +
                    ", top_comments_uid='" + top_comments_uid + '\'' +
                    ", top_comments_name='" + top_comments_name + '\'' +
                    ", top_comments_header='" + top_comments_header + '\'' +
                    ", passtime='" + passtime + '\'' +
                    '}';
        }
    }
}
