package Arrays;

public class Quiz26 {
    
    private int x = 10;
    
    public int y = 11;
    
    public static int z = 12;
 
    public static void main(String[] args) {
        
        int x = 11;
        int y = 12;
        int z = 13;
        new Quiz26().new Initialize().init(); // 10 11 12
        //The inner class has access to all of outer class's variables(even private ones), 
        //except for main's local variables.
    }
    
    class Initialize{
        void init(){
            System.out.println(x + " " + y + " " +z);
        }
    }
 
}


