package cn.waynezw.item.pojo;

import java.io.Serializable;

/**
 * @Auther: Wayne Zhang
 * @Date: 2019-03-15 17:56
 * @Description:
 */
public class Item implements Serializable {

    private Integer id;
    private String name;
    private Integer amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
