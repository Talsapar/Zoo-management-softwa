import java.io.Serializable; 
import java.util.Scanner;
public class Animal implements Serializable {
	static Scanner input= new Scanner(System.in);
	private String name;//שם החיה
	private int dailyFood;//כמות אוכל יומית
	private int dailyWater;//כמות מים יומית
	double weight;//משקל החיה

	public Animal(String name,double weight)//פעולה בונה
	{
		System.out.println("enter his daily food");
		this.dailyFood=Validation.getPositiveNumber();
		System.out.println("enter his daily water");
		this.dailyWater=Validation.getPositiveNumber();
		this.name=name;
		this.weight=weight;
	}
	public String getName()//פעולה שמחזירה את שם החיה
	{
		return this.name;
	}
	public double getWeight()//פעולה שמחזירה את המשקל של החיה
	{
		return this.weight;
	}
	public String toString()//פעולה שמחזירה מחרוזת עם פרטי החיה
	{
		return "name:"+this.name+", daily food:"+this.dailyFood+", daily water:"+this.dailyWater+", weight:"+this.weight;
	}

}