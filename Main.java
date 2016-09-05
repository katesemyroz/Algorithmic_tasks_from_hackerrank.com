package com.company;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void a_very_big_sum()
    {
        ArrayList<Long> l = new ArrayList<Long>();
        Scanner scanner = new Scanner(System.in);
        Integer N = scanner.nextInt();
        for (int i = 0; i < N; i ++)
        {
            long temp = scanner.nextLong();
            l.add(temp);
        }
        scanner.close();
        long sum = 0;
        for (long item: l)
        {
            sum += item;
        }
        System.out.println(sum);
    }

    public static void utopian_tree()
    {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < T; i++)
        {
            list.add(scanner.nextInt());
        }


        for (int cycles : list)
        {
            int tree_height = 1;
            for (int i = 0; i < cycles; i++)
            {
                if ( (i%2) == 0)
                    tree_height *=2;
                else
                {
                    tree_height +=1;
                }
            }
            System.out.println(tree_height);
        }
    }

    public static void modify_the_sequence()
    {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //Integer N = Integer.parseInt(br.readLine());
        ArrayList<Long> l = new ArrayList<Long>();
        Scanner scanner = new Scanner(System.in);
        Integer N = scanner.nextInt();
        for (int i = 0; i < N; i ++)
        {
            long temp = scanner.nextLong();
            //System.out.println("Found :" + temp);
            l.add(temp);
        }
        scanner.close();

        /*for (Integer item : l)
        {
            System.out.print(item + " ");
        }*/

        int sumOfChanges = 0;
        for (int i = 1; i < N; i++)
        {
            if (l.get(i) <= l.get(i-1))
            {
                sumOfChanges++;
                long new_num = l.get(i-1) + 1;
                l.set(i, new_num);
            }
        }
        System.out.println(sumOfChanges);
    }

    public static void diagonal_difference()
    {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int arr[][] = new int[N][N];
        for (int row = 0; row < N; row++)
        {
            for (int column = 0; column < N; column++)
            {
                arr[row][column] = scanner.nextInt();
            }
        }

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < N; i++)
        {
            sum1 += arr[i][i];
            sum2 += arr[i][N-i-1];
        }
        int diff = Math.abs(sum1 - sum2);
        System.out.println(diff);
    }

    public static void lonely_integer()
    {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < N; i++)
        {
            list.add(scanner.nextInt());
        }

        int lonely_num = -1;
        while (list.size() > 1)
        {
            boolean find_pare = false;
            int first_num = list.get(0);
            //System.out.println("first num " + first_num);
            for (int i = 1; i < list.size(); i++)
            {
                System.out.println("first num compare to " + list.get(i));
                if (first_num == list.get(i))
                {
                    list.remove(i);
                    list.remove(0);
                    find_pare = true;
                    System.out.println("matching numbers, find pare is true, break, matching number is " + first_num);
                    break;
                }
                //System.out.println("no pair, continue loop");
            }
            if (!find_pare)
            {
                lonely_num = first_num;
                //System.out.println("find pare is false, lonely num is " + lonely_num);
                break;
            }
        }
        if (list.size() == 1)
            lonely_num = list.get(0);
        System.out.println(lonely_num);
    }

    public static void flipping_bits()
    {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int arr1[] = new int[N];
        int arr2[] = new int[N];
        for (int i = 0; i < N; i++)
        {
            arr1[i] = scanner.nextInt();
            arr2[i] = arr1[i];
        }

    }

    public static void non_divisible_subset()
    {
        Scanner scanner = new Scanner(System.in);
        Integer N = scanner.nextInt();
        Integer K = scanner.nextInt();
        Map<Integer, Integer> quantity_of_divisible_pairs = new HashMap<Integer, Integer>();
        Map<Integer, ArrayList<Integer>> divisible_pairs_with = new HashMap<Integer, ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < N; i ++)
        {
            int temp = scanner.nextInt();
            quantity_of_divisible_pairs.put(temp,0);
            ArrayList<Integer> l = new ArrayList<Integer>();
            divisible_pairs_with.put(temp, l);
            list.add(temp);
        }
        scanner.close();

        for (int i = 0; i < list.size() - 1; i++)
        {
            for (int j = i+1; j < list.size(); j++)
            {
                int first_num = list.get(i);
                int second_num = list.get(j);
                if ((first_num + second_num) % K == 0)
                {
                    int value = quantity_of_divisible_pairs.get(first_num);
                    quantity_of_divisible_pairs.put(first_num, ++value);
                    value = quantity_of_divisible_pairs.get(second_num);
                    quantity_of_divisible_pairs.put(second_num, ++value);

                    ArrayList<Integer> l = divisible_pairs_with.get(first_num);
                    l.add(second_num);
                    divisible_pairs_with.put(first_num, l);
                    l = divisible_pairs_with.get(second_num);
                    l.add(first_num);
                    divisible_pairs_with.put(second_num, l);
                }
            }
        }

        //Print all the values of maps
        //print_map_elements(quantity_of_divisible_pairs, divisible_pairs_with);

        while (!check_if_all_values_are_0(quantity_of_divisible_pairs))
        {
            int max = find_max_value_in_map(quantity_of_divisible_pairs);
            delete_elem(quantity_of_divisible_pairs, divisible_pairs_with, max);
            //print_map_elements(quantity_of_divisible_pairs, divisible_pairs_with);
        }

        int max_size_of_the_array = quantity_of_divisible_pairs.size();
        System.out.println(max_size_of_the_array);
    }

    public static int find_max_value_in_map(Map<Integer, Integer> map)
    {
        Collection<Integer> coll= map.values();
        ArrayList<Integer> all_values = new ArrayList(coll);
        Set<Integer> set =  map.keySet();
        ArrayList<Integer> all_keys = new ArrayList<Integer>(set);
        int max = all_values.get(0);
        int number_with_max_value = all_keys.get(0);
        for (int i = 0; i < all_values.size(); i++)
        {
            if (max < all_values.get(i))
            {
                max = all_values.get(i);
                number_with_max_value = all_keys.get(i);
            }
        }
        //System.out.println("Elem with max pairs = " + number_with_max_value);
        return number_with_max_value;

    }

    public static void delete_elem (Map<Integer, Integer> map1, Map<Integer, ArrayList<Integer>> map2, int number_with_max_value)
    {
        ArrayList<Integer> list = map2.get(number_with_max_value);
        for (Integer num: list)
        {
            if (map1.containsKey(num))
            {
                int value = map1.get(num);
                map1.put(num, --value);
            }
        }
        map1.remove(number_with_max_value);
        map2.remove(number_with_max_value);         //here I delete number_with_max_value from map2, it is not obligatorily
    }
    public static boolean check_if_all_values_are_0 (Map<Integer, Integer> map)
    {
        boolean result = true;
        Collection<Integer> coll= map.values();
        ArrayList<Integer> all_values = new ArrayList(coll);

        for (int value: all_values)
        {
            if (value != 0)
            {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void print_map_elements(Map<Integer, Integer> map1, Map<Integer, ArrayList<Integer>> map2)
    {
        for (Map.Entry entry: map1.entrySet()) {
            Integer key = (Integer) entry.getKey();
            Integer value = (Integer) entry.getValue();
            System.out.println(key + ": " + value);
        }
        System.out.println();
        for (Map.Entry entry: map2.entrySet()) {
            Integer key = (Integer) entry.getKey();
            ArrayList<Integer> values = (ArrayList<Integer>) entry.getValue();
            System.out.print(key + ": ");
            for (Integer value: values)
            {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    public static int solution(int[] A)
    {
        int equil = -1;
        int sizeA = A.length;
        long sum_left = 0;
        long sum_right = 0;
        for (int i = 1; i < sizeA; i++)
        {
            sum_right+=A[i];
        }
        if (sum_right == 0)
            equil = 0;
        for (int i = 1; i < sizeA; i++)
        {
            sum_left += A[i-1];
            sum_right -= A[i];
            if (sum_left == sum_right)
            {
                equil = i;
                break;
            }
        }
        return equil;
    }

    public static int[] max_array_generation(int[] A, int N, int max_value)
    {
        for (int i = 0; i < N; i++)
        {
            A[i] = max_value;
        }
        return A;
    }


    public static void main(String[] args) throws IOException {
        //int A[] = new int[10000];
        //System.out.println(max_array_generation(A, 10000, -2147483648)[2]);
    }
}
