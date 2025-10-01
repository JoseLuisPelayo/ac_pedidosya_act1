package com.jlgp.pedidosya.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de utilidades para la serialización y deserialización de objetos en Java.
 *
 * <p>
 * Proporciona métodos estáticos para guardar objetos individuales o listas de objetos
 * en un archivo, así como para recuperar dichos objetos desde un archivo.
 * </p>
 *
 * @version 1.0 - 2025-09-25
 * @author jose Luis G.P
 */

public abstract class SerializationUtil {

    /**
     * Serializa un único objeto en el archivo especificado.
     *
     * @param file         archivo de destino donde se escribirá el objeto.
     * @param obj          objeto a serializar, debe implementar {@link Serializable}.
     * @param <T>          tipo del objeto serializable.
     * @throws IOException si ocurre un error de escritura en el archivo.
     */
    public static <T extends Serializable> void serializeObjects(File file, T obj) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(obj);
        }
    }

    /**
     * Serializa una lista de objetos en el archivo especificado.
     *
     * @param file         archivo de destino donde se escribirán los objetos.
     * @param obj          lista de objetos a serializar, cada uno debe implementar {@link Serializable}.
     * @param <T>          tipo de los objetos serializables.
     * @throws IOException si ocurre un error de escritura en el archivo.
     */
    public static <T extends Serializable> void serializeObjects(File file, List<T> obj) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Object object : obj) {
                oos.writeObject(object);
            }
        }
    }

    /**
     * Deserializa todos los objetos de un archivo a una lista del tipo especificado.
     *
     * <p>
     * Lee el archivo hasta alcanzar el final (EOF), añadiendo cada objeto deserializado
     * a la lista resultante.
     * </p>
     *
     * @param file                    archivo de origen desde el que se leerán los objetos.
     * @param classType               clase del tipo de objeto esperado (usada para hacer casting seguro).
     * @param <T>                     tipo de los objetos serializables.
     * @return                        lista de objetos deserializados.
     * @throws IOException            si ocurre un error de lectura en el archivo.
     * @throws ClassNotFoundException si no se encuentra la clase de los objetos almacenados.
     */
    public static <T extends Serializable> List<T> deserializeObjects(File file, Class<T> classType) throws IOException, ClassNotFoundException {
        List<T> aux = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                aux.add(classType.cast(ois.readObject()));
            }
        } catch (EOFException e) {
            return aux;
        }
    }

}
