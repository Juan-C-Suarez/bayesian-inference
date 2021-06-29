package bayes;

//Authors: Santiago Caroprese, Luis Manuel Peñaranda, Juan Carlos Suárez
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Clase que realiza inferencia bayesiana
public class Main {
  //Función main que crea los datos a ser utilizados y llama la función que realiza la inferencia.
  public static void main(String[] args) {
    test1();
    test2();
  }

  public static void test1() {
    List<String> rainValores = new ArrayList<>();
    rainValores.add("none");
    rainValores.add("light");
    rainValores.add("heavy");
    VariableIndependiente rain = new VariableIndependiente("rain", rainValores);


    Map<String,Float> rainProb = new HashMap<>();
    rainProb.put("none",(float)(0.7));
    rainProb.put("light",(float)(0.2));
    rainProb.put("heavy",(float)(0.1));
    rain.setProbabilidades(rainProb);
    
    
    List<String> maintenanceValores = new ArrayList<>();
    maintenanceValores.add("yes");
    maintenanceValores.add("no");
    List<Variable> maintenanceDep = new ArrayList<>();
    maintenanceDep.add(rain);
    VariableDependiente maintenance = new VariableDependiente("maintenance", maintenanceDep, maintenanceValores);
    
    Map<List<String>,Map<String,Float>> maintenanceProb = new HashMap<>();
    List<String> maintenanceProbKey1 = new ArrayList<>();
    maintenanceProbKey1.add("none");
    Map<String,Float> maintenanceProbValue1 = new HashMap<>();
    maintenanceProbValue1.put("yes",(float)(0.4));
    maintenanceProbValue1.put("no",(float)(0.6));
    maintenanceProb.put(maintenanceProbKey1, maintenanceProbValue1);
    
    List<String> maintenanceProbKey2 = new ArrayList<>();
    maintenanceProbKey2.add("light");
    Map<String,Float> maintenanceProbValue2 = new HashMap<>();
    maintenanceProbValue2.put("yes",(float)(0.2));
    maintenanceProbValue2.put("no",(float)(0.8));
    maintenanceProb.put(maintenanceProbKey2, maintenanceProbValue2);
    
    List<String> maintenanceProbKey3 = new ArrayList<>();
    maintenanceProbKey3.add("heavy");
    Map<String,Float> maintenanceProbValue3 = new HashMap<>();
    maintenanceProbValue3.put("yes",(float)(0.1));
    maintenanceProbValue3.put("no",(float)(0.9));
    maintenanceProb.put(maintenanceProbKey3, maintenanceProbValue3);
    
    maintenance.setProbabilidades(maintenanceProb);

    List<String> trainValores = new ArrayList<>();
    trainValores.add("on time");
    trainValores.add("delayed");
    List<Variable> trainDep = new ArrayList<>();
    trainDep.add(rain);
    trainDep.add(maintenance);
    VariableDependiente train = new VariableDependiente("train",trainDep,trainValores);

    Map<List<String>,Map<String,Float>> trainProb = new HashMap<>();
    List<String> trainProbKey1 = new ArrayList<>();
    trainProbKey1.add("none");
    trainProbKey1.add("yes");
    Map<String,Float> trainProbValue1 = new HashMap<>();
    trainProbValue1.put("on time",(float)(0.8));
    trainProbValue1.put("delayed",(float)(0.2));
    trainProb.put(trainProbKey1, trainProbValue1);

    List<String> trainProbKey2 = new ArrayList<>();
    trainProbKey2.add("none");
    trainProbKey2.add("no");
    Map<String,Float> trainProbValue2 = new HashMap<>();
    trainProbValue2.put("on time",(float)(0.9));
    trainProbValue2.put("delayed",(float)(0.1));
    trainProb.put(trainProbKey2, trainProbValue2);

    List<String> trainProbKey3 = new ArrayList<>();
    trainProbKey3.add("light");
    trainProbKey3.add("yes");
    Map<String,Float> trainProbValue3 = new HashMap<>();
    trainProbValue3.put("on time",(float)(0.6));
    trainProbValue3.put("delayed",(float)(0.4));
    trainProb.put(trainProbKey3, trainProbValue3);

    List<String> trainProbKey4 = new ArrayList<>();
    trainProbKey4.add("light");
    trainProbKey4.add("no");
    Map<String,Float> trainProbValue4 = new HashMap<>();
    trainProbValue4.put("on time",(float)(0.7));
    trainProbValue4.put("delayed",(float)(0.3));
    trainProb.put(trainProbKey4, trainProbValue4);
    
    List<String> trainProbKey5 = new ArrayList<>();
    trainProbKey5.add("heavy");
    trainProbKey5.add("no");
    Map<String,Float> trainProbValue5 = new HashMap<>();
    trainProbValue5.put("on time",(float)(0.4));
    trainProbValue5.put("delayed",(float)(0.6));
    trainProb.put(trainProbKey5, trainProbValue5);
    
    List<String> trainProbKey6 = new ArrayList<>();
    trainProbKey6.add("heavy");
    trainProbKey6.add("yes");
    Map<String,Float> trainProbValue6 = new HashMap<>();
    trainProbValue6.put("on time",(float)(0.5));
    trainProbValue6.put("delayed",(float)(0.5));
    trainProb.put(trainProbKey6, trainProbValue6);

    train.setProbabilidades(trainProb);
    
    List<String> appointmentValores = new ArrayList<>();
    appointmentValores.add("attend");
    appointmentValores.add("miss");
    List<Variable> appointmentDep = new ArrayList<>();
    appointmentDep.add(train);
    VariableDependiente appointment = new VariableDependiente("appointment", appointmentDep, appointmentValores);

    Map<List<String>,Map<String,Float>> appointmentProb = new HashMap<>();
    List<String> appointmentProbKey1 = new ArrayList<>();
    appointmentProbKey1.add("on time");
    Map<String,Float> appointmentProbValue1 = new HashMap<>();
    appointmentProbValue1.put("attend",(float)(0.9));
    appointmentProbValue1.put("miss",(float)(0.1));
    appointmentProb.put(appointmentProbKey1, appointmentProbValue1);

    List<String> appointmentProbKey2 = new ArrayList<>();
    appointmentProbKey2.add("delayed");
    Map<String,Float> appointmentProbValue2 = new HashMap<>();
    appointmentProbValue2.put("attend",(float)(0.6));
    appointmentProbValue2.put("miss",(float)(0.4));
    appointmentProb.put(appointmentProbKey2, appointmentProbValue2);

    appointment.setProbabilidades(appointmentProb);

    System.out.println("Variables creadas correctamente.");

    List<String> evidencias = new ArrayList<String>();
    evidencias.add("light");
    evidencias.add("no");
    evidencias.add(null);
    evidencias.add(null);
    List<Variable> variables = new ArrayList<Variable>();
    variables.add(rain);
    variables.add(maintenance);
    variables.add(train);
    variables.add(appointment);
    Bayes.realizarInferencia(appointment, evidencias, variables);
  }
  
