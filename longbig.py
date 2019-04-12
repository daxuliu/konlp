import  cmath

class Tn:
    def __init__(self,fun):
        self.func=fun
    def getTn(self,n,a,b):
        h=(b-a)/n
        Tn=0
        for i in range(1,n):
            x=a+i*h
            Tn=Tn+self.func(x)
        Tn=(Tn*2+self.func(a)+self.func(b))*h/2
        return Tn
    def getSn(self,n,a,b):
        Sn=(4/3)*(self.getTn(2*n,a,b))-self.getTn(n,a,b)/3
        return Sn
    def getCn(self,n,a,b):
        Cn=(16/15)*self.getSn(2*n,a,b)-self.getSn(n,a,b)/15
        return Cn
    def getRn(self,n,a,b):
        Rn=self.getCn(2*n,a,b)*64/63-self.getCn(n,a,b)/63
        return Rn
if __name__=="__main__":


    def f(x):
        if x==0:
            return 1
        else:
            return (cmath.cos(x).real)/x
    def fc(x):
        return -cmath.sin(x).real
    def getresult(a,b):
        return cmath.cos(b).real-cmath.cos(a).real
    T=Tn(fc)
    a=0
    b=3
    re=getresult(a,b)
    print("result="+str(getresult(a,b)))
    print(T.getRn(8,a,b))
    for i in range(1,9):
        print('T'+str(i)+'='+str(re-T.getTn(i,a,b)))
        print('S'+str(i)+'=' + str(re-T.getSn(i, a, b)))
        print('C'+str(i)+'='+str(re-T.getCn(i,a,b)))
        print('R'+str(i)+'='+str(re-T.getRn(i,a,b)))




