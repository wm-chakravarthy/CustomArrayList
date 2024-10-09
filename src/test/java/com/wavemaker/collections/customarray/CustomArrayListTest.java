package com.wavemaker.collections.customarray;

import com.wavemaker.collections.pojo.Employee;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomArrayListTest {
    private static List<Employee> customList = null;

    /**
     * this setUp() will execute only once.
     * initialize the customList with 100 initial capacity and add 20 elements
     * also add 20 elements to employeeList for testing.
     * and it will also check if the customList contains all the elements from employeeList
     * also check if the size of customList is 20
     * <p>
     * in this setUp() there are 4 methods which are tested in this test class
     * 1. add()
     * 2. contains()
     * 3. size()
     * 4. isEmpty()
     * 5. iterator()
     */
    @BeforeClass
    public static void setUp() {
        customList = new CustomArrayList<>(100);
        assertTrue("Custom list should be empty.", customList.isEmpty());
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Employee employee = new Employee();
            employee.setEmpId(i + 1);
            employee.setName("testName" + (i + 1));
            employee.setAge(i + 20);
            customList.add(employee);
            employeeList.add(employee);
        }
        assertTrue("Custom list shouldn't be empty.", !customList.isEmpty());
        for (int i = 0; i < 20; i++) {
            assertTrue("Custom list should contain the expected employee.", customList.contains(employeeList.get(i)));
        }
        Iterator<Employee> employeeIterator = customList.iterator();
        while (employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            assertTrue("Custom list should contain the expected employee.", customList.contains(employee));
        }

        ListIterator<Employee> employeeListIterator = customList.listIterator();
        while (employeeListIterator.hasNext()) {
            Employee employee = employeeListIterator.next();
            assertTrue("Custom list should contain the expected employee.", customList.contains(employee));
        }
        assertEquals("Size should be 10 after adding Ten elements.", 20, customList.size());
    }

    /**
     * this testRemove() will remove the employee at index 3
     * and check if the size of customList is 19
     * and check if the customList contains the employee or not
     * in this testRemove() there are 5 methods which are tested in this test class
     * 1. remove(int index)
     * 2. remove(Object o)
     * 3. contains(Object o)
     * 4. size()
     * 5. get(int index)
     */
    @Test
    public void testRemove() {
        int initialSize = customList.size();
        Employee exceptedEmployee = customList.get(3);
        assertEquals("Custom list should contain the expected employee.", exceptedEmployee, customList.get(3));
        customList.remove(3);
        initialSize--;

        assertEquals("Size should be " + initialSize + " after removing one employee.", initialSize, customList.size());
        assertTrue("Custom list shouldn't contain the expected employee.", !customList.contains(exceptedEmployee));

        exceptedEmployee = customList.get(3);
        assertEquals("Custom list should contain the expected employee.", exceptedEmployee, customList.get(3));
        customList.remove(exceptedEmployee);
        initialSize--;

        assertEquals("Size should be " + initialSize + " after removing one employee.", initialSize, customList.size());
        assertTrue("Custom list shouldn't contain the expected employee.", !customList.contains(exceptedEmployee));
    }

    /**
     * this testAddAllAndContainsAll() will add 5 employees to employeeList
     * and check if the size of customList is excepted or not and
     * check if the customList contains all the employees or not
     * in this testAddAllAndContainsAll() there are 3 methods which are tested in this test class
     * 1. addAll(Collection<? extends E> c)
     * 2. containsAll(Collection<?> c)
     * 3. size(
     */
    @Test
    public void testAddAllAndContainsAll() {
        int initialSize = customList.size();
        List<Employee> employeeList = new ArrayList<>();
        for (int i = 20; i < 25; i++) {
            Employee employee = new Employee();
            employee.setEmpId(i + 1);
            employee.setName("testName" + (i + 1));
            employee.setAge(i + 20);
            employeeList.add(employee);
        }
        int expectedSize = initialSize + employeeList.size();
        customList.addAll(employeeList);
        assertEquals("Size should be " + expectedSize + " after adding 5 employees.", expectedSize, customList.size());
        assertTrue("Custom list should contain all the employees.", customList.containsAll(employeeList));
    }

}
