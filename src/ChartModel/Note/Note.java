package ChartModel.Note;
public abstract class Note {
    private int section;
    private int ii;

    public Note(int ii, int section){
        this.ii = ii;
        this.section = section;
    }

    public int getTrim(){
        return 1;
    }

    public int getTiming() {
        return ii;
    }

    public int getSection(){
        return section;
    }
}