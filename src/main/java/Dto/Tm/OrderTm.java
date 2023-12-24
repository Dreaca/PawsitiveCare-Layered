package Dto.Tm;

import lombok.*;


public class OrderTm {
    private String itemCode;
    private String Description;
    private int Qty;
    private double unitPrice;

    public OrderTm(String itemCode, String description, int qty, double unitPrice, double amount) {
        this.itemCode = itemCode;
        Description = description;
        Qty = qty;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderTm{" +
                "itemCode='" + itemCode + '\'' +
                ", Description='" + Description + '\'' +
                ", Qty=" + Qty +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                '}';
    }

    public OrderTm() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public int getQty() {
        return Qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getAmount() {
        return amount;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private double amount;
}
