package com.jlgp.pedidosya.dto;

import com.jlgp.pedidosya.entities.Cliente;
import com.jlgp.pedidosya.entities.Pedido;

import java.io.Serializable;
import java.util.List;

public record Datos(List<Cliente> clientes, List<Pedido> pedidos) implements Serializable {
}
