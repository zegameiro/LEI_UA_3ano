#encoding: utf8

# YOUR NAME: José Gameiro
# YOUR NUMBER: 108840

# COLLEAGUES WITH WHOM YOU DISCUSSED THIS ASSIGNMENT (names, numbers):
# - Rafael Ferreira, 107340
# - João Luís, 107403

from semantic_network import *
from constraintsearch import *


class MySN(SemanticNetwork):

    def __init__(self):
        SemanticNetwork.__init__(self)
        # ADD CODE HERE IF NEEDED
        self.assoc_stats = {}

    def query_local(self,user=None,e1=None,rel=None,e2=None, only_ass=None):

        self.query_result = [] 
        lst = []

        for usr, decl in self.declarations.items(): 
            for rela, ent2 in decl.items():
                if user is None or usr == user:
                    if e1 == None or rela[0] == e1:
                        if rel == None or rela[1] == rel:
                            if e2 == None or ent2 == e2:
                                ent1 = rela[0]
                                lst.append([usr, rela[1], ent1, ent2])   

        for declaration in lst:
            if only_ass and (declaration[1] == 'subtype' or declaration[1] == 'member'):
                continue
            else:
                if declaration[1] == 'subtype':
                    self.query_result.append(Declaration(declaration[0],Subtype(declaration[2], declaration[-1])))

                elif declaration[1] == 'member':
                    self.query_result.append(Declaration(declaration[0],Member(declaration[2], declaration[-1])))

                elif isinstance(declaration[-1], set):
                    for elem in declaration[-1]:
                        self.query_result.append(Declaration(declaration[0],Association(declaration[2], declaration[1], elem)))
                else:
                    self.query_result.append(Declaration(declaration[0],Association(declaration[2], declaration[1], declaration[-1])))

        
        return self.query_result # Your code must leave the output in
                          # self.query_result, which is returned here

    def query(self,entity,assoc=None):

        decl = self.query_local(e1=entity, rel='member') + self.query_local(e1=entity, rel='subtype')
        local = self.query_local(e1=entity, rel=assoc, only_ass=True) + self.query_local(e2=entity, rel=assoc, only_ass=True)
        
        predecessors = [d.relation.entity2 for d in decl]

        for r in [self.query(entity=p, assoc=assoc) for p in predecessors]:
            local += r
            
        self.query_result = local

        return self.query_result # Your code must leave the output in
                          # self.query_result, which is returned here

    def predecessors_path(self, parent, user):

        self.query_local(e1=parent, rel='subtype', user=user)

        if len(self.query_result) == 0:
            return [parent]
        
        for obj in self.query_result:
            if obj.relation.entity2 == None:
                return [obj.relation.entity2, parent]
            
            else:
                res = self.predecessors_path(obj.relation.entity2, user)
                return res + [parent]

    def update_assoc_stats(self, assoc, user=None):

        listUsers = []
        listObjects = []

        result = self.query_local(user=user, rel=assoc)

        for decl in result:
            if isObjectName(decl.relation.entity1):
                listUsers.append(decl.relation.entity1)
            if isObjectName(decl.relation.entity2):
                listObjects.append(decl.relation.entity2)

        assos1 = {}
        assos2 = {} 

        def update_assocs(lista, assos):
            for e in lista:
                q = self.query_local(user=user, rel='member',e1=e)
                if q:
                    entity = q[0].relation.entity2
                    if entity not in assos:
                        assos[entity] = 1
                    else:
                        assos[entity] += 1

        update_assocs(listUsers, assos1)
        update_assocs(listObjects, assos2)


        statistics1 = {}
        statistics2 = {}

        def update_statistics(statistics, assos, lst):
            if assos:
                for key, value in assos.items():
                    statistics[key] = value/len(lst)

        update_statistics(statistics1, assos1, listUsers)
        update_statistics(statistics2, assos2, listObjects)

        
        predecessors1 = []
        predecessors2 = []

        def get_predecessors(statistics, predecessors):
            for t in statistics.keys():
                predecessors += [self.predecessors_path(t, user)]
        
        get_predecessors(statistics1, predecessors1)
        get_predecessors(statistics2, predecessors2)

        if predecessors1:
            for predecessor in predecessors1:
                statistics1[predecessor[-1]] = assos1.get(predecessor[-1])/len(listUsers)
                for elem in predecessor[::-1]:
                    if elem == predecessor[-1]:
                        continue
                    if elem not in statistics1:
                        statistics1[elem] = statistics1[predecessor[-1]]
                    else:
                        statistics1[elem] += statistics1[predecessor[-1]]

        if predecessors2:
            for predecessor in predecessors2:
                statistics2[predecessor[-1]] = (assos2.get(predecessor[-1])/len(listObjects))
                for elem in predecessor[::-1]:
                    if elem == predecessor[-1]:
                        continue
                    if elem not in statistics2:
                        statistics2[elem] = statistics2[predecessor[-1]]
                    else:
                        statistics2[elem] += statistics2[predecessor[-1]]

        def evaluate(N, K):
            return N-K+K**(1/2)


        def stats(asssos, lst, statistics):
            N = 0
            for elem in asssos.keys():
                N += asssos.get(elem)
            K = len(lst)-N
            N += K

            for key in asssos.keys():
                statistics[key] = asssos[key] / evaluate(N, K)
        
        stats(assos1, listUsers, statistics1)
        stats(assos2, listObjects, statistics2)

        self.assoc_stats[(assoc, user)] = (statistics1, statistics2)

    

class MyCS(ConstraintSearch):

    def __init__(self,domains,constraints):
        ConstraintSearch.__init__(self,domains,constraints)
        # ADD CODE HERE IF NEEDED
        pass

    def propagate_restrictions(self, newdomains, var):
        val = newdomains[var][0]

        for vr,d in newdomains.items():
            if vr == var:
                continue
            if (vr, var) in self.constraints:
                newd = [v for v in d if self.constraints[vr, var](vr, v, var, val)]
                newdomains[vr] = newd
                if newdomains[vr] == []:
                    return None

        return newdomains


    def search_all(self,domains=None,xpto=None):
        
        self.calls += 1

        if domains==None:
            domains = self.domains

        xpto = []

        if any([lv==[] for lv in domains.values()]):
            return None

        if all([len(lv)==1 for lv in list(domains.values())]):
            for (var1,var2) in self.constraints:
                constraint = self.constraints[var1,var2]
                if not constraint(var1,domains[var1][0],var2,domains[var2][0]):
                    return None 
            return { v:lv[0] for (v,lv) in domains.items() }
       
        for var in domains.keys():
            if len(domains[var])>1:
                for val in domains[var]:
                    newdomains = dict(domains)
                    newdomains[var] = [val]

                    newdomains = self.propagate_restrictions(newdomains,var)

                    if newdomains == None:
                        continue

                    solution = self.search_all(newdomains)
                    if solution != None:
                        if solution not in xpto:
                            if isinstance(solution, dict):
                                #Add to the list
                                xpto.append(solution)
                            else:
                                [ xpto.append(v) for v in solution ]
                return xpto

