package com.ForWork.lab5;

public final class StringHelper {
    private static StringBuilder[] mStrBuild = null;// array of words

    public static void main(String[] args) {}

    /**
     * This is a private constructor of utility class.
     */
    private StringHelper() { }

    /**
     * return method
     * @return mStrBuild - array of string builder
     */
    static public StringBuilder[] getBuilder() {
        return mStrBuild;
    }

    /**
     * This method is used to edit the text. It works with using next methods:
     * split() - divides the text into words; charAt() - points the index of
     * character which it`s necessary to work with; length() - defines the length of
     * the token; append() - concatenates the string representation;
     * toString() - returns a textual representation of an object;
     *
     * @return builder - result after work method
     */
    static public StringBuilder createNewText(int len) {
        StringBuilder builder = new StringBuilder();
        for (StringBuilder aMStrBuild : mStrBuild) {
            char ch = aMStrBuild.charAt(0);
            if ((ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                    ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
                    && len == aMStrBuild.length()){
                aMStrBuild.delete(0, aMStrBuild.length());
            }else
                builder.append(aMStrBuild + " ");
        }
        if (builder.toString().equals("")) return builder;
        builder.delete(builder.length() - 1, builder.length());// ?
        if (builder.charAt(builder.length() - 1) != '.') {
            builder.append(".");
        }
        return builder;// return result text
    }

    /**
     * method that take an array of symbols and flag help to identify mode
     *
     * @param text - array of symbols

     */
    static public void cutText(char[] text) {

        int counter = 0;// counter of
        for (char ch : text) {
            if (ch == ' ') counter++;
        }

        mStrBuild = new StringBuilder[counter + 1];
        int counterForStringArray = 0;// ?
        mStrBuild[counterForStringArray] = new StringBuilder();// ?

        for (int i = 0; i < text.length; i++) {
            char ch = text[i];// ?

            if (ch == ' ') {
                counterForStringArray++;
                mStrBuild[counterForStringArray] = new StringBuilder();
            } else
                mStrBuild[counterForStringArray].append(ch);
        }
    }

    /**
     * clear data before stop the program or repeat the process creating new text
     */
    static public void clearData() {
        mStrBuild = null;
    }
}
