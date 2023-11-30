package ChartModel.Lane;

import ChartModel.Note.ChargeNote;

public class ChargeLane extends AbstractLane{

    public ChargeLane(int id){
        super(id);
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }

    @Override
    public void addNotes(int ii, int section, int sted){
        super.notes.put(ii, new ChargeNote(ii, section, sted));
    }
}
