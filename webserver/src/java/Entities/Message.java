/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import com.google.gson.Gson;
import java.util.Date;

/**
 *
 * @author orlandocamacho
 */
public class Message {
    private String sender;
    private String addressee; 
    private String text;
    private Date date;

    public Message(String sender, String addressee, String text) {
        this.sender = sender;
        this.addressee = addressee;
        this.text = text;
        this.date = new Date();
    }

    public String getSender() {
        return sender;
    }

    public String getAddressee() {
        return addressee;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Message{" + "sender=" + sender + ", addressee=" + addressee + ", text=" + text + ", date=" + date + '}';
    }
    
    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
