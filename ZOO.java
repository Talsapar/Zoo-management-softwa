import java.io.*; 
import java.util.Scanner;
public class ZOO implements Serializable
{
	private Node<Visitor> Visitors =null;//רשימה של מבקרים שנמצאים כעת בגן החיות
	private Node<VisitorVIP> VisitorsVIP=null ;//רשימה של כל מבקרי VIP
	private Cage[]cages=new Cage[5];//מערך של כלובים באורך 5
	static Scanner input = new Scanner (System.in);

	public ZOO()//פעולה בונה
	{
		boolean exist=readZOOFromFile();
		if (!exist)
		{
			this.Visitors = null;
			this.VisitorsVIP = null;
		}
	}

	public void addNewVisitor()// קוד 1 -פעולה שמוסיפה מבקר חדש 
	{
		Visitor newVisitor;
		System.out.println("Enter Id");
		int ID= Validation.getPositiveNumber();
		if(isVisitorExsist(ID)!=null)//אם המבקר נמצא בגן החיות
			System.out.println("the visitor is already in the zoo");
		else
		{
			VisitorVIP VisitorVIP=isVisitorVIPExsist(ID);
			if(VisitorVIP!=null)//נכנס לתנאי במידה והמבקר הוא VIP
				newVisitor=enterVisitorVIP(VisitorVIP);//פעולה הבודקת האם נישארו מספיק ביקורים להיכנס לגן חיות
			else
			{
				System.out.println("Enter visitor name");
				String name = input.next();
				System.out.println("Enter visitor age");
				double age = Validation.getPositiveDoubleNumber();
				newVisitor=enterVisitor( name, ID, age);
			}
			addVisitorToList(newVisitor);//הוספה בצורה ממויינת לרשימת המבקרים
			System.out.println("enjoy youre Visit");
		}
	}

	private Visitor enterVisitorVIP(VisitorVIP visitorVIP)//המשך לפעולה שמוסיפה מבקר- במידה והמבקר הוא VIP 
	{//VIP בודקת האם יש לו מספיק ביקורים בכרטיסיה ומחזירה עצם מטיפוס Visitor .
		Visitor newVisitor;
		if(visitorVIP.getTab().haveEnoughVisits())// נכנסת לתנאי אם קיימים מספיק ביקורים בכרטיסייה
		{
			System.out.println("you are VIP and you have enough visits in visitTAB we update it");//עדכון
			visitorVIP.getTab().updateNumVisit();//עדכון מספר הביקורים הנוכחים 
			newVisitor = new Visitor (visitorVIP.getName(),visitorVIP.getAge(),visitorVIP.getID(),true);
		}
		else
		{
			newVisitor=enterVisitor(visitorVIP.getName(),visitorVIP.getID(),visitorVIP.getAge());
			removeVisitorVIPFromZoo(visitorVIP);//הורדה של מבקר מרשימת ה VIP כאשר הכרטיסייה שלו ניגמרה
		}
		return newVisitor;

	}

	private Visitor enterVisitor(String name,int ID,double age)//המשך לפעולה שמוסיפה מבקר- במידה והמבקר הוא VIP 
	{//ולא נשארו לו מספיק ביקורים בכרטיסיה ומחזירה עצם מטיפוס Visitor .
		Visitor newVisitor;
		boolean answer1,answer;
		Animal favAnimal;
		System.out.println("regelar tichet cost 25 shekel if you want buy visitTAB press true else press false");
		answer1 = Validation.getBoolean();
		if(answer1)//זא שמבקר רוצה לקנות כרטיסייה
		{
			VisitTab Tab=createNewVisitTAB();//בניית כרטיסיה חדשה 
			System.out.println("youre buing has done succesfully");
			newVisitor = new Visitor (name,age,ID,true);
			System.out.println("youre VIP now");
			favAnimal=addFavAnimal();//קולטת חיה מועדפת תקינה למבקר VIP 
			VisitorVIP newVisitorVIP = new VisitorVIP (name,age,ID,Tab,favAnimal);
			addVisitorVIPToList(newVisitorVIP);//הוספת מבקר ויאייפי לרשימה בצורה ממויינת
		}
		else
		{
			answer=wantGuide();//בודקת האם מבקר מעוניין במדריך או לא
			newVisitor= new Visitor(name,age,ID,answer);
		}
		return newVisitor;
	}

