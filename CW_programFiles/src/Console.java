import java.util.Scanner;

public class Console {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String myOption = "Z";

        while (!myOption.equals("X")) {
            System.out.println();
            System.out.println("--------Formula 1 Championship--------");
            System.out.println("Menu");
            System.out.println("--------------------------------------");
            System.out.println("A - Add New Driver");
            System.out.println("B - Delete Driver");
            System.out.println("C - Change Driver");
            System.out.println("D - Display Driver Statistics");
            System.out.println("E - Display Driver Table");
            System.out.println("F - Add Race");
            System.out.println("G - Save File");
            System.out.println("H - Get Saved File");
            System.out.println("X - Exit");
            System.out.println("--------------------------------------");

            System.out.print("Choose Action : ");
            myOption = sc.next();
            System.out.println("--------------------------------------");


            Formula1ChampionshipManager manager = new Formula1ChampionshipManager();

            switch (myOption) {
                case "A":
                    System.out.print("Choose a team : ");
                    String dTeam = sc.next();

                    System.out.print("Driver full name : ");
                    String dName = sc.next();

                    System.out.print("Driver's country : ");
                    String dCountry = sc.next();

                    Formula1Driver formula1Driver = new Formula1Driver();

                    formula1Driver.setDriverTeam(dTeam);
                    formula1Driver.setDriverCountry(dCountry);
                    formula1Driver.setDriverName(dName);


                    break;
            }
        }
        System.out.println("End..........");
    }
}


