package dao;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import negocio.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import negocio.Usuario;

public class Archivo {

    private String path;

    public Archivo(String path)
    {
        this.path = path;
    }

    public void guardar(List listado)
    {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            
            objectOutputStream.writeObject(listado);
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public List recuperar()
    {
        List listado = null;

        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            listado = (List) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listado;

    }
}
