package leetcode;

import java.util.*;

/**
 * Created by Json Wan on 2017/10/10.
 * Description:
 */
public class P691 {
    public int minStickers(String[] stickers, String target) {
        //1.统计target中各字母数量
        HashMap<Character,Integer> targetMap=new HashMap<>();
        for (int i = 0; i < target.length(); i++) {
            Character ch=target.charAt(i);
            if(targetMap.keySet().contains(ch)){
                targetMap.put(ch,targetMap.get(ch)+1);
            }else{
                targetMap.put(ch,1);
            }
        }
        //printMap(targetMap);
        //2.构造原材料信息List，每个原材料包括各有用字母出现次数的Map，贡献度
        ArrayList<Material> materials=new ArrayList<>();
        for (int i = 0; i < stickers.length; i++) {
            String sticker=stickers[i];
            Material material=new Material();
            for(int j = 0; j < sticker.length(); j++) {
                char ch=sticker.charAt(j);
                if(target.contains(""+ch)){
                    if(material.map.keySet().contains(ch)){
                        int lastCount=material.map.get(ch);
                        if(lastCount<targetMap.get(ch)) {
                            material.valueCount+=1;
                            material.map.put(ch, lastCount + 1);
                        }
                    }else{
                        material.valueCount+=1;
                        material.map.put(ch,1);
                    }
                }
            }
            if(material.valueCount>0){
                materials.add(material);
            }
        }
        //3.对原材料信息进行排序
        materials.sort((o1, o2) -> {
            return o2.valueCount - o1.valueCount;
        });
        //printList(materials);
        //4.贪心算法求解
        int num= calc(materials,targetMap);
        if(num>target.length())return -1;
        return num;
    }

    public int calc(ArrayList<Material> materials,HashMap<Character,Integer> targetMap){
        //show("calc begin");
        int MAX_NUM=9999;
        //printList(materials);
        //printMap(targetMap);
        if(targetMap.keySet().size()==0)return 0;
        if(materials.size()==0){
            if(targetMap.keySet().size()>0){
                return MAX_NUM;
            }
        }
        //遍历原材料，找出贡献度最高的一种方案
        int[] nums=new int[materials.size()];
        for(int i=0;i<materials.size();i++) {
            //将原材料与目标拷贝一份用于计算剩余的数目
//            ArrayList<Material> materialsCopy= (ArrayList<Material>) materials.clone();
            ArrayList<Material> materialsCopy= copyList(materials);
            HashMap<Character,Integer> targetMapCopy= (HashMap<Character, Integer>) targetMap.clone();
            Material material = materials.get(i);
            if (material.valueCount == 0) {
                if (targetMap.keySet().size() > 0) {
                    nums[i]= MAX_NUM;
                } else {
                    nums[i]=0;
                }
            } else {
                //修改targetMap
                Object[] keysArray=material.map.keySet().toArray();
                for (int j=0;j<keysArray.length;j++) {
                    Character ch= (Character) keysArray[j];
                    //targetMap.put(ch,0);
                    if(targetMapCopy.get(ch)!=null) {
                        targetMapCopy.put(ch, targetMapCopy.get(ch) - material.map.get(ch));
                        if (targetMapCopy.get(ch) <= 0) {
                            targetMapCopy.remove(ch);
                        }
                    }else{
                        materialsCopy.get(i).map.remove(ch);
                    }
                }
                //show("after use targetMap:");
                //printMap(targetMapCopy);
                //重新计算贡献度
                calcValueCount(materialsCopy, targetMapCopy);
                nums[i] = 1 + calc(materialsCopy, targetMapCopy);
            }
        }
        //求最小的num
        int minNum=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]<minNum)
                minNum=nums[i];
        }
        //show("calc end");
        return minNum;
    }

    //计算贡献度
    public void calcValueCount(ArrayList<Material> materials,HashMap<Character,Integer> targetMap){
        for(int count=0;count<materials.size();count++){
            Material material=materials.get(count);
            //贡献度清零
            material.valueCount=0;
            Object[] keysArray=material.map.keySet().toArray();
            for (int i = 0; i < keysArray.length; i++) {
                Character ch=(Character)keysArray[i];
                if(targetMap.keySet().contains(ch)){
                    //如果targetMap里面有，取小值
                    material.map.put(ch,min(material.map.get(ch),targetMap.get(ch)));
                    material.valueCount+=material.map.get(ch);
                }else{
                    material.map.remove(ch);
                }
            }
            //无贡献度的材料直接清除掉
            if(material.valueCount==0){
                materials.remove(material);
                count--;
            }
        }
        //System.out.println("hello");
    }

    public static int min(int a,int b){
        return a<b?a:b;
    }
    class Material{
        //有用字母出现次数表
        public HashMap<Character,Integer> map;
        //贡献度
        public int valueCount;
        public Material(){
            map=new HashMap<>();
            valueCount=0;
        }

        @Override
        public String toString() {
            return "valueCount="+valueCount+"\n"
                    +getMapString(map);
        }

        public Material cloneMe(){
            Material material=new Material();
            material.valueCount=valueCount;
            material.map= (HashMap<Character, Integer>) map.clone();
            return material;
        }
    }

    public static ArrayList<Material> copyList(ArrayList<Material> srcList){
        ArrayList<Material> newList=new ArrayList<>();
        for(Material material:srcList){
            newList.add(material.cloneMe());
        }
        return newList;
    }

    public static String getMapString(Map<Character,Integer> map){
        String s=new String();
        for (Character key:map.keySet()){
            s+=(key+"===>"+map.get(key))+"\n";
        }
        return s;
    }
    public static void printList(List list){
        for(Object obj:list){
            System.out.println(obj.toString());
        }
    }

    public static void printMap(Map<Character,Integer> map){
        System.out.println(getMapString(map));
    }
    public static void show(String msg){
        System.out.println(msg);
    }
    public static void main(String[] args) {
        String[] stickers=new String[]{"with","science","example"};
        String target="thehat";
//        System.out.println(""+new P691().minStickers(stickers,target));
//        show("=======================================================");
//        stickers=new String[]{"notice","possible"};
//        target="basicbasic";
//        System.out.println(""+new P691().minStickers(stickers,target));
        show("=======================================================");
        stickers=new String[]{"these","guess","about","garden","him"};
        target="atomher";
        System.out.println(""+new P691().minStickers(stickers,target));
        show("=======================================================");
        stickers=new String[]{"swim","love","father","shape","rich","multiply","new","fill","history"};
        target="operateform";
        long timeStart=System.currentTimeMillis();
        int num=new P691().minStickers(stickers,target);
        System.out.println("time consuming:"+(System.currentTimeMillis()-timeStart)+"ms");
        System.out.println(""+num);
    }
}
