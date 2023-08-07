import java.io.Serializable; 
import java.util.Scanner;
public class Property implements Serializable {
	static Scanner input= new Scanner(System.in);
	private int foodCapacity;//קיבולת האוכל
	private int waterCapacity;//קיבולת המים
	private int weightCapacity;//קיבולת המשקל
	private double currentFood;//כמות אוכל נוכחית
	private double currentWater;//כמות מים נוכחית
	private double currentWeight;//משקל נוכחי

	public Property()//פעולה בונה
	{
		System.out.println("enter food capacity in cage");
		this.foodCapacity=Validation.getPositiveNumber();
		System.out.println("enter water capacity in cage");
		this.waterCapacity=Validation.getPositiveNumber();
		System.out.println("enter weight capacity in cage");
		this.weightCapacity=Validation.getPositiveNumber();
		this.currentFood=0;
		this.currentWater=0;
		this.currentWeight=0;
	}
	
	public double getCurrentFood()//פעולה שמחזירה את כמות האוכל הנוכחית
	{
		return this.currentFood;
	}
	
	public double getCurrentWater()//פעולה שמחזירה את כמות המים הנוכחית
	{
		return this.currentWater;
	}
	
	public void updateWater(double liter)//פעולה שמעדכנת את כמות המים
	{
		 this.currentWater+=liter;
	}
	
	public void updateFood(double kg)//פעולה שמעדכנת את כמות האוכל
	{
		 this.currentFood+=kg;
	}
	
	public void updateWeight(double kg)//פעולה שמעדכנת את המשקל
	{
		 this.currentWeight+=kg;
	}
	
	public double howMuchWaterYouCanFeel()//פעולה המחזירה את כמות המים שניתן למלא 
	{
		 return this.waterCapacity-this.currentWater;
	}
	
	public double howMuchFoodYouCanFeel()//פעולה המחזירה את כמות האוכל שניתן למלא
	{
		 return this.foodCapacity-this.currentFood;
	}
	
	public boolean thereIsEnoughPlaceToAnimal(double weight)//פעולה שמחזירה true  אם יש מספיק מקום להכניס חיה וfalse אחרת.
	{
		 return ((this.weightCapacity-this.currentWeight)>=weight);
	}
	
	public void removeFood(double food)//פעולה שמורידה אוכל ומעדכנת את כמות האוכל הנוכחית
	{
		this.currentFood=this.currentFood-food;
	}
	
	public void removeWater(double water)//פעולה שמורידה מים ומעדכנת את כמות המים הנוכחית
	{
		this.currentWater=this.currentWater-water;
	}
	
	public void removeWeight(double weight)//פעולה שמורידה משקל ומעדכנת את המשקל הנוכחי
	{
		this.currentWeight=this.currentWeight-weight;
	}
	
	public String toString()//פעולה המחזירה מחרוזת של תנאי הכלוב
	{
		return "the water capacity is "+this.waterCapacity+" the current water now is "+this.currentWater+
		" the food capacity is "+this.foodCapacity+" the current food now is "+this.currentFood+
		" the weight capacity is "+this.weightCapacity+" the current weight now is "+this.currentWeight;			
	}

}