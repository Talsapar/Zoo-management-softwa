import java.io.Serializable; 
import java.util.Scanner;
public class Cage implements Serializable{
	private String name;//�� �����
	private Property properties;//���� �����
	private Node <Animal> Animals;//����� �� ����

	public Cage(String name)//����� ����
	{
		this.name=name;
		this.properties= new Property();
		this.Animals=null;
	}
	public String getName()//����� ������� �� �� �����
	{
		return this.name;
	}
	public Property getProperty()//����� ������� ��� ������ Property
	{
		return this.properties;
	}
	public Animal isTheAnimalExsist(String nameAnimal)//����� ������� ��� ������ Animal �� ���� ����� �null  �� �� .
	{
			Node<Animal> pos=this.Animals;
			while(pos!=null)
			{
				if(pos.getValue().getName().equals(nameAnimal))//����� ����� ���� ���� �����
					return pos.getValue();
				pos=pos.getNext();
			}
		return null;
	}
	
	public void addNewAnimal(Animal newAnimal)//����� ������� ����� ������ ��� ������ ����� �����.
	{
		Node <Animal> temp = new Node<Animal> (newAnimal);
		if (this.Animals==null)
			this.Animals=temp; // ����� ����� ������
		else if (this.Animals.getValue().getName().compareTo (newAnimal.getName())>0)
			// ����� ���� ����� ������
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
	
	public void removeFromCage(String name) //����� ������ ��� ������ ����� �����
	{
		Animal animal=isTheAnimalExsist(name);
		if(animal==null)
			System.out.println("sorry, we dont have animal named "+name);
		else//����� ���� �������
			removeAnimal(animal);//����� ������� �� �����
	}
	
	private void removeAnimal(Animal animal)//���� ������ ������ ��� ������ �����
	{
		if (animal == this.Animals.getValue())// ����� ����� ������
			this.Animals=this.Animals.getNext();
		else
		{
			Node<Animal> pos=this.Animals;
			while (pos.getNext().getValue()!= animal)
				pos=pos.getNext();
			pos.setNext(pos.getNext().getNext());
		}
	}
	
	public String toString()//����� ������� ������ �� ������ �� �����
	{
		String animals="";
		Node <Animal> pos=this.Animals;
		while(pos!=null)
		{
			animals+=" "+pos.getValue().getName()+",";//����� ������ ������ �� �� ����� ����� 
			pos=pos.getNext();
		}
		return "the animals in the "+this.name+" cage are:"+animals+" and his properties are:"+this.properties.toString();
	}
	
	public void printAnimalList() // ����� ������� ������ �� �� ����� �����
	{
		Node <Animal> pos=this.Animals;
		while(pos!=null)
		{
			System.out.println(pos.getValue().getName());//����� �� ���� 
			pos=pos.getNext();
		}
	}

} 