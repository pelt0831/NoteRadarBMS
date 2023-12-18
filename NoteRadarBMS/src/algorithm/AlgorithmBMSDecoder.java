import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import ChartModel.Section;
import ChartModel.Timeline;
import ChartModel.Lane.LaneID;

public class AlgorithmBMSDecoder extends ChartDecoder {
    // File path
    String filePath = "C:\\Users\\LG\\Desktop\\텀프로젝트\\NoteRadarBMS\\src\\BMS\\sakazuki.bms";

    // Class Container
    Timeline timeLine = new Timeline();

    // Parsing Util
    private int[] sted = { 0, 0, 0, 0, 0, 0, 0, 0 };
    int id[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
    LaneID LID = new LaneID();

    public AlgorithmBMSDecoder() {
        // try {
        // this.decode();
        // } catch (Exception e) {
        // System.out.println("BMS Decode Failed");
        // }

    }

    private void TrimChange(int i) {
        if (sted[i] == 0) {
            sted[i] = 2;
        } else if (sted[i] == 2) {
            sted[i] = 1;
        } else if (sted[i] == 1) {
            sted[i] = 0;
        }
    }

    private void LaneParseNote(String yy, String Bar) {
        int iid = LID.sid.get(yy);
        // for Each notes
        for (int i = 0; i < Bar.length() / 2; i++) {
            String ii = Bar.substring(i * 2, i * 2 + 2);
            if (!ii.equals("00")) {
                // System.out.println(i);
                timeLine.Sections.getLast().addNotes(iid, i);
            }
        }
    }

    private void ChargeLaneParseNote(String yy, String Bar) {
        int iid = LID.csid.get(yy);
        // for Each notes
        for (int i = 0; i < Bar.length() / 2; i++) {
            String ii = Bar.substring(i * 2, i * 2 + 2);
            if (!ii.equals("00")) {
                // System.out.println(i);
                if (sted[iid - 41] == 2)// if longnote is connected
                    TrimChange(iid - 41);
                timeLine.Sections.getLast().addNotes(iid, i, sted[iid - 41]);
                TrimChange(iid - 41);
            } else {
                if (i == 0 && sted[iid - 41] == 2) {// connected for independent section
                    timeLine.Sections.getLast().addNotes(iid, i, 2);
                }
            }
        }
    }

    private void SOFLANParse(String yy, String Bar) {
        int iid = LID.sofid.get(yy);
        if (iid == 13) {
            // for Each notes
            for (int i = 0; i < Bar.length() / 2; i++) {
                String ii = Bar.substring(i * 2, i * 2 + 2);
                if (!ii.equals("00")) {
                    // HEX to DEC
                    int bpm = Integer.parseInt(ii, 16);
                    // System.out.println(bpm);
                    timeLine.Sections.getLast().addNotes(iid, i, bpm);
                }
            }
        } else if (iid == 18) {

        }
    }

    @Override
    public Timeline decode() throws IOException {
        FileInputStream fStream = new FileInputStream(filePath);
        InputStreamReader iStreamReader = new InputStreamReader(fStream, "UTF-16");
        BufferedReader br = new BufferedReader(iStreamReader);
        timeLine.filePath = filePath;
        String line;
        boolean isData = false;

        while ((line = br.readLine()) != null) {
            int pos = 0;
            if (line.length() < 2) {
                continue;
            }
            if ((pos = line.indexOf("*---------------------- MAIN DATA FIELD")) != -1) {
                // HEADER
                isData = true;
                continue;
            }
            if ((pos = line.indexOf("*---------------------- HEADER FIELD")) != -1) {
                isData = false;
                continue;
            }
            if (!isData) {
                if ((pos = line.indexOf("#TITLE")) != -1) {
                    // System.out.println(pos);
                    int len = "#TITLE".length();
                    pos += len + 1;
                    // System.out.println(line.substring(pos));
                } else if ((pos = line.indexOf("#ARTIST")) != -1) {
                    // System.out.println(pos);
                    int len = "#ARTIST".length();
                    pos += len + 1;
                    // System.out.println(line.substring(pos));
                } else if ((pos = line.indexOf("#BPM")) != -1) {
                    // System.out.println(pos);
                    int len = "#BPM".length();
                    pos += len + 1;
                    // System.out.println(line.substring(pos));
                }
            } else {
                // MAIN DATA FIELD
                if (line.charAt(0) == '#') {
                    String xxxyy = line.substring(1, 6);
                    String xxx = xxxyy.substring(0, 3);
                    String yy = xxxyy.substring(3, 5);
                    String Bar = line.substring(7);
                    int xxxi = Integer.parseInt(xxx);
                    if (Bar.length() % 2 != 0) {
                        System.out.println("Data Field Error Has occured");
                    }
                    if (timeLine.Sections.isEmpty() || timeLine.Sections.getLast().getIndex() < xxxi) {
                        // System.out.println();
                        timeLine.Sections.add(new Section(xxxi, Bar.length() / 2, 8, id));
                    }
                    try {// Parse normal Note
                        int test = LID.sid.get(yy);
                        LaneParseNote(yy, Bar);
                    } catch (Exception e) {
                    }
                    try {// Parse Charge Note
                        int test = LID.csid.get(yy);
                        ChargeLaneParseNote(yy, Bar);
                    } catch (Exception e) {
                    }
                    try {
                        int test = LID.sofid.get(yy);
                        SOFLANParse(yy, Bar);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                }
            }
        }
        return timeLine;
    }

    public void print() {
        for (int i = 0; i < timeLine.Sections.size(); i++) {
            Section section = timeLine.Sections.get(i);
            section.print();
            System.out.println(
                    "////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        }
    }
}
