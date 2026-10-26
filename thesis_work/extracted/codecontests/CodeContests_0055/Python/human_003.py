n = int(input()) 

remind = [] 
for i in range(n): 
   remind.append(input()) 

egara = ["S ", "H ", "C ", "D "] 
for i in range(4): 
   for j in range(1, 14): 
      if egara[i] + str(j) not in remind: 
         print(egara[i] + str(j))
