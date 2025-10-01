package com.jlgp.pedidosya.entities;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private Long clienteId;
    private String producto;
    private int cantidad;

    public String toCsvFormat() {
        return String.format("%d,%d,%s,%d", id, clienteId, producto, cantidad);
    }

    public static String getAttributes() {
        return String.format("%s,%s,%s,%s", "id", "clienteId", "producto", "cantidad");
    }
}
