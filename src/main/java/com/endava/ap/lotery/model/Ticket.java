package com.endava.ap.lotery.model;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "number1")
    private Integer number1;

    @Column(name = "number2")
    private Integer number2;

    @Column(name = "number3")
    private Integer number3;

    @Column(name = "number4")
    private Integer number4;

    @Column(name = "number5")
    private Integer number5;

    @Column(name = "number6")
    private Integer number6;

    @Column(name = "XPRY_DATE")
    private final static LocalDateTime XPRY_DATE = LocalDateTime.now().with(DayOfWeek.SATURDAY).toLocalDate().atStartOfDay();

    @Column(name = "BUY_TIME")
    private final static LocalDateTime BUY_TIME = LocalDateTime.now();

    @ManyToOne
    private Participant owner;

    public Ticket() {
    }

    public static LocalDateTime getXpryDate() {
        return XPRY_DATE;
    }

    public static LocalDateTime getBuyTime() {
        return BUY_TIME;
    }

    public boolean isValid() {
        return LocalDateTime.now().compareTo(XPRY_DATE) < 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber6() {
        return number6;
    }

    public void setNumber6(Integer number6) {
        this.number6 = number6;
    }

    public Integer getNumber5() {
        return number5;
    }

    public void setNumber5(Integer number5) {
        this.number5 = number5;
    }

    public Integer getNumber4() {
        return number4;
    }

    public void setNumber4(Integer number4) {
        this.number4 = number4;
    }

    public Integer getNumber3() {
        return number3;
    }

    public void setNumber3(Integer number3) {
        this.number3 = number3;
    }

    public Integer getNumber2() {
        return number2;
    }

    public void setNumber2(Integer number2) {
        this.number2 = number2;
    }

    public Integer getNumber1() {
        return number1;
    }

    public void setNumber1(Integer number1) {
        this.number1 = number1;
    }

    public Participant getOwner() {
        return owner;
    }

    public void setOwner(Participant owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return Objects.equal(id, ticket.id) &&
                Objects.equal(number1, ticket.number1) &&
                Objects.equal(number2, ticket.number2) &&
                Objects.equal(number3, ticket.number3) &&
                Objects.equal(number4, ticket.number4) &&
                Objects.equal(number5, ticket.number5) &&
                Objects.equal(number6, ticket.number6) &&
                Objects.equal(owner, ticket.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, number1, number2, number3, number4, number5, number6, owner);
    }
}
