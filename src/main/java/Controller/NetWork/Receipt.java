package Controller.NetWork;

import java.util.Random;

public class Receipt {
    private String token;
    private Reciept_type reciept_type;
    private double money;
    private String sourceId;
    private String destId;
    private String description;
    private int id;
    private boolean isPAy;
    private static int idNum=0;

    public Receipt( int id,String token, Reciept_type reciept_type, double money, String sourceId, String destId, String description) {
        this.token = token;
        this.reciept_type = reciept_type;
        this.money = money;
        this.sourceId = sourceId;
        this.destId = destId;
        this.description = description;
        this.id = id;
        this.isPAy=false;
    }

    public boolean isPAy() {
        return isPAy;
    }

    public void setPAy(boolean PAy) {
        isPAy = PAy;
    }

    public Receipt(String token, Reciept_type reciept_type, double money, String sourceId, String destId, String description) {
        this.token = token;
        this.reciept_type = reciept_type;
        this.money = money;
        this.sourceId = sourceId;
        this.destId = destId;
        this.description = description;
        Random random=new Random();
        this.id=Math.abs(random.nextInt())%1000;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Reciept_type getReciept_type() {
        return reciept_type;
    }

    public void setReciept_type(Reciept_type reciept_type) {
        this.reciept_type = reciept_type;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }
}
