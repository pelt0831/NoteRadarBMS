import java.io.IOException;
import java.util.ArrayList;

import ChartModel.Timeline;
import ChartModel.Iterator.Iterator;
import ChartModel.Note.Note;
import ChartModel.Note.SOFLAN;

public class soflanAlgorithm implements Algorithm {
	public float work() {
		float maxSoflanDifficulty = (float) 1;
		float previousBPM = 0;
		float soflanCount = 0;
		float barCount = 100;
		float param = 0;
		try {
			// System.out.println("hey");
			ChartDecoder decoder = new AlgorithmBMSDecoder();
			Timeline timeline;
			timeline = decoder.decode();
			// timeline.print();
			// Iterator Print Examples
			Iterator iterator = timeline.iterator();
			int[] count = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			while (iterator.hasNext()) {
				ArrayList<Note> linNotes = iterator.Next();
				for (Note note : linNotes) {
					if (note == null) {
						// System.out.print(" ");
						continue;
					} else {
						int lanenum = note.getLane();
						String ouString = "";
						if (lanenum == 13) {
							if (previousBPM != ((SOFLAN) note).getBPM())
								soflanCount++;
							previousBPM = ((SOFLAN) note).getBPM();
							System.out.print(((SOFLAN) note).getBPM());
						}
						if (lanenum < 10) {
							// normal Note
							if (count[lanenum] == 2) {
								ouString = "|";
							} else {
								if (lanenum % 2 != 0)
									ouString = "■";
								else
									ouString = "□";
							}
						} else if (lanenum > 40) {
							if (note.getTrim() == 2) {
								ouString = "|";
							} else {
								if (lanenum % 2 != 0) {
									ouString = "◆";
								} else {
									ouString = "◇";
								}
							}
						}
						// System.out.print(ouString);
					}
				}
				// System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		param = soflanCount / barCount / maxSoflanDifficulty * 100;
		return param;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "soflan";
	}
}
