#include <iostream>
#include <algorithm>
#include <vector>
#include "MyGraph.h"

ostream& operator<<(ostream& os, const Link& l)
{
   os << l.v1 << " " << l.v2 << " " << l.w;
   return os;
}

MyGraph::MyGraph(int n)
{
   numVertices = n;
}

MyGraph::MyGraph(const MyGraph& g)
{
   numVertices = g.numVertices;
   edges = g.edges;
}

bool MyGraph::addEdge(int a, int b, float w)
{
   if(a <= 0 || b <= 0 || a > numVertices || b > numVertices){
      return false;
   }
   for(const auto& edge : edges){
      if((edge.v1 == a && edge.v2 == b) || (edge.v1 == b && edge.v2 == a)){
         return false;
      }
   }
   Link newEdge(a,b,w);
   edges.push_back(newEdge);
   return true;
}

void MyGraph::output(ostream& os)
{
   os << numVertices << endl;
   for(const auto& edge : edges){
      os << min(edge.v1, edge.v2) <<  " " << max(edge.v1,edge.v2) << " " << edge.w << endl;
   }
}

vector<Link> MyGraph::MST()
{
   /* Link l1;
   l1.v1 = 1;
   l1.v2 = 2;
   l1.w = 0.1;
   vector<Link> res;
   res.push_back(l1);
   return res; */
   
   vector<Link> sortedEdges = edges;
   sort(sortedEdges.begin(), sortedEdges.end(), [](const Link& a, const Link& b){
      return a.w < b.w;
      }
   );

   vector<int> parent(numVertices + 1);
   for(int i = 0; i <= numVertices; i++){
      parent[i] = i;
   }

   function<int(int)> find = [&](int x) -> int {
      if(parent[x] != x){
         parent[x] = find(parent[x]);
      }
      return parent[x];
   };

   vector<Link> mstEdges;
   for(const auto& edge : sortedEdges){
      int root1 = find(edge.v1);
      int root2 = find(edge.v2);

      if(root1 != root2){
         mstEdges.push_back(edge);
         parent[root1] = root2;

         if(mstEdges.size() == numVertices - 1) break;
      }
   }

   return mstEdges;
}


MyHelper::MyHelper()
{
}

vector<Link> Task1(int n, vector<Link>& pipes, MyHelper& helper)
{
   vector<Link> res = pipes;
   return res;
}

vector<Link> Task2(int n, vector<Link>& pipes, Link newPipe, MyHelper helper)
{
   vector<Link> res = pipes;
   return res;
}
