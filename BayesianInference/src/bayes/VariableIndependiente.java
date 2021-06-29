package bayes;

import java.util.HashMap;
//Santiago Caroprese, Luis Manuel Peñaranda y Juan Carlos Suárez
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Clase que representa una variable independiente. Hereda de Variable.
public class VariableIndependiente extends Variable {
    private Map<String, Float> probabilidades;  //Mapa con la probabilidad de cada uno de los valores que puede tomar la variable
    
    public VariableIndependiente(String nombre, List<String> valores) {
        super(nombre, valores);
        probabilidades = new HashMap<>();
    }

    public Map<String, Float> getProbabilidades() {
        return probabilidades;
    }

    public void setProbabilidades(Map<String, Float> probabilidades) {
        this.probabilidades = probabilidades;
    }
}
