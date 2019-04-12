package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sen {
	public StringBuffer pa;
	public ArrayList<StringBuffer> words;
	public String[] wStrings;
    public HashMap<String, ArrayList<String>> likeofword;
	public Sen(String paString) {
		// TODO Auto-generated constructor stub
		this.pa = new StringBuffer(paString);
		String s=paString.replace(',', ' ');
		System.out.println(s);
		s=s.replace('?', '.');
		
		Pattern pattern = Pattern.compile("[\\d]");
        Matcher matcher = pattern.matcher(s);
        Pattern p = Pattern.compile("[a-zA-z]");
        
        s=matcher.replaceAll("");
        Matcher m = p.matcher(s);
        s=m.replaceAll("");

        this.wStrings = s.split("\\.");
		this.words = new ArrayList<StringBuffer>();
		this.likeofword =new HashMap<String,ArrayList<String>>();
	}

	public ArrayList<StringBuffer> getWords() {
		// 分词获得word
		ArrayList<StringBuffer> w=new ArrayList<StringBuffer>();
		for (String string : wStrings) {
			// StringBuffer sBuffer=new StringBuffer(string);
			String[] s = string.split(" ");
			
			for (String s1 : s) {
				StringBuffer sBuffer = new StringBuffer(s1);
				// System.out.println(s1);
				if (sBuffer.length() != 0) {
					w.add(sBuffer);
				}
				// System.out.println(sBuffer);

			}
		}
        this.words=w;
		return this.words;
	}
	public HashMap<String, ArrayList<String>> getlike() {
		this.getWords();//返回  word 以及like 查询 获取Word;
		for (StringBuffer sb : words) {
			ArrayList<String> list=new ArrayList<String>();
			 
			for(int i=0;i<sb.length();i++) {
				StringBuffer SB2=sb;
				char c=SB2.charAt(i);
				  SB2.setCharAt(i, '_');
				 // System.out.println(SB2);
				  String string=SB2.toString();
				  
				  list.add(string);
				  SB2.setCharAt(i, c);
				  
				  
				  
			}
			for(int i=0;i<sb.length()-1;i++) {
				StringBuffer SB2=sb;
				char c=SB2.charAt(i);
				char c1=SB2.charAt(i+1);
				  SB2.setCharAt(i, '_');
				  SB2.setCharAt(i+1, '_');
				 // System.out.println(SB2);
				  String string=SB2.toString();
				  
				  list.add(string);
				  SB2.setCharAt(i, c);
				  SB2.setCharAt(i+1, c1);
				  
				  
				  
			}
			likeofword.put(sb.toString(), list);
		}
		return this.likeofword;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pString = "멀12게a만 느껴a졌던 환경문제가 점점 일상이 되어가고 있습니다. 미세먼지로 인해 언제 편히 숨 쉬었는지 기억이 나지 않고, 무심코 사용했던 일회용품, 플라스틱들은 골칫덩이 쓰레기가 되었습니다."
				+ "환경은 더 이상 나와 상관없는 문제가 아닙니다. 행복하고 건강한 삶을 위해 환경보호를 실천하는 습관이 필요합니다.";
		Sen sen = new Sen(pString);
		//ArrayList<StringBuffer> arrayList = sen.getWords();
		/*for (StringBuffer string : arrayList) {
			System.out.println(string);
			System.out.println(string.length() + "");
		}*/
		HashMap<String, ArrayList<String>>map=sen.getlike();
		for (String key : map.keySet()) {
			System.out.println(key);
			 ArrayList<String> list=map.get(key);
			 for (String string : list) {
				System.out.println(string);
			}
			
		}
		

	}

}
