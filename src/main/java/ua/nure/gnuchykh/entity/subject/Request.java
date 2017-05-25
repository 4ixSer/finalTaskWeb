package ua.nure.gnuchykh.entity.subject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.users.User;


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
    private User ownerRequest;

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
    private Car characteristics�ar;

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

    public Car getCharacteristics�ak() {
        return characteristics�ar;
    }

    public void setCharacteristics�ar(Car characteristics�ar) {
        this.characteristics�ar = characteristics�ar;
    }

    public Integer getNamberRequest() {
        return namberRequest;
    }

    public void setNamberRequest(Integer namberRequest) {
        this.namberRequest = namberRequest;
    }

    public User getOwnerRequest() {
        return ownerRequest;
    }

    public void setOwnerRequest(User ownerRequest) {
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

    public Request(User ownerRequest, LocalDateTime dataRequest, LocalDateTime dataDeparture, Car characteristics�ak,
            Status status, String note) {
        super();
        this.ownerRequest = ownerRequest;
        this.dataRequest = dataRequest;
        this.dataDeparture = dataDeparture;
        this.characteristics�ar = characteristics�ak;
        this.status = status;
        this.note = note;
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
                + dataRequest + ", dataDeparture=" + dataDeparture + ", characteristics�ak=" + characteristics�ar
                + ", status=" + status + ", note=" + note + "]";
    }

}
