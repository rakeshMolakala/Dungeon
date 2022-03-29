package model;

class UnionFind {
  int[] parent;

  UnionFind(int[] parent) {
    this.parent = parent;
  }

  int find(int x) {
    if (parent[x] != x) {
      parent[x] = find(parent[x]);
    }
    return parent[x];
  }

  void union(int x, int y) {
    int xRoot = find(x);
    int yRoot = find(y);
    if (xRoot != yRoot) {
      parent[xRoot] = yRoot;
    }
  }
}
