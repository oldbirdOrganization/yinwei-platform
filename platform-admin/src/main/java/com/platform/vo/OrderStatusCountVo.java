package com.platform.vo;

import java.io.Serializable;

/**
 * Created by yanrum on 2019/10/21 21:09
 * Description:
 */
public class OrderStatusCountVo implements Serializable {
    private int all;//全部数量
    private int toBeAssigned;//待指派
    private int assigned;//已指派（服务中）
    private int completed;//已完成
    private int invalid;//作废
    private int toBePaid;//待付款
    private int paid;//已付款

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getToBeAssigned() {
        return toBeAssigned;
    }

    public void setToBeAssigned(int toBeAssigned) {
        this.toBeAssigned = toBeAssigned;
    }

    public int getAssigned() {
        return assigned;
    }

    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getInvalid() {
        return invalid;
    }

    public void setInvalid(int invalid) {
        this.invalid = invalid;
    }

    public int getToBePaid() {
        return toBePaid;
    }

    public void setToBePaid(int toBePaid) {
        this.toBePaid = toBePaid;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
}
