package HackerrankCodeTest;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author RShaw
 *
 *         We define the rating for Alice's challenge to be the triplet , and
 *         the rating for Bob's challenge to be the triplet .
 * 
 *         Your task is to find their comparison points by comparing with , with
 *         , and with .
 * 
 *         If , then Alice is awarded point. If , then Bob is awarded point. If
 *         , then neither person receives a point. Comparison points is the
 *         total points a person earned.
 */
public class Triplet {
	
	static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
		int aRank = 0;
		int bRank = 0;
		for(int i = 0;i<a.size();i++) {
			if(a.get(i) > b.get(i)) {
				aRank++;
			}else if(a.get(i) < b.get(i)) {
				bRank++;
			}else {
				//Do Nothing
			}
		}
		List<Integer> rank = new ArrayList<>();
		rank.add(aRank);
		rank.add(bRank);
		return rank;


    }
	
	
	public static void main(String[] args) {
		List<Integer> a = Arrays.asList( 1,2,3,4);
		List<Integer> b = Arrays.asList( 2,2,4,1);
		List<Integer> result = compareTriplets(a, b);
		result.forEach(System.out::println);
	}

}
