/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.onclick.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

    private String content;
    private Date timestamp;

    public Order() {
    }

    public Order(String content, Date timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format("Order : { content='%s', timestamp='%s' }", content, timestamp);
    }
}
