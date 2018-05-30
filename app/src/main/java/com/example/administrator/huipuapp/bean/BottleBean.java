package com.example.administrator.huipuapp.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class BottleBean {


    private  String pageCount;
    private  int state;
    private  int nowPage;
    private List<CommunicationList> communicationList;

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }




    public List<CommunicationList> getCommunicationList() {
        return communicationList;
    }

    public void setCommunicationList(List<CommunicationList> communicationList) {
        this.communicationList = communicationList;
    }

    public  class  CommunicationList{
        private List<Messages> messages;
        private Bottle bottle;
        private String dialogue_id;
        private String registration_id;


        public List<Messages> getMessages() {
            return messages;
        }

        public void setMessages(List<Messages> messages) {
            this.messages = messages;
        }

        public Bottle getBottle() {
            return bottle;
        }

        public void setBottle(Bottle bottle) {
            this.bottle = bottle;
        }

        public String getDialogue_id() {
            return dialogue_id;
        }

        public void setDialogue_id(String dialogue_id) {
            this.dialogue_id = dialogue_id;
        }

        public String getRegistration_id() {
            return registration_id;
        }

        public void setRegistration_id(String registration_id) {
            this.registration_id = registration_id;
        }
    }


    public  class  Bottle implements Serializable {

        private  String bottle_id;
        private  String bottle_user_name;
        private  String bottle_user_area;
        private  String bottle_time;


        public String getBottle_id() {
            return bottle_id;
        }

        public void setBottle_id(String bottle_id) {
            this.bottle_id = bottle_id;
        }

        public String getBottle_user_name() {
            return bottle_user_name;
        }

        public void setBottle_user_name(String bottle_user_name) {
            this.bottle_user_name = bottle_user_name;
        }

        public String getBottle_user_area() {
            return bottle_user_area;
        }

        public void setBottle_user_area(String bottle_user_area) {
            this.bottle_user_area = bottle_user_area;
        }

        public String getBottle_time() {
            return bottle_time;
        }

        public void setBottle_time(String bottle_time) {
            this.bottle_time = bottle_time;
        }
    }


    public  class  Messages implements Serializable{


        private  String message_id;
        private  String message_content;
        private  String message_response_order;
        private  String message_type;
        private  String message_time;
        private  String create_time;


        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }

        public String getMessage_content() {
            return message_content;
        }

        public void setMessage_content(String message_content) {
            this.message_content = message_content;
        }

        public String getMessage_response_order() {
            return message_response_order;
        }

        public void setMessage_response_order(String message_response_order) {
            this.message_response_order = message_response_order;
        }

        public String getMessage_type() {
            return message_type;
        }

        public void setMessage_type(String message_type) {
            this.message_type = message_type;
        }

        public String getMessage_time() {
            return message_time;
        }

        public void setMessage_time(String message_time) {
            this.message_time = message_time;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
