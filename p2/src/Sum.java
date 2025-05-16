public class Sum {
    private int[] table;

    public Sum (int[] table) {
        this.table=table;
    }


    public static  int sumArray(int[] table){
        int c=0;
        for(int i =0; i<table.length; ++i){
            c+=table[i];
        }
        return c;
    }

    public static void print (){
        for(int i=0; i<101; ++i){
            System.out.println(i);
        }
    }

    public static boolean isAdult(int age){
        if(age>=18) return true;
        else return false;
    }
}
