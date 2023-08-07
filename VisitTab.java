import java.io.Serializable;
import java.util.Scanner;
public class VisitTab implements Serializable {
	static Scanner input= new Scanner(System.in);
	private int totalNumVisit;//מספר ביקורים כולל(5/10)
	private int currentNumVisit;//מספר ביקורים נוכחי

	public VisitTab(int totalNumVisit)//פעולה בונה
	{
		this.totalNumVisit=totalNumVisit;
		this.currentNumVisit=1;
	}
	
	public boolean haveEnoughVisits()//פעולה שמחזירה true אם יש מספיק ביקורים בכרטיסיה וfalse אחרת.
	{
		return (this.currentNumVisit<this.totalNumVisit);
	}
	
	public void updateNumVisit()//פעולה שמעדכנת את כמות הביקורים ב1
	{
		 this.currentNumVisit++;
	}
	
	public void leftVisitsInVisiTAB()//פעולה שמדפיסה כמה ביקורים נשארו למבקר
	{
		System.out.println("you have "+(this.totalNumVisit-this.currentNumVisit)+" visits left in visitTab");
	}
	
}