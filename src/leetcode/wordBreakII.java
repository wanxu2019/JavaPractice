package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Json Wan on 2018/9/24.
 * Description:
 */
public class wordBreakII {

    private ArrayList<String> mResult;
    private ArrayList<String> mTemp;

    public ArrayList<String> bestWordBreak(String s, Set<String> dict) {
        mResult = new ArrayList<>();
        mTemp = new ArrayList<>();
        doBreak(s, dict);
        return mResult;
    }

    private void doBreak(String s, Set<String> dict) {
        int len = s.length();
        if (len < 1) {
            StringBuilder builder = new StringBuilder();
            for (int i = mTemp.size() - 1; i >= 0; i--) {
                builder.append(mTemp.get(i)).append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
            mResult.add(builder.toString());
        }

        for (int i = len - 1; i >= 0; i--) {
            String sub = s.substring(i, len);
            if (dict.contains(sub)) {
                mTemp.add(sub);
                doBreak(s.substring(0, i), dict);
                mTemp.remove(mTemp.size() - 1);
            }
        }
    }

    public static ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> result=wordBreakInner(s,dict);
        result.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        return result;
    }
    public static ArrayList<String> wordBreakInner(String s, Set<String> dict) {
        if(s.length()==0){
            ArrayList<String> ret=new ArrayList<>();
            ret.add("");
            return ret;
        }
        if(dict.size()==0){
            return new ArrayList<>();
        }
        boolean flag=false;
        for(int i= s.length() - 1; i >= 0; i--){
            String subStr=s.substring(i);
            if(dict.contains(subStr)){
                flag=true;
                break;
            }
        }
        if(!flag)
            return new ArrayList<>();
        else{
            ArrayList<String> result=new ArrayList<>();
            for(int i=1;i<=s.length();i++){
                String subStr=s.substring(0,i);
                if(dict.contains(subStr)){
                    ArrayList<String> subResult= wordBreakInner(s.substring(i), dict);
                    if(subResult.size()>0){
                        for(String x:subResult){
                            result.add((subStr+" "+x).trim());
                        }
                    }
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Set<String> set=new HashSet<>();
        set.add("cat");
        set.add("cats");
        set.add("and");
        set.add("sand");
        set.add("dog");
        System.out.println(wordBreak("catsanddog", set));
        System.out.println(new wordBreakII().bestWordBreak("catsanddog", set));
    }
}
