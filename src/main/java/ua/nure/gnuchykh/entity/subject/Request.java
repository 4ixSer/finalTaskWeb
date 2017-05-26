package ua.nure.gnuchykh.entity.subject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ua.nure.gnuchykh.entity.cars.TYPE;


/**
 * СУшьность реализуюшее поведние Заявки.
 *
 * @author qny4ix
 *
 */
public class Request implements Serializable {
    /**
     * номер заявки. уникальный он же и ID для базы данных.
     */
    private Integer namberRequest;

    /**
     * Владелец заяки.
     */
    private Integer ownerRequest;

    /**
     * Дата подачи заявки.
     */
    private LocalDateTime dataRequest;

    /**
     * Дата предпологаемого выезда.
     */
    private LocalDateTime dataDeparture;

    /**
     * Характеристики предполагаемой машыны.
     */
    /**
     * Тип машыны.
     */
    private TYPE type;

    /**
     * Грузоподьемность машыны.
     */
    private Double carryingCar;

    /**
     * Обьем машыны.
     */
    private Double amountCar;

    /**
     * Мошьность двигателся
     */
    private Double enginePower;

    /**
     * Статус
     */
    private Status status;

    /**
     * примечание.
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
    // TODO тиуи чет еше поменять
    /*
     * LocalDateTime curDateTime = LocalDateTime.now(); LocalDateTime
     * curDateFuche = LocalDateTime.parse("2017-05-18T10:53:15");
     *
     * System.out.println("Сейчас = "+curDateTime.format(DateTimeFormatter.
     * ofPattern("yyyy'-'MM'-'d hh:mm:ss")));
     *
     * System.out.println("Прошлое = "+curDateFuche.format(DateTimeFormatter.
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

    //TODO переписать метод для изменений параметров времени в зависимости от языка
    //TODO вынести вместе с рейсов в отдельный класс
     public String toStringDataRequest() {

         return dataRequest.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d hh:mm:ss"));
     }

     public String toStringDataDeparture() {

         return dataDeparture.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'d hh:mm:ss"));
     }


     public static LocalDateTime fromValueDataRequest(String time){
         //TODO ПАтер будет меняться в зависимости от языка
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n");
         return LocalDateTime.parse(time, formatter);
     }

     public static LocalDateTime fromValueDataDeparture(String time){
         //TODO ПАтер будет меняться в зависимости от языка
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
