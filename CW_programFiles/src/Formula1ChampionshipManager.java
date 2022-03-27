import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Formula1ChampionshipManager implements ChampionshipManager {

    final static public int maxDrivers = 10;
    Scanner sc = new Scanner(System.in);
    Formula1Driver formula1Driver = new Formula1Driver();


    //Console
    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);
        String myOption = "Z";
        ArrayList<Formula1Driver> driverTeam = new ArrayList<>();



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
            System.out.println("I - Run GUI");
            System.out.println("X - Exit");
            System.out.println("--------------------------------------");

            System.out.print("Choose Action : ");
            myOption = sc.next();
            System.out.println("--------------------------------------");


            Formula1ChampionshipManager manager = new Formula1ChampionshipManager();

            switch (myOption) {
                case "A":
                    manager.addNewDriver(driverTeam);
                    break;

                case "B":
                    manager.removeDriver(driverTeam);
                    break;

                case "C":
                    manager.changeDriver(driverTeam);
                    break;

                case "D":
                    manager.displayStats(driverTeam);
                    break;

                case "E":
                    manager.formula1DriverTable(driverTeam);
                    break;

                case "F":
                    manager.addRace(driverTeam);
                    break;

                case "G":
                    manager.saveFile(driverTeam);
                    break;

                case "H":
                    manager.recoverFile(driverTeam);
                    break;

                case "I":
                    new guiDriverTable(driverTeam);
            }
        }
        System.out.println("End..........");
    }

    static class guiDriverTable extends JFrame{
        JTable table;


        protected JButton sortButtonASC = new JButton("Sort in ascending order");
        protected JButton posSortButton = new JButton("Sort(No. 1st positions)");
        protected JButton raceButton = new JButton("New Race");
        protected JButton createRaceButton = new JButton("Create Race");
        protected JButton displayRaceButton = new JButton("Display Races");
        protected JButton searchButton = new JButton("Search...");

        guiDriverTable(ArrayList<Formula1Driver> driverList){
            //https://stackoverflow.com/questions/26973577/create-jtable-with-arraylist/26973908
            setSize(800, 480);
            setLocationRelativeTo(null);
            //setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
            JPanel panelButton = new JPanel();
            panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
            panelButton.add(sortButtonASC);
            panelButton.add(posSortButton);
            panelButton.add(raceButton);
            panelButton.add(createRaceButton);
            panelButton.add(displayRaceButton);
            panelButton.add(searchButton);
            add(panelButton);

            ArrayList<Formula1Driver> sortedList = driverList;
            int n = driverList.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (sortedList.get(j).getNumOf_points() <= sortedList.get(j + 1).getNumOf_points()) {
                        if (sortedList.get(j).getNumOf_points() == sortedList.get(j + 1).getNumOf_points()) {
                            if (sortedList.get(j).getNumOf_firstPostions() > sortedList.get(j + 1).getNumOf_firstPostions()) {
                                Formula1Driver temp = sortedList.get(j);
                                sortedList.set(j, sortedList.get(j + 1));
                                sortedList.set((j), temp);
                            }
                        } else {
                            Formula1Driver temp = sortedList.get(j);
                            sortedList.set(j, sortedList.get(j + 1));
                            sortedList.set((j + 1), temp);
                        }
                    }
                }
            }

            String[] columnName = {"Driver Name","Country","Team","Number of Races","No. 1st Positions"
                    ,"Total Points"};
            DefaultTableModel model = new DefaultTableModel(columnName,0);
            Object[] rowData = new Object[6];
            for(int i = 0; i<sortedList.size();i++){
                rowData[0] = sortedList.get(i).getDriverName();
                rowData[1] = sortedList.get(i).getDriverCountry();
                rowData[2] = sortedList.get(i).getDriverTeam();
                rowData[3] = sortedList.get(i).getNumOf_races();
                rowData[4] = sortedList.get(i).getNumOf_firstPostions();
                rowData[5] = sortedList.get(i).getNumOf_points();
                model.addRow(rowData);
            }

            model.fireTableDataChanged();
            table = new JTable(model);
            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
            table.setRowSorter(sorter);
            table.setPreferredSize(new Dimension(400, 360));
            add(new JScrollPane(table));

            sortButtonASC.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //https://stackoverflow.com/questions/28823670/how-to-sort-jtable-in-shortest-way
                    List<RowSorter.SortKey> sortASC = new ArrayList<>(25);
                    sortASC.add(new RowSorter.SortKey(5, SortOrder.ASCENDING));
                    sorter.setSortKeys(sortASC);
                }
            });

            posSortButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    List<RowSorter.SortKey> sortASC = new ArrayList<>(25);
                    sortASC.add(new RowSorter.SortKey(4, SortOrder.DESCENDING));
                    sorter.setSortKeys(sortASC);
                }
            });

            raceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Formula1Driver formula1Driver = new Formula1Driver();

                    //https://stackoverflow.com/questions/42532025/how-do-i-generate-any-random-date-between-01-01-2016-to-01-01-2017-using-java/42532418
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(2020, 0, 0);
                    calendar.set(Calendar.DAY_OF_YEAR, new Random().nextInt() % 365);
                    try {
                        formula1Driver.setRaceDate(new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime()));
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }

                    ArrayList<Integer> randomPos = new ArrayList<Integer>();

                    for (int i = 1; i < (driverList.size() + 1); i++) {
                        randomPos.add(i);
                    }
                    Collections.shuffle(randomPos);
                    for (int i = 0; i < driverList.size(); i++) {
                        driverList.get(i).setPosition(randomPos.get(i));
                        driverList.get(i).addPositions();
                        model.fireTableDataChanged();
                    }

                    JFrame textFrame = new JFrame("New Race");

                    Object[] rowData = new Object[2];
                    String[] columnName = {"Driver Name","Poistion"};

                    DefaultTableModel model = new DefaultTableModel(columnName,0);
                    for(int i = 0; i<sortedList.size();i++){
                        rowData[0] = sortedList.get(i).getDriverName();
                        rowData[1] = sortedList.get(i).getPosition();
                        model.addRow(rowData);
                    }

                    model.fireTableDataChanged();
                    JTable table = new JTable(model);

                    JScrollPane textPane = new JScrollPane(table);
                    textFrame.add(textPane);
                    textFrame.setSize(300, 380);
                    textFrame.setVisible(true);
                    validate(); //refresh the table

                    new guiDriverTable(driverList);


                }
            });

            createRaceButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    ArrayList<Integer> startPos = new ArrayList<Integer>();

                    for (int i = 1; i < (driverList.size() + 1); i++) {
                        startPos.add(i);
                    }

                    Collections.shuffle(startPos);
                    for (int i = 0; i < driverList.size(); i++) {
                        driverList.get(i).setStartPos(startPos.get(i));
                        //driverList.get(i).givePosition();
                        //driverList.get(i).addPositions();
                        model.fireTableDataChanged();
                    }

                    JFrame textFrame = new JFrame("New Race");

                    Object[] rowData = new Object[2];
                    String[] columnName = {"Starting Position","Driver Name","Driver position"};

                    DefaultTableModel model = new DefaultTableModel(columnName,0);
                    for(int i = 0; i<sortedList.size();i++){
                        rowData[0] = sortedList.get(i).getStartPos();
                        rowData[1] = sortedList.get(i).getDriverName();
                        //rowData[2] = sortedList.get(i).getPosition();
                        model.addRow(rowData);
                    }

                    model.fireTableDataChanged();
                    JTable table = new JTable(model);

                    JScrollPane textPane = new JScrollPane(table);
                    textFrame.add(textPane);
                    textFrame.setSize(300, 380);
                    textFrame.setVisible(true);
                    validate(); //refresh the table
                }
            });

            validate(); //refresh the table
        }
    }

    @Override
    public void addNewDriver(ArrayList<Formula1Driver> driverList) {
        boolean isAvailable = false;
        //Add a driver
        System.out.print("Choose a team : ");
        String dTeam = sc.next();

        System.out.print("Driver's name : ");
        String dName = sc.next();

        System.out.print("Driver's country : ");
        String dCountry = sc.next();

        formula1Driver.setDriverName(dName);
        formula1Driver.setDriverTeam(dTeam);
        formula1Driver.setDriverCountry(dCountry);


        if (driverList.size() < maxDrivers) {
            if (!driverList.isEmpty()) {
                for (Formula1Driver driver : driverList) {
                    if (driver.getDriverTeam().equals(dTeam)) {
                        System.out.println("This team already has a driver!");
                        isAvailable = false;
                        break;
                    } else
                        isAvailable = true;
                }
            } else
                isAvailable = true;

            if (isAvailable) {
                driverList.add(formula1Driver);
                System.out.println(formula1Driver.getDriverName() + " is added into team " + formula1Driver.getDriverTeam() + ".");
            }
        } else {
            System.out.println("Driver list is full, cannot add more drivers!");
        }
    }

    @Override
    public void removeDriver(ArrayList<Formula1Driver> driverList) {
        if (driverList.size() != 0) {
            System.out.print("Enter driver's name : ");
            String DName = sc.next();
            int index = 0;
            boolean isExist = false;
            for (Formula1Driver driver : driverList) {
                if (driver.getDriverName().equals(DName)) {
                    driverList.remove(index);
                    System.out.println("Driver " + DName + " has been removed from the list!");
                    isExist = true;
                    break;
                }
                index++;
            }
            if (!isExist)
                System.out.println("This driver does not exist!");

        } else {
            System.out.println("Driver list is empty!");
        }
    }

    @Override
    public void changeDriver(ArrayList<Formula1Driver> driverList) {
        if (driverList.size() != 0) {
            System.out.print("Enter team : ");
            String team = sc.next();
            for (Formula1Driver driver : driverList) {
                if (driver.getDriverTeam().equals(team)) {
                    System.out.print("Enter new driver's name: ");
                    String newName = sc.next();
                    System.out.print("Enter driver's country : ");
                    String newCountry = sc.next();
                    driver.setDriverName(newName);
                    driver.setDriverCountry(newCountry);
                    System.out.println("Team " + team + " has changed their driver to " + newName + "!");
                    break;
                } else {
                    System.out.println("This team does not exist!");
                }
            }
        } else {
            System.out.println("Driver list is empty!");
        }
    }

    @Override
    public void displayStats(ArrayList<Formula1Driver> driverList) {
        if (driverList.size() != 0) {
            System.out.print("Enter driver's name : ");
            String Name = sc.next();
            System.out.println();
            for (Formula1Driver driver : driverList) {
                if (driver.getDriverName().equals(Name)) {
                    System.out.println("Name : " + Name
                            + "\n   Name                        : " + driver.getDriverName()
                            + "\n   Country                     : " + driver.getDriverCountry()
                            + "\n   Team                        : " + driver.getDriverTeam()
                            + "\n   Number of races             : " + driver.getNumOf_races()
                            + "\n   Number of 1st positions     : " + driver.getNumOf_firstPostions()
                            + "\n   Number of 2nd positions     : " + driver.getNumOf_secondPostions()
                            + "\n   Number of 3rd positions     : " + driver.getNumOf_thirdPostions()
                            + "\n   Number of points            : " + driver.getNumOf_points());
                    System.out.println();
                    break;
                } else {
                    System.out.println("Driver does not exist!");
                }
            }
        } else {
            System.out.println("No drivers in the list!");
        }
    }

    @Override
    public void addRace(ArrayList<Formula1Driver> driverList) throws ParseException {
        if (driverList.size() != 0) {
            System.out.print("Enter race date : ");
            String date = sc.next();
            formula1Driver.setRaceDate(date);
            System.out.println();
            for (Formula1Driver driver : driverList) {
                System.out.println("Driver : " + driver.getDriverName());
                System.out.print("Enter driver position : ");
                int position = sc.nextInt();
                System.out.println();
                driver.setPosition(position);
                driver.addPositions();
            }
            System.out.println("End of driver list...");
        } else {
            System.out.println("No participants in the race!");
        }
    }

    @Override
    public void formula1DriverTable(ArrayList<Formula1Driver> driverList) {
        ArrayList<Formula1Driver> sortedList = driverList;
        int n = driverList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
               if (sortedList.get(j).getNumOf_points() <= sortedList.get(j + 1).getNumOf_points()) {
                   if (sortedList.get(j).getNumOf_points() == sortedList.get(j + 1).getNumOf_points()) {
                       if (sortedList.get(j).getNumOf_firstPostions() > sortedList.get(j + 1).getNumOf_firstPostions()) {
                           Formula1Driver temp = sortedList.get(j);
                           sortedList.set(j, sortedList.get(j + 1));
                           sortedList.set((j), temp);
                       }
                   }else{
                       Formula1Driver temp = sortedList.get(j);
                       sortedList.set(j, sortedList.get(j + 1));
                       sortedList.set((j + 1), temp);
                   }

               }
            }
        }
            System.out.println("Name\t\t|Country\t|Team\t|Points");
        System.out.println("--------------------------------------");
        for (Formula1Driver driver : sortedList) {
            System.out.println(driver.getDriverName() + "\t|" + driver.getDriverCountry() + "\t|" + driver.getDriverTeam() +
                    "\t|" + driver.getNumOf_points());
        }
    }

    @Override
    public void saveFile(ArrayList<Formula1Driver> driverList) throws IOException {//https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
        //Referred (https://www.codegrepper.com/code-examples/typescript/java+write+arraylist+of+objects+to+file)
        File driverDetails = new File("D:\\OOP_java\\CW\\DriverDetails.ser");
        if( (driverDetails.createNewFile())) { //Creating the file
            System.out.println("Files created successfully....");
        }
        try {
            FileOutputStream fdos = new FileOutputStream("DriverDetails.ser");
            ObjectOutputStream doos = new ObjectOutputStream(fdos);
            doos.writeObject(driverList);

            doos.flush();
            doos.close();

            System.out.println("Data saved successfully...");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR!!!");
        }

    }

   //https://samderlust.com/dev-blog/java/write-read-arraylist-object-file-java
    @Override
    public void recoverFile(ArrayList<Formula1Driver> driverList) throws IOException{
        try{
            FileInputStream drivefileIN = new FileInputStream("DriverDetails.ser");
            ObjectInputStream driverObjectIN = new ObjectInputStream(drivefileIN);

            ArrayList<Formula1Driver> driverFile = (ArrayList<Formula1Driver>) driverObjectIN.readObject();
            driverObjectIN.close();


            for (Formula1Driver driverObject : driverFile){
                driverList.add(driverObject);
            }

            System.out.println("Files recovered succesfullyy...");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
