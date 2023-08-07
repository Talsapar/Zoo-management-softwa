import java.io.Serializable;
public class VisitorVIP extends Visitor implements Serializable{
	private VisitTab Tab;//כרטיסיית ביקורים
	private Animal favAnimal;//חיה אהובה
	
	public VisitorVIP(String name,double age,int ID,VisitTab Tab,Animal favAnimal)//פעולה בונה
	{
		super (name,age,ID,true);
		this.Tab=Tab;
		this.favAnimal=favAnimal;
	}
	
	public VisitTab getTab()//פעולה שמחזירה עצם מטיפוס VisitTab
	{
		return this.Tab;
	}
	
	public Animal getFavAnimal()//פעולה שמחזירה עצם מטיפוס Animal
	{
		return this.favAnimal;
	}
	
	public Animal replaceFavAnimal(Animal favAnimal)//פעולה שמשנה את החיה האהובה ומחזירה עצם מטיפוס Animal
	{
		return this.favAnimal=favAnimal;
	}
	
	public String toString()//פעולה שמחזירה מחרוזת עם הפרטים של VisitorVIP
	{
		return String.format("%-13s",this.name)
		+String.format("%-13s",this.age)
		+String.format("%-13s",this.ID)
		+String.format("%-13s",this.guide)
		+String.format("%-13s",this.favAnimal.getName());
	}

}