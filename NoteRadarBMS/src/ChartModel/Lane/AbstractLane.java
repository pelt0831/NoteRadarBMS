package ChartModel.Lane;
import java.util.HashMap;

import ChartModel.Note.Note;

public abstract class AbstractLane {
    private int id;
    public HashMap<Integer, Note> notes;

    public AbstractLane(int id){
        this.id = id;
        notes = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void addNotes(int ii, int section){
    }

    public void addNotes(int ii, int section, int sted){
    }
}
