package com.example.demo.java.timerExamples;

import java.util.Timer;

/**
 * 
 * Ejemplo de como ejecutar tareas cada cierto tiempo con java de forma sincrona
 *
 */
public class ExecuteTimer {
  public static void main(String[] args){
       TimerExample te1=new TimerExample("Task1");
       TimerExample te2=new TimerExample("Task2");
      Timer t=new Timer();
      t.scheduleAtFixedRate(te1, 0,5*1000);
      t.scheduleAtFixedRate(te2, 0,1000);
   }
}