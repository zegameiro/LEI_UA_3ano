# STUDENT NAME: José Gameiro
# STUDENT NUMBER: 108840

# DISCUSSED TPI-1 WITH: (names and numbers): 
# Pedro Ramos: 107348
# João Luís: 107403
# Daniel Madureira: 107603
# Bárbara Galiza: 105937

from tree_search import *
import math

class OrderDelivery(SearchDomain):

    def __init__(self,connections, coordinates):
        self.connections = connections
        self.coordinates = coordinates

    def actions(self,state):
        city = state[0]
        actlist = []
        for (C1,C2,D) in self.connections:
            if (C1==city):
                actlist += [(C1,C2)]
            elif (C2==city):
               actlist += [(C2,C1)]
        return actlist 

    def result(self,state,action):
        (city1, city2) = action

        if state[0] == city1:
            return (city2, state[1].copy())

    def satisfies(self, state, goal):
        if state[0] in goal[1] and state[0] not in state[1]:
            state[1].append(state[0])

        return set(goal[1]).issubset(state[1]) and goal[0] == state[0]


    def cost(self, state, action):
        (city1, city2) = action

        for city_initial, city_final, dist in self.connections:

            if (city_initial, city_final) in [(city1, city2), (city2, city1)]:
                return dist


    def heuristic(self, state, goal):
        cur_city = state[0]
        cp_goal = state[1].copy()

        if cp_goal == []:
            cp_goal.append(goal[0])

        if state[0] == goal[0]:
            return 0
        
        while cp_goal != []:

            distances = []

            for city in cp_goal:
                distance = math.dist(self.coordinates[cur_city], self.coordinates[city])

                if distance in distances:
                    continue

                else:
                    distances.append(distance)

            min_distance = min(distances)
            cur_city = cp_goal[distances.index(min_distance)]
            cp_goal.remove(cur_city)

        return min_distance
    
 
class MyNode(SearchNode):

    def __init__(self, state, parent, cost=0, heuristic=0, eval=0, marked_for_deletion=False):
        super().__init__(state,parent)
        self.depth = 0 if parent == None else parent.depth + 1
        self.cost = cost
        self.heuristic = heuristic
        self.eval = eval
        self.marked_for_deletion = marked_for_deletion
        self.children = None

    def __str__(self):
        return f"n({self.state}, {self.parent.state}, {self.eval}, {self.cost}, {self.heuristic})"


class MyTree(SearchTree):

    def __init__(self,problem, strategy='breadth',maxsize=None):
        super().__init__(problem,strategy)
        root = MyNode(problem.initial, None, heuristic=problem.domain.heuristic(problem.initial, problem.goal), eval=problem.domain.heuristic(problem.initial, problem.goal) + 0)
        self.open_nodes = [root]
        self.non_terminals = 0
        self.terminals = len(self.open_nodes)
        self.solution = None
        self.maxsize = maxsize
        self.treesize = self.non_terminals + self.terminals  

    def astar_add_to_open(self,lnewnodes):
        self.open_nodes.extend(lnewnodes)
        self.open_nodes.sort(key=lambda node: (node.eval, node.state))


    def search2(self):
        while self.open_nodes != []:
            node = self.open_nodes.pop(0)

            if self.problem.goal_test(node.state):
                self.solution = node
                self.terminals = len(self.open_nodes)+1
                return self.get_path(node)
            
            self.non_terminals += 1
            self.treesize = self.non_terminals + len(self.open_nodes)
            lnewnodes = []

            for a in self.problem.domain.actions(node.state):
                newstate = self.problem.domain.result(node.state,a)

                if newstate not in self.get_path(node):

                    newnode = MyNode(
                        newstate,
                        node,
                        cost = node.cost + self.problem.domain.cost(node.state, a),
                        heuristic = round(self.problem.domain.heuristic(newstate, self.problem.goal)),
                        eval = round(self.problem.domain.heuristic(newstate, self.problem.goal) + (node.cost + self.problem.domain.cost(node.state, a)),
                    ))
                    lnewnodes.append(newnode)
                    node.children = lnewnodes

            if self.strategy == "A*" and self.maxsize is not None:
                self.manage_memory()
            self.add_to_open(lnewnodes)
        return None

    def manage_memory(self):
        while self.treesize > self.maxsize:
            nodes_to_delete = []

            self.open_nodes.sort(key=lambda node: node.eval, reverse=True)

            for node in self.open_nodes:
                if not node.marked_for_deletion:
                    nodes_to_delete.append(node)
                    node.marked_for_deletion = True
                    break

            nodes_to_remove = []
            parents_to_add = []

            for node in nodes_to_delete:
                parent = node.parent
                siblings = parent.children
                if all(sibling.marked_for_deletion for sibling in siblings):
                    nodes_to_remove.extend(siblings)
                    parent.marked_to_delete = True
                    parent.eval = min(sibling.eval for sibling in siblings)
                    self.non_terminals -= 1
                    parents_to_add.append(parent)
    
            if nodes_to_remove:
                self.open_nodes = [node for node in self.open_nodes if node not in nodes_to_remove]
                self.treesize = self.non_terminals + len(self.open_nodes)
                self.add_to_open(parents_to_add)

        for node in self.open_nodes:
            node.marked_for_deletion = False

def orderdelivery_search(domain,city,targetcities,strategy='breadth',maxsize=None):
    state = (city, [])
    goal = (city, targetcities)
    problem = SearchProblem(domain, state, goal)
    search = MyTree(problem, strategy, maxsize)

    search.search2()

    path = []
    for state, goals in search.get_path(search.solution):
        path.append(state)
        
    return search, path
