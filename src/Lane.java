import java.util.ArrayList;
import java.util.HashMap;

public class Lane {
    private int id;
    private HashMap<Integer, Note> notes;

    public Lane(int id){
        this.id = id;
        notes = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void addNotes(int ii){
        notes.put(ii, new Note(ii));
    }
}
