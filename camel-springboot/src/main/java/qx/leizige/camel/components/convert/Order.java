package qx.leizige.camel.components.convert;

import java.io.Serializable;

public class Order implements Serializable {
    private String name;
    private String orderNo;

    public Order(String name, String orderNo) {
        this.name = name;
        this.orderNo = orderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
