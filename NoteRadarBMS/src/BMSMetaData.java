import java.util.HashMap;
import java.util.Map;

import ChartModel.Lane.AbstractLane;
import ChartModel.Note.NormalNote;
import ChartModel.Note.Note;

public class BMSMetaData implements Comparable{
    //show player number
	private int player;
	//number of Key
	private Mode mode;
	//Song Title
	private String title = "";
	//Sub Title
	private String subTitle = "";
	//Genre
	private String genre = "";
	//Artist
	private String artist = "";
	//Sub Artist
	private String subartist = "";
    //Banner
	private String banner = "";
	//BPM
	private double bpm;
	//Level
	private String playlevel = "";
	//(0:beginner, 1:normal, 2:hyper, 3:another, 4:insane)
	private int difficulty = 0;
    //???
	private double total = 100;
	//???
	private TotalType totalType = TotalType.BMSON;
	//MD5
	private String md5 = "";
	//SHA256
	private String sha256 = "";
    //Longnote mode
	private int lnmode = LongNote.TYPE_UNDEFINED;
    //LongNote object
	private int lnobj = -1;

	public static final int LNTYPE_LONGNOTE = 0;
	public static final int LNTYPE_CHARGENOTE = 1;
	public static final int LNTYPE_HELLCHARGENOTE = 2;

	/**
	 * 時間とTimeLineのマッピング
	 */
	private TimeLine[] timelines = new TimeLine[0];

	private ChartInformation info;

	private Map<String, String> values = new HashMap<>();

