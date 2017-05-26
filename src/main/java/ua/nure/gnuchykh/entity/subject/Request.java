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
    private LocalDateTime dataRequest;

    /**
     * ���� ��������������� ������.
     */
    private LocalDateTime dataDeparture;

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

    public LocalDateTime getDataRequest() {
        return dataRequest;
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

    public void setDataRequest(LocalDateTime dataRequest) {

        this.dataRequest = dataRequest;
    }

    public LocalDateTime getDataDeparture() {
        return dataDeparture;
    }

    public void setDataDeparture(LocalDateTime dataDeparture) {
        this.dataDeparture = dataDeparture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //TODO ���������� ����� ��� ��������� ���������� ������� � ����������� �� �����
    //TODO ������� ������ � ������ � ��������� �����
     public String toStringDataRequest() {

         return dataRequest.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d hh:mm:ss"));
     }

     public String toStringDataDeparture() {

         return dataDeparture.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d hh:mm:ss"));
     }


     public static LocalDateTime fromValueDataRequest(String time){
         //TODO ����� ����� �������� � ����������� �� �����
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
         return LocalDateTime.parse(time, formatter);
     }

     public static LocalDateTime fromValueDataDeparture(String time){
         //TODO ����� ����� �������� � ����������� �� �����
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
         return LocalDateTime.parse(time, formatter);

     }

    public Request() {
        super();
    }

    @Override
    public String toString() {
        return "Request [namberRequest=" + namberRequest + ", ownerRequest=" + ownerRequest + ", dataRequest="
                + dataRequest + ", dataDeparture=" + dataDeparture + ", type=" + type + ", carryingCar=" + carryingCar
                + ", amountCar=" + amountCar + ", enginePower=" + enginePower + ", status=" + status + ", note=" + note
                + "]";
    }

    public Request(Integer ownerRequest, LocalDateTime dataRequest, LocalDateTime dataDeparture, TYPE type,
            Double carryingCar, Double amountCar, Double enginePower, Status status, String note) {
        super();
        this.ownerRequest = ownerRequest;
        this.dataRequest = dataRequest;
        this.dataDeparture = dataDeparture;
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
