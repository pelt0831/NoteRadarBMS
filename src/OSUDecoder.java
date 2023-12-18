import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ChartModel.Section;
import ChartModel.Timeline;
import ChartModel.Lane.LaneID;

public class OSUDecoder extends ChartDecoder{
    String filePath = "C:\\Users\\VRLABS\\Documents\\GitHub\\NoteRadarBMS\\osu\\BEMANI Sound Team HERO - EROICA (Snowdrop) [12-EXC7].osu";

    Timeline timeLine = new Timeline();

    int []sted = {0,0,0,0,0,0,0};
    int []id = {1,2,3,4,5,6,7};
    int lnnum = 7;
    LaneID LID = new LaneID();
    int acc = 128;

    //initoffset
    int initoff = 0;

    public OSUDecoder(){
        try {
            this.decode();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String headerString(String line){
        int pos = 0;    
        if((pos = line.indexOf("[General]")) != -1){
            return "[General]";
        }else if((pos = line.indexOf("[Editor]")) != -1){
            return "[Editor]";
        }else if((pos = line.indexOf("[Metadata]")) != -1){
            return "[Metadata]";
        }else if((pos = line.indexOf("[Difficulty]")) != -1){
            return "[Difficulty]";
        }else if((pos = line.indexOf("[Events]")) != -1){
            return "[Events]";
        }else if((pos = line.indexOf("[TimingPoints]")) != -1){
            return "[TimingPoints]";
        }else if((pos = line.indexOf("[HitObjects]")) != -1){
            return "[HitObjects]";
        }return null;
    }

    private void LaneParseNote(String lane, int timing, int section){
        int iid = LID.sid.get(lane);
        timeLine.Sections.get(section).addNotes(iid, timing);
    }

    private void ChargeLaneParseNote(String lane, int stSection, int stindex, int edSection, int edindex){
        int iid = LID.sid.get(lane);
        timeLine.Sections.get(stSection).addNotes(iid+40, stindex, 0);
        timeLine.Sections.get(edSection).addNotes(iid+40, edindex, 1);
        for(int i=stSection+1; i<=edSection; i++){
            timeLine.Sections.get(i).addNotes(iid+40, 0, 2);
        }
    }

    @Override
    public Timeline decode()throws IOException{
        FileInputStream fStream = new FileInputStream(filePath);
        InputStreamReader iStreamReader = new InputStreamReader(fStream, "UTF-8");
        BufferedReader br = new BufferedReader(iStreamReader);
        timeLine.filePath = filePath;
        String line;
        boolean isData = false;
        String header = "";

        int offset = 0;
        double beatLength = 0;
        int sig = 0;

        while((line = br.readLine())!=null){
            int pos = 0;
            if(line.length()<2){
                continue;
            }

            //Verify Header Section
            if((pos = line.indexOf("["))!=-1){
                try {
                    header = headerString(line);               
                    continue;
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println("Invalid Header");
                }
            }
            if(header.equals(""))continue;
            //[General]
            if(header.equals("[General]")){
                //Title
                //Artist
                //BPM
            }
            if(header.equals("[Difficulty]")){
                //lnnum
                if((pos = line.indexOf("CircleSize"))!=-1){
                    int valuepos = line.indexOf(":")+1;
                    lnnum = Integer.parseInt(line.substring(valuepos));
                    System.out.println("keys : "+lnnum);
                }
            }
            if(header.equals("[Metadata]")){
                if((pos = line.indexOf("Title:"))!=-1){
                    int valuepos = line.indexOf(":")+1;
                    System.out.println(line.substring(valuepos));
                }
                if((pos = line.indexOf("Artist:"))!=-1){
                    int valuepos = line.indexOf(":")+1;
                    System.out.println(line.substring(valuepos));
                }
            }
            //timingPoints
            if(header.equals("[TimingPoints]")){
                if(offset != 0) continue;
                
                String []tokens = line.split(",");
                if(Double.parseDouble(tokens[1])< 0){
                    continue;
                }
                offset = Integer.parseInt(tokens[0]);
                beatLength = Double.parseDouble(tokens[1]);
                sig = Integer.parseInt(tokens[2]);
                double bpm = (double)Math.round(1/beatLength*1000*60);
                System.out.println(bpm);
                double seglen = beatLength * sig;
                System.out.println("segment length : "+seglen);
            }
            if(header.equals("[HitObjects]")){
                //ln : tokens[3] = acc, note : tokens[3] = 1
                String []tokens = line.split(",");
                String lanenum = tokens[0];
                int notest = Integer.parseInt(tokens[2]);
                int type = Integer.parseInt(tokens[3]);
                int noteed = Integer.parseInt((tokens[5].split(":"))[0]);
                int secIndex = (int)((notest-offset)/(beatLength*sig));
                int lnsecIndex = (int)((noteed-offset)/(beatLength*sig));
                int noteypos = (int)Math.round((((notest-offset)/(beatLength*sig))-secIndex)*acc);
                int notecypos = (int)Math.round((((noteed-offset)/(beatLength*sig))-lnsecIndex)*acc);
                //ln
                if(timeLine.Sections.isEmpty()){
                    timeLine.Sections.add(new Section(0, acc, lnnum, id));
                }
                if(type == 128){
                    if(timeLine.Sections.getLast().getIndex() < lnsecIndex){
                        for(int i=timeLine.Sections.getLast().getIndex()+1; i<=lnsecIndex; i++){
                            timeLine.Sections.add(new Section(i, acc, lnnum, id));
                        }
                    }
                    ChargeLaneParseNote(lanenum, secIndex, noteypos, lnsecIndex, notecypos);
                }
                else{
                    if(timeLine.Sections.getLast().getIndex() < secIndex){
                        for(int i=timeLine.Sections.getLast().getIndex()+1; i<=secIndex; i++){
                            timeLine.Sections.add(new Section(i, acc, lnnum, id));
                        }
                    }
                    LaneParseNote(lanenum, noteypos, secIndex);
                }
            }
        }
        return timeLine;
    }
}
