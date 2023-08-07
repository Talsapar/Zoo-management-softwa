import java.io.Serializable; 
import java.util.Scanner;
public class Visitor implements Serializable  {
	static Scanner input= new Scanner(System.in);
	protected String name;//�� �����
	protected double age;//��� �����
	protected int ID;//�"� �����
	protected boolean guide;//����� �����

	public Visitor(String name,double age,int ID,boolean guide)//����� ����
	{
		this.name=name;
		this.age=age;
		this.ID=ID;
		this.guide=guide;
	}
	
	public String getName()//����� ������� �� �� �����
	{
		return this.name;
	}
	
	public double getAge()//����� ������� �� ��� �����
	{
		return this.age;
	}
	
	public int getID()//����� ������� �� �"� �����
	{
		return this.ID;
	}
	
	public boolean getGuide()//����� ������� �� ��� ������ ������ �����
	{
		return this.guide;
	}
	
	public void changeGuideToTrue()//����� ����� �� ��� ������ ������ ����� false  � true  �� ���� ��� true  ������ ������ ������
	{
		if(this.guide)//����� ����� ���� ������ �� ����� ��� �� TRUE
			System.out.println("this visitor is already has tour with guide");
		else
		{
			this.guide=true;//����� ������
			System.out.println("we added you to tour with guide");
		}
	}
	
	public void printNameAndID()//����� ������� �� �� ����� ���"� ���
	{
		System.out.println("name:"+this.name+", ID:"+this.ID);
	}
	
	public String toString()//����� ������� ������ �� ���� �����
	{
		return String.format("%-13s",this.name)
		+String.format("%-13s",this.age)
		+String.format("%-13s",this.ID)
		+String.format("%-13s",this.guide);
	}

}