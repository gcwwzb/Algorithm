package PrasingWithLexical_Analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.omg.PortableInterceptor.SUCCESSFUL;

import Lexical_Analysis.LA_FA;

public class new_LL1 {

	String lexString;
	int index = 0;
	new_LA_FA FA;
	String ch;//当前正判断的终结符
	String chType;//当前正判断的终结符类型

	

	
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
		if(index >= lexString.length())
		{
			ch = "输入串不符合规范";
			chType = "";
			return;
		}
			
		index = FA.onFA(lexString, index);
		
		try {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("Lex2.txt"));
		String result = bufferedReader.readLine();		
		ch = result.split(",")[1];
		chType = result.split(",")[0];
		}catch (Exception e) {
			System.err.println("Lex2.txt Not Found");
			System.exit(0);
		}
	}
	
	void error(String mess)
	{
		System.err.println();
		System.err.println("====================================");
		System.err.println();
		System.err.println("  Error :  		--------语法错误--------");
		System.err.println();
		System.err.println("  	"+mess);
	
		System.exit(0);
	}
	
	void success()
	{
		System.out.println();
		System.out.println("====================");
		System.out.println("		语法正确");
		System.exit(0);
	}
	
	void begin()
	{
		 FA = new new_LA_FA();
		 lexString= FA.begin();		
		Advance();
		expression();
		success();
		
	}
	
	

	
	public static void main(String[] args) {
		new new_LL1().begin();
	}
		
}
