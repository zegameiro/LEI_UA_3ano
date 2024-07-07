

# Guiao de representacao do conhecimento
# -- Redes semanticas
# 
# Inteligencia Artificial & Introducao a Inteligencia Artificial
# DETI / UA
#
# (c) Luis Seabra Lopes, 2012-2020
# v1.9 - 2019/10/20
#


# Classe Relation, com as seguintes classes derivadas:
#     - Association - uma associacao generica entre duas entidades
#     - Subtype     - uma relacao de subtipo entre dois tipos
#     - Member      - uma relacao de pertenca de uma instancia a um tipo
#

from collections import Counter
from statistics import mean
from functools import reduce

class Relation:
    def __init__(self,e1,rel,e2):
        self.entity1 = e1
#       self.relation = rel  # obsoleto
        self.name = rel
        self.entity2 = e2
    def __str__(self):
        return self.name + "(" + str(self.entity1) + "," + \
               str(self.entity2) + ")"
    def __repr__(self):
        return str(self)


# Subclasse Association
class Association(Relation):
    def __init__(self,e1,assoc,e2):
        Relation.__init__(self,e1,assoc,e2)

#   Exemplo:
#   a = Association('socrates','professor','filosofia')

class AssocOne(Relation):
    def __init__(self,e1,assoc,e2):
        Relation.__init__(self,e1,assoc,e2)

class AssocNum(Relation):
    def __init__(self,e1,assoc,e2):
        Relation.__init__(self,e1,assoc,e2)

# Subclasse Subtype
class Subtype(Relation):
    def __init__(self,sub,super):
        Relation.__init__(self,sub,"subtype",super)


#   Exemplo:
#   s = Subtype('homem','mamifero')

# Subclasse Member
class Member(Relation):
    def __init__(self,obj,type):
        Relation.__init__(self,obj,"member",type)

#   Exemplo:
#   m = Member('socrates','homem')

# classe Declaration
# -- associa um utilizador a uma relacao por si inserida
#    na rede semantica
#
class Declaration:
    def __init__(self,user,rel):
        self.user = user
        self.relation = rel
    def __str__(self):
        return "decl("+str(self.user)+","+str(self.relation)+")"
    def __repr__(self):
        return str(self)

#   Exemplos:
#   da = Declaration('descartes',a)
#   ds = Declaration('darwin',s)
#   dm = Declaration('descartes',m)

# classe SemanticNetwork
# -- composta por um conjunto de declaracoes
#    armazenado na forma de uma lista


