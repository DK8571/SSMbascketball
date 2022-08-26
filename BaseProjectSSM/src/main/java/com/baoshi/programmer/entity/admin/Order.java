package com.baoshi.programmer.entity.admin;

import com.baoshi.programmer.util.DateUtils;

import java.util.Date;

public class Order {
    private Long id;
    private Long ordertypeid;
    private Long timeid;
    private int number;
    private Long memberid;
    private Double price;
    private Long venuesid;
    private int quote;
    private String quotestr;
    private Date date;
    private String datestr;


    public Long getOrdertypeid() {
        return ordertypeid;
    }

    public void setOrdertypeid(Long ordertypeid) {
        this.ordertypeid = ordertypeid;
    }
    public String getQuotestr() {
        if (quote==0){
            quotestr="无人预定";
        }else if (quote==1){
            quotestr="不可预约比赛";
        }else if (quote==2){
            quotestr="有教学课程不可预约";
        }
        return quotestr;
    }
    public void setQuotestr(String quotestr) {
        this.quotestr = quotestr;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeid() {
        return timeid;
    }

    public void setTimeid(Long timeid) {
        this.timeid = timeid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getMemberid() {
        return memberid;
    }

    public void setMemberid(Long memberid) {
        this.memberid = memberid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getVenuesid() {
        return venuesid;
    }

    public void setVenuesid(Long venuesid) {
        this.venuesid = venuesid;
    }

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordertypeid=" + ordertypeid +
                ", timeid=" + timeid +
                ", number=" + number +
                ", memberid=" + memberid +
                ", price=" + price +
                ", venuesid=" + venuesid +
                ", quote=" + quote +
                ", quotestr='" + quotestr + '\'' +
                ", date=" + date +
                ", datestr='" + datestr + '\'' +
                '}';
    }
}
