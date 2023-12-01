package ChartModel.Iterator;

import java.util.ArrayList;

import ChartModel.Section;
import ChartModel.Lane.LaneID;
import ChartModel.Note.Note;

public class ConcreteIterator implements Iterator{
    //DataModel;
    public ArrayList<Section> Sections;
    public int playsection = 0;
    public int playtiming = 0;
    LaneID LID = new LaneID();

    public ConcreteIterator(ArrayList<Section> sections){
        this.Sections = sections;
    }

    @Override
    public boolean hasNext() throws Exception{
        // TODO Auto-generated method stub
        for (Section section : Sections) {
            int minTiming=9999999;
            if(playsection > section.getIndex())continue;
            if(playsection < section.getIndex()){
                playtiming = 0;
                playsection = section.getIndex();
            }
            //assume we arrived at target section
            for (Integer timing : section.timinglib) {
                if (playtiming < timing) {
                    minTiming = minTiming>timing?timing:minTiming;
                }
            };
            if(minTiming != 9999999){//there is next notes in one section
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Note> Next() {
        ArrayList<Note> temp = new ArrayList<>();
        // TODO Auto-generated method stub
        for (Section section : Sections) {
            int minTiming=9999999;
            if(playsection > section.getIndex())continue;
            if(playsection < section.getIndex()){
                playtiming = 0;
                playsection = section.getIndex();
            }
            //assume we arrived at target section
            for (Integer timing : section.timinglib) {
                if (playtiming < timing) {
                    minTiming = minTiming>timing?timing:minTiming;
                }
            };
            if(minTiming != 9999999){//there is next notes in one section
                playtiming = minTiming;
                //for each lane
                for (Integer sid : LID.SIDint) {//check normal note
                    try {
                        Note tNote = section.Lanes.get(sid).notes.get(minTiming);
                        temp.add(tNote);
                    } catch (Exception e) {
                        // TODO: handle exception
                        temp.add(null);
                    }
                }
                for (Integer sid : LID.CSIDint) {//check normal note
                    try {
                        Note tNote = section.Lanes.get(sid).notes.get(minTiming);
                        temp.add(tNote);
                    } catch (Exception e) {
                        // TODO: handle exception
                        temp.add(null);
                    }
                }

                //make seperate?
                for (Integer sid : LID.SOFIDint) {//check normal note
                    try {
                        Note tNote = section.Lanes.get(sid).notes.get(minTiming);
                        temp.add(tNote);
                    } catch (Exception e) {
                        // TODO: handle exception
                        temp.add(null);
                    }
                }
                return temp;
            }
        }
        return null;
    }

    @Override
    public int FindSection(Note note) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'FindSection'");
    }
    
}
