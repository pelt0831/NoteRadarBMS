package ChartModel.Note;
public class NormalNote extends Note{
    public NormalNote(int ii, int section, int lane){
        super(ii, section, lane);
    }

    public int getTiming() {
        return super.getTiming();
    }

    public int getSection(){
        return super.getSection();
    }
}