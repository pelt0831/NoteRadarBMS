import java.io.File;

/**
 * ChartLoader
 */
public interface ChartLoader {

    public void openFile(File file);

    public void getNoteAt(int timing);

    public void getNotesRange(int start, int end);
}