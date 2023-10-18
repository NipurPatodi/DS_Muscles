package org.dreambig.dsmuscles.topic.backtracking;

public class GeneratePermutationWithFilter {
    private static String swap(String ip, int i, int j){
        char[] tkns= ip.toCharArray();
        char tmp = tkns[i];
        tkns[i]=tkns[j];
        tkns[j]=tmp;
        return String.valueOf(tkns);

    }
    private static boolean isSafe(String ip, int s, int i , int e){
        // start check
        if(s>0 && ip.charAt(s-1)=='A' && ip.charAt(s)=='B') return false;
        // going to get swapped
       else return e != 1 + s || ((ip.charAt(i) != 'A' || ip.charAt(s) != 'B') && (ip.charAt(i) != 'A' || ip.charAt(e) != 'B'));
    }
    private static void permute(String ip, int s, int e){
        if (s==e){
            System.out.println(ip);
            return;
        }
        for (int i=s; i<=e;i++){
            if(isSafe(ip, s, i, e)) {
                ip = swap(ip, i, s);
                permute(ip, s + 1, e);
                ip = swap(ip, i, s);
            }
        }
    }

    public static void  printPermute(String ip){
        permute(ip,0, ip.length()-1);
    }

    public static void main(String[] args) {
        printPermute("ABC");
    }

}
