import java.io.IOException;
import java.util.ArrayList;

import ChartModel.Timeline;
import ChartModel.Iterator.Iterator;
import ChartModel.Note.Note;

public class peakAlgorithm implements Algorithm {
	public float work() {
		float maxPeakDifficulty = (float) 30;
		float paramCount = 0;
		int barForPeak = 72;
		int barForPeakCount = 0;
		float maxPeak = 0;
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
					if (barForPeakCount == barForPeak) {
						if (maxPeak < paramCount)
							maxPeak = paramCount;
						barForPeakCount = 0;
						paramCount = 0;
					}
					barForPeakCount++;
					if (note == null) {
						// System.out.print(" ");
						continue;
					} else {
						int lanenum = note.getLane();
						String ouString = "";
						if (lanenum == 13) {
							// System.out.print(((SOFLAN) note).getBPM());
						}
						if (lanenum < 10) {
							// normal Note
							if (count[lanenum] == 2) {
								ouString = "|";
							} else {
								if (lanenum % 2 != 0) {
									paramCount++;
									ouString = "■";
								} else {
									paramCount++;
									ouString = "□";
								}
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
		param = maxPeak / maxPeakDifficulty * 100;
		return param;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "peak";
	}
}
