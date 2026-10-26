import math
datos = list(str(input()).split())
datos[0] = int(datos[0])
datos[1] = int(datos[1])
datos[2] = int(datos[2])

peso = datos[0]*datos[2]

maximoNecesarios = math.ceil(peso/datos[1])

estado = True

for i in range(1,(maximoNecesarios)+1):
    estado = True
    pila = i*datos[1]
    pila += datos[1]*(maximoNecesarios-i)
    pila -= datos[0]*(maximoNecesarios-i)
    if (pila)<0:
        estado = False
    if(estado):
        print(i)
        break
 		 					  			  	    		  	    	