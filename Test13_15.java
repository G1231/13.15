/*13.15 (USE BIGINTEGER FOR THE RATIONAL CLASS) Redesign and implement 
the Rational class in Listing 13.13 using BigInteger for the numerator 
and denominator. Write a test program that prompts the user to enter two 
rational numbers and display the results as shown in the following sample run:

Enter the first rational number: 3 454 
Enter the second rational number: 7 2389 
3/454 + 7/2389 = 10345/1084606
3/454 – 7/2389 = 3989/1084606
3/454 * 7/2389 = 21/1084606
3/454 / 7/2389 = 7167/3178
7/2389 is 0.0029300962745918793 */

// importing BigInteger and Scanner for implementing BigIntegers and for user input
import java.math.BigInteger;
import java.util.Scanner;
public class Test13_15 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//Asking for first rational
	    System.out.println("Enter the first numerator followed by a space then the denominator");
		String in_1 = input.nextLine();
		//Asking for second rational
	    System.out.println("Enter the second numerator followed by a space then the denominator");
		String in_2 = input.nextLine();
		//closing scanner to stop resource leak
		input.close();
		// splitting inputs into numerator and denominator and storing in array
	    String[] split_1 = in_1.split(" ");
	    String[] split_2 = in_2.split(" ");


	    //creating rational objects based on new numerator and denominators
	    Rational num1 = new Rational(new BigInteger(split_1[0]), new BigInteger(split_1[1]));
		Rational num2 = new Rational(new BigInteger(split_2[0]), new BigInteger(split_2[1]));
		
		//printing results for every type of basic arithmetic and the decimal value of the rationals
	    System.out.println(num1 + " + " + num2 + " = " + num1.add(num2));
	    System.out.println(num1 + " - " + num2 + " = " + num1.subtract(num2));
	    System.out.println(num1 + " * " + num2 + " = " + num1.multiply(num2));
	    System.out.println(num1 + " / " + num2 + " = " + num1.divide(num2));
	    System.out.println(num1 + " is " + num1.doubleValue());
	    System.out.println(num2 + " is " + num2.doubleValue());
		  }
		}

//creating rational class that inherits number and comparable
	  class Rational extends Number implements Comparable<Rational> {
	  // initializing two BigIntegers in place of numerator and denominator
	    private BigInteger bint1;
	    private BigInteger bint2;

	  // Constructing rational with default values
	  public Rational() {
	    this(new BigInteger("0"), new BigInteger("1"));
	  }

	  //constructing rational with specific values 
	  public Rational(BigInteger numerator, BigInteger denominator) {
	          BigInteger gcd = gcd(numerator, denominator);
	          bint1 = (denominator.compareTo(BigInteger.valueOf(0)) > 0 ? BigInteger.valueOf(1) : new BigInteger("-1")).multiply(numerator.divide(gcd));
	          bint2 = denominator.divide(gcd);

	  }

	  // finding the greatest common denominators of both numbers
	  private static BigInteger gcd(BigInteger n, BigInteger d) {
		  BigInteger bint1 = n.abs();
		  BigInteger bint2 = d.abs();
		  BigInteger gcd = BigInteger.valueOf(1);
			for (BigInteger k = BigInteger.valueOf(1); k.compareTo(bint1) <= 0 && k.compareTo(bint2) <= 0; k = k.add(BigInteger.valueOf(1))) {
				if (bint1.remainder(k).compareTo(BigInteger.valueOf(0)) == 0 && bint2.remainder(k).compareTo(BigInteger.valueOf(0)) == 0)
				gcd = k;
	    }

	    return gcd;
	  }

	  // returning numerator
	  public BigInteger getNumerator() {
	    return bint1;
	  }

	  //returning denominator 
	  public BigInteger getDenominator() {
	    return bint2;
	  }

	  //Creating add function for rational
	  public Rational add(Rational secondRational) {
	    BigInteger n = (bint1.multiply(secondRational.getDenominator())).add(bint2.multiply(secondRational.getNumerator()));
	    BigInteger d = bint1.multiply(secondRational.getDenominator());
	    return new Rational(n, d);
	  }

	  //Creating subtract function for rational
	  public Rational subtract(Rational secondRational) {
	    BigInteger n = bint1.multiply(secondRational.getDenominator()).subtract(bint2.multiply(secondRational.getNumerator()));
	    BigInteger d = bint1.multiply(secondRational.getDenominator());
	    return new Rational(n, d);
	  }

	  //Creating multiplication function for rational
	  public Rational multiply(Rational secondRational) {
	    BigInteger n = bint1.multiply(secondRational.getNumerator());
	    BigInteger d = bint2.multiply(secondRational.getDenominator());
	    return new Rational(n, d);
	  }

	  //Creating division function for rational
	  public Rational divide(Rational secondRational) {
	    BigInteger n = bint1.multiply(secondRational.getDenominator());
	    BigInteger d = bint2.multiply(secondRational.getNumerator());
	    return new Rational(n, d);
	  }

	  @Override //to string method
	  public String toString() {
	    if (bint2.intValue() == 1)
	      return bint1 + "";
	    else
	      return bint1 + "/" + bint2;
	  }

	  @Override //check equality method
	  public boolean equals(Object other) {
	    if (((this.subtract((Rational)(other))).getNumerator()).intValue() == 0)
	      return true;
	    else
	      return false;
	  }

	  @Override 
	  public int intValue() {
	    return (int)doubleValue();
	  }

	  @Override 
	  public float floatValue() {
	    return (float)doubleValue();
	  }

	  @Override  
	  public double doubleValue() {
	    return bint1.doubleValue()/(bint2.doubleValue());
	  }

	  @Override 
	  public long longValue() {
	    return (long)doubleValue();
	  }

	  @Override 
	  public int compareTo(Rational o) {
	    if ((this.subtract(o).getNumerator()).compareTo(BigInteger.valueOf(0)) == 0)
	      return 0;
	    else if ((this.subtract(o).getNumerator()).compareTo(BigInteger.valueOf(0)) > 0)
	      return 1;
	    else
	      return -1;
	  }
	}
