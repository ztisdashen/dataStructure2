package com.Minimum_Span_Trees;

/**
 * @ClassName Test
 * @Description TODO
 * @Author zt648
 * @Date 2019/6/14 7:54
 * @Version 1.0
 */

public class Test {
    public static void main(String[] args) {
        User user = new User("aaa","aaa");
        User user1 = new User("bbb","bbb");
        User[] users = new User[2];
        users[0] = user1;
        users[1] = user;
        users[0].setName("mmmm");
        System.out.println(users[0]);
    }

}
