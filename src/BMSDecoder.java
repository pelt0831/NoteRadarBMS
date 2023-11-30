import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Logger;

public class BMSDecoder extends ChartDecoder{
    public BMSDecoder(){
        try {
            this.decode();            
        } catch (Exception e) {
            System.out.println("??");
        }

    }

    public void decode()throws IOException{
        Path path = Paths.get("C:\\Users\\VRLABS\\Documents\\GitHub\\textage2bms\\rootage.bms");
        // byte[] data = Files.readAllBytes(path);
        FileInputStream fStream = new FileInputStream("C:\\Users\\BOCCHI_THE_LAPTOP\\Documents\\GitHub\\textage2bms\\rootage.bms");
        InputStreamReader iStreamReader = new InputStreamReader(fStream, "UTF-16");
        BufferedReader br = new BufferedReader(iStreamReader);
        String line;
        boolean isData = false;
        ArrayList<Section> TimeLine = new ArrayList<>();
        String sid[] = {"11","12","13","14","15","18","19","16"};


        while((line = br.readLine())!=null){
            int pos = 0;
            if(line.length()<2){
                continue;
            }
            if((pos = line.indexOf("*---------------------- MAIN DATA FIELD"))!=-1){
                //HEADER
                isData = true;
                continue;
            }
            if((pos = line.indexOf("*---------------------- HEADER FIELD"))!=-1){
                isData = false;
                continue;
            }
            if(!isData){
                if((pos = line.indexOf("#TITLE"))!=-1){
                    //System.out.println(pos);
                    int len = "#TITLE".length(); pos += len+1;
                    System.out.println(line.substring(pos));
                }
                else if((pos = line.indexOf("#ARTIST"))!=-1){
                    //System.out.println(pos);
                    int len = "#ARTIST".length(); pos += len+1;
                    System.out.println(line.substring(pos));
                }
                else if((pos = line.indexOf("#BPM"))!=-1){
                    //System.out.println(pos);
                    int len = "#BPM".length(); pos += len+1;
                    System.out.println(line.substring(pos));
                }
            }
            else{
                //MAIN DATA FIELD
                if(line.charAt(0)=='#'){
                    String xxxyy = line.substring(1, 6);
                    String xxx = xxxyy.substring(0,3);
                    String yy = xxxyy.substring(3, 5);
                    String Bar = line.substring(7);
                    int xxxi = Integer.parseInt(xxx);
                    if(Bar.length()%2 != 0){
                        System.out.println("Data Field Error Has occured");
                    }
                    if(TimeLine.isEmpty() || TimeLine.getLast().getIndex() <= xxxi){
                        TimeLine.add(new Section(xxxi, Bar.length()%2, 7, null))
                    }
                    if(yy == "11"){
                        for(int i=0;i<Bar.length()/2; i++){
                            String ii = Bar.substring(i*2, i*2+2);
                            if(ii != "00"){
                                TimeLine.getLast();
                            }
                        }
                    }
                    if(yy == "12"){
                        for(int i=0;i<Bar.length()/2; i++){
                            String ii = Bar.substring(i*2, i*2+2);
                            if(ii != "00"){
                                FullLane.get(0).addNotes(Integer.parseInt(xxx),Bar.length()/2,i);
                            }
                        }
                    }
                    if(yy == "13"){
                        for(int i=0;i<Bar.length()/2; i++){
                            String ii = Bar.substring(i*2, i*2+2);
                            if(ii != "00"){
                                FullLane.get(0).addNotes(Integer.parseInt(xxx),Bar.length()/2,i);
                            }
                        }
                    }
                    if(yy == "14"){
                        for(int i=0;i<Bar.length()/2; i++){
                            String ii = Bar.substring(i*2, i*2+2);
                            if(ii != "00"){
                                FullLane.get(0).addNotes(Integer.parseInt(xxx),Bar.length()/2,i);
                            }
                        }
                    }
                    if(yy == "15"){
                        for(int i=0;i<Bar.length()/2; i++){
                            String ii = Bar.substring(i*2, i*2+2);
                            if(ii != "00"){
                                FullLane.get(0).addNotes(Integer.parseInt(xxx),Bar.length()/2,i);
                            }
                        }
                    }
                    if(yy == "18"){
                        for(int i=0;i<Bar.length()/2; i++){
                            String ii = Bar.substring(i*2, i*2+2);
                            if(ii != "00"){
                                FullLane.get(0).addNotes(Integer.parseInt(xxx),Bar.length()/2,i);
                            }
                        }
                    }
                    if(yy == "19"){
                        for(int i=0;i<Bar.length()/2; i++){
                            String ii = Bar.substring(i*2, i*2+2);
                            if(ii != "00"){
                                FullLane.get(0).addNotes(Integer.parseInt(xxx),Bar.length()/2,i);
                            }
                        }
                    }
                    if(yy == "16"){
                        for(int i=0;i<Bar.length()/2; i++){
                            String ii = Bar.substring(i*2, i*2+2);
                            if(ii != "00"){
                                FullLane.get(0).addNotes(Integer.parseInt(xxx),Bar.length()/2,i);
                            }
                        }
                    }
                    


                    System.out.println(xxx+""+yy+" "+Bar.length());
                    
                    //SCRATCH = 16
                    //1 == 
                    //2 == 
                    //3 == 
                    //4 == 
                    //5 == 
                    //6 == 
                    //7 == 
                }
            }
            //if(line.length() < 30){System.out.println(pos+""+line);}
        }
        final long time = System.currentTimeMillis();
        
    }
}
