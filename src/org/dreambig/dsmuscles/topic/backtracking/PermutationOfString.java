package org.dreambig.dsmuscles.topic.backtracking;

/***
 * WAP to generate permutation of given string
 */
public class PermutationOfString {
    private static String swap(String ip, int i, int j){

        char [] tkns = ip.toCharArray();
        char tmp= tkns[i];
        tkns[i]= tkns[j];
        tkns[j]=tmp;
        return String.valueOf(tkns);

    }


    private static void permute (String ip, int s, int e){
        if(s==e){
            System.out.println(ip);
            return;
        }
        for (int i=s; i<=e;i++){
            ip=swap(ip, i, s);
            permute(ip, s+1, e);
            ip=swap(ip, i, s);
        }


    }
    public static void  printPermute(String ip){
        permute(ip,0, ip.length()-1);
    }

    public static void main(String[] args) {
        printPermute("abc");
    }
}
