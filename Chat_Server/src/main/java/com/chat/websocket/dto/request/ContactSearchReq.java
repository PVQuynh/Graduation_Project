package com.chat.websocket.dto.request;

public class ContactSearchReq {
    public  int page =1 ;
    public  int size =10;
    public String text;
    public  boolean ascending = false;

    public String orderBy;
}
