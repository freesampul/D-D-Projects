public class moveToBackTester {
    public static void main(String[] args)
{
MyArrayList<Integer> ints = new MyArrayList<Integer>();
//Set max to 10000000 for an efficiency check, or 100 for an accuracy check
int max = 10000000;
for (int i = 0; i < max; i++)
{
if (i % 5 == 0 || i % 17 == 0)
ints.add(new Integer(1));
else
ints.add(new Integer(2));
}
long startTime = System.currentTimeMillis();
ints.moveToBack( new Integer(1));
long totalTime = System.currentTimeMillis() - startTime;
System.out.println("Total milliseconds for moveToBack is " + totalTime);
boolean mistake = false;
for (int i = 0; !mistake && i < max*6/10; i++)
{
if (ints.get(i).equals(new Integer(1)))
mistake = true;
}
if (!mistake)
System.out.println("Probably correct!");
else
System.out.println("Incorrect!");
}
}
