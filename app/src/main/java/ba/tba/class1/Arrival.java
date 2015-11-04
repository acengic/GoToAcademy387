package ba.tba.class1;

/**
 * Created by jackblack on 10/26/15.
 */
public class Arrival {
//    private String lineName;
//    private String operatorName;
//    private Double eta;
//    private String price;
//    private String arrival;
//    private String referentTime;

    public int eta;
    public String arrivalTime;
    public String operatorName;
    public String lineName;
    public String price;
    public String arrival;
    public String referentTime;

    public Arrival(int minutesToArrival, String arrivalTime, String operator, String line) {
        this.eta = minutesToArrival;
        this.arrivalTime = arrivalTime;
        this.operatorName = operator;
        this.lineName = line;
    }
}

