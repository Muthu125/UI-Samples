package com.inqbarna.tablefixheaders.adapters;

import java.util.Calendar;

/**
 * Created by karthickm on 7/15/2016.
 */
public interface ITimeAndSalesModelPresenter {
    /**
     * Request to retrieve list of TimeAndSales data for selected time interval
     *
     * @param mCounterId ID to retrieve the data for that particular symbol
     * @param fromTime   fromTime
     * @param toTime     toTime
     */
    void sendRequest(String mCounterId, String fromTime, String toTime, boolean isTimeChange, boolean isFT);


    Calendar getCurrentTime();

    /**
     * Opens the TimePicker Dialog on click of 'To' and sends the request for Time and Sales
     *
     * @param mCounterId ID to retrieve the data for that particular symbol
     */
    void toTimeDialog(String mCounterId);

    /**
     * Opens the TimePicker Dialog on click of 'From' and sends the request for Time and Sales
     *
     * @param mCounterId ID to retrieve the data for that particular symbol
     */
    void fromTimeDialog(String mCounterId);

    /**
     * Initializes Default 'from' and 'to' time and sends initial request
     *
     * @param mCounterId ID to retrieve the data for that particular symbol
     */
    void initialiseFromAndToTimeAndSendRequest(String mCounterId);
    void initialiseFromAndToTimeAndSendRequestFT(String mCounterId);

    void sendRequestOnLoadMore(String counterID, String fromTime, String toTime);

    void sendRequestOnLoadMoreFT(String counterID, String fromTime, String toTime);

    int getPageQuery();
    int getMaxCount();
}
