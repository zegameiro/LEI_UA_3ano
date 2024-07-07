import math

#Exercicio 1.1
def comprimento(lista):
	if lista == []:
		return 0
	
	return 1 + comprimento(lista[1:])


#Exercicio 1.2
def soma(lista):
	if lista == []:
		return 0
	
	return lista[0] + soma(lista[1:])
	


#Exercicio 1.3
def existe(lista, elem):
	if lista == []:
		return False
	
	if lista[0] == elem:
		return True
	
	else:
		return existe(lista[1:], elem)


#Exercicio 1.4
def concat(l1, l2):
	if l2 == []:
		return l1
	l1.append(l2[0])

	return concat(l1, l2[1:])


#Exercicio 1.5
def inverte(lista):
	if lista == []:
		return []
	return concat([lista[-1]], inverte(lista[:-1]))


#Exercicio 1.6
def capicua(lista):
	# return lista == inverte(lista)

	if comprimento(lista) <= 1:
		return True
	
	return lista[0] == lista[-1] and capicua(lista[1:-1])


#Exercicio 1.7
def concat_listas(lista):
	if lista == []:
		return []
	
	return concat(lista[0], concat_listas(lista[1:]))
	

#Exercicio 1.8
def substitui(lista, original, novo):
	if lista == []:
		return []
	
	value = lista[0]

	if lista[0] == original:
		value = novo

	return concat([value], substitui(lista[1:], original, novo))

	
#Exercicio 1.9
def fusao_ordenada(lista1, lista2):
	if lista1 == []:
		return lista2
	
	if lista2 == []:
		return lista1
	
	if lista1[0] < lista2[0]:
		return concat([lista1[0]], fusao_ordenada(lista1[1:], lista2))
	
	return concat([lista2[0]], fusao_ordenada(lista1, lista2[1:]))
	

#Exercicio 1.10
def lista_subconjuntos(lista):
	if lista == []:
		return [[]]

	res = lista_subconjuntos(lista[1:])
	
	return res + [[lista[0]] + y for y in res]


#Exercicio 2.1
def separar(lista):
	if lista == []:
		return [], []
	
	a1, b1 = lista[0]
	lista_a1, lista_b1 = separar(lista[1:])

	return [a1] + lista_a1, [b1] + lista_b1


#Exercicio 2.2
def remove_e_conta(lista, elem):
	if lista == []:
		return ([], 0)
	
	res = remove_e_conta(lista[0:-1], elem)

	if lista[-1] == elem:
		aux = list(res)
		aux[1] += 1
		res = tuple(aux)
	else:
		res[0].append(lista[-1])

	return res

#Exercicio 3.1
def cabeca(lista):
	if lista == []:
		return None
	
	return lista[0]


#Exercicio 3.2
def cauda(lista):
	if lista == []:
		return None
	
	return lista[1:]


#Exercicio 3.3
def juntar(l1, l2):
	if len(l1) != len(l2):
		return None
	
	if l1 == []:
		return []
	
	a1 = l1[0]
	b1 = l2[0]

	return [(a1, b1)] + juntar(l1[1:], l2[1:])
    

#Exercicio 3.4
def menor(lista):
	if lista == []:
		return None
	
	min = menor(lista[0:-1])

	if min == None:
		min = lista[0]

	elif min > lista[-1]:
		min = lista[-1]

	return min


#Exercicio 3.6
def max_min(lista):

	if not lista:
		return None
	
	if len(lista) == 1:
		return lista[0], lista[0]
	
	max, min = max_min(lista[:-1]) 

	if lista[-1] > max:
		max = lista[-1]

	if lista[-1] < min:
		min = lista[-1]

	return max, min