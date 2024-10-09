package com.wavemaker.collections.controller;

import com.wavemaker.collections.customarray.CustomArrayList;
import com.wavemaker.collections.pojo.Employee;

import java.util.Iterator;
import java.util.List;

public class TestCustomArray {
    public static void main(String[] args) {
        List<Integer> customList = new CustomArrayList<>(30);
        for (int i = 0; i < 10; i++) {
            customList.add(i + 1);
        }
        System.out.println("Size is :" + customList.size());
        for (Integer integer : customList) {
            System.out.println(integer);
        }
        List<Integer> customListContainsTest = new CustomArrayList<>(30);
        for (int i = 0; i < 5; i++) {
            customListContainsTest.add(i + 1);
        }
//        for (int i = 0; i < 5; i++) {
//            System.out.println(customListContainsTest.get(i));
//        }
        System.out.println("Does customList contains List : " + customList.containsAll(customListContainsTest));
        customList.addAll(customListContainsTest);
        for (Integer integer : customList) {
            System.out.println(integer);
        }
        Iterator<Integer> employeeIterator = customList.iterator();
//        testCustomListByEmployeePojo();
    }

    private static void testCustomListByEmployeePojo() {
        CustomArrayList<Employee> customList = new CustomArrayList<>();
        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setEmpId(i + 1);
            employee.setAge(i + 1);
            employee.setName("test" + i);
            customList.add(employee);
        }
        System.out.println("Size is :" + customList.size());
        for (int i = 0; i < 10; i++) {
            System.out.println(customList.get(i));
        }
    }
}
