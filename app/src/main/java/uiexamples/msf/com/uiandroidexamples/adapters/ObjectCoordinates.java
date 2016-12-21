
package uiexamples.msf.com.uiandroidexamples.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ObjectCoordinates {

    private List<Poly> poly = new ArrayList<Poly>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The poly
     */
    public List<Poly> getPoly() {
        return poly;
    }

    /**
     * 
     * @param poly
     *     The Poly
     */
    public void setPoly(List<Poly> poly) {
        this.poly = poly;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
