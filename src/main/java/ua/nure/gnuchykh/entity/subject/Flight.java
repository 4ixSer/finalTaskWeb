package ua.nure.gnuchykh.entity.subject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.users.User;


/**
 * ��������� ����������� ��������� �����.
 *
 * @author qny4ix
 *
 */
public class Flight implements Serializable{
    /**
     * ����� �����. �� �� � ID.
     */
    private Integer namberFlight;

    /**
     * ���� �������� �����.
     */
    private LocalDateTime date;

    /**
     * ������ �����.
     */
    private Status status;

    /**
     * ��� ��������� � ������ �����.
     */
    private User driver;

    /**
     * ��� ����������� ������ ����.
     */
    private User dispatcher;

    /**
     * C���� �� ������ � �����.
     */
    private Car car;

    /**
     * ����������.
     */
    private String note;

    public Flight(LocalDateTime date, Status status, User driver, User dispatcher, Car car, String note) {
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



    public User getDriver() {
        return driver;
    }



    public void setDriver(User driver) {
        this.driver = driver;
    }



    public User getDispatcher() {
        return dispatcher;
    }



    public void setDispatcher(User dispatcher) {
        this.dispatcher = dispatcher;
    }



    public Car getCar() {
        return car;
    }



    public void setCar(Car car) {
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


    //TODO ���������� ����� ��� ��������� ���������� ������� � ����������� �� �����
    //TODO ������� � �������� ����� ����
    public String toStringDate() {

        return date.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d hh:mm:ss"));
    }



    public static LocalDateTime fromValueData(String time){
        //TODO ����� ����� �������� � ����������� �� �����
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
        return LocalDateTime.parse(time, formatter);
    }




}
