import java.io.Serializable;
public class VisitorVIP extends Visitor implements Serializable{
	private VisitTab Tab;//�������� �������
	private Animal favAnimal;//��� �����
	
	public VisitorVIP(String name,double age,int ID,VisitTab Tab,Animal favAnimal)//����� ����
	{
		super (name,age,ID,true);
		this.Tab=Tab;
		this.favAnimal=favAnimal;
	}
	
	public VisitTab getTab()//����� ������� ��� ������ VisitTab
	{
		return this.Tab;
	}
	
	public Animal getFavAnimal()//����� ������� ��� ������ Animal
	{
		return this.favAnimal;
	}
	
	public Animal replaceFavAnimal(Animal favAnimal)//����� ����� �� ���� ������ ������� ��� ������ Animal
	{
		return this.favAnimal=favAnimal;
	}
	
	public String toString()//����� ������� ������ �� ������ �� VisitorVIP
	{
		return String.format("%-13s",this.name)
		+String.format("%-13s",this.age)
		+String.format("%-13s",this.ID)
		+String.format("%-13s",this.guide)
		+String.format("%-13s",this.favAnimal.getName());
	}

}