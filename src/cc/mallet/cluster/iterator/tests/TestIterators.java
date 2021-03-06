/*
 * Copyright (c) 2014. Seonggyu Lee. All Rights Reserved.
 * User: Seonggyu Lee
 * Date: 14. 9. 30 오후 6:22
 * Created Date : $today.year.month.day
 * Last Modified : 14. 9. 30 오후 6:22
 * User email: shalomeir@gmail.com
 */

package cc.mallet.cluster.iterator.tests;

import cc.mallet.cluster.Clustering;
import cc.mallet.cluster.iterator.*;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import cc.mallet.util.Randoms;

import junit.framework.*;

/**
 *
 * @author "Aron Culotta" <culotta@degas.cs.umass.edu>
 * @version 1.0
 * @since 1.0
 * @see junit.framework.TestCase
 */
public class TestIterators extends TestCase
{
	public TestIterators (String name)
	{
		super (name);
	}

	private Clustering generateClustering (InstanceList instances) {
		int[] labels = new int[]{0,0,0,1,1,1,2,2,2,2};
		return new Clustering(instances, 3, labels);
	}
		
	public void testEvaluators ()
	{
		Randoms random = new Randoms(1);
		InstanceList instances = new InstanceList(random, 100, 2).subList(0,10);
		System.err.println(instances.size() + " instances");
		Clustering clustering = generateClustering(instances);
		System.err.println("clustering=" + clustering);

		System.err.println("ClusterSampleIterator");
		NeighborIterator iter = new ClusterSampleIterator(clustering,
																											random,
																											0.5,
																											10);
		while (iter.hasNext()) {
			Instance instance = (Instance)iter.next();
			System.err.println(instance.getData() + "\n");
		}
		
		System.err.println("\n\nPairSampleIterator");
		iter = new PairSampleIterator(clustering,
																	random,
																	0.5,
																	10);
		while (iter.hasNext()) {
			Instance instance = (Instance)iter.next();
			System.err.println(instance.getData() + "\n");
		}

		System.err.println("\n\nAllPairsIterator");
		iter = new AllPairsIterator(clustering);																
		while (iter.hasNext()) {
			Instance instance = (Instance)iter.next();
			System.err.println(instance.getData() + "\n");
		}
}

	public static Test suite ()
	{
		return new TestSuite (TestIterators.class);
	}

	protected void setUp ()
	{
	}

	public static void main (String[] args)
	{
		junit.textui.TestRunner.run (suite());
	}
	
}
		
