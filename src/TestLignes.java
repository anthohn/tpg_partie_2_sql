import java.util.HashMap;

public class TestLignes {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        HashMap<String, String> hm = new HashMap<>();
        hm.put("prenom", "Jaqcue");
        hm.put("nom", "Truc");


        hm.put("prenom", "Jacques");

        for (String k : hm.keySet()) {
            System.out.println(k + " : " + hm.get(k));
        }

        System.out.println(hm.values());
    }
}
