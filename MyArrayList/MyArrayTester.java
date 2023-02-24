import java.util.ArrayList;
public class MyArrayTester {
    public static void main(String [] args) {
        MyArrayList<String> words = new MyArrayList<String>();
        System.out.println(words.isEmpty());
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        words.add(a);
        words.add(b);
        words.add(a);
        words.add(null);
        words.add(d);
        System.out.println(words.toString());
        words.moveToBack(a);
        words.clone();
        System.out.println(words.toString());
    }
}
//Big O for myArrayList Methods - Best/Wrost/Average (when they happen)
//Big O of given code
// Improve written code 
//Size() isEmpty()  get(i) set(i, E)
//Contains (E) - Best & worst 
//String()  O(n) w/StringBuilder
//Increase/Resize O(n)
//Add (e) add(i, ee) Best 0(1) Worst O(n) when there is no room AVERAGE - O(1)
//Remove(i) Best & Worst O(1)/(n) Average - O(n) (removing from middle)
//Remove(E) need to find object O(1) front -> remove O(n) = O(n)
//Contains O(n) -> O(1) = O(n) 
//Remove always has O(n), no best/worst 
