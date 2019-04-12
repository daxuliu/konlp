rgb=input()
rgb=rgb.split(' ')
r=int(rgb[0])
y=int(rgb[1])
g=int(rgb[2])
n=int(input())
time=[]
for i in range(n):
    t=[]
    temp=input()
    temp=temp.split(" ")
    for j in temp:
        t.append(int(j))
    time.append(t)
res=0
for i in time:
    if(i[0]==0 or i[0]==1):
        res=res+i[1]


    elif(i[0]==2):
        res=res+i[1]+r

print(res)
