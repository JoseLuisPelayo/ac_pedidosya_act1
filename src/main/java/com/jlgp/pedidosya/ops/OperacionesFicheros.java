package com.jlgp.pedidosya.ops;

import com.jlgp.pedidosya.dto.Datos;
import com.jlgp.pedidosya.entity.Cliente;
import com.jlgp.pedidosya.entity.Pedido;
import com.jlgp.pedidosya.utils.SerializationUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OperacionesFicheros {

    private static final File dataFile = new File("src/main/resources/datos.obj");
    private static final File clienteCsv = new File("src/main/resources/clientes.csv");
    private static final File pedidosCsv = new File("src/main/resources/pedidos.csv");

    public void crearDatos(List<Cliente> clientes, List<Pedido> pedidos) {
        clientes.add(new Cliente(1, "María López", "maria@mail.com"));
        clientes.add(new Cliente(2, "Juan García", "juan@mail.com"));
        clientes.add(new Cliente(3, "Lucía Fernández", "lucia@mail.com"));

        pedidos.add(new Pedido(101, 1L, "Paella", 2));
        pedidos.add(new Pedido(102, 1L, "Tortilla de patatas", 1));
        pedidos.add(new Pedido(103, 2L, "Gazpacho", 3));
        pedidos.add(new Pedido(104, 3L, "Croquetas de jamón", 4));
        pedidos.add(new Pedido(105, 3L, "Chocolate", 2));
    }

    public void serializarDatos(List<Cliente> clientes, List<Pedido> pedidos) throws IOException {
        SerializationUtil.serializeObjects(dataFile, new Datos(clientes, pedidos));
    }

    public Datos deserializarDatos() {
        List<Datos> datos = null;
        try {
            datos = SerializationUtil.deserializeObjects(dataFile, Datos.class);
            return datos.getFirst();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void clientsToCsv(List<Cliente> list) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(clienteCsv))) {
            pw.println(Cliente.getAttributes());
            list.forEach(c -> {
                pw.println(c.toCSVFormat());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void ordersToCsv(List<Pedido> list) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(pedidosCsv))) {
            pw.println(Pedido.getAttributes());
            list.forEach(p -> {
                pw.println(p.toCsvFormat());
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> csvToClients() {
        String line = null;
        List<Cliente> aux = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(clienteCsv))) {
            String[] attributes = br.readLine().split(",");
            HashMap<String, String> cliente = new HashMap<>();
            while (br.ready()) {
                String[] values = br.readLine().split(",");
                for (int i = 0; i < attributes.length; i++) {
                    cliente.put(attributes[i], values[i]);
                }
                aux.add(new Cliente(
                        Long.parseLong(cliente.get("id")),
                        cliente.get("name"),
                        cliente.get("email")
                ));
            }

            return aux;
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }

    public List<Pedido> csvToOrders() {
        String line = null;
        List<Pedido> aux = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pedidosCsv))) {
            String[] attributes = br.readLine().split(",");
            HashMap<String, String> pedido = new HashMap<>();
            while (br.ready()) {
                String[] values = br.readLine().split(",");
                for (int i = 0; i < attributes.length; i++) {
                    pedido.put(attributes[i], values[i]);
                }
                aux.add(new Pedido(
                        Long.parseLong(pedido.get("id")),
                        Long.parseLong(pedido.get("clienteId")),
                        pedido.get("producto"),
                        Integer.parseInt(pedido.get("cantidad"))
                ));
            }

            return aux;
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }
}
