package org.marvi.junit5app.ejemplos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter

public class Banco {
    private String nombre;
    private List<Cuenta> cuentas;

    public Banco(){
        cuentas = new ArrayList<>();
    }
    public void transferir(Cuenta origen, Cuenta destino, BigDecimal saldo) {
        origen.debito(saldo);
        destino.credito(saldo);
    }

    public void addCuenta(Cuenta cuenta){
        cuentas.add(cuenta);
        cuenta.setBanco(this);
    }
}
