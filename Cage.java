import java.io.Serializable; 
import java.util.Scanner;
public class Cage implements Serializable{
	private String name;//שם הכלוב
	private Property properties;//תנאי הכלוב
	private Node <Animal> Animals;//רשימה של חיות

	public Cage(String name)//פעולה בונה
	{
		this.name=name;
		this.properties= new Property();
		this.Animals=null;
	}
	public String getName()//פעולה שמחזירה את שם הכלוב
	{
		return this.name;
	}
	public Property getProperty()//פעולה שמחזירה עצם מטיפוס Property
	{
		return this.properties;
	}
	public Animal isTheAnimalExsist(String nameAnimal)//פעולה שמחזירה עצם מטיפוס Animal אם החיה קיימת וnull  אם לא .
	{
			Node<Animal> pos=this.Animals;
			while(pos!=null)
			{
				if(pos.getValue().getName().equals(nameAnimal))//כניסה לתנאי כאשר החיה קיימת
					return pos.getValue();
				pos=pos.getNext();
			}
		return null;
	}
	
	public void addNewAnimal(Animal newAnimal)//פעולה שמוסיפה בצורה ממוינת חיה לרשימת החיות בכלוב.
	{
		Node <Animal> temp = new Node<Animal> (newAnimal);
		if (this.Animals==null)
			this.Animals=temp; // הוספת חוליה ראשונה
		else if (this.Animals.getValue().getName().compareTo (newAnimal.getName())>0)
			// הוספה לפני חוליה ראשונה
		{
			temp.setNext(this.Animals);
			this.Animals=temp;
		}
		else
		{
			Node <Animal> pos=this.Animals;
			while (pos.getNext()!=null&& 
					pos.getNext().getValue().getName().compareTo (newAnimal.getName())<0)
				pos=pos.getNext();
			temp.setNext(pos.getNext());
			pos.setNext (temp);
		}
	}
	
	public void removeFromCage(String name) //פעולה שמסירה חיה מרשימת החיות בכלוב
	{
		Animal animal=isTheAnimalExsist(name);
		if(animal==null)
			System.out.println("sorry, we dont have animal named "+name);
		else//הורדת החיה מהרשימה
			removeAnimal(animal);//הורדה מהרשימה של החיות
	}
	
	private void removeAnimal(Animal animal)//המשך הפעולה שמסירה חיה מרשימת הכלוב
	{
		if (animal == this.Animals.getValue())// ביטול חוליה ראשונה
			this.Animals=this.Animals.getNext();
		else
		{
			Node<Animal> pos=this.Animals;
			while (pos.getNext().getValue()!= animal)
				pos=pos.getNext();
			pos.setNext(pos.getNext().getNext());
		}
	}
	
	public String toString()//פעולה המחזירה מחרוזת עם הפרטים על הכלוב
	{
		String animals="";
		Node <Animal> pos=this.Animals;
		while(pos!=null)
		{
			animals+=" "+pos.getValue().getName()+",";//יצירת מחרוזת המכילה את כל החיות בכלוב 
			pos=pos.getNext();
		}
		return "the animals in the "+this.name+" cage are:"+animals+" and his properties are:"+this.properties.toString();
	}
	
	public void printAnimalList() // פעולה המחזירה מחרוזת של כל החיות בכלוב
	{
		Node <Animal> pos=this.Animals;
		while(pos!=null)
		{
			System.out.println(pos.getValue().getName());//הדפסת שם החיה 
			pos=pos.getNext();
		}
	}

} 