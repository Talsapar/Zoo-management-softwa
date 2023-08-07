import java.util.Scanner;
/* The class was written by Yuri prachichki */
public class Validation
{
	public static int getValidNum (int num)
	{
		Scanner input=new Scanner(System.in);
		String tmp;
		int answer=-1;
		do
		{         
			System.out.println ("enter number between 1 to "+ num);
			tmp = input.next();
			if (isNumeric(tmp))
				answer= Integer.parseInt(tmp);
		}  while (answer <1 || answer >num);
		return answer;
	}


	public static int getPositiveNumber()
	{
		Scanner input=new Scanner(System.in);
		String tmp;
		int answer=-1;
		do
		{         
			System.out.println ("enter positive number ");
			tmp = input.next();
			if(isNumeric(tmp))
				answer= Integer.parseInt(tmp);
		} while (answer <0);
		return answer;
	}

	private static boolean isNumeric(String num)
	{
		for(int i=0;i<num.length();i++)
			if (!Character.isDigit(num.charAt(i)))
				return false;
		return true;
	}

	public static double getPositiveDoubleNumber()
	{
		Scanner input=new Scanner(System.in);
		String tmp;
		double answer=-1;
		do
		{         
			System.out.println ("enter positive and double number ");
			tmp = input.next();
			if(isNumericDouble(tmp))
				answer= Integer.parseInt(tmp);
		} while (answer <0);
		return answer;
	}
	private static boolean isNumericDouble(String num)
	{
		int count=0;
		if(num.charAt(0)=='.'||num.charAt(num.length()-1)=='.')
			return false;
		for(int i=0;i<num.length();i++)
		{
			if(num.charAt(i)=='.')
				count++;
			if (count>1)
				return false;
			if(num.charAt(i)=='.'||!Character.isDigit(num.charAt(i)))
				return false;			
		}
		return true;
	}

	public static boolean getBoolean()
	{
		Scanner input=new Scanner(System.in);
		String tmp;
		do
		{     	
			System.out.println("enter true or false ");
			tmp = input.next();
			if (tmp.equals ("true") )
				return true;
			if (tmp.equals ("false") )
				return false;
		} while (true);
	}
}