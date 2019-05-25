package book3;
import java.util.stream.*;
import java.util.*;
public class P45 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stream<Integer> randomStream = Stream.generate(()->{
			return (int)(10+Math.random()*1001);
		}).limit(100000);
		List<Integer> list = randomStream.collect(Collectors.toList());
		ArrayList<Integer> lst1 = (ArrayList<Integer>)list; 
		LinkedList<Integer> lst2 = (LinkedList<Integer>)list;
		Iterator<Integer> l1 = lst1.iterator();
		Iterator<Integer> l2 = lst2.iterator();
		
		long start = System.nanoTime();
		while(l1.hasNext()) {
			if(l1.next() % 2 == 0) {
				l1.remove();
			}
		}
		long end = System.nanoTime();
		System.out.println((end - start) / 1000);
		start = System.nanoTime();
		while(l2.hasNext()) {
			if(l2.next() % 2 == 0) {
				l2.remove();
			}
		}
		end = System.nanoTime();
		System.out.println((end - start) / 1000);
		
	}

}
