package comparator.common;

import java.util.Comparator;

public class ComparatorClass implements Comparator<ComparatorClass>{
	private int anInt;
	private double aDouble;
	private char aChar;
	private String aString;	

	public	ComparatorClass(int intToSet, double doubleToSet, char charToSet, String stringToSet){
		this.anInt = intToSet;
		this.aDouble = doubleToSet;
		this.aChar = charToSet;
		this.aString = stringToSet;	
	}
	
	public void setInt(int intToSet){
		this.anInt = intToSet;
	}
	
	public int getInt(){
		return (this.anInt);
	}
	
	public void setDouble(double doubleToSet){
		this.aDouble = doubleToSet;
	}
	
	public double getDouble(){
		return (this.aDouble);
	}
	
	public void setString(String stringToSet){
		this.aString = stringToSet;
	}
	
	public String getString(){
		return (this.aString);
	}
	
	public void setChar(char charToSet){
		this.aChar = charToSet;
	}
	
	public char getChar(){
		return (this.aChar);
	}
	
	public void printData(){
		System.out.println(this.anInt + " " + this.aDouble + " " + this.aChar + " " + this.aString);
		//System.out.println();
	}
	
	/*
    public int compare(Individual o1, Individual o2) {
		return (int) (-(o1.getFitness() - o2.getFitness()));	//for reverse order
	}
    */
    
	
	//@Override
	public int compare(ComparatorClass o1, ComparatorClass o2) {
		return (int) (o1.getInt() - o2.getInt());
		//return (int) (-(o1.getFitness() - o2.getFitness()));
	}
	

	public static Comparator<ComparatorClass> intComparator = new Comparator<ComparatorClass>() {
	//public Comparator<ComparatorClass> intComparator = new Comparator<ComparatorClass>() {
		@Override
		public int compare(ComparatorClass o1, ComparatorClass o2) {
			return ((int) (o1.getInt() - o2.getInt()));
			//return ((int) (-(o1.getInt() - o2.getInt())));
		}
	};
	
	public static Comparator<ComparatorClass> doubleComparator = new Comparator<ComparatorClass>() {
		@Override
		public int compare(ComparatorClass o1, ComparatorClass o2) {
			return ((int) (o1.getDouble() - o2.getDouble()));
			//return ((int) (-(o1.getDouble() - o2.getDouble())));
		}
	};
	
	public static Comparator<ComparatorClass> charComparator = new Comparator<ComparatorClass>() {
		@Override
		public int compare(ComparatorClass o1, ComparatorClass o2) {
			return ((int) (o1.getChar() - o2.getChar()));
			//return ((int) (-(o1.getChar() - o2.getChar())));
		}
	};
	
	public static Comparator<ComparatorClass> stringComparator = new Comparator<ComparatorClass>() {
		@Override
		public int compare(ComparatorClass o1, ComparatorClass o2) {
			/*
			return ((int) (o1.getString() - o2.getString()));
			*/
			//int i, j, k;
			String aStr, bStr;
			aStr = o1.getString();
			bStr = o2.getString();
			/*
			//k = aStr.substring(i).compareTo(bStr.substring(j));
			if(k == 0) // last names match, check entire name
				return (aStr.compareTo(bStr));
			else
				return k;
			*/
			return (aStr.compareTo(bStr));
		}
	};

}