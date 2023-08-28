package Jdbc;

public class Bus {
    private int busNo;
    private boolean ac;
    private int capacity;
    Bus(int a,boolean b,int c)
    {
        busNo=a;
        ac=b;
        capacity=c;
    }

    public int getBusNo() {
        return busNo;
    }
    public void setBusNo(int a)
    {
        busNo=a;
    }
    public boolean getAc() {
        return ac;
    }
    public void setAc(boolean a)
    {
        ac=a;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int a)
    {
        capacity=a;
    }
}
