package cn.waynezw.order.pojo;

import java.io.Serializable;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 17:56
 * @Description:
 */
public class Order implements Serializable {
    private Integer id;
    private String orderNo;
    private Integer itemId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}
