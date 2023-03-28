// An implementation of a Training Record as an ArrayList
package com.stir.cscu9t4practical1;

import java.util.*;


public class TrainingRecord {
    private List<Entry> tr;
    
    public TrainingRecord() {
        tr = new ArrayList<Entry>();
    } //constructor
    
    // add a record to the list
   public String addEntry(Entry e) {
	   String message = "";
	   if (uniqueEntry(e) == true) {
		   tr.add(e);
		   message = "Record added successfully";
	   } 
	   else {
		   System.out.println("Entry cannot be added twice");
           message = "Failed to add, entry already exists";
	   } 
	   return message; 
   } // addClass
   
   // checks if the entry entered isn't already in the system
   public boolean uniqueEntry(Entry e){
       ListIterator<Entry> iter = tr.listIterator();
       boolean uniqueEntry = true;
       while (iter.hasNext()) {
           Entry current = iter.next();
           if (current.getName().equalsIgnoreCase(e.getName()) && current.getDay()==e.getDay()
           && current.getMonth()==e.getMonth() && current.getYear()==e.getYear()) 
           {
        	   uniqueEntry = false;
           }
       } return uniqueEntry;
   } // uniqueEntry
   
   // remove the entry given the name, day, month and year
   public String removeEntry (String n, int d, int m, int y) {
	   ListIterator<Entry> iter = tr.listIterator();
       String result = "";
       if (!iter.hasNext()){
           result = "No matching entries found";
           
       } while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getName().equalsIgnoreCase(n) && current.getDay()==d && current.getMonth()==m && current.getYear()==y) {
        	  result = "Entry deleted";
              iter.remove(); 
          }
       } return result;
   }
   
   // look up the entry of a given day and month
   public String lookupEntry (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "No entries found";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result = current.getEntry();
            }
       return result;
   } // lookupEntry
   
   // look up all entries of a given day and month
   public String findAll (int d, int m, int y) {
       ListIterator<Entry> iter = tr.listIterator();
       String result = "";
       while (iter.hasNext()) {
          Entry current = iter.next();
          if (current.getDay()==d && current.getMonth()==m && current.getYear()==y) 
             result = result + current.getEntry();
            }
       return result;
   } // findAll
   
   // Count the number of entries
   public int getNumberOfEntries(){
       return tr.size();
   }
   // Clear all entries
   public void clearAllEntries(){
       tr.clear();
   }
   
} // TrainingRecord