// AllTest.java, created Mon Mar 13 18:30:16 2006 by akiezun
// Copyright (C) 2006 Adam Kiezun <akiezun@mit.edu>
// Licensed under the Modified BSD Licence; see COPYING for details.
package jpaul.tests;

import jpaul.Constraints.SetConstraints.TestSetConstraints;
import jpaul.DataStructs.MapSetRelationTests;
import jpaul.DataStructs.Relation3Tests;
import jpaul.DataStructs.TestSetFactory;
import jpaul.DataStructs.TestUnionFind;
import jpaul.DataStructs.WorkQueueTests;
import jpaul.Graphs.TestDiGraph;
import jpaul.Graphs.TestBinTreeUtil;
import jpaul.RegExps.TestNFA;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

public class AllTests extends TestCase {

    public static Test suite() {
	TestSuite suite= new TestSuite();
	suite.addTestSuite(TestSetConstraints.class);
	suite.addTestSuite(TestSetFactory.class);
	suite.addTestSuite(TestUnionFind.class);
	suite.addTestSuite(TestNFA.class);
	suite.addTestSuite(WorkQueueTests.class);
	suite.addTestSuite(Relation3Tests.class);
	suite.addTestSuite(TestDiGraph.class);
	suite.addTestSuite(TestBinTreeUtil.class);
        suite.addTestSuite(MapSetRelationTests.class);
	return suite;
    }
    
    public static void main(String[] args) {
	TestRunner.run(AllTests.suite());
    }
}
