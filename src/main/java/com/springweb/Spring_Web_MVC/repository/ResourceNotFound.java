package com.springweb.Spring_Web_MVC.repository;

public class ResourceNotFound  extends RuntimeException{
    public ResourceNotFound(String message) {
        super(message);
    }
}
