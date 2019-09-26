package com.example.demo.spring.scheduling;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@EnableScheduling
public class ScheduledConfigurationAsync implements SchedulingConfigurer {
	TaskScheduler taskScheduler;
	private ScheduledFuture<?> job1;
	private ScheduledFuture<?> job2;
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		ThreadPoolTaskScheduler threadPoolTaskScheduler =new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(10);// Set the pool of threads
		threadPoolTaskScheduler.setThreadNamePrefix("scheduler-thread");
		threadPoolTaskScheduler.initialize();
		job1(threadPoolTaskScheduler);// Assign the job1 to the scheduler
		job2(threadPoolTaskScheduler);// Assign the job1 to the scheduler
		this.taskScheduler=threadPoolTaskScheduler;
		
		// this will be used in later part of the article during refreshing the cron expression dynamically
//		refreshCronSchedule(); //Jugar a comentar y descomentar esta linea
		
		taskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
	}

	/*
	 * Se ejecuta cada 5 segundos y se atasca por el sleep 10 seg, pero no para a el job2
	 */
	private void job1(TaskScheduler scheduler) {
		job1 = scheduler.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " The Task1 executed at " + new Date());
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String cronExp = "0/5 * * * * ?";// Can be pulled from a db .
				return new CronTrigger(cronExp).nextExecutionTime(triggerContext);
			}
		});
	}

	/*
	 * Se ejecuta cada segundo de forma asincrona con respecto al job1
	 */
	private void job2(TaskScheduler scheduler){
		job2=scheduler.schedule(new Runnable(){
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+" The Task2 executed at "+ new Date());
			}
		}, new Trigger(){
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				String cronExp="0/1 * * * * ?";//Can be pulled from a db . This will run every minute
				return new CronTrigger(cronExp).nextExecutionTime(triggerContext);
			}
		});
	}
	

	
	
	
	private volatile boolean job1Flag=false;
	
	
    private void scheduleJob1(TaskScheduler scheduler) {
        job1 = scheduler.schedule(new Runnable() {
          @Override
          public void run() {           
               System.out.println(Thread.currentThread().getName() + " The Task1 executed at " + new Date());
               try {
                Thread.sleep(10000);
               } catch (InterruptedException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
               }
               job1Flag=true;// setting the flag true to mark it complete
        }
        }, new Trigger() {
           @Override
           public Date nextExecutionTime(TriggerContext triggerContext) {
                  String cronExp = "0/5 * * * * ?";// Can be pulled from a db 
                  return new CronTrigger(cronExp).nextExecutionTime(triggerContext);
                }
            });
       }
    
    
    
    private void scheduleJob2(TaskScheduler scheduler) {
        job2=scheduler.schedule(new Runnable(){
         @Override
         public void run() {
           synchronized(this){
             while(!job1Flag){
                 System.out.println(Thread.currentThread().getName()+" waiting for job1 to complete to execute "+ new Date());
               try {
                    wait(1000);// add any number of seconds to wait 
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                    }
             }
           }
            System.out.println(Thread.currentThread().getName()+" The Task2 executed at "+ new Date());
            job1Flag=false;
        }
       }, new Trigger(){
              @Override
              public Date nextExecutionTime(TriggerContext triggerContext) {
              String cronExp="0/5 * * * * ?";//Can be pulled from a db . This will run every minute
              return new CronTrigger(cronExp).nextExecutionTime(triggerContext);
        }
      });
    }
    
    
    
    /*
     *  supongamos que tenemos job1 que se ejecuta durante una hora a la 1 am y job2 que se ejecuta a las 2 am. Pero, job2 no debe comenzar a menos que complete1. 
     *  También tenemos otra lista de trabajos que pueden ejecutarse entre la 1 y las 2 am y son independientes de otros trabajos.
     *  
     *  Veamos cómo podemos crear una dependencia entre el trabajo1 y el trabajo2, pero ejecutar todos los trabajos de forma asincrónica a la hora programada.
     *  
     *  Cambiar una expresión de Cron dinámicamente.
     *  Siempre podemos mantener la expresión cron en un archivo de propiedades utilizando la configuración de Spring. 
     *  Si el servidor Spring Config no está disponible, también podemos buscarlo desde el DB. 
     *  Cualquier actualización de la expresión cron actualizará el Programador. 
     *  Pero para cancelar la programación actual y ejecutar la nueva programación, podemos exponer una API para actualizar el trabajo cron:
     */
	public void refreshCronSchedule(){
		
		  if(job1!=null){
		   job1.cancel(true);
		   scheduleJob1(taskScheduler);
		  }
		  
		  if(job2!=null){
		   job2.cancel(true);
		   scheduleJob2(taskScheduler);
		  }
		}
}
