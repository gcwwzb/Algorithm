package PrasingWithLexical_Analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.AbstractDocument.BranchElement;

public class new_LA_FA {

	// 当前读入的字符
	char ch;
	//当前指针位置
	int index = 0;
	// 存放单词符号
	String strToken = "";

	//标识符表
	HashMap<Integer, String> identTable = new HashMap<Integer, String>();
	//常量表
	HashMap<Integer, String> numberTable = new HashMap<Integer, String>();
	
	// 保留字表
	HashMap<String, String> keyWords = new HashMap<String, String>();
	
	

	void createMap() {

		try {
			FileReader fileReader = new FileReader("keywords.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String flush = bufferedReader.readLine();
			String[] arrys = flush.split(",");
			for (int i = 0; i < arrys.length; i++)
				keyWords.put(arrys[i],arrys[i]+"sym");

		} catch (Exception e) {
			System.err.println("keywords.txt Not Found");
			e.printStackTrace();
		}
	}

	// 判断字符是否为数字
	boolean isDigit() {
		if (Character.isDigit(ch)) {
			// java内部方法，此处也可以利用编码判断
			return true;
		}

		return false;
	}

	// 判断字符是否为字母
	boolean isLetter() {
		// java内部方法，此处也可以利用编码判断
		if (Character.isLetter(ch))
			return true;

		return false;
	}

	// 从保留字表中查询缓冲区内的单词是否为保留字
	String Reserve() {
		String string = strToken;
		if (keyWords.containsKey(string))
			return keyWords.get(string);
		else
			return "notFound";
	}

	// 从输入串中获得字符
	void getChar(String str) {
				ch = str.charAt(index);
				index++;
	}
	//自动屏蔽空格
	// 从index开始，返回搜索的下一个index
	void getBC(String str) {
		for (int i = index; i <str.length(); i++) {
			if (str.charAt(i) != ' ') {
				ch = str.charAt(i);
				index = i+1;
				return;
			}
			
			
		}
		index = str.length();
	}

	int chargeIdent(String str) {
		PrintStream ps = null;
		try {
			 ps = new PrintStream(new FileOutputStream("Lex2.txt",false));
		} catch (FileNotFoundException e) {
			System.err.println("Lex.txt Not Found");
			e.printStackTrace();
		}
		while (isDigit() || isLetter()) {
			strToken+=ch;// 将当前字符放入缓冲区中
			 getChar(str);
		}
		index--;//指针向前一位（多读了一个）
		String result = Reserve();
		if(result.equals("notFound"))
		{
			int key = identTable.size();
			identTable.put(key+1, strToken);//在常数表中添加
			System.out.println("(  "+"ident"+" , "+strToken+"  )");
			ps.println("ident"+","+strToken);
		
		}else {
			System.out.println("(  "+result+" , "+strToken+"  )");
			ps.println(result+","+strToken);
		}
		
		strToken="";// 清空缓冲区
		ps.close();
		return index;
	
	}
	
	int chargeNumber(String str) {
		PrintStream ps = null;
		try {
			 ps = new PrintStream(new FileOutputStream("Lex2.txt",false));
		} catch (FileNotFoundException e) {
			System.err.println("Lex.txt Not Found");
			e.printStackTrace();
		}
		while (isDigit() ) {
			strToken+=ch;// 将当前字符放入缓冲区中
			getChar(str);
		}
		index--;
		int key = numberTable.size();
		numberTable.put(key+1, strToken);//在常数表中添加
		System.out.println("(  "+"number"+" , "+strToken+"  )");
		ps.println("number"+","+strToken);
		strToken="";// 清空缓冲区
		ps.close();
		return index;
		
	}

	public String begin() {
		createMap();
		String str = loadFile();	
			return str;
	}
	
	public int onFA(String str,int tag)
	{			
			index = tag;
			getBC(str);
			
			if(index>=str.length())
			{//避免读入的序列最后是空格而导致异常
				return -1;
			}
			
			PrintStream ps = null;
			try {
				 ps = new PrintStream(new FileOutputStream("Lex2.txt",false));
			} catch (FileNotFoundException e) {
				System.err.println("Lex2.txt Not Found");
				e.printStackTrace();
			}
			
			if(isLetter())
			{chargeIdent(str);
			}else if (isDigit()) {
				chargeNumber(str);
			}else if (ch=='+') {
				System.out.println("(  "+"plus"+"  ,  "+ch+"  )");
				ps.println("plus"+","+ch);
			}else if (ch=='-') {
				System.out.println("(  "+"minus"+"  ,  "+ch+"  )");
				ps.println("minus"+","+ch);
			}else if (ch=='*') {
				System.out.println("(  "+"times"+"  ,  "+ch+"  )");
				ps.println("times"+","+ch);
			}else if (ch=='/') {
				System.out.println("(  "+"slash"+"  ,  "+ch+"  )");
				ps.println("slash"+","+ch);
			}else if (ch=='=') {
				System.out.println("(  "+"eql"+"  ,  "+ch+"  )");
				ps.println("eql"+","+ch);
			}
			
			else if (ch=='<') {
				getChar(str);
				if(ch=='>') {
					System.out.println("(  "+"neq"+"  ,  "+"<>"+"  )");
				ps.println("neq"+","+"<>");
				}
				
				else if (ch=='=') {
				System.out.println("(  "+"leq"+"  ,  "+"<="+"  )");
				ps.println("leq"+","+"<=");
				}
				else {
					index--;
					System.out.println("(  "+"lss"+"  ,  "+"<"+"  )");
					ps.println("lss"+","+"<");
				}
			}
			else if (ch=='>') {
				getChar(str);			
				if (ch=='=') {
				System.out.println("(  "+"geq"+"  ,  "+">="+"  )");
				ps.println("geq"+","+">=");
				}
				else {
					index--;
					System.out.println("(  "+"gtr"+"  ,  "+">"+"  )");
					ps.println("gtr"+","+">");
				}
			}
			else if (ch==':') {
				getChar(str);			
				if (ch=='=') {
				System.out.println("(  "+"becomes"+"  ,  "+":="+"  )");
				ps.println("becomes"+","+":=");
				}
				else {
					error();
				}
			}
			else if (ch=='(') {
				System.out.println("(  "+"Lparen"+"  ,  "+ch+"  )");
				ps.println("Lparen"+","+ch);
			}
			else if (ch==')') {
				System.out.println("(  "+"Rparen"+"  ,  "+ch+"  )");
				ps.println("Rparen"+","+ch);
			}
			else if (ch==',') {
				System.out.println("(  "+"comma"+"  ,  "+ch+"  )");
				ps.println("comma"+","+ch);
			}
			else if (ch==';') {
				System.out.println("(  "+"semicolon"+"  ,  "+ch+"  )");
				ps.println("semicolon"+","+ch);
			}
			else if (ch=='.') {
				System.out.println("(  "+"period"+"  ,  "+ch+"  )");
				ps.println("period"+","+ch);
			}
			else {
				error();
			}
			
		return index;
	
	}
	
	String loadFile()
	{
		String stream = "";
		try {
			File file = new File("Lex2.txt");
			if(file.exists())
				file.delete();
			
			FileReader fileReader = new FileReader("input.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String str;
			
			while ((str = bufferedReader.readLine())!=null) {			
				stream+=str+" "; //加入空格防止不同行被连接在一起
			}

			bufferedReader.close();
		}catch (Exception e) {
			System.err.println("input.txt Not Found");
			e.printStackTrace();
		}
		
		
		return stream;
		
	}
	
	void error()
	{
		System.err.println();
		System.err.println("======================");
		System.err.println("  Error : 	词法分析出错");
		System.err.println();
		System.err.println("  	wrong char  "+ch);
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new_LA_FA fa = new new_LA_FA();
		fa.begin();
	}
}
