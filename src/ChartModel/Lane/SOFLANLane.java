package ChartModel.Lane;

import ChartModel.Note.SOFLAN;

public class SOFLANLane extends AbstractLane{
    public SOFLANLane(int id){
        super(id);
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }

    @Override
    public void addNotes(int ii, int section, int sted){
        super.notes.put(ii, new SOFLAN(ii, section, sted));
    }
}
