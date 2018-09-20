import java.lang.reflect.Array;

public class Normalize {

    public static byte[] enc(byte [] in){
        int lengthDatin;
        int lengthDatout;
        boolean change = false;

        lengthDatin = Array.getLength(in);

        if((lengthDatin % 16) != 0){
            int temp = (lengthDatin / 16) + 1;
            lengthDatout = temp * 16;
            change = true;
        } else {
            lengthDatout = lengthDatin;
        }
        byte [] out = new byte[lengthDatout];

        for(int i = 0; i < lengthDatin;i++){
            out[i] = in[i];
        }

        if(change){
            for(int i = lengthDatin; i < lengthDatout;i++){
            out[i] = 0;
            }
        }
        return out;
    }

    public static byte[] dec(byte [] in, int lengthorigin){
        byte [] out = new byte[lengthorigin];
        for(int i = 0; i < lengthorigin;i++){
            out[i] = in[i];
        }
        return out;
    }
}