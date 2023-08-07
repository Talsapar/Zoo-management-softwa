import java.io.Serializable; 
import java.util.Scanner;
public class Animal implements Serializable {
	static Scanner input= new Scanner(System.in);
	private String name;//�� ����
	private int dailyFood;//���� ���� �����
	private int dailyWater;//���� ��� �����
	double weight;//���� ����

	public Animal(String name,double weight)//����� ����
	{
		System.out.println("enter his daily food");
		this.dailyFood=Validation.getPositiveNumber();
		System.out.println("enter his daily water");
		this.dailyWater=Validation.getPositiveNumber();
		this.name=name;
		this.weight=weight;
	}
	public String getName()//����� ������� �� �� ����
	{
		return this.name;
	}
	public double getWeight()//����� ������� �� ����� �� ����
	{
		return this.weight;
	}
	public String toString()//����� ������� ������ �� ���� ����
	{
		return "name:"+this.name+", daily food:"+this.dailyFood+", daily water:"+this.dailyWater+", weight:"+this.weight;
	}

}