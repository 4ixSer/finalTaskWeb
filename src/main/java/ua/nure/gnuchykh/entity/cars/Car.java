package ua.nure.gnuchykh.entity.cars;

import java.io.Serializable;

/**
 * Класс реализуюший описание поведение машины.
 * @author qny4ix
 *
 */
public class Car implements Serializable {


    /**
     * ID машины для хранения в базе.
     */
    private Integer id;
    /**
     * Номер машины. Уникален в базе данных
     */
    private String namber;

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
     * Исправность машины.
     */
    private Status statusCar;

    /**
     * Коментарии.
     */

    private String comments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamber() {
        return namber;
    }

    public void setNamber(String namber) {
        this.namber = namber;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }



    public Double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(Double enginePower) {
        this.enginePower = enginePower;
    }

    public Car() {

    }

    public Status getStatusCar() {
        return statusCar;
    }

    public void setStatusCar(Status statusCar) {
        this.statusCar = statusCar;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", namber=" + namber + ", type=" + type + ", carryingCar=" + carryingCar
                + ", amountCar=" + amountCar + ", enginePower=" + enginePower + ", statusCar=" + statusCar
                + ", comments=" + comments + "]";
    }

    public Car(String namber, TYPE type, Double carryingCar, Double amountCar, Double enginePower, Status statusCar,
            String comments) {
        super();
        this.namber = namber;
        this.type = type;
        this.carryingCar = carryingCar;
        this.amountCar = amountCar;
        this.enginePower = enginePower;
        this.statusCar = statusCar;
        this.comments = comments;
    }


}
