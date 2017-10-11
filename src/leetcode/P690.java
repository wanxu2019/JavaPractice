package leetcode;

import java.util.*;

/**
 * Created by Json Wan on 2017/10/7.
 * Description:
 * You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.
 For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.
 Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.
 Example 1:
 Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 Output: 11
 Explanation:
 Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 */
public class P690 {

    public static class Employee{
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }
    //my solution 17ms
    public int getImportance(List<Employee> employees, int id) {
        int importance=0;
        for(int i=0;i<employees.size();i++){
            Employee e=employees.get(i);
            if(e.id==id){
                importance+=e.importance;
                for(int j=0;j<e.subordinates.size();j++){
                    importance+=getImportance(employees,e.subordinates.get(j));
                }
            }
        }
        return importance;
    }
    public int ss9ms(List<Employee> employees, int id) {
        int result=0;
        Map<Integer,Employee> map=new HashMap<Integer,Employee>();
        for(Employee ee:employees){
            map.put(ee.id,ee);
        }
        Queue<Employee> queue=new LinkedList<>();
        queue.offer(map.get(id));
        while(!queue.isEmpty()){
            Employee target=queue.poll();
            result+=target.importance;
            for(int sub:target.subordinates){
                queue.offer(map.get(sub));
            }
        }
        return result;
    }
    public int ss8ms(List<Employee> employees, int id) {
        Map <Integer, Employee> map = new HashMap<Integer, Employee> ();
        for (Employee e : employees) {
            map.put(e.id, e);
        }
        return getImportanceHelper(map, id);
    }
    private int getImportanceHelper(Map <Integer, Employee> map, int id) {
        Employee root = map.get(id);
        int total = root.importance;
        for (int subordinate : root.subordinates) {
            total += getImportanceHelper(map, subordinate);
        }
        return total;
    }
    public int ss7ms(List<Employee> employees, int id) {
        if(employees==null||id>employees.size()||employees.get(id-1)==null) return 0;
        int nTemp = employees.get(id-1).importance;
        for(int i=0; i<employees.get(id-1).subordinates.size(); i++)
        {
            nTemp+=ss7ms(employees, employees.get(id-1).subordinates.get(i));
        }
        return nTemp;
    }
    public int ss6ms(List<Employee> employees, int id) {
        Employee employee = employees.get(id - 1);
        int importance = employee.importance;
        for(Integer sid: employee.subordinates)
            importance += ss6ms(employees, sid);
        return importance;
    }
    public static int ss5ms(List<Employee> employees, int id) {
        Employee target = employees.get(id - 1);
        int importance = target.importance;
        if (target.subordinates.size() == 0) return importance;
        for (Integer subordinateId: target.subordinates) {
            importance += ss5ms(employees, subordinateId);
        }
        return importance;
    }
}
