package ua.nure.gnuchykh.entity.subject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Entity for Flight.
 *
 * @author qny4ix
 *
 */
public class Flight implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7059452558861612583L;

    /**
     * Flight number. He is also ID.
     */
    private Integer namberFlight;

    /**
     * Date of creation of the flight.
     */
    private LocalDateTime date;

    /**
     * Flight Status.
     */
    private Status status;

    /**
     * The driver on this flight.
     */
    private Integer driver;

    /**
     * Who saw this flight.
     */
    private Integer dispatcher;

    /**
     * ID of the car in flight.
     */
    private Integer car;

    /**
     * Note.
     */
    private String note;

    public Flight(final LocalDateTime date,final Status status,final Integer driver,final Integer dispatcher,final Integer car,final String note) {
        super();
        this.date = date;
        this.status = status;
        this.driver = driver;
        this.dispatcher = dispatcher;
        this.car = car;
        this.note = note;
    }

    public Flight() {
        super();
    }

    public Integer getNamberFlight() {
        return namberFlight;
    }

    public void setNamberFlight(final Integer namberFlight) {
        this.namberFlight = namberFlight;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    public Integer getDriver() {
        return driver;
    }

    public void setDriver(final Integer driver) {
        this.driver = driver;
    }

    public Integer getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(final Integer dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(final Integer car) {
        this.car = car;
    }

    public String getNote() {
        return note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Flight [namberFlight=" + namberFlight + ", date=" + date + ", status=" + status + ", driver=" + driver
                + ", dispatcher=" + dispatcher + ", car=" + car + ", note=" + note + "]";
    }
    /**
     * The method of converting a date into a string of a staged format."yyyy'-'MM'-'d HH:mm:ss"
     */
    public String toStringDate() {

        return date.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d HH:mm:ss"));
    }
    /**
     * The method of converting a string in a certain format to a DateTimeFormatter.
     */
    public static LocalDateTime fromValueDate(final String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
        return LocalDateTime.parse(time, formatter);
    }

}
