package ChartModel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import ChartModel.Lane.AbstractLane;
import ChartModel.Lane.ChargeLane;
import ChartModel.Lane.NormalLane;
import ChartModel.Lane.SOFLANLane;
import ChartModel.Note.Note;
import ChartModel.Note.SOFLAN;

public class Section {
    private int index;
    private int nn;
    public HashMap<Integer, AbstractLane> Lanes;
    public Set<Integer> timinglib = new HashSet<>();

    public Section(int section, int nn, int lnum, int[] ids){
        this.index = section;
        this.nn = nn;
        Lanes = new HashMap<>();
        for(int i=0; i<lnum;i++){
            Lanes.put(ids[i], new NormalLane(ids[i]));//Normal Lane
            Lanes.put(ids[i]+40, new ChargeLane(ids[i]+40));//Charge Lane
        }
        Lanes.put(13, new SOFLANLane(13));//SOFLAN Lane
    }

    public int getIndex() {
        return index;
    }

    public int sectionSize(){
        return nn;
    }

    public void addNotes(int lid, int ii){
        Lanes.get(lid).addNotes(ii, index);
        timinglib.add(ii);
    }

    public void addNotes(int lid, int ii, int sted){
        Lanes.get(lid).addNotes(ii, index, sted);
        timinglib.add(ii);
    }

    public void print(){
        int [] lsted = {1,1,1,1,1,1,1,1};
        for(int j=0; j<8; j++){
            //each Lane
            AbstractLane lane = Lanes.get(j+1);  
            AbstractLane clane = Lanes.get(j+41);          
            for(int n =0 ;n<nn;n++){
                String outString = "";
                try {
                        Note CN = clane.notes.get(n);
                        lsted[j] = CN.getTrim();
                        CN.getTiming();
                        if(j%2==0){
                            outString = "◆";
                        }else{
                            outString = "◇";
                        }
                        if (lsted[j] == 2) {
                            outString = "◆";
                            lsted[j] = 0;
                        }
                    } catch (Exception ex) {
                        // TODO: handle exception of no key
                        try {
                            Note not = lane.notes.get(n);
                            not.getTiming();
                            if(j%2==0){
                                outString = "■";
                            }else{
                                outString = "□";
                            }
                        } catch (Exception e) {
                            if(lsted[j] == 0){
                                outString = "=";
                            }else{
                                outString = " ";
                            }                              
                        }
                    }
                System.out.print(outString);
            }
            System.out.println();
        }
        AbstractLane BPM = Lanes.get(13);
        int len = 0;
        for(int n =0 ;n<nn;n++){
                String outString = "";
                try {
                    Note bpm = BPM.notes.get(n);
                    int num = ((SOFLAN) bpm).getBPM();
                    outString = Integer.toString(num);
                    len = Integer.toString(num).length();
                }catch(Exception ee){
                    if(len > 0){
                        outString = "";
                        len--;
                    }
                    else{
                        outString = " ";
                    }
                }
                System.out.print(outString);
        }
        System.out.println();
    }
}
