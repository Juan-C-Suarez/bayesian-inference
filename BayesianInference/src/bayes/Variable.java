/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bayes;

//Santiago Caroprese, Luis Manuel Peñaranda y Juan Carlos Suárez
import java.util.List;
import java.util.Map;

//Clase abstracta que representa una variable
public abstract class Variable {
    private String nombre;        //Nombre de la variable
    private List<String> valores; //Lista de valores que puede tomar la variable

    public Variable(String nombre, List<String> valores) {
        this.nombre = nombre;
        this.valores = valores;
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getValores() {
        return valores;
    }
}
