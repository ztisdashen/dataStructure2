package book_1;

public class Q1_6 {
	public static void permute(String str){
        char[] ch = str.toCharArray();
        permute(ch, 0, ch.length-1);
    }

    private static void permute(char[] str, int low, int high){
        int length = str.length;
        if(low == high){
            String s = "";
            for(int i=0;i<length;i++){
                s += str[i];
            }
            System.out.println(s);
        }
        for (int i = low; i < length; i++) {
            swap(str, low, i);
            permute(str, low + 1, high);
            swap(str, low, i);
        }
    }

    public static void swap(char[] str, int m, int n){
        char temp = str[m];
        str[m] = str[n];
        str[n] = temp;
    }
    public static void main(String[] args) {
    	permute("1234");
    }

}
/**
s  = abc;
swap --> abc
permute -> abc,1,2{
swap -abc
permute - abc,2,2;
}

swap -> abc



*/