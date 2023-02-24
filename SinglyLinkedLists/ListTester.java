

public class ListTester {
    public static void main(String [] args) {
        SinglyLinkedList<Integer> nums = new SinglyLinkedList<Integer>();
        for (int i=0; i< 100; i++) {
            nums.add(i);
    
        }
        System.out.print(nums.indexOf(45));
        System.out.print(nums.indexOf(105));
        System.out.print(nums.toString());
        System.out.print(nums.add(0));
        nums.add(0,3);
        System.out.println(nums.toString());
        nums.remove(45);
        nums.set(1,2);
        nums.remove(0);
        nums.remove(100);
        nums.remove(101);
        nums.add(101);
        nums.add(0, 0);
        System.out.println(nums.toString());
        
        

        
    }
}