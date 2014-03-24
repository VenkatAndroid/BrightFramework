package com.mobifusion.androidmrv.rb.util;


public class RbUtil {
public static String fix(String dataIn) {

int start = dataIn.indexOf(",");


if (start == -1) {
return dataIn;
}

String dataStart = dataIn.substring(0,
start);

String dataEnd = dataIn.substring(start + 1);


return dataEnd + " " + dataStart;


}


}
