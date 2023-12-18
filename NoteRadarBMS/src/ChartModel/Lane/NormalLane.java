package ChartModel.Lane;

import ChartModel.Note.NormalNote;

public class NormalLane extends AbstractLane{
    public NormalLane(int id){
        super(id);
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }

    @Override
    public void addNotes(int ii, int section){
        super.notes.put(ii, new NormalNote(ii, section,super.getId()));
    }
}
