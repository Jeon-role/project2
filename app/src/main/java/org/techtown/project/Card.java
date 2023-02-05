package org.techtown.project;


public class Card {                    //아이템타입이 무엇인지 찾는 클래스
    int itemViewType;

    public Card(int itemViewType) {
        this.itemViewType = itemViewType;
    }

    public int getItemViewType() {
        return itemViewType;
    }
    public void setItemViewType(int itemViewType) {
        this.itemViewType = itemViewType;
    }

}
