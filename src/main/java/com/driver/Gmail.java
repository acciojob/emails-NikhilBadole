package com.driver;

import java.util.*;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    TreeMap<String, Mails> inbox = new TreeMap<>();  //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    TreeMap<String, Mails> trash = new TreeMap<>();  //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        Mails mail = new Mails(date, sender, message);

        while(inbox.size() >= this.inboxCapacity){
            Map.Entry<String,Mails> oldestStringMail = inbox.pollFirstEntry();
            trash.put(oldestStringMail.getKey(), oldestStringMail.getValue());
        }

        if(inbox.size() < this.inboxCapacity){
            inbox.put(message, mail);
        }
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

    }

    public void deleteMail(String message){
        if(inbox.containsKey(message)){
           trash.put(message,inbox.get(message));
           inbox.remove(message);
        }
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

    }

    public String findLatestMessage(){
        if(inbox.size() == 0){
            return "null";
        }

        Map.Entry<String,Mails> LatestStringMail = inbox.lastEntry();
        return LatestStringMail.getKey();
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

    }

    public String findOldestMessage(){
        if(inbox.size() == 0){
            return "null";
        }

        Map.Entry<String,Mails> oldestStringMail = inbox.firstEntry();
        return oldestStringMail.getKey();
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

    }

    public int findMailsBetweenDates(Date start, Date end){
        int numberOfMailsBetweenDates = 0;
        for(String msg : inbox.keySet()){
            Mails currMail = inbox.get(msg);
            if(currMail.getDate().after(start) || currMail.getDate().before(end) || currMail.getDate().equals(start) || currMail.getDate().equals(end)){
                numberOfMailsBetweenDates++;
            }
        }
        return numberOfMailsBetweenDates;
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
    }

    public int getInboxSize(){
        return inbox.size();
        // Return number of mails in inbox
    }

    public int getTrashSize(){
        return trash.size();
        // Return number of mails in Trash
    }

    public void emptyTrash(){
        trash.clear();
        // clear all mails in the trash
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
