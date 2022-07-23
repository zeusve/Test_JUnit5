package org.marvi.junit5app.ejemplos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.marvi.junit5app.ejemplos.exceptions.DinerosuficienteException;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Cuenta {
    private String persona;
    private BigDecimal saldo;

    public void debito (BigDecimal saldo){
        BigDecimal nuevoSaldo = this.saldo.subtract(saldo);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new DinerosuficienteException("Dinero Insuficiente");
        }
        this.saldo = nuevoSaldo;
    }

    public void credito (BigDecimal saldo){
        this.saldo = this.saldo.add(saldo);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Cuenta cuenta)){
            return false;
        }
        if (this.persona == null || this.saldo == null){
            return false;
        }
        return this.persona.equals(cuenta.getPersona()) && this.saldo.equals(cuenta.getSaldo());
    }
}