  public static void st2te(){

    List<String> examValores = new ArrayList<>();
    examValores.add("e0");
    examValores.add("e1");
    VariableIndependiente exam = new VariableIndependiente("exam level", examValores);

    Map<String,Float> examProb = new HashMap<>();
    examProb.put("e0",(float)(0.7));
    examProb.put("e1",(float)(0.3));
    exam.setProbabilidades(examProb);

    List<String> iqValores = new ArrayList<>();
    iqValores.add("i0");
    iqValores.add("i1");
    VariableIndependiente iq = new VariableIndependiente("iq level", iqValores);

    Map<String,Float> iqProb = new HashMap<>();
    iqProb.put("i0",(float)(0.8));
    iqProb.put("i1",(float)(0.2));
    iq.setProbabilidades(iqProb);
    
    
    List<String> aptiValores = new ArrayList<>();
    aptiValores.add("s0");
    aptiValores.add("s1");
    List<Variable> aptiDep = new ArrayList<>();
    aptiDep.add(iq);
    VariableDependiente apti = new VariableDependiente("apti score", aptiDep, aptiValores);
    
    Map<List<String>,Map<String,Float>> aptiProb = new HashMap<>();
    List<String> aptiProbKey1 = new ArrayList<>();
    aptiProbKey1.add("i0");
    Map<String,Float> aptiProbValue1 = new HashMap<>();
    aptiProbValue1.put("s0",(float)(0.75));
    aptiProbValue1.put("s1",(float)(0.25));
    aptiProb.put(aptiProbKey1, aptiProbValue1);
    
    List<String> aptiProbKey2 = new ArrayList<>();
    aptiProbKey2.add("i1");
    Map<String,Float> aptiProbValue2 = new HashMap<>();
    aptiProbValue2.put("s0",(float)(0.4));
    aptiProbValue2.put("s1",(float)(0.6));
    aptiProb.put(aptiProbKey2, aptiProbValue2);
    
    apti.setProbabilidades(aptiProb);

    List<String> markValores = new ArrayList<>();
    markValores.add("m0");
    markValores.add("m1");
    List<Variable> markDep = new ArrayList<>();
    markDep.add(iq);
    markDep.add(exam);
    VariableDependiente mark = new VariableDependiente("marks",markDep,markValores);

    Map<List<String>,Map<String,Float>> markProb = new HashMap<>();
    List<String> markProbKey1 = new ArrayList<>();
    markProbKey1.add("i0");
    markProbKey1.add("e0");
    Map<String,Float> markProbValue1 = new HashMap<>();
    markProbValue1.put("m0",(float)(0.6));
    markProbValue1.put("m1",(float)(0.4));
    markProb.put(markProbKey1, markProbValue1);

    List<String> markProbKey2 = new ArrayList<>();
    markProbKey2.add("i0");
    markProbKey2.add("e1");
    Map<String,Float> markProbValue2 = new HashMap<>();
    markProbValue2.put("m0",(float)(0.9));
    markProbValue2.put("m1",(float)(0.1));
    markProb.put(markProbKey2, markProbValue2);

    List<String> markProbKey3 = new ArrayList<>();
    markProbKey3.add("i1");
    markProbKey3.add("e0");
    Map<String,Float> markProbValue3 = new HashMap<>();
    markProbValue3.put("m0",(float)(0.5));
    markProbValue3.put("m1",(float)(0.5));
    markProb.put(markProbKey3, markProbValue3);
    
    List<String> markProbKey4 = new ArrayList<>();
    markProbKey4.add("i1");
    markProbKey4.add("e1");
    Map<String,Float> markProbValue4 = new HashMap<>();
    markProbValue4.put("m0",(float)(0.8));
    markProbValue4.put("m1",(float)(0.2));
    markProb.put(markProbKey4, markProbValue4);

    mark.setProbabilidades(markProb);
    
    List<String> admissionValores = new ArrayList<>();
    admissionValores.add("a0");
    admissionValores.add("a1");
    List<Variable> admissionDep = new ArrayList<>();
    admissionDep.add(mark);
    VariableDependiente admission = new VariableDependiente("admission", admissionDep, admissionValores);

    Map<List<String>,Map<String,Float>> admissionProb = new HashMap<>();
    List<String> admissionProbKey1 = new ArrayList<>();
    admissionProbKey1.add("m0");
    Map<String,Float> admissionProbValue1 = new HashMap<>();
    admissionProbValue1.put("a0",(float)(0.6));
    admissionProbValue1.put("a1",(float)(0.4));
    admissionProb.put(admissionProbKey1, admissionProbValue1);

    List<String> admissionProbKey2 = new ArrayList<>();
    admissionProbKey2.add("m1");
    Map<String,Float> admissionProbValue2 = new HashMap<>();
    admissionProbValue2.put("a0",(float)(0.9));
    admissionProbValue2.put("a1",(float)(0.1));
    admissionProb.put(admissionProbKey2, admissionProbValue2);
    admission.setProbabilidades(admissionProb);
    
    System.out.println("//// Ejercicio 1 ////");
    //punto 1
    List<String> evidencias = new ArrayList<String>();
    evidencias.add("e0");
    evidencias.add(null);
    evidencias.add(null);
    evidencias.add(null);
    evidencias.add(null);
    List<Variable> variables = new ArrayList<Variable>();
    variables.add(exam);
    variables.add(iq);
    variables.add(apti);
    variables.add(mark);
    variables.add(admission);
    System.out.println("//// Punto 1 ////");
    Map<String, Float> resultado = Bayes.realizarInferencia(admission, evidencias, variables);

    //punto 2
    List<String> evidencias2 = new ArrayList<String>();
    evidencias2.add(null);
    evidencias2.add(null);
    evidencias2.add(null);
    evidencias2.add(null);
    evidencias2.add(null);
    List<Variable> variables2 = new ArrayList<Variable>();
    variables2.add(exam);
    variables2.add(iq);
    variables2.add(apti);
    variables2.add(mark);
    variables2.add(admission);
    System.out.println("//// Punto 2 ////");
    Map<String, Float> resultado2 = Bayes.realizarInferencia(apti, evidencias2, variables2);
  
  }

