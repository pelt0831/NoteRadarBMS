package ChartModel;
import java.util.ArrayList;

import ChartModel.Iterator.Aggregate;
import ChartModel.Iterator.Iterator;

public class Timeline implements Aggregate{
    public String filePath ="";
    //Data Model
    public ArrayList<Section> Sections = new ArrayList<>();

    public void print(){
        for (Section section : Sections) {
            section.print();
            System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////");
        }
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }
}
