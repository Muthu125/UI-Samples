
package uiexamples.msf.com.uiandroidexamples.adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Poly {

    private List<Point> points = new ArrayList<Point>();
    private String colors;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The points
     */
    public List<Point> getPoints() {
        return points;
    }

    /**
     * 
     * @param points
     *     The points
     */
    public void setPoints(List<Point> points) {
        this.points = points;
    }

    /**
     * 
     * @return
     *     The colors
     */
    public String getColors() {
        return colors;
    }

    /**
     * 
     * @param colors
     *     The Colors
     */
    public void setColors(String colors) {
        this.colors = colors;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
