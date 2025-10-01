package com.jlgp.pedidosya;

import com.jlgp.pedidosya.dto.Datos;
import com.jlgp.pedidosya.entity.Cliente;
import com.jlgp.pedidosya.entity.Pedido;
import com.jlgp.pedidosya.ops.OperacionesFicheros;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AcPedidosyaAct1Application {
    private static List<Pedido> pedidos;
    private static List<Cliente> clientes;

    static {
        pedidos = new ArrayList<>();
        clientes = new ArrayList<>();
    }

    public static void main(String[] args) {
        try {
            OperacionesFicheros o = new OperacionesFicheros();

            o.crearDatos(clientes, pedidos);
            o.serializarDatos(clientes, pedidos);

            System.out.println("********************** Antes de serializar **************************");
            clientes.forEach(System.out::println);
            pedidos.forEach(System.out::println);

            Datos datos = o.deserializarDatos();
            clientes = datos.clientes();
            pedidos = datos.pedidos();

            System.out.println("********************** Después de serializar **************************");
            clientes.forEach(System.out::println);
            pedidos.forEach(System.out::println);


            System.out.println("********************** Exporto a CSV **************************");
            o.clientsToCsv(clientes);
            o.ordersToCsv(pedidos);

            System.out.println("********************** Importo de CSV **************************");
            clientes = o.csvToClients();
            clientes.forEach(System.out::println);
            pedidos = o.csvToOrders();
            pedidos.forEach(System.out::println);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
