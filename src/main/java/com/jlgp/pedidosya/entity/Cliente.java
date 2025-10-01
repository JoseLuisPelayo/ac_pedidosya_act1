package com.jlgp.pedidosya.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String nombre;
	private String email;

    public String toCSVFormat() {
        return String.format("%d,%s,%s", id, nombre, email);
    }

    public static String getAttributes() {
        return String.format("%s,%s,%s", "id", "nombre", "email");
    }
}
