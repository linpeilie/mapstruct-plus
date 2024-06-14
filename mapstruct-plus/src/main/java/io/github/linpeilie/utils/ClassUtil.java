package io.github.linpeilie.utils;

public class ClassUtil {

    /**
     * 简化类全限定名
     * @param qualifiedName
     * @return
     */
    public static String simplifyQualifiedName(String qualifiedName) {
        String[] arr = qualifiedName.split("\\.");
        if (arr.length == 1) {
            return arr[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                sb.append("_");
                sb.append(arr[i]);
            } else {
                sb.append(arr[i].charAt(0));
            }
        }
        return sb.toString();
    }

}
