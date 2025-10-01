package com.jlgp.pedidosya.entities;

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
    private String name;
	private String email;

    public String toCSVFormat() {
        return String.format("%d,%s,%s", id, name, email);
    }

    public static String getAttributes() {
        return String.format("%s,%s,%s", "id", "name", "email");
    }
}
