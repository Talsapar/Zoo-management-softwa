import java.io.*; 
import java.util.Scanner;
public class ZOO implements Serializable
{
	private Node<Visitor> Visitors =null;//����� �� ������ ������� ��� ��� �����
	private Node<VisitorVIP> VisitorsVIP=null ;//����� �� �� ����� VIP
	private Cage[]cages=new Cage[5];//���� �� ������ ����� 5
	static Scanner input = new Scanner (System.in);

	public ZOO()//����� ����
	{
		boolean exist=readZOOFromFile();
		if (!exist)
		{
			this.Visitors = null;
			this.VisitorsVIP = null;
		}
	}

	public void addNewVisitor()// ��� 1 -����� ������� ���� ��� 
	{
		Visitor newVisitor;
		System.out.println("Enter Id");
		int ID= Validation.getPositiveNumber();
		if(isVisitorExsist(ID)!=null)//�� ����� ���� ��� �����
			System.out.println("the visitor is already in the zoo");
		else
		{
			VisitorVIP VisitorVIP=isVisitorVIPExsist(ID);
			if(VisitorVIP!=null)//���� ����� ����� ������ ��� VIP
				newVisitor=enterVisitorVIP(VisitorVIP);//����� ������ ��� ������ ����� ������� ������ ��� ����
			else
			{
				System.out.println("Enter visitor name");
				String name = input.next();
				System.out.println("Enter visitor age");
				double age = Validation.getPositiveDoubleNumber();
				newVisitor=enterVisitor( name, ID, age);
			}
			addVisitorToList(newVisitor);//����� ����� ������� ������ �������
			System.out.println("enjoy youre Visit");
		}
	}

	private Visitor enterVisitorVIP(VisitorVIP visitorVIP)//���� ������ ������� ����- ����� ������ ��� VIP 
	{//VIP ����� ��� �� �� ����� ������� �������� ������� ��� ������ Visitor .
		Visitor newVisitor;
		if(visitorVIP.getTab().haveEnoughVisits())// ����� ����� �� ������ ����� ������� ���������
		{
			System.out.println("you are VIP and you have enough visits in visitTAB we update it");//�����
			visitorVIP.getTab().updateNumVisit();//����� ���� �������� ������� 
			newVisitor = new Visitor (visitorVIP.getName(),visitorVIP.getAge(),visitorVIP.getID(),true);
		}
		else
		{
			newVisitor=enterVisitor(visitorVIP.getName(),visitorVIP.getID(),visitorVIP.getAge());
			removeVisitorVIPFromZoo(visitorVIP);//����� �� ���� ������ � VIP ���� ��������� ��� ������
		}
		return newVisitor;

	}

	private Visitor enterVisitor(String name,int ID,double age)//���� ������ ������� ����- ����� ������ ��� VIP 
	{//��� ����� �� ����� ������� �������� ������� ��� ������ Visitor .
		Visitor newVisitor;
		boolean answer1,answer;
		Animal favAnimal;
		System.out.println("regelar tichet cost 25 shekel if you want buy visitTAB press true else press false");
		answer1 = Validation.getBoolean();
		if(answer1)//�� ����� ���� ����� ��������
		{
			VisitTab Tab=createNewVisitTAB();//����� ������� ���� 
			System.out.println("youre buing has done succesfully");
			newVisitor = new Visitor (name,age,ID,true);
			System.out.println("youre VIP now");
			favAnimal=addFavAnimal();//����� ��� ������ ����� ����� VIP 
			VisitorVIP newVisitorVIP = new VisitorVIP (name,age,ID,Tab,favAnimal);
			addVisitorVIPToList(newVisitorVIP);//����� ���� ������� ������ ����� �������
		}
		else
		{
			answer=wantGuide();//����� ��� ���� ������� ������ �� ��
			newVisitor= new Visitor(name,age,ID,answer);
		}
		return newVisitor;
	}

	private VisitTab createNewVisitTAB()//���� ������ ������� ����- ����� ������ ���� ����� ������� ���� �� ��� ������ VisitTab ������ ����
	{
		int numVisits;
		System.out.println("you have to optiond : 5 vitist in 100 shekel, or 10 visits 180 shekel");
		do
		{         
			System.out.println("how much you want? press 5 or 10");
			numVisits = input.nextInt();
		}  while (numVisits!=5 && numVisits!=10);
		VisitTab Tab =new VisitTab(numVisits);
		return Tab;
	}

