package ChartModel.Lane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LaneID {
    public HashMap<String, Integer>sid= new HashMap<>(){{
        put("11",1);put("12",2);put("13",3);put("14",4);put("15",5);put("18",6);put("19",7);put("16",8);
    }};
    public HashMap<String, Integer>csid = new HashMap<>(){{
        put("51",41);put("52",42);put("53",43);put("54",44);put("55",45);put("58",46);put("59",47);put("56",48);
    }};
    public HashMap<String, Integer>sofid = new HashMap<>(){{
        put("03",13);put("08",18);
    }};

    public ArrayList<Integer>SIDint= new ArrayList<>(List.of(1,2,3,4,5,6,7,8));
    public ArrayList<Integer>CSIDint= new ArrayList<>(List.of(41,42,43,44,45,46,47,48));
    public ArrayList<Integer>SOFIDint= new ArrayList<>(List.of(13));
}
