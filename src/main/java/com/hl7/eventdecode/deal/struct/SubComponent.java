package com.hl7.eventdecode.deal.struct;

/**
 * SubComponent
 */
public class SubComponent {

    public String subComponentString = "";

    public SubComponent(String string){
        this.subComponentString = string;
    }

    public String get(){
        return subComponentString;
    }
    
}
