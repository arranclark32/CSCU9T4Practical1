// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField terrain = new JTextField(15);
    private JTextField tempo = new JTextField(10);
    private JTextField repetitions = new JTextField(2);
    private JTextField recovery = new JTextField(2);
    private JTextField where = new JTextField(20);
    
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JLabel labtrn = new JLabel(" Terrain:");
    private JLabel labtmp = new JLabel(" Tempo:");
    private JLabel labreps = new JLabel(" Repetitions:");
    private JLabel labrec = new JLabel(" Recovery (mins):");
    private JLabel labwhr = new JLabel(" Where:");
    
    
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find By Date");
    private JButton remove = new JButton("Remove");
    
    
    String[] training = {"Generic", "Swim", "Cycle", "Sprint"}; 
    private JComboBox trainingType = new JComboBox(training);

    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);
        add(labtrn);
        add(terrain);
        dist.setEditable(true);
        add(labtmp);
        add(tempo);
        dist.setEditable(true);
        add(labreps);
        add(repetitions);
        dist.setEditable(true);
        add(labrec);
        add(recovery);
        dist.setEditable(true);
        add(labwhr);
        add(where);
        dist.setEditable(true);
        
        trainingType.addActionListener(this);
        add(trainingType);
        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(remove);
        remove.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        
        setSize(800, 340);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry((String) trainingType.getSelectedItem());
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate) {
        	message = findAll();
        }
        if (event.getSource() == remove) {
            message = removeEntry();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
    	String message = "";
        System.out.println("Adding "+what+" entry to the records");
        int m, d, y, h, mm, s, reps, rec;
        float km;
        String n = "", trn = "", tmp = "", whr = "";
        Entry e = null;
        
        km = java.lang.Float.parseFloat(dist.getText());
        m = Integer.parseInt(month.getText());
        d = Integer.parseInt(day.getText());
        y = Integer.parseInt(year.getText());
        h = Integer.parseInt(hours.getText());
        mm = Integer.parseInt(mins.getText());
        s = Integer.parseInt(secs.getText());
        n = name.getText();
            
        if (what.equals("cycle")) {
                    trn = terrain.getText();
                    tmp = tempo.getText();
                    e = new CycleEntry(n, d, m, y, h, mm, s, km, trn, tmp);
                    
        } else if (what.equals("sprint")) {
                    reps = Integer.parseInt(repetitions.getText());
                    rec = Integer.parseInt(recovery.getText());
                    e = new SprintEntry(n, d, m, y, h, mm, s, km, reps, rec);
                    
        } else if (what.equals("swim")) {
                    whr = where.getText();
                    e = new SwimEntry(n, d, m, y, h, mm, s, km, whr);
                    
        } else {	
                    e = new Entry(n, d, m, y, h, mm, s, km);
        } 
        message = myAthletes.addEntry(e);
        return message ;
    }
    
    // Method to remove entry from records
    public String removeEntry() {
    	String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        System.out.println("Removing entry for " + n + " from " + d + "/" + m + "/" + y);
        outputArea.setText("looking up record ...");
        String message = myAthletes.removeEntry(n, d, m, y);
        return message;
    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }
    
    public String findAll() {
    	int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.findAll(d, m, y);
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");

    }// blankDisplay
    
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

