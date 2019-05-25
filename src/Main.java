import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        /*list.set(0,1);
        list.set(1,1);*/
        System.out.println(list.get(0) == list.get(1));

    }
}
