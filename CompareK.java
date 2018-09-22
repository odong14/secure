public class CompareK {
    public static boolean compare(byte[] a, byte[] b){
        int panjangA = a.length;
        int panjangB = b.length;

        if( panjangA != panjangB){
            return false;
        }

        for(int i = 0; i < panjangA; i++){
            if(a[i] != b[i]) return false;
        }

        return true;

    }
}