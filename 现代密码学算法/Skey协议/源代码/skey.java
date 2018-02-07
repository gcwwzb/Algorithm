import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.MalformedInputException;

import javax.swing.JOptionPane;

public class skey {

	String[] hashLink = new String[10];
	String[] keys = new String[9];
	String saved = "";
	int flag = 0;// 游标
	File file = new File("Key.txt");//哈希link保存

	public void Init() throws Exception{

		String init = JOptionPane.showInputDialog(" 初始化： ");

		String string = init;
		for (int i = 0; i < 10; i++) {

			String tem = MD5.myMD5(string);
			string = tem;
			hashLink[i] = string;
			System.out.println(string);
		}

		saved = string;// 保留最后一个

		PrintStream ps = new PrintStream(new FileOutputStream(file));

		for (int i = 0; i < 9; i++) {
			keys[i] = hashLink[8 - i];// 保管密钥，反向hash链
			ps.println(keys[i]);
		}

		ps.close();
	}

	public void Login() throws Exception{

		while (true) {
			String key = JOptionPane.showInputDialog(null, "确认登陆吗?");
			if (saved.equals(MD5.myMD5(key)))
				{flag++;
				JOptionPane.showMessageDialog(null, "第" + flag + "次登陆成功!");}
			else 				
				{
				JOptionPane.showMessageDialog(null,  "登陆失败!");
				continue;
				}

			if (flag == 9) {
				JOptionPane.showMessageDialog(null, "动态口令已使用完毕");
				break;
			}

			saved = key;//服务器更换口令
			
		}

	}

	public static void main(String[] args) throws Exception{

		skey skey = new skey();
		skey.Init();
		skey.Login();
		
		
	}

}
