package cn.lnd.test.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/30 11:43
 */
public class Test02 {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        unsafeAdd(strings, Integer.valueOf(42));
        String s = strings.get(0); // ClassCastException: java.lang.Integer cannot be cast to java.lang.String
    }

    private static void unsafeAdd(List strings, Object o) {
        /*  这段程序可以进行编译，但是因为它使用了原生态类型List, 你会收到一条警告：
            Unchecked call to 'add(E)' as a member of raw type 'java.util.List'*/
        strings.add(o);
    }

    /**
     * 在不确定或者不在乎集合中的元素类型的清况下，你也许会使用原生态类型。例如，
     * 假设想要编写一个方法，它有两个集合，并从中返回它们共有元素的数量。
     */
    static int numElementsInCommon(Set s1, Set s2) {
        int result = 0;
        for (Object o1 : s1)
            if (s2.contains(o1))
                result++;
        return result;
    }

    /*
    无限制通配符
        这个方法可行，但它使用了原生态类型，这是很危险的。安全的替代做法是使用无限
        制的通配符类型(unbounded wildcard type ) 。如果要使用泛型，但不确定或者不关心实际的
        类型参数，就可以用一个问号代替。例如，泛型Set<E> 的无限制通配符类型为Set<?>
        （读作“某个类型的集合") 。这是最普通的参数化Set 类型，可以持有任何集合。下面是
        numElementsInCommon 方法使用了无限制通配符类型时的情形：
    * */
    static int numElementsInCommon2(Set<?> s1, Set<?> s2) {
        int result = 0;
        for (Object o1 : s1)
            if (s2.contains(o1))
                result++;
        return result;
    }

    /*
    总结：
        Set<Object> 是个参数化类型，表示可以包含任何对象类型的一个集合；
        Set<?> 则是一个通配符类型， 表示只能包含某种未知对象类型的一个集合；
        Set 是一个原生态类型，它脱离了泛型系统。
        前两种是安全的，最后一种不安全。
    */
}

