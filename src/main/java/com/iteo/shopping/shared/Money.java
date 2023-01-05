package com.iteo.shopping.shared;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Embeddable
public class Money {

    public static final Money ZERO = new Money(BigDecimal.ZERO);
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private BigDecimal value;

    public Money() {
    }

    public Money(BigDecimal value) {
        this.value = value;
    }

    public Money add(Money other) {
        return new Money(value.add(other.value));
    }

    public Money multiply(Integer other) {
        return new Money(value.multiply(BigDecimal.valueOf(other)));
    }
    public Money multiply(Money other) {
        return new Money(value.multiply(other.value));
    }
    public Money multiply(BigDecimal other) {
        return new Money(value.multiply(other));
    }

    public Money subtract(Money other) {
        return new Money(value.subtract(other.value));
    }

    public Money percentage(BigDecimal percentage) {
        return new Money(value.multiply(percentage).divide(ONE_HUNDRED, RoundingMode.HALF_UP));
    }

    public Money percentage(Integer percentage) {
        return new Money(value.multiply(BigDecimal.valueOf(percentage)).divide(ONE_HUNDRED, RoundingMode.HALF_UP));
    }

    public BigDecimal toBigDecimal() {
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return value.setScale(2, RoundingMode.HALF_UP).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value.equals(money.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
