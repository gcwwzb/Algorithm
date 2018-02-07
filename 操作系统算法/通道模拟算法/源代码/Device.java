package Channel;
public class Device {
    private String data;//设备、存储器中的数据
    private int dataLength;

    Device() {
    }

    Device(String data, Channel cm) {
        this.setData(data);
        this.dataLength = data.length();
        if (this.dataLength > cm.getMaxDevCap())//自动修正大小
            cm.setMaxDevCap(this.dataLength);
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    int getDataLength() {
        return dataLength;
    }

    void setData(String data) {
        this.data = data;
    }

    String getData() {
        return data;
    }

}

