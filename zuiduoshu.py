n=int(input())
arr=input()
arr=arr.split(' ')
a=[]
b={}
for i in arr:
    a.append(int(i))
    b[i]=0
for i in a:
    b[str(i)]=b[str(i)]+1
re=0
k=[]
for i in b:

    if(re<b[i]):
        re=b[i]
for i in b:
    if(b[i]==re):
        k.append(i)

ki=[]
for i in k:
    ki.append(int(i))
c=ki[0]
for i in ki:
    if (i<c):
        c=i
print(c)
