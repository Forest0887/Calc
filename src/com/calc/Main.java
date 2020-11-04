package com.calc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
    	while (true){
			Scanner in = new Scanner(System.in);
			String val = in.nextLine();
			if (val.equals("exit")){
				break;
			}
			new Calc(val);
			// на выходе калькулятор дает только целую часть чисел (по условию)
		}
    }
}