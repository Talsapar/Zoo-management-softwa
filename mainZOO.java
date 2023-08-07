public class mainZOO
{
	public static void main(String[] args)
	{
		ZOO ZOO= new ZOO(); 
		int answer = getAnswer();
		while (answer !=18)
		{
			switch (answer)
			{
			case 1 : ZOO.addNewVisitor(); break;
			case 2 : ZOO.addNewAnimal(); break;
			case 3 : ZOO.removeAnimalFromCage(); break;
			case 4 : ZOO.removeVisitorFromZoo(); break;
			case 5 : ZOO.printNameCagesAndAnimal();break;
			case 6 : ZOO.printAllAnimalInCage();break;
			case 7 : ZOO.printAllVisitorsDetails(); break;
			case 8 : ZOO.printAllVIPVisitorsDetails(); break;
			case 9 : ZOO.addVisitorToGruopWithGuide(); break;
			case 10 : ZOO.printAllNamesAndIDWithGuide(); break;
			case 11 : ZOO.addOrRemoveFoodFromCage();break;
			case 12 : ZOO.addOrRemoveWaterFromCage();break;
			case 13 : ZOO.printSpecipicAnimalDetailes(); break;
			case 14 : ZOO.printSpecipicCageDetailes(); break;
			case 15 : ZOO.leftVisitsInVisitTAB(); break;
			case 16 : ZOO.favAnimalAmongVIPVisitors(); break;
			case 17 : ZOO.howMuchPeopleAboveAge(); break;
			}
			answer = getAnswer();
		}
		System.out.println ("thank you! we hope you enjoy our ZOO :)");
		ZOO.writeToFile();

	}

	private static void showMenu()//הדפסת האפשרויות של הקוד של המשתמש
	{
		System.out.println("enter:");
		System.out.println("1 -- add new visitor");
		System.out.println("2 -- add new animal");
		System.out.println("3 -- remove animal from ZOO");
		System.out.println("4 -- remove visitor from ZOO");
		System.out.println("5 -- print name cages and animals name in the cages");
		System.out.println("6 -- print all names of animals in specipic cage");
		System.out.println("7 -- print all visitors detailes");
		System.out.println("8 -- print all VIP visitors detailes");
		System.out.println("9 -- add visitor to group with guide");
		System.out.println("10 -- print all names and ID of visitor with guide");
		System.out.println("11 -- add or remov food from cage");
		System.out.println("12 -- add or remov water from cage");
		System.out.println("13 -- print specipic animal detailes");
		System.out.println("14 -- print specipic cage detailes");
		System.out.println("15 -- to know how much visits left in visitor VIP's visit tab");
		System.out.println("16 -- favorit animal among VIP visitors");
		System.out.println("17--to know how much people in the zoo above specfic age");
		System.out.println("18 -- finish");
	}
	private static int getAnswer()//מסננת קלט והחזרת התשובה שבטווח
	{
		int answer;
		do 
		{
			showMenu();
			answer =Validation.getPositiveNumber();
		} while (answer<1 || answer>18);
		return answer;
	}
}