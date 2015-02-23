package MainPackage;

import java.util.Scanner;

public class Main {

	double yearsToWork;
	double investmentRate;
	int yearsRetired;
	double paybackRate;
	double requiredIncome;
	float monthlySSI;
	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("\n\t** Lab 1 **\n");

		Main a = new Main();
		a.getVars();
		a.input.close();
		a.PV(a.paybackRate / 12, a.yearsRetired * 12, a.requiredIncome - a.monthlySSI, 0);
	}

	public void getVars() {

		System.out.print("Please enter expected years to work: ");
		yearsToWork = input.nextDouble();

		System.out
				.print("Please enter expected annual interest (return) on investment: ");
		investmentRate = input.nextDouble() / 100;
		while (investmentRate > .20) {
			System.out.print("Annual investment interest must be in the range of 0-20%. Please enter a new value: ");
			investmentRate = input.nextDouble() / 100;
		}

		System.out.print("Please enter how many years you expect to draw savings (years retired): ");
		yearsRetired = input.nextInt();

		System.out.print("Please enter expected annual payback interest (return) on investment: ");
		paybackRate = input.nextDouble() / 100;
		while (paybackRate > .03) {
			System.out.print("Annual payback interest must be in the range of 0-3%. Please enter a new value: ");
			paybackRate = input.nextDouble() / 100;
		}

		System.out.print("Please enter required income: ");
		requiredIncome = input.nextFloat();

		System.out.print("Please enter expected SSI income: ");
		monthlySSI = input.nextFloat();

		printUserInput();
	}

	private void printUserInput() {

		System.out.println("\nINVESTMENT:");
		System.out.println("Work for " + yearsToWork + " years");
		System.out.println("Expected interest at " + investmentRate + "%");

		System.out.println("\nPAYBACK:");
		System.out.println("Retire for " + yearsRetired + " years");
		System.out.println("Payback interest at " + paybackRate + "%");
		System.out.println("Required income is $" + requiredIncome);
		System.out.println("Monthly SSI is $" + monthlySSI);
	}

	public void PV(double rate, double nper, double pmt, double fv) {
		double savings = -(((1 - Math.pow(1 + rate, nper)) / rate) * pmt * (1 + rate * fv) - fv) / Math.pow(1 + rate, nper);
		System.out.printf("\tYou need $%.2f saved.", savings);

		PMT(savings, investmentRate / 12, yearsToWork * 12);
	}

	public void PMT(double savings, double rate, double nper) {
		double payment = -(savings * rate) / (1 - Math.pow(1 + rate, nper));
		System.out.printf("\n\tYou must save $%.2f each month.", payment);
	}

}