	private Animal addFavAnimal()//���� ������ ������� ����- ����� ������� �� ����� ��� ����� 
	{//������ ����� VIP ����� ��� ����� ����� ������� ��� ������ Animal
		Animal favAnimal;
		do{
			printAnimals();//������ �� ���� �� ����� ��� �����
			System.out.println("enter youre choice");
			String newFavAnimal=input.next();
			favAnimal=isAnimalExsist(newFavAnimal);
		}while(favAnimal==null);
		return favAnimal;
	}

	private boolean wantGuide()//���� ������ ������� ����- ����� ������ �� ����� 
	{//�� ��� ������� ������ ������ true  �� �� � false ����. 
		boolean answer;
		System.out.println("regelar tichet cost 25 shekel");
		System.out.println("Enter true if the visitor want guide or false if not");
		answer=Validation.getBoolean();
		return answer;
	}

	private VisitorVIP isVisitorVIPExsist(int ID)//����� ������� ��� ������ VisitorVIP �� ���� ���� ������ null
	{
		Node<VisitorVIP> pos=this.VisitorsVIP;
		while(pos!=null)
		{
			if(pos.getValue().getID()==ID)//����� ����� �� ���� ����� ����
				return pos.getValue();//����� ���� �������� ������ ���� �������
			pos=pos.getNext();
		}
		return null;
	}

	private Visitor isVisitorExsist(int ID)//����� ������� ��� ������ Visitor �� ���� ���� ������ null
	{
		Node<Visitor> pos=this.Visitors;
		while(pos!=null)
		{
			if(pos.getValue().getID()==ID)//����� ����� �� ���� ����� ����
				return pos.getValue();//����� ���� �������� ������ ����
			pos=pos.getNext();
		}
		return null;
	}

	private void addVisitorToList (Visitor newVisitor)//  ����� ������� ���� ������ ������� ����� ������  
	{
		Node <Visitor> temp = new Node<Visitor> (newVisitor);
		if (this.Visitors==null)
			this.Visitors=temp; // ����� ����� ������
		else if (this.Visitors.getValue().getName().compareTo (newVisitor.getName())>0)
			// ����� ���� ����� ������
		{
			temp.setNext(this.Visitors);
			this.Visitors=temp;
		}
		else
		{
			Node <Visitor> pos=this.Visitors;
			while (pos.getNext()!=null&& 
					pos.getNext().getValue().getName().compareTo (newVisitor.getName())<0)
				pos=pos.getNext();
			temp.setNext(pos.getNext());
			pos.setNext (temp);
		}
	}


	private void addVisitorVIPToList (VisitorVIP newVisitorVIP)//  ����� ������� ���� VIP ������ ����� � VIP ����� ������
	{
		Node <VisitorVIP> temp = new Node<VisitorVIP> (newVisitorVIP);
		if (this.VisitorsVIP==null)
			this.VisitorsVIP=temp; // ����� ����� ������
		else if (this.VisitorsVIP.getValue().getName().compareTo (newVisitorVIP.getName())>0)
			// ����� ���� ����� ������
		{
			temp.setNext(this.VisitorsVIP);
			this.VisitorsVIP=temp;
		}
		else
		{
			Node <VisitorVIP> pos=this.VisitorsVIP;
			while (pos.getNext()!=null&& 
					pos.getNext().getValue().getName().compareTo (newVisitorVIP.getName())<0)
				pos=pos.getNext();
			temp.setNext(pos.getNext());
			pos.setNext (temp);
		}
	}

	public void addNewAnimal() // ��� 2- ����� ������� ��� ���� ��� �����
	{
		System.out.println("choose the type of the animal,else write new one");
		printCages();//����� ���� ������� ��� �����
		String nameCage=input.next();
		Cage cage=cageByName(nameCage);//����� ������ ���� ������ ���� ��������
		if(cage==null&&this.cages[4]!=null)//����� ����� ���� ���� ������� ���
			System.out.println("im sorry we dont have enoght place to youre animal");
		else{
			if(cage==null)//����� ����� ���� �� ���� ���� �� ��� ������
				cage=addNewCageToZoo();//����� ���� ���
			System.out.println("enter the animal name");
			String nameAnimal=input.next();
			if(isAnimalExsist(nameAnimal)!=null)//����� ����� ���� �� ��� ����� ��� �� �� ���
				System.out.println("sorry,i have in the zoo this animal we cant add it");
			else
				addNewAnimalToCage(nameAnimal,cage);//����� ���� ������ ����� ����� ��������
		}
	}

