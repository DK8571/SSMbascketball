package com.baoshi.programmer.entity.admin;

import com.baoshi.programmer.util.DateUtils;

import java.util.Date;

public class Turnover {
    private Date date;
    private String datestr;
    private Double price;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDatestr() {
        if(date!=null){
            datestr = DateUtils.datetoString(date,"yyyy-MM-dd");
        }
        return datestr;
    }

    public void setDatestr(String datestr) {
        this.datestr = datestr;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Turnover{" +
                "date=" + date +
                ", datestr='" + datestr + '\'' +
                ", price=" + price +
                '}';
    }
}
