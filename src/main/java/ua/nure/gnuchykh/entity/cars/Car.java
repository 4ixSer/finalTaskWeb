package ua.nure.gnuchykh.entity.cars;

import java.io.Serializable;

/**
 * Entity for car.
 *
 * @author qny4ix
 *
 */
public class Car implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1632407514475212381L;

    /**
     * ID of the machine for storage in the database.
     */
    private Integer id;
    /**
     * Number of the car. Unique in the database
     */
    private String namber;

    /**
     * Type of machine.
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
     * Serviceability of the machine.
     */
    private Status statusCar;

    /**
     * Comments.
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

    public void setNamber(final String namber) {
        this.namber = namber;
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

    public String getComments() {
        return comments;
    }

    public void setComments(final String comments) {
        this.comments = comments;
    }



    public Double getEnginePower() {
        return enginePower;
    }

    public void setEnginePower(final Double enginePower) {
        this.enginePower = enginePower;
    }

    public Car() {

    }

    public Status getStatusCar() {
        return statusCar;
    }

    public void setStatusCar(final Status statusCar) {
        this.statusCar = statusCar;
    }

    @Override
    public String toString() {
        return "Car [id=" + id + ", namber=" + namber + ", type=" + type + ", carryingCar=" + carryingCar
                + ", amountCar=" + amountCar + ", enginePower=" + enginePower + ", statusCar=" + statusCar
                + ", comments=" + comments + "]";
    }

    public Car(final String namber, final TYPE type, final Double carryingCar, final Double amountCar, final Double enginePower, final Status statusCar,
            final String comments) {
        super();
        this.namber = namber;
        this.type = type;
        this.carryingCar = carryingCar;
        this.amountCar = amountCar;
        this.enginePower = enginePower;
        this.statusCar = statusCar;
        this.comments = comments;
    }

    public Car(final TYPE type, final Double carryingCar, final Double amountCar, final Double enginePower,
            final String comments) {
        super();
        this.type = type;
        this.carryingCar = carryingCar;
        this.amountCar = amountCar;
        this.enginePower = enginePower;
        this.comments = comments;
    }


}
