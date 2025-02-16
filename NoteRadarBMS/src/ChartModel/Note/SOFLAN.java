package ChartModel.Note;
public class SOFLAN extends Note {
    private int BPM;

    public SOFLAN(int ii, int section, int lane, int bpm){
        super(ii, section, lane);
        this.BPM = bpm;
    }

    public int getTrim(){
        return 1;
    }

    public int getBPM() {
        return BPM;
    }

    public int getTiming() {
        return super.getTiming();
    }

    public int getSection(){
        return super.getSection();
    }
}