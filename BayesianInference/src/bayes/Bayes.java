package bayes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bayes {
   //Método que realiza la inferencia bayesiana. Recibe la variable
  //cuyas distribución de probabilidad es calculada, la lista de variables y la
  //lista de evidencias (paralela a la lista de variables). Si alguna
  //posición en la lista de evidencias tiene valor null, esto
  //representa que la variable en esa posición es oculta. Retorna
  //mapa en el que la llave es el valor que toma la variable y el
  //valor es su probabilidad
  public static Map<String, Float> realizarInferencia(Variable var, List<String> evidencias, List<Variable> variables){
    //Se valida que el número de evidencias sea igual al número de variables.
    if(evidencias.size() != variables.size()){
      System.out.println("La lista de variables y la lista de evidencias deben tener el mismo tamaño.");
      return null;
    }
    //Se valida que la variable a ser inferida esté presente en la lista de variables.
    if(!variables.contains(var)){
      System.out.println("La lista de variables debe contener la variable que se está analizando.");
      return null;
    }

    System.out.println("Se va a realizar la inferencia bayesiana considerando las siguientes variables:");
    for(int i = 0; i < variables.size(); i++){
      if(variables.get(i) == var){
        System.out.println(variables.get(i).getNombre() + " cuya distribución de probabilidades se calculará.");
      }
      else if(evidencias.get(i) == null){
        System.out.println(variables.get(i).getNombre() + ", que es oculta");
      }
      else{
        System.out.println(variables.get(i).getNombre() + " con el valor " + evidencias.get(i));
      } 
    }
    System.out.println("");

    Map<String, Float> probValor = new HashMap<String, Float>();  //Mapa en el que se guarda la probabilidad sin normalizar de cada valor de la variable analizada.
    //Se calcula la probabilidad sin normalizar de todas las posibilidades de la variable a ser inferida

    for(String val : var.getValores()){
      List<List<String>> combinaciones = null;
      //Se obtienen todas las posibles combinaciones, dados los valores de la
      //variable que se está analizando y de las variables evidencia
      for(int i = 0; i < evidencias.size(); i++){
        //Si la variable es la que se está infieriendo, se utiliza el valor cuya probabilidad se está obteniendo (val)
        if(variables.get(i) == var){
          combinaciones = combinar(combinaciones, variables.get(i), val);
        }
        //Si la variable no es la que se está infiriendo, se utiliza el valor indicado por las evidencias
        else{
          combinaciones = combinar(combinaciones, variables.get(i), evidencias.get(i));
        }
      }
      float probabilidad = 0;
      //Se recorren todas las combinaciones asociadas al valor cuya probabilidad se está calculando
      for(List<String> combinacion : combinaciones){
        float probComb = 1;
        //Se recorren todas las variables de la combinación
        for(Variable v : variables){
          int i = variables.indexOf(v);
          //Si la variable es independiente, se obtiene su probabilidad y se multiplica.
          if(v instanceof VariableIndependiente){
            VariableIndependiente vi = (VariableIndependiente)v;
            if(vi.getProbabilidades() == null){
              System.out.println("la variable " + vi.getNombre() + " no tiene probabilidades.");
              return null;
            }
            probComb *= vi.getProbabilidades().get(combinacion.get(i)); 
          }
          //Si la variable es dependiente, se obtienen los valores de las variables de
          //las que depende y se obtiene la probabilidad asociada a esos valores de las dependencias,
          //la cual es multiplicada.
          else if(v instanceof VariableDependiente){
            VariableDependiente vd = (VariableDependiente)v;
            List<String> valDep = new ArrayList<String>();
            //Se recorre la lista de variables de las que depende y se obtienen sus valores
            for(Variable dep : vd.getDependencias()){
              int iDep = variables.indexOf(dep);
              if(iDep == -1){
                System.out.println("Falta la dependencia " + dep.getNombre() + " de la variable " + v.getNombre());
                return null;
              }
              valDep.add(combinacion.get(iDep));
            }
            //Se multiplica la probabilidad adecuada, dadas las dependencias
            int iVar = variables.indexOf(v);
            if(vd.getProbabilidades() == null){
              System.out.println("la variable " + v.getNombre() + " no tiene probabilidades.");
              return null;
            }
            probComb = probComb*vd.getProbabilidades().get(valDep).get(combinacion.get(iVar));
          }
        }
        System.out.println("La combinacion " + combinacion + " tiene probabilidad " + probComb);
        //Se suma la probabilidad de la combinación al total de la probabilidad sin normalizar
        probabilidad += probComb;
      }
      //Una vez se sumaron las probabilidades de todas las combinaciones asociadas a ese
      //valor de la variable, se guarda en el mapa esta probabilidad, asociada a su valor
      probValor.put(val, probabilidad);
    }
    
    //Se calcula alfa a partir de las probabilidades sin normalizar
    //(la sumatoria de las probabilidades debe ser 1)
    float total = 0;
    System.out.println("");
    for(String valor : probValor.keySet()){
      System.out.println("Antes de multiplicar por alfa, la probabilidad de " + valor + " es " + probValor.get(valor));
      total += probValor.get(valor);
    }
    float alfa = 1/total;
    System.out.println("");
    System.out.println("Alfa es " + alfa);
    System.out.println("");
    //Se multiplica alfa por las probabilidades
    for(String valor : probValor.keySet()){
      probValor.put(valor, probValor.get(valor)*alfa);
      System.out.println("Para el valor " + valor + " la probabilidad es " + probValor.get(valor));
    }
    return probValor;
  }

  //Método que permite obtener todas las posibles combinaciones de los valores de un grupo de variables.
  //Recibe lista de las combinaciones que se tienen hasta el momento,
  //la variable cuyas combinaciones se van a agregar y el valor que esta debe tomar.
  //Si valor es null, se asume que la variable es oculta
  //La primera vez que se llama, combinaciones es null
  //Retorna la lista de todas las posibles combinaciones luego de considerar la nueva variable
  public static List<List<String>> combinar(List<List<String>> combinaciones, Variable var, String valor){
    //Si combinaciones es null, se crea la lista de combinaciones
    if(combinaciones == null){
      combinaciones = new ArrayList<List<String>>();
      combinaciones.add(new ArrayList<String>());
    }
    List<List<String>> res = new ArrayList<List<String>>();
    List<String> valores = new ArrayList<String>(); 
    //Si es una variable oculta, se consideran todos los valores que puede tomar
    if(valor == null){
      valores = var.getValores();
    } else { //Si es una variable evidencia, solo se considera el valor pasado como parámetro
      valores.add(valor);
    }
    //Para todas las posibles combinaciones que se tienen hasta el momento,
    //se generan todas las posibles combinaciones con los valores de la variable actual
    for(List<String> c : combinaciones){
      for(String val : valores){
        List<String> nuevaC = new ArrayList<String>(c);
        nuevaC.add(val);
        res.add(nuevaC);
      }
    }
    return res;  
  }
}
