package Test;

/**
 * @ClassName Cat
 * @Description TODO
 * @Author zt648
 * @Date 2019/6/5 12:22
 * @Version 1.0
 */

public class Cat extends Pet {
    @Override
    public void say() {
        //super.say();
        System.out.println("cat");
    }
    public void shot(){
        System.out.println("miao");
    }

    public static void main(String[] args) {

    }

}
