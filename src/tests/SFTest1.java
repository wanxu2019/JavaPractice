package tests;

import java_basics_2.Interrupt;

import java.util.*;

/**
 * Created by Json Wan on 2018/9/18.
 * Description:
 */
public class SFTest1 {
    
    static class Score{
        private String id;
        private float s100;
        private float s800;
        private float sJump;
        private float sBall;

        public Score(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public void setS100(float s100) {
            this.s100 = s100;
        }

        public void setS800(float s800) {
            this.s800 = s800;
        }

        public void setsJump(float sJump) {
            this.sJump = sJump;
        }

        public void setsBall(float sBall) {
            this.sBall = sBall;
        }

        public String getS100() {
            if(s100>=12.05 && s100<=12.45){
                return "GOOD";
            }else if(s100>=12.45 && s100<=12.95){
                return "PASS";
            }else if(s100<=12.05){
                return "GREAT";
            }else{
                return "FAIL";
            }
        }

        public String getS800() {
            if(s800>=2.155 && s800<=2.245){
                return "GOOD";
            }else if(s800>=2.245 && s800<=2.315){
                return "PASS";
            }else if(s800<=2.155){
                return "GREAT";
            }else{
                return "FAIL";
            }
        }

        public String getsJump() {
            if(sJump>=255.5 && sJump<265.5){
                return "GOOD";
            }else if(sJump>=245.5 && sJump<255.5){
                return "PASS";
            }else if(sJump>=265.5){
                return "GREAT";
            }else{
                return "FAIL";
            }
        }

        public String getsBall() {
            if(sBall>=9.55 && sBall<10.35){
                return "GOOD";
            }else if(sBall>=8.65 && sBall<9.55){
                return "PASS";
            }else if(sBall>=10.35){
                return "GREAT";
            }else{
                return "FAIL";
            }
        }

        private int count=0;
        public int scoreCount(){
            if(count==0) {
                HashSet<String> set = new HashSet<>();
                set.add(getS100());
                set.add(getS800());
                set.add(getsJump());
                set.add(getsBall());
                count = set.size();
            }
            return count;
        }

        @Override
        public String toString() {
            return this.id
                    +"\t"+this.getS100()
                    +"\t"+this.getS800()
                    +"\t"+this.getsJump()
                    +"\t"+this.getsBall()
                    ;
        }
    }
    public static void main(String[] args) {
        //输入读取
        Scanner scanner = new Scanner(System.in);
        String line=scanner.nextLine();
        List<Score> scoreList=new ArrayList<>();
        while(!line.trim().equals("end")){
            String strArr[]=line.split(" ");
            Score score=new Score(strArr[0]);
            for(int i=1;i<strArr.length;i++){
                String seg=strArr[i];
                if(seg.contains("s")){
                    score.setS100(Float.parseFloat(seg.replace("s",".")));
                }else if(seg.contains("cm")){
                    score.setsJump(Float.parseFloat(seg.replace("cm","")));
                }else if(seg.contains("m") && seg.indexOf("m")==seg.length()-1){
                    score.setsBall(Float.parseFloat(seg.replace("m","")));
                }else if(seg.contains("m")){
                    score.setS800(Float.parseFloat(seg.replace("m",".")));
                }
            }
            scoreList.add(score);
            line=scanner.nextLine();
        }
        System.out.println("NO.\t100M\t800M\tJUMP\tBALL");
        HashMap<String, Integer> levelMap=new HashMap<String, Integer>();
        levelMap.put("GREAT",1);
        levelMap.put("GOOD",2);
        levelMap.put("PASS",3);
        levelMap.put("FAIL",4);
        scoreList.sort(new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if(o1.scoreCount()==o2.scoreCount()){
                    int l11=levelMap.get(o1.getS100());
                    int l12=levelMap.get(o2.getS100());
                    if(l11!=l12)
                        return l11-l12;
                    else{
                        int l21=levelMap.get(o1.getS800());
                        int l22=levelMap.get(o2.getS800());
                        if(l21!=l22)
                            return l21-l22;
                        else{
                            int l31=levelMap.get(o1.getsJump());
                            int l32=levelMap.get(o2.getsJump());
                            if(l31!=l32)
                                return l31-l32;
                            else{
                                int l41=levelMap.get(o1.getsBall());
                                int l42=levelMap.get(o2.getsBall());
                                if(l41!=l42)
                                    return l41-l42;
                                else{
                                    int l51=Integer.parseInt(o1.getId());
                                    int l52=Integer.parseInt(o2.getId());
                                    return l51-l52;
                                }
                            }
                        }
                    }
                }else{
                    return o2.scoreCount()-o1.scoreCount();
                }
            }
        });
        for(Score score:scoreList){
            System.out.println(score.toString());
        }
    }
}