class SemanticNetwork:
    def __init__(self,ldecl=None):
        self.declarations = [] if ldecl==None else ldecl

    def __str__(self):
        return str(self.declarations)
    
    def insert(self,decl):
        self.declarations.append(decl)

    def query_local(self,user=None,e1=None,rel=None,rel_type=None,e2=None):
        self.query_result = \
            [ d for d in self.declarations
                if  (user == None or d.user==user)
                and (e1 == None or d.relation.entity1 == e1)
                and (rel == None or d.relation.name == rel)
                and (rel_type == None or isinstance(d.relation, rel_type))
                and (e2 == None or d.relation.entity2 == e2) ]
        return self.query_result
    
    def show_query_result(self):
        for d in self.query_result:
            print(str(d))

    def list_associations(self):
        associations = set()

        for declaration in self.declarations:
            if isinstance(declaration.relation, Association):
                associations.add(declaration.relation.name)

        return associations
    
    def list_objects(self):
        objects = set()
        for declaration in self.declarations:
            if isinstance(declaration.relation, Member):
                objects.add(declaration.relation.entity1)

        return objects

    def list_users(self):
        users = set()
        for declaration in self.declarations:
            users.add(declaration.user)

        return users
    
    def list_types(self):
        types = set()
        for declaration in self.declarations:
            if isinstance(declaration.relation, Member):
                types.add(declaration.relation.entity2)

            if isinstance(declaration.relation, Subtype):
                types.add(declaration.relation.entity1)
                types.add(declaration.relation.entity2)

        return types
    
    def list_local_associations(self, ent):
        lst = set()
        for declaration in self.declarations:
            if isinstance(declaration.relation, Association) and declaration.relation.entity1 == ent:
                lst.add(declaration.relation.name)

        return lst
    
    def list_relations_by_user(self, ent):
        lst = set()
        for declaration in self.declarations:
            if declaration.user == ent:
                lst.add(declaration.relation.name)

        return lst
    
    def associations_by_user(self, ent):
        associations = set()
        for declaration in self.declarations:
            if declaration.user == ent and isinstance(declaration.relation, Association):
                associations.add(declaration.relation.name)

        total_assoc = len(associations)

        return total_assoc
    
    def list_local_associations_by_entity(self, ent):
        lst = set()
        for declaration in self.declarations:
            if declaration.relation.entity1 == ent and isinstance(declaration.relation, Association):
                lst.add((declaration.relation.name, declaration.user))

        return lst
    
    def predecessor(self, ent1, ent2):
        for declaration in self.declarations:
            if not isinstance(declaration.relation, Member) and not isinstance(declaration.relation, Subtype):
                continue

            if declaration.relation.entity2 != ent1:
                continue

            if declaration.relation.entity1 == ent2:
                return True
            
            return (True and self.predecessor(declaration.relation.entity1, ent2))
            
        return False
    
    def predecessor_path(self, predecessor, antecessor):
        for declaration in self.declarations:
            if not isinstance(declaration.relation, Member) and not isinstance(declaration.relation, Subtype):
                continue

            if declaration.relation.entity2 != predecessor:
                continue

            if declaration.relation.entity1 == antecessor:
                return [predecessor, antecessor]
            
            return [predecessor] + self.predecessor_path(declaration.relation.entity1, antecessor)
            
        return []
    
    def query(self, entity=None, rel=None):
        local = self.query_local(e1=entity, rel=rel, rel_type=Association)

        decl = self.query_local(e1=entity, rel_type=(Member,Subtype))
        predecessors = [d.relation.entity2 for d in decl]

        for r in [self.query(entity=p, rel=rel) for p in predecessors]:
            local += r

        return local
    
    def query2(self, e1=None, rel=None):
        inherited = self.query(entity=e1, rel=rel)

        local = self.query_local(e1=e1, rel=rel, rel_type=(Member, Subtype))

        return local + inherited
    
    def query_cancel(self, e1=None, rel=None):
        local = self.query_local(e1=e1, rel=rel, rel_type=Association)
        local_rels = [r.relation.name for r in local]

        decl = self.query_local(e1=e1, rel_type=(Member,Subtype))
        predecessors = [d.relation.entity2 for d in decl]

        for r in [self.query_cancel(e1=p, rel=rel) for p in predecessors]:
            r_filtered =  [d for d in r if d.relation.name not in local_rels]
            local += r_filtered

        return local
    
    def query_down(self, e2=None, rel=None, first = True):
        local = [] if first else self.query_local(e1=e2, rel=rel, rel_type=Association)

        decl = self.query_local(e2=e2, rel_type=(Member,Subtype))
        descendents = [d.relation.entity1 for d in decl]

        for r in [self.query_down(e2=p, rel=rel, first=False) for p in descendents]:
            local += r

        return local
    
    def query_induce(self, type=None, rel=None):
        inherited = self.query_down(type, rel)
        
        return Counter([d.relation.entity2 for d in inherited]).most_common(1)[0][0]
    
    def query_local_assoc(self, entity=None, rel=None):
        local = self.query_local(e1=entity, rel=rel)

        for d in local:
            if isinstance(d.relation, Association):
                all_assoc = Counter([l.relation.entity2 for l in local]).most_common()

                all_assoc_freq = [(val, count/len(local)) for val, count in all_assoc]

                # lim = 0
                # res = []
                # for val, freq in all_assoc_freq:
                #     res.append((val,freq))
                #     lim += freq
                def aux(transporte, elem):
                    res, lim = transporte
                    val, freq = elem
                    return (res + [elem], lim + freq) if lim < 0.75 else res
                
                return reduce(aux, all_assoc_freq, ([], 0))
                    

            elif isinstance(d.relation, AssocOne):
                val, count = Counter([l.relation.entity2 for l in local]).most_common()[0]
                return (val, count/len(local))
            
            elif isinstance(d.relation, AssocNum):
                return mean([float(l.relation.entity2) for l in local])
            
    def query_assoc_value(self, E, A):
        local = self.query_local(e2=E, rel=A)

        val_count = Counter([d.relation.entity2 for d in local]).most_common()

        if len(val_count) == 1:
            return val_count[0][0]
        
        decl = self.query(entity=E, rel= A)
        val_count = Counter([d.relation.entity2 for d in decl]).most_common()

        return val_count[0][0]
            

