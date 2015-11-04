package ba.tba.class1;

import java.util.Map;
import java.util.HashMap;

public class Next {

    private String lineName;
    private String operatorName;
    private Double eta;
    private String price;
    private String arrival;
    private String referentTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The lineName
     */
    public String getLineName() {
        return lineName;
    }

    /**
     *
     * @param lineName
     * The line_name
     */
    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    /**
     *
     * @return
     * The operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     *
     * @param operatorName
     * The operator_name
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    /**
     *
     * @return
     * The eta
     */
    public Double getEta() {
        return eta;
    }

    /**
     *
     * @param eta
     * The eta
     */
    public void setEta(Double eta) {
        this.eta = eta;
    }

    /**
     *
     * @return
     * The price
     */
    public String getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The arrival
     */
    public String getArrival() {
        return arrival;
    }

    /**
     *
     * @param arrival
     * The arrival
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    /**
     *
     * @return
     * The referentTime
     */
    public String getReferentTime() {
        return referentTime;
    }

    /**
     *
     * @param referentTime
     * The referent_time
     */
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