	private Cage addNewCageToZoo()//����� ������� ���� ��� ����� ������� ���� �� ���� ���� ��� �� 
	{
		String name;
		System.out.println("we dont have this type of cage, plese give us the next detailes");
		System.out.println("enter the name cage that represent the animal type");
		name=input.next();
		Cage cage=new Cage(name);
		this.cages[indexOfNewCage()]=cage;
		System.out.println("the cage had created seccusfully");
		return cage;
	}

	private void addNewAnimalToCage(String nameAnimal,Cage cage)//����� ������� ��� ���� ����� ���� ���� ��� ���� ���� ��� �����
	{
		Animal animal;
		System.out.println("enter the animal weight");
		double weight= Validation.getPositiveDoubleNumber();
		if(!cage.getProperty().thereIsEnoughPlaceToAnimal(weight))//����� ����� ���� ��� ����� ���� ������ ���� ���� 
			System.out.println("sorry, we dont have enough place in the cage");
		else
		{
			animal=new Animal(nameAnimal,weight);
			cage.addNewAnimal(animal);//����� ���� �����
			cage.getProperty().updateWeight(weight);//����� ����� ������ �����
			System.out.println("the animal has added to the cage ");
		}
	}

	private Animal isAnimalExsist(String nameAnimal)//����� ������� ��� ������ Animal �� ���� ���� ������ null
	{
		Animal animal;
		int count=0;
		while(count<5&&this.cages[count]!=null)//���� �� ���� �������
		{
			animal= this.cages[count].isTheAnimalExsist(nameAnimal);
			if(animal!=null)
				return animal;
			count++;
		}
		return null;
	}

	private int indexOfNewCage()//����� ������� ������ �� ���� ��� �-1- ���� �� ������� ������
	{
		for(int i=0;i<5;i++)
		{
			if(this.cages[i]==null)
				return i;
		}
		return -1;
	}

	public void removeAnimalFromCage() //��� 3- ���� ��� ��� �����
	{
		printAnimals();
		String name=input.next();
		Animal removeAnimal=isAnimalExsist(name);
		boolean flag=removeFromListAndUpdateWeight(name);
		if(flag)//������ ����� ���� ���� ����� ������ �����
		{
			System.out.println("the animal removed");
			replaceToVIPVisitor(removeAnimal);//����� ���� ������ �� ���� ������� ����� ����� ������ ���� ������
		}
		else
			System.out.println("we dont have this animal in the zoo");
	}

	private boolean removeFromListAndUpdateWeight(String name) //����� ���� ����� ���-������ true  �� ���� ����� ������ �- False  ����
	{
		boolean flag=false;
		for(int i=0;i<5;i++)
			if(this.cages[i]!=null&&this.cages[i].isTheAnimalExsist(name)!=null)//����� ����� ���� ���� �����
			{
				this.cages[i].getProperty().removeWeight(this.cages[i].isTheAnimalExsist(name).getWeight());//���� ����� �� ���� ������ ������ �� �����
				this.cages[i].removeFromCage(name);//���� ������ �����
				flag=true;
			}
		return flag;
	}

	public void removeVisitorFromZoo() //��� 4- ���� ���� ��� �����
	{
		System.out.println("Enter visitor ID");
		int ID = Validation.getPositiveNumber();
		Visitor visitor=isVisitorExsist(ID);
		if(visitor==null)//��� ���� ��� ��� ������ �������
			System.out.println("sorry, we dont have visitor with ID "+ID);
		else//����� ����� ��������
		{
			removeVisitors(visitor);//����� ������� �� ������
			System.out.println("we hope you enjoy our zoo, see you next time :)");
		}	
	}

	private void removeVisitors(Visitor visitor) //����� ������� ���� ������ �������
	{
		if (visitor == this.Visitors.getValue())// ����� ����� ������
			this.Visitors=this.Visitors.getNext();
		else
		{
			Node<Visitor> pos=this.Visitors;
			while (pos.getNext().getValue()!= visitor)
				pos=pos.getNext();
			pos.setNext(pos.getNext().getNext());
		}
	}