  public static void test2(){
    List<String> partyValores = new ArrayList<>();
    partyValores.add("Si");
    partyValores.add("No");
    VariableIndependiente party = new VariableIndependiente("party", partyValores);
    
    Map<String,Float> partyProb = new HashMap<>();
    partyProb.put("Si",(float)(0.60216));
    partyProb.put("No",(float)(0.39784));
    party.setProbabilidades(partyProb);
    
    //smart
    List<String> smartValores = new ArrayList<>();
    smartValores.add("Si");
    smartValores.add("No");
    VariableIndependiente smart = new VariableIndependiente("smart", smartValores);
    
    Map<String,Float> smartProb = new HashMap<>();
    smartProb.put("Si",(float)(0.70472));
    smartProb.put("No",(float)(0.29528));
    smart.setProbabilidades(smartProb);

    //creative
    List<String> creativeValores = new ArrayList<>();
    creativeValores.add("Si");
    creativeValores.add("No");
    VariableIndependiente creative = new VariableIndependiente("creative", creativeValores);
    
    Map<String,Float> creativeProb = new HashMap<>();
    creativeProb.put("Si",(float)(0.69932));
    creativeProb.put("No",(float)(0.30068));
    creative.setProbabilidades(creativeProb);

    //project
    List<String> projectValores = new ArrayList<>();
    projectValores.add("Si");
    projectValores.add("No");
    List<Variable> projectDep = new ArrayList<>();
    projectDep.add(creative);
    projectDep.add(smart);
    VariableDependiente project = new VariableDependiente("project", projectDep, projectValores);
    //Si, Si
    Map<List<String>,Map<String,Float>> projectProb = new HashMap<>();
    List<String> projectProbKey1 = new ArrayList<>();
    projectProbKey1.add("Si");
    projectProbKey1.add("Si");
    Map<String,Float> projectProbValue1 = new HashMap<>();
    projectProbValue1.put("Si",(float)(0.90484));
    projectProbValue1.put("No",(float)(0.09516));
    projectProb.put(projectProbKey1, projectProbValue1);
    //Si, No
    List<String> projectProbKey2 = new ArrayList<>();
    projectProbKey2.add("Si");
    projectProbKey2.add("No");
    Map<String,Float> projectProbValue2 = new HashMap<>();
    projectProbValue2.put("Si",(float)(0.40307));
    projectProbValue2.put("No",(float)(0.59693));
    projectProb.put(projectProbKey2, projectProbValue2);
    //No, Si
    List<String> projectProbKey3 = new ArrayList<>();
    projectProbKey3.add("No");
    projectProbKey3.add("Si");
    Map<String,Float> projectProbValue3 = new HashMap<>();
    projectProbValue3.put("Si",(float)(0.79326));
    projectProbValue3.put("No",(float)(0.20674));
    projectProb.put(projectProbKey3, projectProbValue3);
    //No, No
    List<String> projectProbKey4 = new ArrayList<>();
    projectProbKey4.add("No");
    projectProbKey4.add("No");
    Map<String,Float> projectProbValue4 = new HashMap<>();
    projectProbValue4.put("Si",(float)(0.10731));
    projectProbValue4.put("No",(float)(0.89269));
    projectProb.put(projectProbKey4, projectProbValue4);
    project.setProbabilidades(projectProb);

    //hw
    List<String> hwValores = new ArrayList<>();
    hwValores.add("Si");
    hwValores.add("No");
    List<Variable> hwDep = new ArrayList<>();
    hwDep.add(smart);
    hwDep.add(party);
    VariableDependiente hw = new VariableDependiente("hw", hwDep, hwValores);
    //Si, Si
    Map<List<String>,Map<String,Float>> hwProb = new HashMap<>();
    List<String> hwProbKey1 = new ArrayList<>();
    hwProbKey1.add("Si");
    hwProbKey1.add("Si");
    Map<String,Float> hwProbValue1 = new HashMap<>();
    hwProbValue1.put("Si",(float)(0.80252));
    hwProbValue1.put("No",(float)(0.19748));
    hwProb.put(hwProbKey1, hwProbValue1);
    //Si, No
    List<String> hwProbKey2 = new ArrayList<>();
    hwProbKey2.add("Si");
    hwProbKey2.add("No");
    Map<String,Float> hwProbValue2 = new HashMap<>();
    hwProbValue2.put("Si",(float)(0.89790));
    hwProbValue2.put("No",(float)(0.1021));
    hwProb.put(hwProbKey2, hwProbValue2);
    //No, Si
    List<String> hwProbKey3 = new ArrayList<>();
    hwProbKey3.add("No");
    hwProbKey3.add("Si");
    Map<String,Float> hwProbValue3 = new HashMap<>();
    hwProbValue3.put("Si",(float)(0.09447));
    hwProbValue3.put("No",(float)(0.90553));
    hwProb.put(hwProbKey3, hwProbValue3);
    //No, No
    List<String> hwProbKey4 = new ArrayList<>();
    hwProbKey4.add("No");
    hwProbKey4.add("No");
    Map<String,Float> hwProbValue4 = new HashMap<>();
    hwProbValue4.put("Si",(float)(0.09447));
    hwProbValue4.put("No",(float)(0.90553));
    hwProb.put(hwProbKey4, hwProbValue4);
    hw.setProbabilidades(hwProb);
    
    //mac
    List<String> macValores = new ArrayList<>();
    macValores.add("Si");
    macValores.add("No");
    List<Variable> macDep = new ArrayList<>();
    macDep.add(creative);
    macDep.add(smart);
    VariableDependiente mac = new VariableDependiente("mac", macDep, macValores);
    //Si, Si
    Map<List<String>,Map<String,Float>> macProb = new HashMap<>();
    List<String> macProbKey1 = new ArrayList<>();
    macProbKey1.add("Si");
    macProbKey1.add("Si");
    Map<String,Float> macProbValue1 = new HashMap<>();
    macProbValue1.put("Si",(float)(0.68564));
    macProbValue1.put("No",(float)(0.31436));
    macProb.put(macProbKey1, macProbValue1);
    //Si, No
    List<String> macProbKey2 = new ArrayList<>();
    macProbKey2.add("Si");
    macProbKey2.add("No");
    Map<String,Float> macProbValue2 = new HashMap<>();
    macProbValue2.put("Si",(float)(0.89635));
    macProbValue2.put("No",(float)(0.10365));
    macProb.put(macProbKey2, macProbValue2);
    //No, Si
    List<String> macProbKey3 = new ArrayList<>();
    macProbKey3.add("No");
    macProbKey3.add("Si");
    Map<String,Float> macProbValue3 = new HashMap<>();
    macProbValue3.put("Si",(float)(0.41347));
    macProbValue3.put("No",(float)(0.58653));
    macProb.put(macProbKey3, macProbValue3);
    //No, No
    List<String> macProbKey4 = new ArrayList<>();
    macProbKey4.add("No");
    macProbKey4.add("No");
    Map<String,Float> macProbValue4 = new HashMap<>();
    macProbValue4.put("Si",(float)(0.12329));
    macProbValue4.put("No",(float)(0.87671));
    macProb.put(macProbKey4, macProbValue4);
    mac.setProbabilidades(macProb);
//-------------------------------------------------------
    List<String> successValores = new ArrayList<>();
    successValores.add("Si");
    successValores.add("No");
    List<Variable> successDep = new ArrayList<>();
    successDep.add(project);
    successDep.add(hw);
    VariableDependiente success = new VariableDependiente("success",successDep, successValores);

    Map<List<String>,Map<String,Float>> successProb = new HashMap<>();
    List<String> successKey1 = new ArrayList<>();
    successKey1.add("Si");
    successKey1.add("Si");
    Map<String,Float> successValue1 = new HashMap<>();
    successValue1.put("Si",(float)(0.89633));
    successValue1.put("No",(float)(0.10367));
    successProb.put(successKey1, successValue1);
    
    List<String> successKey2 = new ArrayList<>();
    successKey2.add("Si");
    successKey2.add("No");
    Map<String,Float> successValue2 = new HashMap<>();
    successValue2.put("Si",(float)(0.20737));
    successValue2.put("No",(float)(0.79263));
    successProb.put(successKey2, successValue2);

    List<String> successKey3 = new ArrayList<>();
    successKey3.add("No");
    successKey3.add("Si");
    Map<String,Float> successValue3 = new HashMap<>();
    successValue3.put("Si",(float)(0.30714));
    successValue3.put("No",(float)(0.69286));
    successProb.put(successKey3, successValue3);
  
    List<String> successKey4 = new ArrayList<>();
    successKey4.add("No");
    successKey4.add("No");
    Map<String,Float> successValue4 = new HashMap<>();
    successValue4.put("Si",(float)(0.05066));
    successValue4.put("No",(float)(0.94934));
    successProb.put(successKey4, successValue4);
    success.setProbabilidades(successProb);

//---------------------------------------------------------
    
    List<String> happyValores = new ArrayList<>();
    happyValores.add("Si");
    happyValores.add("No");
    List<Variable> happyDep = new ArrayList<>();
    happyDep.add(success);
    happyDep.add(mac);
    happyDep.add(party);
    VariableDependiente happy = new VariableDependiente("happy",happyDep, happyValores);

    Map<List<String>,Map<String,Float>> happyProb = new HashMap<>();
    List<String> happyKey1 = new ArrayList<>();
    happyKey1.add("Si");
    happyKey1.add("Si");
    happyKey1.add("Si");
    Map<String,Float> happyValue1 = new HashMap<>();
    happyValue1.put("Si",(float)(0.95842));
    happyValue1.put("No",(float)(0.04158));
    happyProb.put(happyKey1, happyValue1);
    
    List<String> happyKey2 = new ArrayList<>();
    happyKey2.add("Si");
    happyKey2.add("Si");
    happyKey2.add("No");
    Map<String,Float> happyValue2 = new HashMap<>();
    happyValue2.put("Si",(float)(0.35837));
    happyValue2.put("No",(float)(0.64163));
    happyProb.put(happyKey2, happyValue2);

    List<String> happyKey3 = new ArrayList<>();
    happyKey3.add("Si");
    happyKey3.add("No");
    happyKey3.add("Si");
    Map<String,Float> happyValue3 = new HashMap<>();
    happyValue3.put("Si",(float)(0.72082));
    happyValue3.put("No",(float)(0.27918));
    happyProb.put(happyKey3, happyValue3);
  
    List<String> happyKey4 = new ArrayList<>();
    happyKey4.add("Si");
    happyKey4.add("No");
    happyKey4.add("No");
    Map<String,Float> happyValue4 = new HashMap<>();
    happyValue4.put("Si",(float)(0.30769));
    happyValue4.put("No",(float)(0.69231));
    happyProb.put(happyKey4, happyValue4);

    List<String> happyKey5 = new ArrayList<>();
    happyKey5.add("No");
    happyKey5.add("Si");
    happyKey5.add("Si");
    Map<String,Float> happyValue5 = new HashMap<>();
    happyValue5.put("Si",(float)(0.49234));
    happyValue5.put("No",(float)(0.50766));
    happyProb.put(happyKey5, happyValue5);
    
    List<String> happyKey6 = new ArrayList<>();
    happyKey6.add("No");
    happyKey6.add("Si");
    happyKey6.add("No");
    Map<String,Float> happyValue6 = new HashMap<>();
    happyValue6.put("Si",(float)(0.20619));
    happyValue6.put("No",(float)(0.79381));
    happyProb.put(happyKey6, happyValue6);

    List<String> happyKey7 = new ArrayList<>();
    happyKey7.add("No");
    happyKey7.add("No");
    happyKey7.add("Si");
    Map<String,Float> happyValue7 = new HashMap<>();
    happyValue7.put("Si",(float)(0.42043));
    happyValue7.put("No",(float)(0.57957));
    happyProb.put(happyKey7, happyValue7);
  
    List<String> happyKey8 = new ArrayList<>();
    happyKey8.add("No");
    happyKey8.add("No");
    happyKey8.add("No");
    Map<String,Float> happyValue8 = new HashMap<>();
    happyValue8.put("Si",(float)(0.09646));
    happyValue8.put("No",(float)(0.90354));
    happyProb.put(happyKey8, happyValue8);  
    happy.setProbabilidades(happyProb);

    //Punto 1
    List<Variable> variables = new ArrayList<Variable>();
    variables.add(creative);
    variables.add(smart);
    variables.add(party);
    variables.add(project);
    variables.add(mac);
    variables.add(hw);
    variables.add(success);
    variables.add(happy);

    System.out.println("//// Ejercicio 2 ////");
    List<String> evidencias = new ArrayList<String>();
    evidencias.add("No");
    evidencias.add("Si");
    evidencias.add("Si");
    evidencias.add(null);
    evidencias.add(null);
    evidencias.add(null);
    evidencias.add(null);
    evidencias.add(null);
    System.out.println("//// Punto 1 ////");
    Bayes.realizarInferencia(happy, evidencias, variables);

    //Punto 2
    List<String> evidencias2 = new ArrayList<String>();
    evidencias2.add(null);
    evidencias2.add(null);
    evidencias2.add("No");
    evidencias2.add("Si");
    evidencias2.add(null);
    evidencias2.add("Si");
    evidencias2.add(null);
    evidencias2.add(null);
    System.out.println("//// Punto 2 ////");
    Bayes.realizarInferencia(happy, evidencias2, variables);
  }
  
}
