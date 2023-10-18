package org.dreambig.dsmuscles.leetcode.medium;

public class StrongPaswordChecker {

    private int getRequiredChar(String pswd){
      int low=1,upper=1,digit=1;
      for (char c:pswd.toCharArray()){
        if(Character.isLowerCase(c)) low=0;
        else if(Character.isUpperCase(c)) upper=0;
        else if(Character.isDigit(c)) digit=0;

      }
      return low+upper+digit;
    }




    public int strongPasswordChecker(String password) {
      int requiredChar = getRequiredChar(password);
      // Rule 1.
      // if len <6 , return Math.max(requiredChar, 6-Len);
      if (password.length()<6)
        return Math.max(requiredChar, 6-password.length());
      int replace=0, oneD=0, twoD=0;
      // Now working on repeated string
        for (int i=0;i<password.length();){
            // computing repeated string
            int rLen=1;
            while(i+rLen<password.length() && password.charAt(i+rLen)==password.charAt(i+rLen-1)){
                rLen++;
            }
            if(rLen>=3){
                replace+=rLen/3;
                if(rLen%3==0) oneD+=1;
                if(rLen%3==1) twoD+=2;
            }
            i+=rLen;
        }

        if(password.length()<=20) return Math.max(requiredChar, replace);
        // Hardest part

        int delete =password.length()-20;

        // Adjusting replace count
         replace -= Math.min(oneD,delete);

         replace -= Math.min(twoD, Math.max(0, delete-oneD))/2;

         replace-= Math.max(0, delete-oneD-twoD)/3;

         return delete+ Math.max(requiredChar, replace);



    }
}
