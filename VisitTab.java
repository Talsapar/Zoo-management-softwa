import java.io.Serializable;
import java.util.Scanner;
public class VisitTab implements Serializable {
	static Scanner input= new Scanner(System.in);
	private int totalNumVisit;//���� ������� ����(5/10)
	private int currentNumVisit;//���� ������� �����

	public VisitTab(int totalNumVisit)//����� ����
	{
		this.totalNumVisit=totalNumVisit;
		this.currentNumVisit=1;
	}
	
	public boolean haveEnoughVisits()//����� ������� true �� �� ����� ������� �������� �false ����.
	{
		return (this.currentNumVisit<this.totalNumVisit);
	}
	
	public void updateNumVisit()//����� ������� �� ���� �������� �1
	{
		 this.currentNumVisit++;
	}
	
	public void leftVisitsInVisiTAB()//����� ������� ��� ������� ����� �����
	{
		System.out.println("you have "+(this.totalNumVisit-this.currentNumVisit)+" visits left in visitTab");
	}
	
}