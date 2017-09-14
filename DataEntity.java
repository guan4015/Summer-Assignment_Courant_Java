// Edited by Xiao Guan, August 14, 2017

// This file implements the member function of class DataEntity which is used as the row
// data in the data table

// Modifications:
// Adding getDate, setDate, getOpen, setOpen, getHigh, setHigh, getLow, setLow member functions 
// Adding getClose, setClose, getVolume, setVolume, getAdjClose, setAdjClose member functions

import java.util.Date;

public class DataEntity {
	
	// Declare the private member data
    private Date date;
    private double open;
    private double high;
    private double low;
    private double close;
    private long volume;
    private double adjClose;
	
    // Implement the public methods
	public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(double adjClose) {
        this.adjClose = adjClose;
    }


}
