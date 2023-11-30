import java.io.IOException;

import ChartModel.Timeline;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("hey");
        ChartDecoder decoder = new BMSDecoder();
        Timeline timeline = decoder.decode();
        timeline.print();
    }
    
}

