package com.example.UserService.dto.request;

public class UserSearchReq {
    public  int page =1 ;
    public  int size =10;
    public String text;
    public  boolean ascending = false;

    public String orderBy;
}
