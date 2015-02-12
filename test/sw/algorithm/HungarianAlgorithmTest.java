package sw.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class HungarianAlgorithmTest {

	@Test
	public void hungarianAlgorithmTest1() throws Exception {
		List<String> list1 = new ArrayList<String>();
		list1.add("cats");
		list1.add("dogs");
		list1.add("fish");
		list1.add("birds");
		list1.add("insects");

		List<String> list2 = new ArrayList<String>();
		list2.add("Cattss");
		list2.add("Dogs");
		list2.add("Fi");
		list2.add("Bird");
		list2.add("insects and other");

		double[][] costMatrix = new double[list1.size()][list2.size()];
		for (int j = 0; j < list2.size(); j++) {
			for (int i = 0; i < list1.size(); i++) {
				costMatrix[i][j] = -1 * StringUtils.getJaroWinklerDistance(list1.get(i), list2.get(j));
			}
		}
		HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(costMatrix);
		int[] result = hungarianAlgorithm.execute();
		double totalScore = 0.0;
		for (int i = 0; i < result.length; i++) {
			String configColumn = list1.get(i);

			int fileIndex = result[i];
			if (fileIndex >= 0) {
				String fileColumn = list2.get(fileIndex);
				double score = StringUtils.getJaroWinklerDistance(configColumn, fileColumn);
				totalScore += score;
				System.out.println(configColumn + " " + fileColumn + " " + score);
			}
		}

		double normalizedScore = 0.0;
		if (totalScore > 0.0 && list1.size() > 0.0) {
			normalizedScore = totalScore / list1.size();
		}
		System.out.println("Score: " + totalScore);
		System.out.println("Normalized Score: " + normalizedScore);
	}
}