package Parsing_Analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.omg.PortableInterceptor.SUCCESSFUL;

import Lexical_Analysis.LA_FA;

public class LL1 {

	String ch;//当前正判断的终结符
	String chType;//当前正判断的终结符类型
	int index = 0;
	boolean flag = false;//判断是否是完整表达式
	
	HashMap<String, String> LexTable = new HashMap<String, String>();
	ArrayList<String>LexList = new ArrayList<String>();
	
	//表达式的识别:
	//	Exp = ExpFlag Pro Exp1
	//	Exp1 = [+ | -] Pro Exp1	| 空
	//	ExpFlag = +|-|空
	
	void expFlag()
	{//表达式是否带符号
		if(ch.equals("+")||ch.equals("-"))
		{
			Advance();
			return;
		}
	}
	
	void expression()
	{
		expFlag();
		pro();
		expression1();
				
	}
	
	void expression1()
	{
		if(addingOperator()) {
			Advance();
			pro();
			expression1();
		}
		
	}
	
	//项的识别
	// Pro = Atom Pro1
	//Pro1 = [*|/] Atom Pro1	| 空
	
		void pro()
		{
			
			atom();
			pro1();
			
		}
		void pro1()
		{
			if(mutilingOperator())
			{
				Advance();
				atom();
				pro1();
			}
			
			
		}
	
	//因子的识别
	//Atom = ident | number 	| (Exp)
	void atom()
	{
		
		if(chType.equals("ident")) {
			 
			Advance();
			return;
		}
			
		else if(chType.equals("number")) {
			 
			Advance();
			return;
		}
			
		else if(chType.equals("Lparen"))
		{
			 
				Advance();
				
				
				expression();
				
				if(chType.equals("Rparen")) 
				{
					 
					Advance();
					return;
				}
				else {
					error("缺少右括号      ）");
				}
		}
		else {
			error(ch+"："+chType+"    无法识别");
		}
		
	}
	
	
	//加法运算符识别
	boolean addingOperator()
	{
		if(ch.equals("+")||ch.equals("-"))
		{
			return true;
		}
			
		else {
			return false;			
		}
	}

	//乘法运算符识别
	boolean mutilingOperator()
	{
		if(ch.equals("*")||ch.equals("/"))
		{
			return true;
		}
		else {
			return false;
		}
	}

	//关系运算符识别
	boolean relationOperator()
	{
		if(ch.equals("=")||ch.equals(":=")||ch.equals("<=")
				||ch.equals(">=")||ch.equals("<")||ch.equals(">")||ch.equals("<>"))
		{
			Advance();
			return true;
		}
		else {
			String mess = "关系运算符识别错误";
			error(mess);
			return false;
		}
	}
	
	//读取下一个终结符号
	void Advance()
	{
		if(index >= LexList.size())
		{
			ch = "输入串不符合规范";
			chType = "";
			return;
		}
			
	
		ch = LexList.get(index);
		chType = LexTable.get(ch);
		index++;
	}
	
	void error(String mess)
	{
		System.err.println("Error :  		--------语法错误--------");
		System.err.println("		"+mess);
		System.err.println();
		System.exit(0);
	}
	
	void success()
	{
		System.out.println("语法正确");
		System.exit(0);
	}
	
	void begin()
	{
		loadFile();
		Advance();
		expression();
		success();
		
	}
	
	
	//读入词法分析得到的的结果，将结果放入词汇表中
	void loadFile()
	{
		try {
			BufferedReader	bufferedReader = new BufferedReader(new FileReader("Lex.txt"));
		
		
		String string = null;
		while ((string = bufferedReader.readLine())!=null) {
			String type = string.split(",")[0];
			String value = string.split(",")[1];
			LexTable.put(value, type);
			LexList.add(value);
			
		}
		bufferedReader.close();
		} catch (Exception e) {
			System.err.println("Lex.txt Not Found");
			System.exit(0);
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new LA_FA().begin();
		new LL1().begin();
	}
		
}
