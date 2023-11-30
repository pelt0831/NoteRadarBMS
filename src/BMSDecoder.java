import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        FileInputStream fStream = new FileInputStream("C:\\Users\\VRLABS\\Documents\\GitHub\\textage2bms\\rootage.bms");
        InputStreamReader iStreamReader = new InputStreamReader(fStream, "UTF-16");
        BufferedReader br = new BufferedReader(iStreamReader);
        String line;
        while((line = br.readLine())!=null){
            int pos = 0;
            if(line.length()<2){
                continue;
            }
            if((pos = line.indexOf("#TITLE"))!=-1){
                //System.out.println(pos);
                int len = "#TITLE".length(); pos += len+1;
                System.out.println(line.substring(pos));
            }
            //if(line.length() < 30){System.out.println(pos+""+line);}
        }
        final long time = System.currentTimeMillis();
        
    }
}
