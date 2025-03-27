#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <functional>
#include "MyGraph.h"


struct {

  bool operator()(Link l1, Link l2) const {return (l1.v1 < l2.v1) || ((l1.v1 == l2.v1) && (l1.v2 < l2.v2) );}
} myless;

main()
{
   ifstream ifile("test2_1.txt");
   int n, e;
   vector<float> satcost;

   ifile >> n >> e;

   vector<Link> l, l2;
   for (int i = 0; i < e; i++)
	{
	   Link s;
	   ifile >> s.v1 >> s.v2 >> s.w;
	   l.push_back(s);
	   l2.push_back(s);
	}
   int m;
   ifile >> m;
   vector<Link> test;
   for (int i = 0; i < m; i++)
	{
	   Link s;
	   ifile >> s.v1 >> s.v2 >> s.w;
	   test.push_back(s);
	}

   MyHelper helper;

   vector<Link>  res1 = Task1(n, l, helper);
   float w1 = 0.0;
   for (int i = 0; i < res1.size(); i++)
	{
           w1 += res1[i].w;
 	   if (res1[i].v1 > res1[i].v2)
		{
		   int temp = res1[i].v1;
		   res1[i].v1 = res1[i].v2;
		   res1[i].v2 = temp;
		}

	}
   sort(res1.begin(), res1.end(), myless);

   cout << "Task 1 :  [";
   for (int i = 0; i < res1.size(); i++)
	{
           if (i > 0)
		cout << ", ";
	   cout << "(" << res1[i].v1 << ", " << res1[i].v2 << ")";
	}
   cout <<"] ";
   cout << w1;
   cout << "\n";

   for (int i = 0; i < m; i++)
	{
   	   vector<Link> res2 = Task2(n, l2, test[i], helper);
	   cout << "(" << test[i] << ") : ";
	   if (res2.size())
		{
		   cout << "replaced edge: " <<  "(" << res2[0] << ") ";
		}
	   else
		{
		   cout << "not replaced";
		}
	  cout << endl;
	}

}
