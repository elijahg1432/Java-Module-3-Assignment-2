//code edited by Elijah Gonzalez. User enters some rationals and then it does some math with them.

//we must import our BIGINTEGER and scanner.
import java.math.BigInteger;
import java.util.Scanner;

//our main class where everything is inside of.
public class Rational extends Number implements Comparable<Rational> {

    // the assignment asks for the variables of numerator and denominator to be BigIntegers. We give them a default value of "1", the user will change them later anyways.
    private BigInteger numerator = new BigInteger("1");
    private BigInteger denominator = new BigInteger("1");

    //we need a rational with default properties, we also must call it later in the code. so we do that here.
    public Rational() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    //we make our rational with the users input.
    public Rational(BigInteger numerator, BigInteger denominator) {

        // to do rational stuff, we need the greater common denominator, also known as GCD, so we do that here.
        BigInteger gcd = numerator.gcd(denominator);
        if (denominator.signum() < 0) { 

            //we dont allow negative denominators. so they get negated.
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        //we have to divdide the numerator and denominator by the GCD.
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.abs().divide(gcd);
    }

    //return numerator.
    public BigInteger getNumerator() {
        return numerator;
    }

    //return denominator
    public BigInteger getDenominator() {
        return denominator;
    }

    //we must add our rational numbers now
    public Rational add(Rational secondRational) {

        //we gotta cross multiply them. now!
        BigInteger n1 = this.numerator;
        BigInteger d1 = this.denominator;
        BigInteger n2 = secondRational.getNumerator();
        BigInteger d2 = secondRational.getDenominator();

        //we do math to get number
        BigInteger n = n1.multiply(d2).add(n2.multiply(d1));
        BigInteger d = d1.multiply(d2);

        //we give user their new rational
        return new Rational(n, d);
    }

    //we must subtract.
    public Rational subtract(Rational secondRational) {

        //same as before, but instead of adding them later... we subtract.
        BigInteger n1 = this.numerator;
        BigInteger d1 = this.denominator;
        BigInteger n2 = secondRational.getNumerator();
        BigInteger d2 = secondRational.getDenominator();

        //we do that here. we have to multiply still
        BigInteger n = n1.multiply(d2).subtract(n2.multiply(d1));
        BigInteger d = d1.multiply(d2);

        //return the new rational number
        return new Rational(n, d);
    }  

    // WE MUST... multiply
    public Rational multiply(Rational secondRational) {

        //you should be used to this part by now.
        BigInteger n1 = this.numerator;
        BigInteger d1 = this.denominator;
        BigInteger n2 = secondRational.getNumerator();
        BigInteger d2 = secondRational.getDenominator();

        //simple cross multiplication
        BigInteger n = n1.multiply(n2);
        BigInteger d = d1.multiply(d2);

        //return the new rational number
        return new Rational(n, d);  
    }

    //divide, we must, we simply do.
    public Rational divide(Rational secondRational) {

        //last one. 
        BigInteger n1 = this.numerator;
        BigInteger d1 = this.denominator;
        BigInteger n2 = secondRational.getNumerator();
        BigInteger d2 = secondRational.getDenominator();

        //we multiply to divide. i know its weird but trust
        BigInteger n = n1.multiply(d2);
        BigInteger d = d1.multiply(n2);

        //give the user their rational only because we are feeling nice today.
        return new Rational(n, d);
    }

    //we make rational double so we can use it later.
    public double toDouble() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override
    public String toString() {
        
        //we make it so that the rational can be a string so we can display it later.
        if (denominator == BigInteger.ONE)
            return numerator + ""; //if the denominator is 1, just return the numerator
        else
            return numerator + "/" + denominator; //otherwise it is a fraction
    }

    @Override
    public boolean equals(Object other) {
        //check if the difference between two rationals is zero
        if ((this.subtract((Rational)(other))).getNumerator() == BigInteger.ZERO)
            return true;
        else
            return false;
    }

    @Override
    public int intValue() {
        //convert rational to integer we simply must
        return (int) doubleValue();
    }

    @Override
    public float floatValue() {
        //more conversions but this time as a float.
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        // this time we convert it to an int.
        int newNumerator = numerator.intValue();
        int newDenominator = denominator.intValue();
        return newNumerator * 1.0 / newDenominator;
    }

    @Override
    public long longValue() {
        //we must make it a long.
        return (long) doubleValue();
    }

    @Override
    public int compareTo(Rational o) {
        //this is how we tell what kind of number the rational is
        BigInteger difference = this.subtract(o).getNumerator();

        if (difference.compareTo(BigInteger.ZERO) > 0)
            return 1; //if the rational is greater than zero it is positive
        else if (difference.compareTo(BigInteger.ZERO) < 0)
            return -1; //if the rational is less than zero it is negative
        else
            return 0; //otherwise it is 0 if it is neither. what else could it be? 
    }
    
    // this is the main class, where we ask the user for their inputs and display the iformation.
    public class Main {
        public static void main(String[] args) {
            try (Scanner scanner = new Scanner(System.in)) {

                //we ask for the first rational number, we assign the first number to numerator, the second to denominator, and with that we have our first pair.
                System.out.print("Enter the first rational number: ");
                BigInteger numerator1 = scanner.nextBigInteger();
                BigInteger denominator1 = scanner.nextBigInteger();
                Rational r1 = new Rational(numerator1, denominator1);
   
                //we do the same with the second, except instead of assigning it as n1, d1, and r1 (the users first numerator denominator and rational) its assigned to n1 d2 and r1
                System.out.print("Enter the second rational number: ");
                BigInteger numerator2 = scanner.nextBigInteger();
                BigInteger denominator2 = scanner.nextBigInteger();
                Rational r2 = new Rational(numerator2, denominator2);
   
                //we do one last set of math so the user can see what we did.
                Rational sum = r1.add(r2);
                Rational difference = r1.subtract(r2);
                Rational product = r1.multiply(r2);
                Rational quotient = r1.divide(r2);
   
                //we then tell the user what their outputs were. only because we were feeling nice.
                System.out.println(r1 + " + " + r2 + " = " + sum);
                System.out.println(r1 + " – " + r2 + " = " + difference);
                System.out.println(r1 + " * " + r2 + " = " + product);
                System.out.println(r1 + " / " + r2 + " = " + quotient);
                System.out.println(r2 + " is " + r2.toDouble());

                //we then close the scanner because it would be rude to leave it open.
                scanner.close();
            }
        }
    }
}