	private void replaceToVIPVisitor(Animal removeAnimal) //����� ������ �� ���� � VIP ��� ������ �� �� ���� ������ 
	{
		Node<VisitorVIP> pos=this.VisitorsVIP;
		while (pos!= null)
		{
			if(pos.getValue().getFavAnimal().getName().equals(removeAnimal.getName()))//����� ����� ���� ����� ���� ������� ����� ������ ���� �����
				replaceFavAnimal(pos);//����� ���� ������� ���� ����
			pos=pos.getNext();
		}
	}

	private void replaceFavAnimal(Node<VisitorVIP> pos) //���� ������� �� ���� ������ �� ���� � VIP
	{
		Animal newAnimal;
		System.out.println("the fav animal of visitor with ID : "+ pos.getValue().getID()+" removed");
		do{
			printAnimals();
			String newFavAnimal=input.next();
			newAnimal=isAnimalExsist(newFavAnimal);
		}while(newAnimal==null);
		pos.getValue().replaceFavAnimal(newAnimal);//���� ������� ��� ����� �����
		System.out.println("your fav animal has changed sucssefully");
	}

	private void removeVisitorVIPFromZoo(VisitorVIP visitorVIP) //����� ������� ���� VIP ������ ����� � VIP
	{
		if (visitorVIP == this.VisitorsVIP.getValue())// ����� ����� ������
			this.VisitorsVIP=this.VisitorsVIP.getNext();
		else//����� ���� �������
		{
			Node<VisitorVIP> pos=this.VisitorsVIP;
			while (pos.getNext().getValue()!= visitorVIP)
				pos=pos.getNext();
			pos.setNext(pos.getNext().getNext());
		}
	}

	public void printNameCagesAndAnimal () // ��� 5- ����� ������� �� ���� ������� ������ ���.
	{
		int count=0;
		while(count<5&&this.cages[count]!=null)//���� �� ���� �������
		{
			System.out.println(this.cages[count].getName()+":");//����� �� �����
			this.cages[count].printAnimalList();//����� ����� �����
			count++;
		}
	}

	public void printAllAnimalInCage () // ��� 6- ����� ������� ����� �� ���� �� ���� ������ 
	{
		String choice;
		printCages();//����� �� �������
		do{
			System.out.println("enter youre chioe");
			choice=input.next();	
		}while(indexOfCage(choice)==-1);
		this.cages[indexOfCage(choice)].printAnimalList();//����� ����� ����� �� ����� ������
	}

	private int indexOfCage(String cageName)//����� ������ �� �� ���� ������� �� ������ ��� �����
	{
		int count=0;
		while(count<5&&this.cages[count]!=null)
		{
			if(this.cages[count].getName().equals(cageName))//����� ����� ���� ����� �� ����� ������ ������ �� ������
				return count;
			count++;
		}
		return -1;
	}

	private Cage cageByName(String cageName)//����� ������ �� �� ���� ������� ����� ���� ������ Cage  ��������
	{
		for(int i=0;i<5;i++)
			if(this.cages[i]!=null&&this.cages[i].getName().equals(cageName))//����� ����� ���� ����� �� ����� ������ �����
				return this.cages[i];
		return null;
	}

	public void printAllVisitorsDetails() // ��� 7- ����� �� ���� ������� �����
	{
		String name= String.format("%-13s","name");
		String age= String.format("%-13s","age");
		String ID= String.format("%-13s","ID");
		String guide= String.format("%-13s","guide");
		System.out.println(name+age+ID+guide);
		Node<Visitor> pos=this.Visitors;
		while(pos!=null)
		{
			System.out.println(pos.toString());
			pos=pos.getNext();
		}
	}

	public void printAllVIPVisitorsDetails() // ��� 8- ����� �� ���� ����� � VIP �����
	{
		String name= String.format("%-13s","name");
		String age= String.format("%-13s","age");
		String ID= String.format("%-13s","ID");
		String guide= String.format("%-13s","guide");
		String favAnimal= String.format("%-13s","favotir animal");
		System.out.println(name+age+ID+guide+favAnimal);
		Node<VisitorVIP> pos=this.VisitorsVIP;
		while(pos!=null)
		{
			System.out.println(pos.toString());
			pos=pos.getNext();
		}
	}

	public void addVisitorToGruopWithGuide() // ��� 9- ����� ���� ����� �� �����
	{
		System.out.println("enter Id:");
		int ID=Validation.getPositiveNumber();
		if(isVisitorExsist(ID)==null)//����� ����� ���� ����� �� ehho
			System.out.println("this visitor dosent exsist");
		else
			isVisitorExsist(ID).changeGuideToTrue();
	}

