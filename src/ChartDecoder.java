import java.nio.file.Path;

public abstract class ChartDecoder {
    int lntype;
    //DecodeLog arraylist?

    public static ChartDecoder getDecoder(Path p) {
		final String s = p.getFileName().toString().toLowerCase();
		if (s.endsWith(".bms")) {
			return new BMSDecoder();
		} else if (s.endsWith(".osu")) {
			//return new OSUDecoder(BMSModel.LNTYPE_LONGNOTE);
		}
		return null;
	}

}
