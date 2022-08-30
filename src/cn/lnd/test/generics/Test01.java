package cn.lnd.test.generics;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Author lnd
 * @Description
 * @Date 2022/8/30 11:18
 */
public class Test01 {



    // 通过这条声明，编译器知道stamps 应该只包含Stamp 实例，并给予保证
    private static Collection<Stamp> stamps = new ArrayList();

    public static void main(String[] args) {
        stamps.add(new Stamp());
        //stamps.add( new Coin());
        // 从集合中检索元素时，编译器会替你插入隐式的转换，并确保它们不会失败（依然假设所有代码都没有产生或者隐瞒任何编译警告）
        for (Stamp stamp : stamps) {

        }
    }
}

class Stamp{

}

class Coin{

}

    /*
        使用原生态类型（没有类型参数的泛型）是合法的，但是永远不应该这么
        做。如果使用原生态类型，就失掉了泛型在安全性和描述性方面的所有优势。既然不应该
        使用原生态类型，为什么Java 语言的设计者还要允许使用它们呢？这是为了提供兼容性。
        因为泛型出现的时候， Java 平台即将进入它的第二个十年，已经存在大量没有使用泛型的
        Java 代码。人们认为让所有这些代码保持合法，并且能够与使用泛型的新代码互用，这一
        点很重要。它必须合法才能将参数化类型的实例传递给那些被设计成使用普通类型的方法，
        反之亦然。这种需求被称作移植兼容性(Migration Compatib 山ty), 促成了支持原生态类型，
        以及利用擦除(erasure) (详见第28 条）实现泛型的决定。

        虽然不应该在新代码中使用像List 这样的原生态类型，使用参数化的类型以允许插
        入任意对象（比如List<Obj ect>) 是可行的。原生态类型List 和参数化的类型List
        <Object> 之间到底有什么区别呢？不严格地说，前者逃避了泛型检查，后者则明确告知
        编译器，它能够待有任意类型的对象。虽然可以将Lis 七<String > 传递给类型List 的参
        数，但是不能将它传给类型Lis 七<0切ect> 的参数。泛型有子类型化(subtyping) 的规则，
        巨st<String> 是原生态类型List 的一个子类型，而不是参数化类型List<Object>
        的子类型（详见第28 条） 。因此， 如果使用像List 这样的原生态类型，就会失掉类型安全
        性， 但是如果使用像Lis 区Objec 七＞这样的参数化类型，则不会。
    */