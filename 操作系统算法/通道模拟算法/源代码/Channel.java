package Channel;

	import java.util.ArrayList;
	import java.util.List;

	public class Channel implements Runnable {
	    private String interrupt;//提示中断信号
	    private int maxDevCap = 0;//所有设备的最大传输数据长度
	    private List<Device> Devices;//所有外围设备
	    private List<Device> memory;//存储器

	    public Channel() {
	        this.interrupt = "none";
	        this.Devices = new ArrayList<Device>();
	        this.Devices.add(new Device());
	        this.Devices.add(new Device());
	        this.Devices.add(new Device());
	        this.memory = new ArrayList<Device>();
	        this.memory.add(new Device("computer", this));
	        this.memory.add(new Device("architecture", this));
	        this.memory.add(new Device("channalTest", this));
	    }

	    public void setInterrupt(String interrupt) {
	        this.interrupt = interrupt;
	    }

	    public String getInterrupt() {
	        return interrupt;
	    }

	    public void setMaxDevCap(int maxDevCap) {
	        this.maxDevCap = maxDevCap;
	    }

	    public int getMaxDevCap() {
	        return maxDevCap;
	    }

	    public void setDevices(List<Device> devices) {
	        Devices = devices;
	    }

	    public List<Device> getDevices() {
	        return Devices;
	    }

	    public void printDevice() {
	        for (Device d : this.Devices) {
	            System.out.print(" "+d.getData());
	        }
	    }

	    @Override
	    public void run() {//通道处理器进行数据传送
	    	System.out.println("通道处理机连接设备数量："+this.Devices.size());
	        for (int i = 0; i < this.maxDevCap; i++)
	            for (int j = 0; j < this.Devices.size(); j++) {
	                if (i < this.memory.get(j).getDataLength()) {
	                    this.Devices.get(j).setData(this.memory.get(j).getData().substring(0, i + 1));

	                }
                    System.out.println();
	                printDevice();
	            }
	       // printDevice();
	        this.interrupt = "finish";//传送完后发中断
	    }

	

	
	

	}



