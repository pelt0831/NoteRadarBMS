package ChartModel.Note;
public abstract class Note {
    private int section;
    private int lane;
    private int ii;

    public Note(int ii, int section, int lane){
        this.ii = ii;
        this.section = section;
        this.lane = lane;
    }

    public int getTrim(){
        return 1;
    }

    public int getTiming() {
        return ii;
    }

    public int getLane() {
        return lane;
    }

    public int getSection(){
        return section;
    }
}