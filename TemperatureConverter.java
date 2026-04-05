import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Temperature value: ");
        double value = sc.nextDouble();

        System.out.print("Enter unit (C/F/K): ");
        char unit = Character.toUpperCase(sc.next().charAt(0));

        if (unit == 'C') {
            double f = (value * 9/5) + 32;
            double k = value + 273.15;

            System.out.println("Celsius: " + value + " °C");
            System.out.println("Fahrenheit: " + f + " °F");
            System.out.println("Kelvin: " + k + " K");
        }
        else if (unit == 'F') {
            double c = (value - 32) * 5/9;
            double k = c + 273.15;

            System.out.println("Fahrenheit: " + value + " °F");
            System.out.println("Celsius: " + c + " °C");
            System.out.println("Kelvin: " + k + " K");
        }
        else if (unit == 'K') {
            if (value < 0) {
                System.out.println("Invalid input! Kelvin cannot be negative.");
                return;
            }

            double c = value - 273.15;
            double f = (c * 9/5) + 32;

            System.out.println("Kelvin: " + value + " K");
            System.out.println("Celsius: " + c + " °C");
            System.out.println("Fahrenheit: " + f + " °F");
        }
        else {
            System.out.println("Invalid unit! Use C, F, or K.");
        }

        sc.close();
    }
}