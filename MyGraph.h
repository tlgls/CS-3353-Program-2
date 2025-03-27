#include <iostream>
#include <vector>

using namespace std;

struct Link {

public:

   int v1, v2;
   float w;

   Link(int v1, int v2, float w) : v1(v1), v2(v2), w(w) {}
};

ostream& operator<<(ostream&, const Link&);

class MyGraph {

public:
   MyGraph(int n);
   MyGraph(const MyGraph&);

   bool addEdge(int a, int b, float w);
   void output(ostream& os);
   vector<Link> MST();

private:
   vector<Link> edges;
   int numVertices;
};

class MyHelper {

public:
    MyHelper();
    int x;
};

vector<Link> Task1(int n, vector<Link>& pipes, MyHelper& helper);
vector<Link> Task2(int n, vector<Link>& pipes, Link newPipe, MyHelper helper);
