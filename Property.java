import java.io.Serializable; 
import java.util.Scanner;
public class Property implements Serializable {
	static Scanner input= new Scanner(System.in);
	private int foodCapacity;//������ �����
	private int waterCapacity;//������ ����
	private int weightCapacity;//������ �����
	private double currentFood;//���� ���� ������
	private double currentWater;//���� ��� ������
	private double currentWeight;//���� �����

	public Property()//����� ����
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
	
	public double getCurrentFood()//����� ������� �� ���� ����� �������
	{
		return this.currentFood;
	}
	
	public double getCurrentWater()//����� ������� �� ���� ���� �������
	{
		return this.currentWater;
	}
	
	public void updateWater(double liter)//����� ������� �� ���� ����
	{
		 this.currentWater+=liter;
	}
	
	public void updateFood(double kg)//����� ������� �� ���� �����
	{
		 this.currentFood+=kg;
	}
	
	public void updateWeight(double kg)//����� ������� �� �����
	{
		 this.currentWeight+=kg;
	}
	
	public double howMuchWaterYouCanFeel()//����� ������� �� ���� ���� ����� ���� 
	{
		 return this.waterCapacity-this.currentWater;
	}
	
	public double howMuchFoodYouCanFeel()//����� ������� �� ���� ����� ����� ����
	{
		 return this.foodCapacity-this.currentFood;
	}
	
	public boolean thereIsEnoughPlaceToAnimal(double weight)//����� ������� true  �� �� ����� ���� ������ ��� �false ����.
	{
		 return ((this.weightCapacity-this.currentWeight)>=weight);
	}
	
	public void removeFood(double food)//����� ������� ���� ������� �� ���� ����� �������
	{
		this.currentFood=this.currentFood-food;
	}
	
	public void removeWater(double water)//����� ������� ��� ������� �� ���� ���� �������
	{
		this.currentWater=this.currentWater-water;
	}
	
	public void removeWeight(double weight)//����� ������� ���� ������� �� ����� ������
	{
		this.currentWeight=this.currentWeight-weight;
	}
	
	public String toString()//����� ������� ������ �� ���� �����
	{
		return "the water capacity is "+this.waterCapacity+" the current water now is "+this.currentWater+
		" the food capacity is "+this.foodCapacity+" the current food now is "+this.currentFood+
		" the weight capacity is "+this.weightCapacity+" the current weight now is "+this.currentWeight;			
	}

}