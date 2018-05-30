package android.zgy.meichang.mySQLite;


public class myOrder {
    public int id;
    public String content;
    public String danjia;
    public String date;

    public myOrder() {
    }

    public myOrder(int id, String content, String danjia, String date) {
        this.id = id;
        this.content = content;
        this.danjia = danjia;
        this.date = date;
    }
}
