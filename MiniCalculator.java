package wtf;
import java.util.Scanner;

public class MiniCalculator {
    public static void main(String[] args) {
		Scanner vput = new Scanner(System.in); 
		int fnum,snum,adn;
	
		double ans; 
		System.out.println("Hello,This simple calculator is created by Victor Alagwu Using the Switch Function");
		System.out.print("Enter the first digit: ");
		fnum =vput.nextInt(); 
		
		System.out.println("Choose any of the following operators ");
		System.out.println("Addition = 1"); 
		System.out.println("Subtraction = 2");
		System.out.println("Multplication = 3");
		System.out.println("Division = 4");
		
		System.out.print("Enter the sign you want: ");
		
		adn =vput.nextInt();
		System.out.println(" ");
		System.out.print("Enter the second number: ");
		snum =vput.nextInt();
		
		switch(adn){
		case 1: 
			ans = fnum + snum;
			System.out.println("The addition is : " + ans);
		break;
		case 2: 
			ans = fnum - snum;
			System.out.println("The subtraction is : " + ans);
		break;
		case 3: 
			ans = fnum * snum;
			System.out.println("The mutliplication is : " + ans);
		break;
		case 4: 
			ans = fnum / snum;
			System.out.println("The division : " + ans);
		break;
		
		default:
			System.out.println("Sorry your values are incorrect");
		}
	}

}
