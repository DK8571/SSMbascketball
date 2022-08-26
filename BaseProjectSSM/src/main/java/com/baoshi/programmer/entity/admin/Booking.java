package com.baoshi.programmer.entity.admin;

import java.util.Date;

public class Booking {
    private Long id;
    private Long ordertypeid;
    private Long timeid;
    private String time;
    private int number;
    private Long memberid;
    private Double price;
    private Long venuesid;
    private int quote;
    private String quotestr;
    private Date date;
    private String datestr;
    private int sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrdertypeid() {
        return ordertypeid;
    }

    public void setOrdertypeid(Long ordertypeid) {
        this.ordertypeid = ordertypeid;
    }

    public Long getTimeid() {
        return timeid;
    }

    public void setTimeid(Long timeid) {
        this.timeid = timeid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getQuotestr() {
        if (quote==0){
            quotestr="无人预定";
        }else if (quote==1){
            quotestr="有比赛不可预约";
        }else if (quote==2){
            quotestr="不可预约比赛";
        }
        return quotestr;
    }

    public void setQuotestr(String quotestr) {
        this.quotestr = quotestr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDatestr() {
        return datestr;
    }

    public void setDatestr(String datestr) {
        this.datestr = datestr;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", ordertypeid=" + ordertypeid +
                ", timeid=" + timeid +
                ", time='" + time + '\'' +
                ", number=" + number +
                ", memberid=" + memberid +
                ", price=" + price +
                ", venuesid=" + venuesid +
                ", quote=" + quote +
                ", quotestr='" + quotestr + '\'' +
                ", date=" + date +
                ", datestr='" + datestr + '\'' +
                ", sum=" + sum +
                '}';
    }
}
