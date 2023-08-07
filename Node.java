import java.io.Serializable;
public class Node<T> implements Serializable
{
	private T value ;
	private Node<T> next;
	public Node (T x)
	{
		this.value = x;
		this.next=null;
	}
	public Node(T x, Node <T> next)
	{
		this.value = x;
		this.next=next;
	}
	public T getValue()
	{
		return this.value;
	}
	public Node <T>getNext()
	{
		return this.next;
	}
	public void setValue(T x)
	{
		this.value=x;
	}
	public void setNext(Node <T> next)
	{
		this.next=next;
	}
	public String toString()
	{
		return ""+this.value;
	}
}