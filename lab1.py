import pylab as pl
import cmath
class lagelangri:
    def __init__(self):
        pass
    def jihanshu(self,x,j,points):
        re=1
        for i in range(0, len(points)):
            if i!=j:
                re=re*(x-points[i][0])/(points[j][0]-points[i][0])
#求解基函数
        return re
    def duoxiangshi(self,points,x):
        re=0
        for i in range(len(points)):
#求解多项式
            re=re+points[i][1]*self.jihanshu(x,i,points)
        return re
class newtown:
    def __init__(self,points):
        self.points=points
#初始化
    def chashang(self):
        points=self.points
      #一阶差商
        re=[]
        for i in range(len(points)-1):
            r=(points[i][1]-points[i+1][1])/(points[i][0]-points[i+1][0])
            rs=[]
            x=[]
            x.append(points[i][0])
            x.append(points[i+1][0])

            rs.append(r)
            rs.append(x)
            re.append(rs)

        return re
    def chashangs(self,res,n):
        count=1
        if count==n:
            return self.chashang()
        else:
            count=count+1
            r=res
            re=[]
            for i in range(len(r)-1):
                res = (r[i][0] - r[i + 1][0]) / (r[i][-1][0] - r[i + 1][-1][-1])
                rs = []
                x = r[i][-1]
                x.append(r[i + 1][-1][-1])


                rs.append(res)
                rs.append(x)
                re.append(rs)

            return  re
    def cs(self,n):
        re = self.chashang()
        #print(re)
        # print(newtown.chashangs(re,2))
        # print(re)
        for i in range(1, n+1):
           # print(i)
            r = newtown.chashangs(re, i)
            re = r
        return re

    def getfun(self,x,n):
        f=self.points[0][0]
        p=self.points
        cs=[]

        for i in range(1,n+1):
            cs.append(self.cs(i)[0])
        #print(str(cs)+'cs')
        for i in cs:

            r=i[0]
            #print('r='+str(r))
           # print(str(i[-1][0:-1])+"i[-1][0:-1]")
            for j in i[-1][0:-1]:

            #    print('r=' + str(r))
                r=r*(x-j)
             #   print('x-j='+str(x-j)+'r='+str(r))
               # print(str(r)+':r')
            #print(f)
            #print(str(r) + ':r')
            f=f+r
        return f

def func(x):

    return x*x*x
if __name__=="__main__":
    #points = [[1, 1], [2, 4], [3, 9], [4, 16], [5, 25]]
    points=[]
    fun=cmath.exp#插值函数

    for i in range(9):
        p=[]
        p.append(i)
        p.append(fun(i).real)
        points.append(p)
    print(points)
    print('real='+str(fun(4.5).real))
    lagelangri=lagelangri()
    r=lagelangri.duoxiangshi(points,4.5)
    print('lagelangri:'+str(r))
    newtown=newtown(points)
    #re=newtown.chashang()

    f = newtown.getfun(4.5, 4)
    print("newtown:"+str(f))
    print(newtown.chashangs(newtown.chashang(),5))
    #print(re)
    #print(newtown.chashangs(re,2))
    #print(re)
    '''for i in range(1,4):
        print(i)
        r=newtown.chashangs(re,i)
        re=r
        print(re)
        '''
    lglr=[]
    n=[]
    for i in points:
        lglr.append(lagelangri.duoxiangshi(points,i[0]+0.5))
        n.append(newtown.getfun(i[0]+0.5,4))

    #print(cs)
    x=[]
    y=[]
    for i in points:
        x.append(i[0])
        y.append(i[1])
    pl.plot(x,y)
    pl.show()
    pl.plot(x, lglr)
    pl.show()
    pl.plot(x, n)
    pl.show()

