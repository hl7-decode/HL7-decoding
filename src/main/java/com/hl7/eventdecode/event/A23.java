package com.hl7.eventdecode.event;

import com.hl7.eventdecode.deal.*;
import com.hl7.eventdecode.deal.exception.HashNullPointException;
import com.hl7.eventdecode.segment.AL1;
import com.hl7.eventdecode.segment.DB1;
import com.hl7.eventdecode.segment.NK1;
import com.hl7.eventdecode.segment.PID;
import com.hl7.in_mysql.enuitity.Allergy;
import com.hl7.in_mysql.enuitity.Disability;
import com.hl7.in_mysql.enuitity.Family;
import com.hl7.in_mysql.enuitity.Patient;
import com.hl7.in_mysql.manager.*;
import com.hl7.in_mysql.util.Json;
import com.sun.org.apache.bcel.internal.generic.FMUL;

/**
 * A23 删除病人记录
 * MSH
 * PID
 * PV1
 * DB1
 */

public class A23 {

    private Terser terser;

    public A23(Terser terser){
        this.terser = terser;
    }

    public void opreation() {

        Patient patient = new PID(this.terser).getPatient();
        //获取患者信息并

        PatientManager.delete(patient);
        FamilyManager.delete(patient.patient_id);
        AllergyManager.delete(patient.patient_id);
        OutPatientManager.delete(patient.patient_id);
        PatientHospitalManager.delete(patient.patient_id);
        DisabilityManager.delete(patient.patient_id);

    }
}
