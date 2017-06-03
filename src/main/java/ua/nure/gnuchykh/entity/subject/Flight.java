package ua.nure.gnuchykh.entity.subject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Сушьность реализуюшая поведение рейса.
 *
 * @author qny4ix
 *
 */
public class Flight implements Serializable {
    /**
     * Номер рейса. Он же и ID.
     */
    private Integer namberFlight;

    /**
     * Дате создания рейса.
     */
    private LocalDateTime date;

    /**
     * Статус рейса.
     */
    private Status status;

    /**
     * Кто находится в данном рейсе.
     */
    private Integer driver;

    /**
     * Кто расматривал данный рейс.
     */
    private Integer dispatcher;

    /**
     * Cылка на машину в рейсе.
     */
    private Integer car;

    /**
     * примечание.
     */
    private String note;

    public Flight(LocalDateTime date, Status status, Integer driver, Integer dispatcher, Integer car, String note) {
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

    public void setNamberFlight(Integer namberFlight) {
        this.namberFlight = namberFlight;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getDriver() {
        return driver;
    }

    public void setDriver(Integer driver) {
        this.driver = driver;
    }

    public Integer getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(Integer dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Flight [namberFlight=" + namberFlight + ", date=" + date + ", status=" + status + ", driver=" + driver
                + ", dispatcher=" + dispatcher + ", car=" + car + ", note=" + note + "]";
    }

    public String toStringDate() {

        return date.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d hh:mm:ss"));
    }

    public static LocalDateTime fromValueData(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
        return LocalDateTime.parse(time, formatter);
    }

}
