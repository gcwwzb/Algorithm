package Channel;

public class CPU {
    private Channel channelManager;

    public CPU() {
        this.channelManager = new Channel();

    }

    public void setChannel(Channel channelManager) {
        this.channelManager = channelManager;
    }

    public Channel getChannel() {
        return channelManager;
    }

    public void runCpu() {
        while (true) {
            if (channelManager.getInterrupt().equals("none")) {
                System.out.println("CPU忙...");
                try {
                	new Thread().sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
                
                channelManager.setInterrupt("noned");
            }
            if (channelManager.getInterrupt().equals("init")) {
                System.out.println("CPU中断，准备响应外部设备请求");
                System.out.println("收到I/O指令，通道初始化...");
                try {
                	new Thread().sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
                channelManager.setInterrupt("none");

                Thread t = new Thread(this.channelManager);
                t.start();
            }
            if (channelManager.getInterrupt().equals("finish")) {
                System.out.println("CPU中断");
                System.out.println("I/O指令执行完成");
                try {
                	new Thread().sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
                channelManager.setInterrupt("none");
            }
        }
    }
    
    public static void main(String[] args) {
        CPU cpu = new CPU();
        cpu.getChannel().setInterrupt("init");
        cpu.runCpu();
    }
    
}

