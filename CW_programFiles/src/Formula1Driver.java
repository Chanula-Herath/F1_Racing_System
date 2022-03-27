import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formula1Driver extends Driver{

    private int position;
    private int startPos;
    private int numOf_firstPostions = 0;
    private int numOf_secondPostions = 0;
    private int numOf_thirdPostions = 0;

    private int numOf_points = 0;
    private int numOf_races = 0;

    private Date raceDate;

    public Formula1Driver(){}

    //Getters
    public int getNumOf_races() {return numOf_races;}
    public int getPosition() {return position;}
    public int getNumOf_points() {return numOf_points;}
    public int getNumOf_firstPostions() {return numOf_firstPostions;}
    public int getNumOf_secondPostions() {return numOf_secondPostions;}
    public int getNumOf_thirdPostions() {return numOf_thirdPostions;}


    //Setters
    public void setNumOf_races(int numOf_races) {this.numOf_races = numOf_races;}
    public void setPosition(int position) {this.position = position;}
    public void setNumOf_points(int numOf_points) {this.numOf_points = numOf_points;}
    public void setNumOf_firstPostions(int numOf_firstPostions) {this.numOf_firstPostions = numOf_firstPostions;}
    public void setNumOf_secondPostions(int numOf_secondPostions) {this.numOf_secondPostions = numOf_secondPostions;}
    public void setNumOf_thirdPostions(int numOf_thirdPostions) {this.numOf_thirdPostions = numOf_thirdPostions;}

    //Add positions
    public void addPositions(){
        switch (position){
            case 1:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_firstPostions(getNumOf_firstPostions()+1);
                setNumOf_points(getNumOf_points()+25);
                break;
            case 2:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_secondPostions(getNumOf_secondPostions()+1);
                setNumOf_points(getNumOf_points()+18);
                break;
            case 3:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_thirdPostions(getNumOf_thirdPostions()+1);
                setNumOf_points(getNumOf_points()+15);
                break;
            case 4:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_points(getNumOf_points()+12);
                break;
            case 5:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_points(getNumOf_points()+10);
                break;
            case 6:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_points(getNumOf_points()+8);
                break;
            case 7:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_points(getNumOf_points()+6);
                break;
            case 8:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_points(getNumOf_points()+4);
                break;
            case 9:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_points(getNumOf_points()+2);
                break;
            case 10:
                setNumOf_races(getNumOf_races()+1);
                setNumOf_points(getNumOf_points()+1);
                break;
        }
    }

    //probabilistic positions
    public void givePosition(){

    }

    public Date getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(String raceDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        this.raceDate = sdf.parse(raceDate);
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }
}
