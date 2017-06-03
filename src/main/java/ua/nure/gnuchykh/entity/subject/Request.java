package ua.nure.gnuchykh.entity.subject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ua.nure.gnuchykh.entity.cars.TYPE;


/**
 * ��������� ����������� �������� ������.
 *
 * @author qny4ix
 *
 */
public class Request implements Serializable {
    /**
     * ����� ������. ���������� �� �� � ID ��� ���� ������.
     */
    private Integer namberRequest;

    /**
     * �������� �����.
     */
    private Integer ownerRequest;

    /**
     * ���� ������ ������.
     */
    private LocalDateTime dateRequest;

    /**
     * ���� ��������������� ������.
     */
    private LocalDateTime dateDeparture;

    /**
     * �������������� �������������� ������.
     */
    /**
     * ��� ������.
     */
    private TYPE type;

    /**
     * ���������������� ������.
     */
    private Double carryingCar;

    /**
     * ����� ������.
     */
    private Double amountCar;

    /**
     * ��������� ����������
     */
    private Double enginePower;

    /**
     * ������
     */
    private Status status;

    /**
     * ����������.
     */
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNamberRequest() {
        return namberRequest;
    }

    public void setNamberRequest(Integer namberRequest) {
        this.namberRequest = namberRequest;
    }

    public Integer getOwnerRequest() {
        return ownerRequest;
    }

    public void setOwnerRequest(Integer ownerRequest) {
        this.ownerRequest = ownerRequest;
    }

    public LocalDateTime getDateRequest() {
        return dateRequest;
    }
    // TODO ���� ��� ��� ��������
    /*
     * LocalDateTime curDateTime = LocalDateTime.now(); LocalDateTime
     * curDateFuche = LocalDateTime.parse("2017-05-18T10:53:15");
     *
     * System.out.println("������ = "+curDateTime.format(DateTimeFormatter.
     * ofPattern("yyyy'-'MM'-'d hh:mm:ss")));
     *
     * System.out.println("������� = "+curDateFuche.format(DateTimeFormatter.
     * ofPattern("yyyy'-'MM'-'d hh:mm:ss")));
     *
     */

    public void setDateRequest(LocalDateTime dateRequest) {

        this.dateRequest = dateRequest;
    }

    public LocalDateTime getDateDeparture() {
        return dateDeparture;
    }

    public void setDateDeparture(LocalDateTime dateDeparture) {
        this.dateDeparture = dateDeparture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

     public String toStringDateRequest() {

         return dateRequest.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d hh:mm:ss"));
     }

     public String toStringDateDeparture() {

         return dateDeparture.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d hh:mm:ss"));
     }


     public static LocalDateTime fromValueDateRequest(String time){
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
         return LocalDateTime.parse(time, formatter);
     }

     public static LocalDateTime fromValueDateDeparture(String time){
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

    public Request(Integer ownerRequest, LocalDateTime dateRequest, LocalDateTime dateDeparture, TYPE type,
            Double carryingCar, Double amountCar, Double enginePower, Status status, String note) {
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

    public void setType(TYPE type) {
        this.type = type;
    }

    public Double getCarryingCar() {
        return carryingCar;
    }

    public void setCarryingCar(Double carryingCar) {
        this.carryingCar = carryingCar;
    }

    public Double getAmountCar() {
        return amountCar;
    }

    public void setAmountCar(Double amountCar) {
        this.amountCar = amountCar;
    }

    public Double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Double enginePower) {
        this.enginePower = enginePower;
    }



}
