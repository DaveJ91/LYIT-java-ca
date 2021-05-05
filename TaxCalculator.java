// Name: David O'Meara
// Student Number: L00162952

/* This a program which calculates the tax, taxable income, tax payable and effective tax rate for
    an individual based on their income and life circumstances */

import java.util.Scanner;

public class TaxCalculator {

    static double taxFreeThreshold;
    static double taxRate;


    public static void main (String args[]){

        Scanner keyboardIn = new Scanner(System.in);

        // Prompt user to enter income and store it as a double
        System.out.println("Please enter your annual income:");
        double income = keyboardIn.nextDouble();


        // https://stackoverflow.com/questions/4816661/how-can-i-assign-the-euro-or-the-pound-symbol-to-a-variable
        // Source: https://stackoverflow.com/questions/34518061/how-to-format-a-double-into-thousand-separated-and-2-decimals-using-java-util-fo
        System.out.println("You entered \u20ac" + (String.format("%,.2f", income)));

        // If the user earns less than 5000 they pay no tax
        if (income < 5000.0){
            System.out.println("Lucky you!");
            System.out.println("As your income is under \u20ac5,000 you do not have to pay any income tax");
        }

        else {
            // Prompt the user to select a category
            System.out.println("Please select the category you belong to:");
            System.out.println("(Please enter a number)");
            System.out.println("[1] Student part-time");
            System.out.println("[2] Single full-time");
            System.out.println("[3] Married full-time");
            int taxCategory = keyboardIn.nextInt();
            

            switch(taxCategory) {

                case 1:
                    // PT Students have a 10% tax rate on their income
                    System.out.println("You selected: [1] Student part-time");
                    taxRate = .1;
                    taxFreeThreshold = 0;
                    
                    break;
                case 2: 
                    // Single FT workers pay 30% tax on their income above 10000
                    System.out.println("You selected: [2] Single full-time");
                    taxRate = .3;
                    taxFreeThreshold = 10000;
                    break;
                case 3: 
                    // Married FT workers tax free threshold depends on whether their spouse is working
                    // The tax rate for them is 25% on their income above the threshold
                    taxRate = .25;
                    System.out.println("You selected: [3] Married full-time");
                    // Prompt the user to find out if their spouse is working
                    System.out.println("Is your spouse working?");
                    System.out.println("(Please enter Y or N)");

                    // Source: https://www.geeksforgeeks.org/gfact-51-java-scanner-nextchar/
                    char workingSpouse = keyboardIn.next().charAt(0);

                    // If their spouse is working their threshold is 20,000. If not its 30,000
                    if (workingSpouse == 'Y' || workingSpouse == 'y'){
                        System.out.println("You specified that you have a working spouse");
                        taxFreeThreshold = 20000;
                    } else {
                        System.out.println("You specified that your spouse is not working");
                        taxFreeThreshold = 30000;
                    }
                    break;

                default:
                    // End the program if they don't enter one of the 3 categories
                    System.out.println("You did not select a valid category. Please run the program again");
                    // Source: https://stackoverflow.com/questions/22452930/terminating-a-java-program
                    System.exit(0);

            }
            double taxableIncome = income - taxFreeThreshold;    
            double taxPayable = taxableIncome * taxRate;
            double takeHomePay = income - taxPayable;
            double effectiveTaxRate = taxPayable/income * 100;

            System.out.println("----------");
            System.out.println("Annual Income: \u20ac" + (String.format("%,.2f", income)));
            System.out.println("Taxable Income: \u20ac" + (String.format("%,.2f", taxableIncome)));
            System.out.println("Total tax payable: \u20ac" + (String.format("%,.2f", taxPayable)));
            System.out.println("Take Home Pay: \u20ac" + (String.format("%,.2f", takeHomePay)));
            System.out.println("Your actual tax paid percentage is: " + (String.format("%,.2f", effectiveTaxRate))+ "%");
            System.out.println("----------");
        
    
            
        }
        

        

    }
}