	private VisitTab createNewVisitTAB()//המשך לפעולה שמוסיפה מבקר- במידה והמבקר רוצה לקנות כרטיסיה נבנה לו עצם מטיפוס VisitTab ונחזיר אותו
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

	private Animal addFavAnimal()//המשך לפעולה שמוסיפה מבקר- פעולה שמדפיסה את החיות בגן החיות 
	{//ונותנת למבקר VIP לבחור חיה אהובה תקינה ומחזירה עצם מטיפוס Animal
		Animal favAnimal;
		do{
			printAnimals();//מדפיסה את שמות כל החיות בגן החיות
			System.out.println("enter youre choice");
			String newFavAnimal=input.next();
			favAnimal=isAnimalExsist(newFavAnimal);
		}while(favAnimal==null);
		return favAnimal;
	}

	private boolean wantGuide()//המשך לפעולה שמוסיפה מבקר- פעולה שבודקת עם המבקר 
	{//אם הוא מעוניין המדריך מחזירה true  אם כן ו false אחרת. 
		boolean answer;
		System.out.println("regelar tichet cost 25 shekel");
		System.out.println("Enter true if the visitor want guide or false if not");
		answer=Validation.getBoolean();
		return answer;
	}

	private VisitorVIP isVisitorVIPExsist(int ID)//פעולה שמחזירה עצם מטיפוס VisitorVIP אם קיים אחרת מחזירה null
	{
		Node<VisitorVIP> pos=this.VisitorsVIP;
		while(pos!=null)
		{
			if(pos.getValue().getID()==ID)//כניסה לתנאי רק כאשר המבקר קיים
				return pos.getValue();//החזרת העצם הרלוונטי מטיפוס מבקר ויאייפי
			pos=pos.getNext();
		}
		return null;
	}

	private Visitor isVisitorExsist(int ID)//פעולה שמחזירה עצם מטיפוס Visitor אם קיים אחרת מחזירה null
	{
		Node<Visitor> pos=this.Visitors;
		while(pos!=null)
		{
			if(pos.getValue().getID()==ID)//כניסה לתנאי רק כאשר המבקר קיים
				return pos.getValue();//החזרת העצם הרלוונטי מטיפוס מבקר
			pos=pos.getNext();
		}
		return null;
	}