	public void printAllNamesAndIDWithGuide() // ��� 10- ����� ���� ���"� �� ������� ���������� ����� �� �����
	{
		System.out.println("the visitors with guide are:");
		Node <Visitor> pos=this.Visitors;
		while(pos!=null)
		{ 
			if(pos.getValue().getGuide())
				pos.getValue().printNameAndID();//����� �� �� ��"�
			pos=pos.getNext();
		}
	}

	public void addOrRemoveFoodFromCage() // ��� 11- ����� �� ����� �� ���� �����
	{
		String answer=addOrRemove("food");
		String nameCage=cageToAddOrRemove();//����� �� ���� ����
		Cage relevantCage=cageByName(nameCage);//����� ����� �������
		double amount,//����� ��� ���� ���� ����� ������ �� �����
		maxAmount=relevantCage.getProperty().howMuchFoodYouCanFeel(),//����� ������ �� ����� ��������� ����� ������ 
		currentToRemove=relevantCage.getProperty().getCurrentFood();//����� ������ �� ����� ��������� ����� ������ 
		if(answer.equals("add"))//����� ������
		{	
			amount=amountToAdd("kg",maxAmount);//����� ���� ����� ������
			relevantCage.getProperty().updateFood(amount);//����� ����� ������� �����
			System.out.println("we update the food amount");
		}
		else//����� ������
		{
			amount=amountToRemove("kg",currentToRemove);//����� ���� ����� ������
			relevantCage.getProperty().removeFood(amount);//����� ����� ������� �����
			System.out.println("we update the food amount");
		}
	}

	public void addOrRemoveWaterFromCage() // ��� 12- ����� �� ����� �� ��� �����
	{
		String answer,nameCage;
		answer=addOrRemove("water");
		nameCage=cageToAddOrRemove();//����� �� ���� ����
		Cage relevantCage=cageByName(nameCage);//����� ����� �������
		double amount,//����� ��� ���� ���� ����� ������ �� �����
		maxAmount=relevantCage.getProperty().howMuchWaterYouCanFeel(),//����� ������ �� ����� ��������� ����� ������ 
		currentToRemove=relevantCage.getProperty().getCurrentWater();//����� ������ �� ����� ��������� ����� ������ 
		if(answer.equals("add"))//����� ������
		{
			amount=amountToAdd("liter",maxAmount);//����� ���� ����� ������
			relevantCage.getProperty().updateWater(amount);//����� ����� ������� �����
			System.out.println("we update the water amount");
		}
		else
		{
			amount=amountToRemove("liter",currentToRemove);//����� ���� ����� ������
			relevantCage.getProperty().removeWater(amount);//����� ���� ����� ������
			System.out.println("we update the water amount");
		}
	}

	private String addOrRemove(String word) // ����� ������ �� ������/������ ���/����
	{
		String answer;
		do{
			System.out.println("write 'add' to add "+word+" and 'remove' to remove "+word+" :");
			answer=input.next();
		}while(!answer.equals("add")&&!answer.equals("remove"));
		return answer;
	}

	private String cageToAddOrRemove() // ����� ������ ����� ���� ������/������ ���/����
	{
		String nameCage;
		do{
			System.out.println("enter cage name from next options:");
			printCages();
			nameCage=input.next();
		}while(indexOfCage(nameCage )==-1);
		return nameCage;
	}

	private double amountToAdd(String word,double maxAmount) // ����� ������ ���� ������
	{
		double amount;
		do{
			System.out.println("you can add "+maxAmount+" "+word+" how much you want to add?");
			amount=Validation.getPositiveDoubleNumber();
		}while(amount>maxAmount);
		return amount;
	}

	private double amountToRemove(String word,double currentToRemove) // ����� ������ ���� ������
	{
		double amount;
		do{
			System.out.println("you can remove "+currentToRemove+" "+word+" how much you want to remove?");
			amount=Validation.getPositiveDoubleNumber();
		}while(amount>currentToRemove);
		return amount;
	}

	public void printSpecipicAnimalDetailes() // ��� 13- ����� ����� �� ��� �������
	{
		printAnimals();
		System.out.println("enter your choice");
		String animalName=input.next();
		boolean flag=true;
		int count=0;
		while(count<5&&this.cages[count]!=null)
		{
			if(this.cages[count].isTheAnimalExsist(animalName)!=null)//����� ����� ���� ����� ���� ������
			{
				System.out.println(this.cages[count].isTheAnimalExsist( animalName).toString());//����� ���� ����
				flag=false;
			}
			count++;
		}
		if(flag)//����� ����� ������ �� �����
			System.out.println("we dont have this animal in the zoo");
	}

