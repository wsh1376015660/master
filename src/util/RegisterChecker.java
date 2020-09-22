package util;

/**
 * 验证用户名是否符合规则
 * 包含字母
 * 包含数字
 * 最小长度6
 * 最大长度20
 */

public class RegisterChecker {

    private boolean letter = true; // 包含字母
    private boolean digit = true; // 包含数字
    private int minLength = 6; // 最小长度
    private int maxLength = 20; // 最大长度

    public RegisterChecker(){ }

    /**
     * 密码符合规则，返回true
     */
    public boolean check(String password){
        if(password==null || password.length()<this.minLength || password.length()>this.maxLength){
            // 长度不符合
            return false;
        }

        boolean containLetter = false;
        boolean containDigit = false;

        for(char ch : password.toCharArray()){
            if(Character.isUpperCase(ch)){
                containLetter = true;
            }else if(Character.isLowerCase(ch)){
                containLetter = true;
            }else if(Character.isDigit(ch)) {
                containDigit = true;
            }
            else{
                // 非法字符
                return false;
            }
        }
        if(this.letter && !containLetter){
            return false;
        }
        if(this.digit && !containDigit){
            return false;
        }
        return true;
    }


}
