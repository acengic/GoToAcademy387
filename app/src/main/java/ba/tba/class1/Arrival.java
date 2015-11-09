package ba.tba.class1;

import java.util.HashMap;
import java.util.Map;

public class Arrival {

    private String line_name;
    private String operator_name;
    private Double eta;
    private String price;
    private String arrival;
    private String referentTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getLineName() {
        return line_name;
    }
    public void setLineName(String line_name) {
        this.line_name = line_name;
    }

    public String getOperatorName() {
        return operator_name;
    }
    public void setOperatorName(String operator_name) {
        this.operator_name = operator_name;
    }

    public Double getEta() {
        return eta;
    }
    public void setEta(Double eta) {
        this.eta = eta;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getArrival() {
        return arrival;
    }
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getReferentTime() {
        return referentTime;
    }
    public void setReferentTime(String referentTime) {
        this.referentTime = referentTime;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}