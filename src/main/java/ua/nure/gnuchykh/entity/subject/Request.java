package ua.nure.gnuchykh.entity.subject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ua.nure.gnuchykh.entity.cars.TYPE;

/**
 * The entity of the Request.
 *
 * @author qny4ix
 *
 */
public class Request implements Serializable {

    private static final long serialVersionUID = -2442250265543236498L;

    /**
     * Number. It is unique and the ID for the database.
     */
    private Integer namberRequest;

    /**
     * The owner of the Request.
     */
    private Integer ownerRequest;

    /**
     * Request date.
     */
    private LocalDateTime dateRequest;

    /**
     * Date of departure.
     */
    private LocalDateTime dateDeparture;

    /**
     * Characteristics of the proposed machine. Type of machine.
     */
    private TYPE type;

    /**
     * Load capacity of the machine.
     */
    private Double carryingCar;

    /**
     * The volume of the machine.
     */
    private Double amountCar;

    /**
     * Engine power.
     */
    private Double enginePower;

    /**
     * Status.
     */
    private Status status;

    /**
     * Note.
     */
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(final String note) {
        this.note = note;
    }

    public Integer getNamberRequest() {
        return namberRequest;
    }

    public void setNamberRequest(final Integer namberRequest) {
        this.namberRequest = namberRequest;
    }

    public Integer getOwnerRequest() {
        return ownerRequest;
    }

    public void setOwnerRequest(final Integer ownerRequest) {
        this.ownerRequest = ownerRequest;
    }

    public LocalDateTime getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(final LocalDateTime dateRequest) {

        this.dateRequest = dateRequest;
    }

    public LocalDateTime getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(final LocalDateTime dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(final Status status) {
        this.status = status;
    }

    /**
     * The method of converting a string in a certain format to a
     * DateTimeFormatter. "yyyy'-'MM'-'d HH:mm:ss"
     */
    public String toStringDateRequest() {

        return dateRequest.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d HH:mm:ss"));
    }

    /**
     * The method of converting a string in a certain format to a
     * DateTimeFormatter. "yyyy'-'MM'-'d HH:mm:ss"
     */
    public String toStringDateDeparture() {

        return dateDeparture.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d HH:mm:ss"));
    }

    /**
     * The method of converting a date into a string of a staged format.
     *
     */
    public static LocalDateTime fromValueDateRequest(final String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * The method of converting a date into a string of a staged format.
     *
     */
    public static LocalDateTime fromValueDateDeparture(final String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
        return LocalDateTime.parse(time, formatter);

    }

    public Request() {
        super();
    }

    @Override
    public String toString() {
        return "Request [namberRequest=" + namberRequest + ", ownerRequest=" + ownerRequest + ", dateRequest="
                + dateRequest + ", dateDeparture=" + dateDeparture + ", type=" + type + ", carryingCar=" + carryingCar
                + ", amountCar=" + amountCar + ", enginePower=" + enginePower + ", status=" + status + ", note=" + note
                + "]";
    }

    public Request(final Integer ownerRequest, final  LocalDateTime dateRequest, final LocalDateTime dateDeparture, final TYPE type,
            final Double carryingCar, final Double amountCar, final Double enginePower, final Status status, final String note) {
        super();
        this.ownerRequest = ownerRequest;
        this.dateRequest = dateRequest;
        this.dateDeparture = dateDeparture;
        this.type = type;
        this.carryingCar = carryingCar;
        this.amountCar = amountCar;
        this.enginePower = enginePower;
        this.status = status;
        this.note = note;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(final TYPE type) {
        this.type = type;
    }

    public Double getCarryingCar() {
        return carryingCar;
    }

    public void setCarryingCar(final Double carryingCar) {
        this.carryingCar = carryingCar;
    }

    public Double getAmountCar() {
        return amountCar;
    }

    public void setAmountCar(final Double amountCar) {
        this.amountCar = amountCar;
    }

    public Double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(final Double enginePower) {
        this.enginePower = enginePower;
    }

}
