
package uiexamples.msf.com.uiandroidexamples.adapters;

import java.util.HashMap;
import java.util.Map;

public class Point {

    private Integer x;
    private Integer y;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The x
     */
    public Integer getX() {
        return x;
    }

    /**
     * 
     * @param x
     *     The X
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * 
     * @return
     *     The y
     */
    public Integer getY() {
        return y;
    }

    /**
     * 
     * @param y
     *     The Y
     */
    public void setY(Integer y) {
        this.y = y;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
