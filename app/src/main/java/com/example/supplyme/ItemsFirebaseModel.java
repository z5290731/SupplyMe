package com.example.supplyme;

public class ItemsFirebaseModel {

    public String itemName;
    public String itemType;
    public String itemQuantity;
    public String itemDescription;
    public String itemSupplier;
    public String itemContactSupplier;

    public ItemsFirebaseModel(String itemName, String itemType, String itemQuantity, String itemDescription, String itemSupplier, String itemContactSupplier) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemQuantity = itemQuantity;
        this.itemDescription = itemDescription;
        this.itemSupplier = itemSupplier;
        this.itemContactSupplier = itemContactSupplier;
    }

    public ItemsFirebaseModel() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemSupplier() {
        return itemSupplier;
    }

    public void setItemSupplier(String itemSupplier) {
        this.itemSupplier = itemSupplier;
    }

    public String getItemContactSupplier() {
        return itemContactSupplier;
    }

    public void setItemContactSupplier(String itemContactSupplier) {
        this.itemContactSupplier = itemContactSupplier;
    }
}
