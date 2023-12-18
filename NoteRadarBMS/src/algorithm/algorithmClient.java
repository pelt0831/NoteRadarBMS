public class algorithmClient {
    public static void main(String args[]) {
        Parameter parameter1 = new Parameter();
        Parameter parameter2 = new Parameter();
        Parameter parameter3 = new Parameter();
        Parameter parameter4 = new Parameter();
        Parameter parameter5 = new Parameter();
        Parameter parameter6 = new Parameter();

        parameter1.setAlgorithm(new notesAlgorithm());
        parameter2.setAlgorithm(new chordAlgorithm());
        parameter3.setAlgorithm(new peakAlgorithm());
        parameter4.setAlgorithm(new scratchAlgorithm());
        parameter5.setAlgorithm(new soflanAlgorithm());
        parameter6.setAlgorithm(new chargeAlgorithm());

        String param1Name = parameter1.name();
        float param1 = parameter1.work();
        String param2Name = parameter2.name();
        float param2 = parameter2.work();
        String param3Name = parameter3.name();
        float param3 = parameter3.work();
        String param4Name = parameter4.name();
        float param4 = parameter4.work();
        String param5Name = parameter5.name();
        float param5 = parameter5.work();
        String param6Name = parameter6.name();
        float param6 = parameter6.work();

        System.out.println(param1Name);
        System.out.println(param1);
        System.out.println(param2Name);
        System.out.println(param2);
        System.out.println(param3Name);
        System.out.println(param3);
        System.out.println(param4Name);
        System.out.println(param4);
        System.out.println(param5Name);
        System.out.println(param5);
        System.out.println(param6Name);
        System.out.println(param6);
        System.out.println("/////////////////////");

        parameter6.setAlgorithm(new newAlgorithm());
        param6Name = parameter6.name();
        param6 = parameter6.work();

        System.out.println(param6Name);
        System.out.println(param6);

        // param1 = parameter1.work();
        param6 = parameter6.work();

    }
}
