package com.warframe.mytmall.pojo;

/**
 * @Author warframe[github.com/WarframePrimer]
 * @Date 2017/9/13 10:24
 */
public class OrderItemCustom {
    private int id;
    private int oid;
    private int pid;
    private int uid;
    private int number;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOid() {
        return oid;
    }

    @Override
    public String toString() {
        return "OrderItemCustom{" +
                "id=" + id +
                ", oid=" + oid +
                ", pid=" + pid +
                ", uid=" + uid +
                ", number=" + number +
                '}';
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
