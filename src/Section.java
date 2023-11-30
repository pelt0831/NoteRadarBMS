import java.util.ArrayList;
import java.util.HashMap;

public class Section {
    private int index;
    private int nn;
    HashMap<Integer, Lane> Lanes;

    public Section(int section, int nn, int lnum, int[] ids){
        this.index = section;
        this.nn = nn;
        Lanes = new HashMap<>();
        for(int i=0; i<lnum;i++){
            Lanes.put(ids[i], new Lane(ids[i]));
        }
    }

    public int getIndex() {
        return index;
    }

    public void addNotes(int lid, int ii){
        Lanes.get(lid).addNotes(ii);
    }
}
