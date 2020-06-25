package com.example.test_covid.FAQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> faq1 = new ArrayList<String>();
        faq1.add("The most common symptoms of COVID-19 are fever, dry cough, and tiredness.");


        List<String> faq2 = new ArrayList<String>();
        faq2.add("The time between exposure to COVID-19 and the moment when symptoms start is commonly around five to six days but can range from 1 – 14 days.");


        List<String> faq3 = new ArrayList<String>();
        faq3.add("When grocery shopping, keep at least 1-metre distance from others and avoid touching your eyes, mouth and nose. Once home, wash your hands thoroughly and also after handling and storing your purchased products.");

        List<String> faq4 = new ArrayList<String>();
        faq4.add("There is currently no confirmed case of COVID-19 transmitted through food or food packaging.");

        List<String> faq5 = new ArrayList<String>();
        faq5.add("The most important thing to know about coronavirus on surfaces is that they can easily be+ cleaned with common household disinfectants that will kill the virus. Studies have shown that the COVID-19 virus can survive for up to 72 hours on plastic and stainless steel, less than 4 hours on copper and less than 24 hours on cardboard.");



        expandableListDetail.put("what are the symptoms of covid19?", faq1);
        expandableListDetail.put("How long does it take to develop symptoms after exposure to covid19?", faq2);
        expandableListDetail.put("How to grocery shop safely?", faq3);
        expandableListDetail.put("Does packed food transmit covid19?", faq4);
        expandableListDetail.put("How long does the virus survive on surfaces?", faq5);
        return expandableListDetail;
    }
}