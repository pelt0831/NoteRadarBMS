import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import ChartModel.Timeline;
import ChartModel.Iterator.Iterator;
import ChartModel.Note.Note;
import ChartModel.Note.SOFLAN;

public class Client {
    public static void main(String[] args) throws Exception {
        // File file = new File("output.txt");
        // FileOutputStream fos = new FileOutputStream(file);
        // PrintStream ps = new PrintStream(fos);
        // System.setOut(ps);

        System.out.println("hey");
        ChartDecoder decoder = new OSUDecoder();
        Timeline timeline = decoder.decode();
        timeline.print();
        //Iterator Print Examples
        /* 
        Iterator iterator = timeline.iterator();
        int [] count = {1,1,1,1,1,1,1,1,1};
        while (iterator.hasNext()) {
            ArrayList<Note> linNotes = iterator.Next();
            for (Note note : linNotes) {
                if(note == null){
                    System.out.print("  ");
                    continue;
                }
                else{
                    int lanenum = note.getLane();
                    String ouString = "";
                    if(lanenum == 13){
                        System.out.print(((SOFLAN)note).getBPM());
                    }
                    if(lanenum < 10){
                        //normal Note
                        if(count[lanenum] == 2){
                            ouString = "|";
                        }else{
                            if(lanenum%2 != 0)
                                ouString = "■";
                            else
                                ouString = "□";                         
                        }
                    }else if(lanenum > 40){
                        if(note.getTrim() == 2){
                            ouString = "|";
                        }
                        else{
                            if(lanenum%2 != 0)
                                ouString = "◆";
                            else
                                ouString = "◇";
                        }
                    }
                    System.out.print(ouString);
                }
            }
            System.out.println();
        }*/
    }
    
}

