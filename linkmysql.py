import pymysql
from word import Word
class linkmysql:
    def __init__(self,ip,user,password,database):
        self.db=pymysql.connect(ip,user,password,database)
        self.cursor=self.db.cursor()
    def insert(self,word):
        content=word.content
        nature=word.Nature
        next=str(word.next)
        last=str(word.last)
        zh=word.zh
        count=word.count
        sql='select * from words'
        insertsql = "INSERT INTO words(content,nature,next,last,count,zh) VALUES(%s,%s,%s,%s,%s,%s)"
        self.cursor.execute(insertsql, (content,nature,next,last,count,zh))
        self.db.commit()
    def select(self,w):
        #sql = 'select * from standaryword where cixing='+"'[]'"
        sql = 'select * from standaryword where ko=%s'
        self.cursor.execute(sql,(w))
        res=self.cursor.fetchall()
        print(res)
        return  res
    def select2(self,w):
        #sql = 'select * from standaryword where cixing='+"'[]'"
        sql = 'select * from standaryword where cixing=%s'
        self.cursor.execute(sql,(w))
        res=self.cursor.fetchall()
        print(res)
        return  res
    def selectwithsql(self,sql):
        #sql = 'select * from standaryword where cixing='+"'[]'"

        self.cursor.execute(sql)
        res=self.cursor.fetchall()
        #print(res)

        return  res
    def myinsert(self,ob,sql):
        self.cursor.execute(sql,(ob))
    def insertstd(self,s1,s2,s3="null"):
        insertsql = "INSERT INTO standaryword(ko,zh,cixing) VALUES(%s,%s,%s)"
        self.cursor.execute(insertsql, (s1, s2,s3))
        self.db.commit()
        self.db.commit()

    def insertyufa(self,s1,s2):
        insertsql = "INSERT INTO yufa(word,yufa) VALUES(%s,%s)"
        self.cursor.execute(insertsql, (s1, s2))
        self.db.commit()
        self.db.commit()
    def change(self,id,cixing):
        sql="update standaryword set cixing=%s where id=%s;"
        self.cursor.execute(sql, (cixing,id))
        self.db.commit()


    def getlike(self,w):
        sql = 'select * from topik where word=%s'
        self.cursor.execute(sql,(w))
        res = self.cursor.fetchall()
        return  res

    def like1(self,w,i):
        sql=sql = 'select * from topik where word LIKE %s'
        wo=list(w)
        wo[i]="_"
        w=''.join(wo)

        self.cursor.execute(sql,(w))
        res = self.cursor.fetchall()
        return  res
    def like2(self,w,i):
        sql = sql = 'select * from topik where word LIKE %s'
        wo = list(w)
        wo[i] = "_"
        wo[i+1]="_"
        w = ''.join(wo)

        self.cursor.execute(sql, (w))
        res = self.cursor.fetchall()
        return  res
    def likes(self,w):
        l=()
        re = []
        if self.getlike(w)!=l:
            for i in self.getlike(w):
                re.append(i)
            return  re

        else:
            for i in range(len(w)):
                r=self.like1(w,i)
                for j in r:
                    re.append(j)
            if(re==[]):
                for i in range(len(w)-1):
                    r=self.like2(w,i)
                    for j in r:
                        re.append(j)
        return  re
if  __name__ == '__main__':
    linkmysql=linkmysql("47.107.227.208","root","hismine","koweb")
    """word=Word('the')
    word.zh='这'
    word.count=1
    word.last={'a':2}
    word.Nature="ad"
    word.next={'b':2}
    linkmysql.insert(word)
    linkmysql.myinsert("test","insert into sen(sen) values(%s)")
    linkmysql.insertyufa("test","test")
    linkmysql.select('우편')
    linkmysql.select('a')
    linkmysql.change(3,'test2')"""

    print(linkmysql.likes('쌀11다'))
    #print(linkmysql.like('쌀쌀하'))





