package algorithms;

import java.util.ArrayList;
import java.util.HashMap;

//去除相邻的重复字符串（重复次数大于2），跳过数字
public class RemoveRepeatedString {
    private static StringBuilder repeatedString; //重复的字符串

    public static void main(String[] args) {
        String strings[] = {
                "刚才我说了我要退款我要退款",
                "刚才我说了我要退款我要退款我要退款，我都说了我要退款我要退款我要退款",
                "刚才我说了我要退款我说了我要退款",
                "10000",
                "阿里巴巴",
                "阿里旺旺旺旺旺旺"
        };
        for (String string : strings) {
            String result = solve(string);
            System.out.println(result);
        }
    }

    //需要考虑子串里面有重复字符的情况
    //思路：从前往后单字符检索，保存字符对应下标，跳过数字，检索到之前出现过的字符之后双下标逐个找重复的字符（可从后往前找，效率更高）
    private static String solve(String string) {
        char[] chars = string.toCharArray();
        HashMap<Character, ArrayList<Integer>> record = new HashMap<>();
        StringBuilder result = new StringBuilder();
        boolean needBreak;
        for (int i = 0; i < chars.length; i++) {
            needBreak = false;
            char cur = chars[i];
            if (cur < '0' || cur > '9') {
                if (!record.containsKey(cur)) {
                    ArrayList<Integer> indexList = new ArrayList<>();
                    indexList.add(i);
                    record.put(cur, indexList);
                }
                //当前字符在之前出现过
                else {
                    ArrayList<Integer> indexList = record.get(cur);
                    for (int j = 0; j < indexList.size(); j++) {
                        int preIndex = indexList.get(j);
                        int count = getRepeatedCount(chars, preIndex, i);
                        //重复次数大于2
                        if (count >= 2 && repeatedString.length() >= 2) {
                            //跳过重复的字符串，只保留一个
                            i += (count - 1) * repeatedString.length() - 1;
                            needBreak = true;
                            break;
                        }
                    }
                    if (!needBreak) {
                        //此处不应直接覆盖，而应该把之前出现过的位置全部存储起来，因为重复的有可能是一个本身有重复字符的子串
                        indexList.add(i);
                    }
                }
            }
            if (!needBreak) {
                result.append(cur);
            }
        }
        return result.toString();
    }

    //获取重复的字符串个数
    private static int getRepeatedCount(char[] chars, int preIndex, int curIndex) {
        int preIndexBackup=preIndex;
        int repeatLength = curIndex - preIndex;
        int end1 = curIndex - 1;
        int end2 = curIndex - 1 + repeatLength;
        repeatedString = new StringBuilder();
        int count = 1;
        //获取重复的字符串
        while (end2 < chars.length && end1 > preIndex && chars[end1] == chars[end2]) {
            end1--;
            end2--;
            if (end1 == preIndex) {
                count++;
                preIndex+=repeatLength;
                end1 += 2 * repeatLength - 1;
                end2 += 2 * repeatLength - 1;
            }
        }
        if (count > 1) {
            for (int i = preIndexBackup; i < curIndex; i++) {
                repeatedString.append(chars[i]);
            }
        }
        return count;
    }
}
