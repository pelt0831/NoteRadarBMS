package ChartModel.Iterator;

import java.util.ArrayList;
import java.util.Set;

import ChartModel.Section;
import ChartModel.Lane.AbstractLane;
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
    public int hasNext() throws Exception{
        int minTiming=9999999;
        for (Section section : Sections) {
            if(playsection >= section.getIndex())continue;
            if(playsection < section.getIndex())playtiming = 0;
            //assume we arrived at target section
            for (Integer timing : section.timinglib) {
                if (playtiming < timing) {
                    minTiming = minTiming>timing?timing:minTiming;
                }
            };
            if(minTiming != 9999999){
                return minTiming;
            }
        }
        return -1;
    }

    @Override
    public ArrayList<Note> Next() {
        // TODO Auto-generated method stub
        int minTiming=9999999;
        for (Section section : Sections) {
            if(playsection >= section.getIndex())continue;
            if(playsection < section.getIndex()){playtiming = 0;playsection = section.getIndex()}
            //assume we arrived at target section
            for (Integer timing : section.timinglib) {
                if (playtiming < timing) {
                    minTiming = minTiming>timing?timing:minTiming;
                }
            };
            if(minTiming != 9999999){
                //export Notes

            }
        }
    }

    @Override
    public int FindSection(Note note) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'FindSection'");
    }
    
}
