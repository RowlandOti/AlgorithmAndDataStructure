import abc
import numpy as np


####################################################################
#
# The base class represnstation of a Graph with all the interface methods
#
####################################################################

class Graph(abc.ABC):
    def __init__(self, num_vertices, directed=False):
        self.num_vertices = num_vertices
        self.directed = directed

    @abc.abstractmethod
    def addEdge(self, v_one, v_two, weight):
        pass

    @abc.abstractmethod
    def getAdjascentVertices(self, v):
        pass

    @abc.abstractmethod
    def getInwardNoDegree(self, v):
        pass

    @abc.abstractmethod
    def getEdgeWeight(self, v_one, v_two):
        pass

    @abc.abstractmethod
    def display(self):
        pass


####################################################################
#
# A single Node in a Graph is represented by an adjacency set. Every node
# has a vertex id. Each Node is associated with a set of adjacent vertices
#
####################################################################

class Node():
    def __init__(self, v_id):
        self.vertex_id = v_id
        self.adjacency_set = set()

    def addEdge(self, v):
        if self.vertex_id == v:
            raise ValueError("The vertex %d cannot be adjacent to itself" % v)

        self.adjacency_set.add(v)

    def getAdjacentVertices(self, v):
        return sorted(self.adjacency_set)

    ####################################################################
    #
    # Represents a graph as an adjascency set. A graph is a list of Nodes
    # and each Node has a set of adjacent vertices.
    #
    # The graph in this form cannot be used to repesent weighted edges. Only
    # unweighted edges can be represented
    #
    ####################################################################


class AdjascencySetGraph(Graph):
    def __init__(self, num_vertices, directed=False):
        super(AdjascencySetGraph, self).__init__(num_vertices, directed)
        self.vertex_list = []
        for i in range(num_vertices):
            self.vertex_list.append(Node(i))

    def addEdge(self, v_one, v_two, weight=1):
        if v_one >= self.num_vertices or v_two >= self.num_vertices or v_one < 0 or v_two < 0:
            raise ValueError("Vertices %d and %d are out of bounds" % (v_one, v_two))

        if weight != 1:
            raise ValueError("An adjacency set cannot represent edge weights >1")

        self.vertex_list[v_one].addEdge(v_two)

        if self.directed == False:
            self.vertex_list[v_two].addEdge(v_one)

    def getAdjascentVertices(self, v):
        if v < 0 or v >= self.num_vertices:
            raise ValueError("Cannot access vertex %d" % v)

        return self.vertex_list[v].getAdjacentVertices

    def getInwardNoDegree(self, v):
        if v < 0 or v >= self.num_vertices:
            raise ValueError("Cannot access vertex %d" % v)

        no_degree = 0
        for i in range(self.num_vertices):
            if v in self.getAdjascentVertices(i):
                no_degree += 1

        return no_degree

    def getEdgeWeight(self, v_one, v_two):
        return 1

    def display(self):
        for i in range(self.num_vertices):
            for v in self.getAdjascentVertices(i):
                print(i, "-->", v)


####################################################################
#
# Represents a graph as an adjascency matrix. A cell in the matrix has a 
# value when there exists an edge between the vertex represented by the 
# row and column numbers.
# Weighted graphs can hold values >1 in the matrix cells. A value of 0
# indicated that there is no edge
#
####################################################################

class AdjascencyMatrixGraph(Graph):
    def __init__(self, num_vertices, directed=False):
        super(AdjascencyMatrixGraph, self).__init__(num_vertices, directed)
        self.matrix = np.zeros((num_vertices, num_vertices))

    def addEdge(self, v_one, v_two, weight=1):
        if v_one >= self.num_vertices or v_two >= self.num_vertices or v_one < 0 or v_two < 0:
            raise ValueError("Vertices %d and %d are out of bounds" % (v_one, v_two))

        if weight < 1:
            raise ValueError("An edge cannot have weght < 1")

        self.matrix[v_one][v_two] = weight

        if self.directed == False:
            self.matrix[v_two][v_one] = weight

    def getAdjascentVertices(self, v):
        if v < 0 or v >= self.num_vertices:
            raise ValueError("Cannot access vertex %d" % v)

        adjascent_vertices = []
        for i in range(self.num_vertices):
            if self.matrix[v][i] > 0:
                adjascent_vertices.append(i)

        return adjascent_vertices

    def getInwardNoDegree(self, v):
        if v < 0 or v >= self.num_vertices:
            raise ValueError("Cannot access vertex %d" % v)

        no_degree = 0
        for i in range(self.num_vertices):
            if self.matrix[i][v] > 0:
                no_degree += 1

        return no_degree

    def getEdgeWeight(self, v_one, v_two):
        return self.matrix[v_one][v_two]

    def display(self):
        for i in range(self.num_vertices):
            for v in self.getAdjascentVertices(i):
                print(i, "-->", v)


g = AdjascencyMatrixGraph(num_vertices=4, directed=False)

g.addEdge(0, 1)
g.addEdge(0, 2)
g.addEdge(2, 3)

for i in range(4):
    print("Adjascent to: ", i, g.getAdjascentVertices(i))

for i in range(4):
    print("NoInDegree: ", i, g.getInwardNoDegree(i))

for i in range(4):
    for j in g.getAdjascentVertices(i):
        print("Edge weight: ", i, " ", j, " weight: ", g.getEdgeWeight(i, j))

g.display()

g = AdjascencySetGraph(num_vertices=4, directed=False)

g.addEdge(0, 1)
g.addEdge(0, 2)
g.addEdge(2, 3)

for i in range(4):
    print("Adjascent to: ", i, g.getAdjascentVertices(i))

for i in range(4):
    print("NoInDegree: ", i, g.getInwardNoDegree(i))

for i in range(4):
    for j in g.getAdjascentVertices(i):
        print("Edge weight: ", i, " ", j, " weight: ", g.getEdgeWeight(i, j))

g.display()
