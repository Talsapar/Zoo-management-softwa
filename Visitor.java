import java.io.Serializable; 
import java.util.Scanner;
public class Visitor implements Serializable  {
	static Scanner input= new Scanner(System.in);
	protected String name;//שם המבקר
	protected double age;//גיל המבקר
	protected int ID;//ת"ז המבקר
	protected boolean guide;//מדריך למבקר

	public Visitor(String name,double age,int ID,boolean guide)//פעולה בונה
	{
		this.name=name;
		this.age=age;
		this.ID=ID;
		this.guide=guide;
	}
	
	public String getName()//פעולה שמחזירה את שם המבקר
	{
		return this.name;
	}
	
	public double getAge()//פעולה שמחזירה את גיל המבקר
	{
		return this.age;
	}
	
	public int getID()//פעולה שמחזירה את ת"ז המבקר
	{
		return this.ID;
	}
	
	public boolean getGuide()//פעולה שמחזירה את ערך התכונה המדריך למבקר
	{
		return this.guide;
	}
	
	public void changeGuideToTrue()//פעולה שמשנה את ערך התכונה המדריך למבקר false  ל true  אם הערך הוא true  מעדכנת בהדפסה מתאימה
	{
		if(this.guide)//כניסה לתנאי כאשר התכונה של המבקר כבר על TRUE
			System.out.println("this visitor is already has tour with guide");
		else
		{
			this.guide=true;//שינוי התכונה
			System.out.println("we added you to tour with guide");
		}
	}
	
	public void printNameAndID()//פעולה שמדפיסה את שם המבקר והת"ז שלו
	{
		System.out.println("name:"+this.name+", ID:"+this.ID);
	}
	
	public String toString()//פעולה שמחזירה מחרוזת עם פרטי המבקר
	{
		return String.format("%-13s",this.name)
		+String.format("%-13s",this.age)
		+String.format("%-13s",this.ID)
		+String.format("%-13s",this.guide);
	}

}