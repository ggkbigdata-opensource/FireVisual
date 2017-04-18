package com.fire.app.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class AppFireEventBean extends AppFireEvent{
    // Fields
    private Date occurTime;
    

}
