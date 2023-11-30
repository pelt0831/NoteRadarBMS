package ChartModel.Note;
public class ChargeNote extends Note{
    private int trim; //0:start, 1:end 2:connected

    public ChargeNote(int ii, int section, int sted){
        super(ii, section);
        this.trim = sted;
    }

    @Override
    public int getTrim(){
        return trim;
    }

    public int getTiming() {
        return super.getTiming();
    }

    public int getSection(){
        return super.getSection();
    }
}