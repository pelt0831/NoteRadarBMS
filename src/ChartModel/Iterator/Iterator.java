package ChartModel.Iterator;

import java.util.ArrayList;

import ChartModel.Note.Note;

public interface Iterator {
    //if notes exists return timstamp, otherwise -1
    int hasNext() throws Exception;
    ArrayList<Note> Next();
    int FindSection(Note note);
}
