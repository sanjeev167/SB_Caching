package com.pon.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pon.model.Student;

/**
 * @author Sanjeev
 *
 */
@Service
public class StudentService 
{
    @Cacheable("student")
    public Student getStudentByID(String id) 
    {
    	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	   LocalDateTime now = LocalDateTime.now();  
    	System.out.println("Call received for getting student details at time = "+dtf.format(now));
        try
        {
            System.out.println("Going to sleep for 15 Secs.. to simulate backend call.");
            Thread.sleep(1000*15);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
 //The first call will take 15 second to respond; but the subsequent call for the same record will be served from the cache instantly.
        return new Student(id,"Sajal" ,"V");
    }
}