	private void printAnimals() // ����� ���� �� ����� ��� �����
	{
		int count=0;
		System.out.println("choose animal name from the list belong:");
		while(count<5&&this.cages[count]!=null)
		{
			this.cages[count].printAnimalList();
			count++;
		}
	}

	private void printCages()//����� ���� ������� ��� �����
	{
		int count=0;
		System.out.println("choose the name cage from the next options:");
		while(count<5&&this.cages[count]!=null)
		{
			System.out.println(this.cages[count].getName());
			count++;
		}
	}

	public void printSpecipicCageDetailes() // ��� 14- ����� ����� �� ���� ������
	{
		printCages();
		System.out.println("enter your choice");
		String cageName=input.next();
		boolean flag=true;
		int count=0;
		while(count<5&&this.cages[count]!=null)
		{
			if((this.cages[count].getName()).equals(cageName))//����� ����� ������� ����� �����
			{
				System.out.println(this.cages[count].toString());
				flag=false;
			}
			count++;
		}
		if(flag)//����� ����� ���� ����� �� ����
			System.out.println("we dont have this cage in the zoo");
	}

	public void leftVisitsInVisitTAB() // ��� 15- ����� ���� �������� ������ ��������� ����� VIP
	{
		int ID;
		System.out.println("enter ID");
		ID=Validation.getPositiveNumber();
		if(isVisitorVIPExsist(ID)==null)//����� ����� ���� ��� ���� ������� �� ��"� �������
			System.out.println("we dont have VIP Visitor with ID: "+ID);
		else
			isVisitorVIPExsist(ID).getTab().leftVisitsInVisiTAB();//����� ���� �������� ������ ����� ��������
	}

	public void favAnimalAmongVIPVisitors() // ��� 16- �� ���� ����� �VIP ������� ��� �������
	{
		printAnimals();
		String nameAnimal=input.next();
		if(isAnimalExsist(nameAnimal)==null)//����� ����� ���� ���� �� �����
			System.out.println("the zoo dosent have this animal");
		else
			HowMuchVisitorVIPLove (nameAnimal);//����� �� ���� ����� �������� ������� �� ����
	}

	private void HowMuchVisitorVIPLove(String nameAnimal) // ���� ���� 16 ������ ������� �� ���� ����� �VIP ������� ��� �������
	{
		Node<VisitorVIP>pos=this.VisitorsVIP;
		int count=0;
		while(pos!=null)
		{
			if(pos.getValue().getFavAnimal().getName().equals(nameAnimal))//����� ����� ���� ����� ����� ������� ����� ���� ������ ����
				count++;
			pos=pos.getNext();
		}
		System.out.println(count+ " people love "+ nameAnimal);
	}

	public void howMuchPeopleAboveAge()//��� 17- ���� ������ ������� ��� ����� ��� �� ���� ������
	{
		System.out.println("enter age");
		double age=Validation.getPositiveDoubleNumber();
		Node<Visitor>pos=this.Visitors;
		int count=0;
		while(pos!=null)
		{
			if(pos.getValue().getAge()>=age)//����� ����� ���� ��� ����� ��� �� ���� ����� ������
				count++;
			pos=pos.getNext();
		}
		System.out.println( count+" people are above and equle age "+age);
	}

	private boolean readZOOFromFile()//����� �����
	{
		boolean flag=false;
		File f = new File("ZOO.dat");
		if (f.exists())
		{
			flag=true;
			try
			{
				FileInputStream readFile = new FileInputStream("ZOO.dat");
				ObjectInputStream read = new ObjectInputStream(readFile);
				ZOO zoo =(ZOO)read.readObject();
				this.Visitors=zoo.Visitors;
				this.VisitorsVIP=zoo.VisitorsVIP;
				this.cages=zoo.cages;
				read.close();
				readFile.close();
			}
			catch(Exception exc)
			{
				exc.printStackTrace();
			}
		}
		return (flag);
	}

	public void writeToFile()//����� �����
	{
		try
		{
			FileOutputStream saveFile=new FileOutputStream("ZOO.dat");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			save.writeObject(this);
			save.close();
			saveFile.close();
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
	}
}