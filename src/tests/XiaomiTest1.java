package tests;


/**
 * Created by Json Wan on 2018/9/13.
 * Description:
 */
public class XiaomiTest1 {

    private static String solution(String line) {
        // 在此处理单行数据
        String[] numstrs=line.split(" ");
        int[] nums=new int[numstrs.length];
        java.util.List<Integer> numList=new java.util.ArrayList<>();
        for(int i=0;i<numstrs.length;i++){
            nums[i]=Integer.parseInt(numstrs[i]);
            numList.add(nums[i]);
        }
        numList.sort(new java.util.Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        int count=1;
        int hit=2;
        int last_i=0;
        int i=0;
        while(hit<numList.get(numList.size()-1)){
            while(numList.get(i)<=hit)i++;
            if(i!=last_i){
                last_i=i;
            }else{
                count+=1;
            }
            hit+=2;
        }
        return count+"";
    }

    public static void main(String[] args) {
        System.out.println(solution("1 12 3 6 10"));
    }
}