    {

    }
	public BMSMetaData() {
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null) {
			this.title = "";
			return;
		}
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		if (subTitle == null) {
			this.subTitle = "";
			return;
		}
		this.subTitle = subTitle;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		if (genre == null) {
			this.genre = "";
			return;
		}
		this.genre = genre;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		if (artist == null) {
			this.artist = "";
			return;
		}
		this.artist = artist;
	}

	public String getSubArtist() {
		return subartist;
	}

	public void setSubArtist(String artist) {
		if (artist == null) {
			this.subartist = "";
			return;
		}
		this.subartist = artist;
	}

	public double getBpm() {
		return bpm;
	}

	public void setBpm(double bpm) {
		;
		this.bpm = bpm;
	}

	public String getPlaylevel() {
		return playlevel;
	}

	public void setPlaylevel(String playlevel) {
		this.playlevel = playlevel;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getMinBPM() {
		double bpm = this.getBpm();
		for (TimeLine time : timelines) {
			final double d = time.getBPM();
			bpm = (bpm <= d) ? bpm : d;
		}
		return bpm;
	}

	public double getMaxBPM() {
		double bpm = this.getBpm();
		for (TimeLine time : timelines) {
			final double d = time.getBPM();
			bpm = (bpm >= d) ? bpm : d;
		}
		return bpm;
	}

	public void setAllTimeLine(TimeLine[] timelines) {
		this.timelines = timelines;
	}

	public TimeLine[] getAllTimeLines() {
		return timelines;
	}

	public long[] getAllTimes() {
		TimeLine[] times = getAllTimeLines();
		long[] result = new long[times.length];
		for (int i = 0; i < times.length; i++) {
			result[i] = times[i].getTime();
		}
		return result;
	}

	public int getLastTime() {
		return (int) getLastMilliTime();
	}

	public long getLastMilliTime() {
		final int keys = mode.key;
		for (int i = timelines.length - 1;i >= 0;i--) {
			final TimeLine tl = timelines[i];
			for (int lane = 0; lane < keys; lane++) {
				if (tl.existNote(lane) || tl.getHiddenNote(lane) != null
						|| tl.getBackGroundNotes().length > 0 || tl.getBGA() != -1
						|| tl.getLayer() != -1) {
					return tl.getMilliTime();
				}
			}
		}
		return 0;
	}

	public int getLastNoteTime() {
		return (int) getLastNoteMilliTime();
	}

	public long getLastNoteMilliTime() {
		final int keys = mode.key;
		for (int i = timelines.length - 1;i >= 0;i--) {
			final TimeLine tl = timelines[i];
			for (int lane = 0; lane < keys; lane++) {
				if (tl.existNote(lane)) {
					return tl.getMilliTime();
				}
			}
		}
		return 0;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getFullTitle() {
		return title + (subTitle != null && subTitle.length() > 0 ? " " + subTitle : "");
	}

	public String getFullArtist() {
		return artist + (subartist != null && subartist.length() > 0 ? " " + subartist : "");
	}

	public void setMD5(String hash) {
		this.md5 = hash;
	}

	public String getMD5() {
		return md5;
	}

	public String getSHA256() {
		return sha256;
	}

	public void setSHA256(String sha256) {
		this.sha256 = sha256;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
		for(TimeLine tl : timelines) {
			tl.setLaneCount(mode.key);
		}
	}

	public Mode getMode() {
		return mode;
	}

	public ChartInformation getChartInformation() {
		return info;
	}

	public void setChartInformation(ChartInformation info) {
		this.info = info;
	}
	
	public int[] getRandom() {
		return info != null ? info.selectedRandoms : null;
	}
	
	public String getPath() {
		return info != null && info.path != null ? info.path.toString() : null;
	}
	
	public int getLntype() {
		return info != null ? info.lntype : LNTYPE_LONGNOTE;
	}

	public int getTotalNotes() {
		return BMSModelUtils.getTotalNotes(this);
	}

	public boolean containsUndefinedLongNote() {
		final int keys = mode.key;
		for (TimeLine tl : timelines) {
			for (int i = 0; i < keys; i++) {
				if (tl.getNote(i) != null && tl.getNote(i) instanceof LongNote
						&& ((LongNote) tl.getNote(i)).getType() == LongNote.TYPE_UNDEFINED) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean containsLongNote() {
		final int keys = mode.key;
		for (TimeLine tl : timelines) {
			for (int i = 0; i < keys; i++) {
				if (tl.getNote(i) instanceof LongNote) {
					return true;
				}
			}
		}
		return false;
	}

	public EventLane getEventLane() {
		return new EventLane(this);
	}	

	public AbstractLane[] getLanes() {
		AbstractLane[] lanes = new AbstractLane[mode.key];
		for(int i = 0;i < lanes.length;i++) {
			lanes[i] = new AbstractLane(this, i);
		}
		return lanes;
	}

	public int getLnobj() {
		return lnobj;
	}

	public void setLnobj(int lnobj) {
		this.lnobj = lnobj;
	}

	public int getLnmode() {
		return lnmode;
	}

	public void setLnmode(int lnmode) {
		this.lnmode = lnmode;
	}

	public Map<String, String> getValues() {
		return values;
	}

	public String toChartString() {
		StringBuilder sb = new StringBuilder();
		sb.append("JUDGERANK:" + judgerank + "\n");
		sb.append("TOTAL:" + total + "\n");
		if(lnmode != 0) {
			sb.append("LNMODE:" + lnmode + "\n");			
		}
		double nowbpm = -Double.MIN_VALUE;
		StringBuilder tlsb = new StringBuilder();
		for(TimeLine tl : timelines) {
			tlsb.setLength(0);
			tlsb.append(tl.getTime() + ":");
			boolean write = false;			
			if(nowbpm != tl.getBPM()) {
				nowbpm = tl.getBPM();
				tlsb.append("B(" + nowbpm + ")");
				write = true;
			}
			if(tl.getStop() != 0) {
				tlsb.append("S(" + tl.getStop() + ")");
				write = true;
			}
			if(tl.getSectionLine()) {
				tlsb.append("L");
				write = true;
			}

			tlsb.append("[");
			for(int lane = 0;lane < mode.key;lane++) {
				Note n = tl.getNote(lane);
				if(n instanceof NormalNote) {
					tlsb.append("1");
					write = true;
				} else if(n instanceof LongNote) {
					LongNote ln = (LongNote)n;
					if(!ln.isEnd()) {
						final char[] lnchars = {'l','L','C','H'};
						tlsb.append(lnchars[ln.getType()] + ln.getMilliDuration());
						write = true;
					}
				} else if(n instanceof MineNote) {
					tlsb.append("m" + ((MineNote)n).getDamage());
					write = true;					
				} else {
					tlsb.append("0");						
				}
				if(lane < mode.key - 1) {
					tlsb.append(",");					
				}
			}
			tlsb.append("]\n");
			
			if(write) {
				sb.append(tlsb);
			}
		}
		return sb.toString();
	}

	public JudgeRankType getJudgerankType() {
		return judgerankType;
	}

	public void setJudgerankType(JudgeRankType judgerankType) {
		this.judgerankType = judgerankType;
	}

	public TotalType getTotalType() {
		return totalType;
	}

	public void setTotalType(TotalType totalType) {
		this.totalType = totalType;
	}

	public enum JudgeRankType {
		BMS_RANK, BMS_DEFEXRANK, BMSON_JUDGERANK;
	}

	public enum TotalType {
		BMS, BMSON;
	}
}
