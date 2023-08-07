import java.io.*; 
import java.util.Scanner;
public class IOtest
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner (System.in);
		String st;
		try   
		{
			PrintStream wf = new PrintStream(new File("C:\\test.txt"));
			for (int i=0;i<4;i++)
			{
				System.out.println ("enter String");
				st=input.next();
				wf.println(st);
			}
			wf.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			Scanner rf = new Scanner(new File("C:\\test.txt"));
			String s;
			while (rf.hasNext()) 
			{
				s=rf.next();
				System.out.println(s);			
			}
			rf.close();
		}
		catch (Exception e)
	    {
			e.printStackTrace();
			
		}
	
		
		int num;
		try   
		{
			PrintStream wf = new PrintStream(new File("C:\\test.txt"));
			wf.println (true);  
			for (int i=0;i<3;i++)
			{
				System.out.println ("enter String");
				st=input.next();
				System.out.println("enter int");
				num=input.nextInt();
				wf.println(st);
				wf.println (num);
			}
			wf.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		String s; int n, sum=0;
		try  
		{
			Scanner rf = new Scanner(new File("C:\\test.txt"));
			boolean b= rf.nextBoolean();  
			System.out.println("boolean="+b); 
			while (rf.hasNext()) 
			{
				s=rf.next();
				n=rf.nextInt(); 
				sum=sum+n; 
				System.out.println(s+"  "+n);
			}
			rf.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("sum= "+sum);
	}
}