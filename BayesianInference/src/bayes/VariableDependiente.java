package bayes;

//Santiago Caroprese, Luis Manuel Peñaranda y Juan Carlos Suárez
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Clase que representa una variable dependiente. Hereda de Variable.
public class VariableDependiente extends Variable{
    private List<Variable> dependencias;  //Lista con las variables de las que depende
    private Map<List<String>,Map<String,Float>> probabilidades; //Mapa con las probabilidades de cada uno de los valores de la variable para cada una de las combinaciones de las variables de las que depende. La llave es una lista de Strings que indican los valores que toman las variables de las que depende en cada caso. El valor es un mapa en donda la llave es el valor que toma la variable y el valor es la probabilidad asociada.
    
    public VariableDependiente(String nombre, List<Variable> dep, List<String> valores) {
        super(nombre, valores);
        dependencias = dep;
    }

    public List<Variable> getDependencias() {
        return dependencias;
    }

    public void setDependencias(List<Variable> dependencias) {
        this.dependencias = dependencias;
    }

    public Map<List<String>, Map<String, Float>> getProbabilidades() {
        return probabilidades;
    }

    public void setProbabilidades(Map<List<String>, Map<String, Float>> probabilidades) {
        this.probabilidades = probabilidades;
    }
    
}
