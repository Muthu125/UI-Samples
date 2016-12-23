package uiexamples.msf.com.uiandroidexamples.adapters;

/**
 * Created by muthuv on 12/23/2016.
 */

public class RollOverDataClass implements Comparable<RollOverDataClass> {

    private boolean isSectionHeader = false;
    private String headerName;

    public boolean isSectionHeader() {
        return isSectionHeader;
    }

    public void setSectionHeader(boolean sectionHeader) {
        isSectionHeader = sectionHeader;
    }



    @Override
    public int compareTo(RollOverDataClass rollOverDataClass) {
        return 0;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }
}