	private void addVisitorToList (Visitor newVisitor)//  פעולה שמוסיפה מבקר לרשימת המבקרים בצורה ממוינת  
	{
		Node <Visitor> temp = new Node<Visitor> (newVisitor);
		if (this.Visitors==null)
			this.Visitors=temp; // הוספת חוליה ראשונה
		else if (this.Visitors.getValue().getName().compareTo (newVisitor.getName())>0)
			// הוספה לפני חוליה ראשונה
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


	private void addVisitorVIPToList (VisitorVIP newVisitorVIP)//  פעולה שמוסיפה מבקר VIP לרשימת מבקרי ה VIP בצורה ממוינת
	{
		Node <VisitorVIP> temp = new Node<VisitorVIP> (newVisitorVIP);
		if (this.VisitorsVIP==null)
			this.VisitorsVIP=temp; // הוספת חוליה ראשונה
		else if (this.VisitorsVIP.getValue().getName().compareTo (newVisitorVIP.getName())>0)
			// הוספה לפני חוליה ראשונה
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

	public void addNewAnimal() // קוד 2- פעולה שמוסיפה חיה חדשה לגן החיות
	{
		System.out.println("choose the type of the animal,else write new one");
		printCages();//הדפסת שמות הכלובים בגן החיות
		String nameCage=input.next();
		Cage cage=cageByName(nameCage);//החזרת הפנייה לעצם מטיפוס כלוב הרלוונטי
		if(cage==null&&this.cages[4]!=null)//כניסה לתנאי כשכל מערך הכלובים מלא
			System.out.println("im sorry we dont have enoght place to youre animal");
		else{
			if(cage==null)//כניסה לתנאי כאשר לא קיים כלוב עם השם המבוקש
				cage=addNewCageToZoo();//יצירת כלוב חדש
			System.out.println("enter the animal name");
			String nameAnimal=input.next();
			if(isAnimalExsist(nameAnimal)!=null)//כניסה לתנאי כאשר יש בגן החיות חיה עם שם זהה
				System.out.println("sorry,i have in the zoo this animal we cant add it");
			else
				addNewAnimalToCage(nameAnimal,cage);//הוספת החיה לרשימת החיות בכלוב הרלוונטי
		}
	}

	private Cage addNewCageToZoo()//פעולה שמוסיפה כלוב חדש למערך הכלובים כאשר לא קיים כלום בשם זה 
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

	private void addNewAnimalToCage(String nameAnimal,Cage cage)//פעולה שמכניסה חיה חדשה לכלוב קיים כאשר שמה אינו קיים בגן החיות
	{
		Animal animal;
		System.out.println("enter the animal weight");
		double weight= Validation.getPositiveDoubleNumber();
		if(!cage.getProperty().thereIsEnoughPlaceToAnimal(weight))//כניסה לתנאי כאשר אין מספיק מקום מבחינת משקל לחיה 
			System.out.println("sorry, we dont have enough place in the cage");
		else
		{
			animal=new Animal(nameAnimal,weight);
			cage.addNewAnimal(animal);//הוספת החיה לכלוב
			cage.getProperty().updateWeight(weight);//עדכון המשקל הנוכחי בכלוב
			System.out.println("the animal has added to the cage ");
		}
	}

	private Animal isAnimalExsist(String nameAnimal)//פעולה שמחזירה עצם מטיפוס Animal אם קיים אחרת מחזירה null
	{
		Animal animal;
		int count=0;
		while(count<5&&this.cages[count]!=null)//מעבר על מערך הכלובים
		{
			animal= this.cages[count].isTheAnimalExsist(nameAnimal);
			if(animal!=null)
				return animal;
			count++;
		}
		return null;
	}

	private int indexOfNewCage()//פעולה שמחזירה אינדקס של כלוב חדש ו-1- כאשר כל הכלובים תפוסים
	{
		for(int i=0;i<5;i++)
		{
			if(this.cages[i]==null)
				return i;
		}
		return -1;
	}

	public void removeAnimalFromCage() //קוד 3- הסרת חיה מגן החיות
	{
		printAnimals();
		String name=input.next();
		Animal removeAnimal=isAnimalExsist(name);
		boolean flag=removeFromListAndUpdateWeight(name);
		if(flag)//נכנסים לתנאי כאשר החיה הוסרה והמשקל עודכן
		{
			System.out.println("the animal removed");
			replaceToVIPVisitor(removeAnimal);//שינוי החיה האהובה של אורח ויאייפי במידה והחיה שהוסרה היתה בחירתו
		}
		else
			System.out.println("we dont have this animal in the zoo");
	}

	private boolean removeFromListAndUpdateWeight(String name) //פעולת המשך להסרת חיה-מחזירה true  אם החיה קיימת והוסרה ו- False  אחרת
	{
		boolean flag=false;
		for(int i=0;i<5;i++)
			if(this.cages[i]!=null&&this.cages[i].isTheAnimalExsist(name)!=null)//כניסה לתנאי כאשר החיה קיימת
			{
				this.cages[i].getProperty().removeWeight(this.cages[i].isTheAnimalExsist(name).getWeight());//הסרת המשקל של החיה מהמשקל הנוכחי של הכלוב
				this.cages[i].removeFromCage(name);//הסרה מרשימת החיות
				flag=true;
			}
		return flag;
	}

	public void removeVisitorFromZoo() //קוד 4- הסרת מבקר מגן החיות
	{
		System.out.println("Enter visitor ID");
		int ID = Validation.getPositiveNumber();
		Visitor visitor=isVisitorExsist(ID);
		if(visitor==null)//אין מבקר בשם שיש להוריד מהרשימה
			System.out.println("sorry, we dont have visitor with ID "+ID);
		else//הורדת המבקר מהרשימהן
		{
			removeVisitors(visitor);//מחיקה מהרשימה של מבקרים
			System.out.println("we hope you enjoy our zoo, see you next time :)");
		}	
	}

	private void removeVisitors(Visitor visitor) //פעולה שמורידה מבקר מרשימת המבקרים
	{
		if (visitor == this.Visitors.getValue())// ביטול חוליה ראשונה
			this.Visitors=this.Visitors.getNext();
		else
		{
			Node<Visitor> pos=this.Visitors;
			while (pos.getNext().getValue()!= visitor)
				pos=pos.getNext();
			pos.setNext(pos.getNext().getNext());
		}
	}

	private void replaceToVIPVisitor(Animal removeAnimal) //פעולה שמוצאת את מבקר ה VIP שיש להחליף לו את החיה האהובה 
	{
		Node<VisitorVIP> pos=this.VisitorsVIP;
		while (pos!= null)
		{
			if(pos.getValue().getFavAnimal().getName().equals(removeAnimal.getName()))//כניסה לתנאי כאשר מצאנו מבקר ויאייפי שהחיה האהובה עליו הוסרה
				replaceFavAnimal(pos);//החלפת החיה המועדפת לחיה חדשה
			pos=pos.getNext();
		}
	}

	private void replaceFavAnimal(Node<VisitorVIP> pos) //פעלה שמחליפה את החיה האהובה של מבקר ה VIP
	{
		Animal newAnimal;
		System.out.println("the fav animal of visitor with ID : "+ pos.getValue().getID()+" removed");
		do{
			printAnimals();
			String newFavAnimal=input.next();
			newAnimal=isAnimalExsist(newFavAnimal);
		}while(newAnimal==null);
		pos.getValue().replaceFavAnimal(newAnimal);//אחרי שניקלטה חיה תקינה נחליף
		System.out.println("your fav animal has changed sucssefully");
	}

	private void removeVisitorVIPFromZoo(VisitorVIP visitorVIP) //פעולה שמורידה מבקר VIP מרשימת מבקרי ה VIP
	{
		if (visitorVIP == this.VisitorsVIP.getValue())// ביטול חוליה ראשונה
			this.VisitorsVIP=this.VisitorsVIP.getNext();
		else//הורדת מבקר ויאייפי
		{
			Node<VisitorVIP> pos=this.VisitorsVIP;
			while (pos.getNext().getValue()!= visitorVIP)
				pos=pos.getNext();
			pos.setNext(pos.getNext().getNext());
		}
	}

	public void printNameCagesAndAnimal () // קוד 5- פעולה שמדפיסה את שמות הכלובים והחיות בהם.
	{
		int count=0;
		while(count<5&&this.cages[count]!=null)//מעבר על מערך הכלובים
		{
			System.out.println(this.cages[count].getName()+":");//הדפסת שם הכלוב
			this.cages[count].printAnimalList();//הדפסת החיות בכלוב
			count++;
		}
	}

	public void printAllAnimalInCage () // קוד 6- פעולה שמדפיסה רשימה של חיות של כלוב מסויים 
	{
		String choice;
		printCages();//הדפסת כל הכלובים
		do{
			System.out.println("enter youre chioe");
			choice=input.next();	
		}while(indexOfCage(choice)==-1);
		this.cages[indexOfCage(choice)].printAnimalList();//הדפסת רשימת החיות של הכלוב המבוקש
	}

	private int indexOfCage(String cageName)//פעולה שמקבלת שם של כלוב ומחזירה את המיקום שלו במערך
	{
		int count=0;
		while(count<5&&this.cages[count]!=null)
		{
			if(this.cages[count].getName().equals(cageName))//כניסה לתנאי כאשר מצאנו את הכלוב שנידרש להחזיר את מיקומו
				return count;
			count++;
		}
		return -1;
	}

	private Cage cageByName(String cageName)//פעולה שמקבלת שם של כלוב ומחזירה הפניה לעצם מטיפוס Cage  הרלוונטי
	{
		for(int i=0;i<5;i++)
			if(this.cages[i]!=null&&this.cages[i].getName().equals(cageName))//כניסה לתנאי כאשר מצאנו את הכלוב שנידרש למצוא
				return this.cages[i];
		return null;
	}

	public void printAllVisitorsDetails() // קוד 7- הדפסת כל פרטי המבקרים בטבלה
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

	public void printAllVIPVisitorsDetails() // קוד 8- הדפסת כל פרטי מבקרי ה VIP בטבלה
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

	public void addVisitorToGruopWithGuide() // קוד 9- הוספת מבקר לטיול עם מדריך
	{
		System.out.println("enter Id:");
		int ID=Validation.getPositiveNumber();
		if(isVisitorExsist(ID)==null)//כניסה לתנאי כאשר המבקר לא ehho
			System.out.println("this visitor dosent exsist");
		else
			isVisitorExsist(ID).changeGuideToTrue();
	}

	public void printAllNamesAndIDWithGuide() // קוד 10- הדפסת שמות והת"ז של המבקרים המעוניינים בטיול עם מדריך
	{
		System.out.println("the visitors with guide are:");
		Node <Visitor> pos=this.Visitors;
		while(pos!=null)
		{ 
			if(pos.getValue().getGuide())
				pos.getValue().printNameAndID();//הדפסה של שם ות"ז
			pos=pos.getNext();
		}
	}

	public void addOrRemoveFoodFromCage() // קוד 11- הוספה או הורדה של אוכל מכלוב
	{
		String answer=addOrRemove("food");
		String nameCage=cageToAddOrRemove();//קליטה של כלוב תקין
		Cage relevantCage=cageByName(nameCage);//מציאת הכלוב הלוונטי
		double amount,//משתנה שבו נשים כמות תקינה להוספה או הורדה
		maxAmount=relevantCage.getProperty().howMuchFoodYouCanFeel(),//משתנה שמחזיק את הכמות המקסימלית שאפשר להוסיף 
		currentToRemove=relevantCage.getProperty().getCurrentFood();//משתנה שמחזיק את הכמות המקסימלית שאפשר להוריד 
		if(answer.equals("add"))//כניסה בהוספה
		{	
			amount=amountToAdd("kg",maxAmount);//קליטת כמות תקינה להוספה
			relevantCage.getProperty().updateFood(amount);//עדכון הכמות הנוכחית בכלוב
			System.out.println("we update the food amount");
		}
		else//כניסה בהורדה
		{
			amount=amountToRemove("kg",currentToRemove);//קליטת כמות תקינה להורדה
			relevantCage.getProperty().removeFood(amount);//עדכון הכמות הנוכחית בכלוב
			System.out.println("we update the food amount");
		}
	}

	public void addOrRemoveWaterFromCage() // קוד 12- הוספה או הורדה של מים מכלוב
	{
		String answer,nameCage;
		answer=addOrRemove("water");
		nameCage=cageToAddOrRemove();//קליטה של כלוב תקין
		Cage relevantCage=cageByName(nameCage);//מציאת הכלוב הלוונטי
		double amount,//משתנה שבו נשים כמות תקינה להוספה או הורדה
		maxAmount=relevantCage.getProperty().howMuchWaterYouCanFeel(),//משתנה שמחזיק את הכמות המקסימלית שאפשר להוסיף 
		currentToRemove=relevantCage.getProperty().getCurrentWater();//משתנה שמחזיק את הכמות המקסימלית שאפשר להוריד 
		if(answer.equals("add"))//כניסה בהוספה
		{
			amount=amountToAdd("liter",maxAmount);//קליטת כמות תקינה להוספה
			relevantCage.getProperty().updateWater(amount);//עדכון הכמות הנוכחית בכלוב
			System.out.println("we update the water amount");
		}
		else
		{
			amount=amountToRemove("liter",currentToRemove);//קליטת כמות תקינה להורדה
			relevantCage.getProperty().removeWater(amount);//קליטת כמות תקינה להורדה
			System.out.println("we update the water amount");
		}
	}

	private String addOrRemove(String word) // פעולה שבודקת אם להוריד/להוסיף מים/אוכל
	{
		String answer;
		do{
			System.out.println("write 'add' to add "+word+" and 'remove' to remove "+word+" :");
			answer=input.next();
		}while(!answer.equals("add")&&!answer.equals("remove"));
		return answer;
	}

	private String cageToAddOrRemove() // פעולה שבודקת מאיזה כלוב להוריד/להוסיף מים/אוכל
	{
		String nameCage;
		do{
			System.out.println("enter cage name from next options:");
			printCages();
			nameCage=input.next();
		}while(indexOfCage(nameCage )==-1);
		return nameCage;
	}

	private double amountToAdd(String word,double maxAmount) // פעולה שקולטת כמות להוספה
	{
		double amount;
		do{
			System.out.println("you can add "+maxAmount+" "+word+" how much you want to add?");
			amount=Validation.getPositiveDoubleNumber();
		}while(amount>maxAmount);
		return amount;
	}

	private double amountToRemove(String word,double currentToRemove) // פעולה שקולטת כמות להורדה
	{
		double amount;
		do{
			System.out.println("you can remove "+currentToRemove+" "+word+" how much you want to remove?");
			amount=Validation.getPositiveDoubleNumber();
		}while(amount>currentToRemove);
		return amount;
	}

	public void printSpecipicAnimalDetailes() // קוד 13- הדפסת פרטים של חיה ספציפית
	{
		printAnimals();
		System.out.println("enter your choice");
		String animalName=input.next();
		boolean flag=true;
		int count=0;
		while(count<5&&this.cages[count]!=null)
		{
			if(this.cages[count].isTheAnimalExsist(animalName)!=null)//כניסה לתנאי כאשר הגענו לחיה הרצויה
			{
				System.out.println(this.cages[count].isTheAnimalExsist( animalName).toString());//הדפסת פרטי החיה
				flag=false;
			}
			count++;
		}
		if(flag)//כניסה לתנאי כשהחיה לא קיימת
			System.out.println("we dont have this animal in the zoo");
	}

	private void printAnimals() // הדפסת שמות כל החיות בגן החיות
	{
		int count=0;
		System.out.println("choose animal name from the list belong:");
		while(count<5&&this.cages[count]!=null)
		{
			this.cages[count].printAnimalList();
			count++;
		}
	}

	private void printCages()//הדפסת שמות הכלובים בגן החיות
	{
		int count=0;
		System.out.println("choose the name cage from the next options:");
		while(count<5&&this.cages[count]!=null)
		{
			System.out.println(this.cages[count].getName());
			count++;
		}
	}

	public void printSpecipicCageDetailes() // קוד 14- הדפסת פרטים של כלוב ספציפי
	{
		printCages();
		System.out.println("enter your choice");
		String cageName=input.next();
		boolean flag=true;
		int count=0;
		while(count<5&&this.cages[count]!=null)
		{
			if((this.cages[count].getName()).equals(cageName))//כניסה לתנאי כשהגענו לכלוב הרצוי
			{
				System.out.println(this.cages[count].toString());
				flag=false;
			}
			count++;
		}
		if(flag)//כניסה לתנאי כאשר הכלוב לא קיים
			System.out.println("we dont have this cage in the zoo");
	}

	public void leftVisitsInVisitTAB() // קוד 15- הדפסת כמות הביקורים שנותרו בכרטיסייה למבקר VIP
	{
		int ID;
		System.out.println("enter ID");
		ID=Validation.getPositiveNumber();
		if(isVisitorVIPExsist(ID)==null)//כניסה לתנאי כאשר אין מבקר ויאייפי עם הת"ז שניקלטה
			System.out.println("we dont have VIP Visitor with ID: "+ID);
		else
			isVisitorVIPExsist(ID).getTab().leftVisitsInVisiTAB();//הדפסת כמות הביקורים שנותרה למבקר הויאייפי
	}

	public void favAnimalAmongVIPVisitors() // קוד 16- מה מספר מבקרי הVIP שאוהבים חיה ספציפית
	{
		printAnimals();
		String nameAnimal=input.next();
		if(isAnimalExsist(nameAnimal)==null)//כניסה לתנאי כאשר החיה לא קיימת
			System.out.println("the zoo dosent have this animal");
		else
			HowMuchVisitorVIPLove (nameAnimal);//הדפסה של כמות מבקרי הויאייפי שאוהבים את החיה
	}

	private void HowMuchVisitorVIPLove(String nameAnimal) // המשך לקוד 16 –פעולה שמדפיסה את מספר מבקרי הVIP שאוהבים חיה ספציפית
	{
		Node<VisitorVIP>pos=this.VisitorsVIP;
		int count=0;
		while(pos!=null)
		{
			if(pos.getValue().getFavAnimal().getName().equals(nameAnimal))//כניסה לתנאי כאשר הגענו למבקר ויאייפי שזוהי החיה האהובה עליו
				count++;
			pos=pos.getNext();
		}
		System.out.println(count+ " people love "+ nameAnimal);
	}

	public void howMuchPeopleAboveAge()//קוד 17- מספר האנשים הנמצאים בגן החיות מעל או בגיל מסויים
	{
		System.out.println("enter age");
		double age=Validation.getPositiveDoubleNumber();
		Node<Visitor>pos=this.Visitors;
		int count=0;
		while(pos!=null)
		{
			if(pos.getValue().getAge()>=age)//כניסה לתנאי כאשר גיל המבקר שוה או גדול מהגיל הנידרש
				count++;
			pos=pos.getNext();
		}
		System.out.println( count+" people are above and equle age "+age);
	}

	private boolean readZOOFromFile()//קריאה מקובץ
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

	public void writeToFile()//כתיבה לקובץ
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