package at.alphaplan.AlphaWeb.foundation;

import org.springframework.util.Assert;

import static java.lang.String.format;

public class AssertUtil {

    //Messages
    private static final String isNotNullMsg = "%s must not be null!";
    private static final String hasMinSizeMsg = "%s must be more or equal %d";
    private static final String hasMaxSizeMsg = "%s must be less or equal %d";
    private static final String hasMinTextSize = "%s must be less or equal %d characters";
    private static final String hasMaxTextSize = "%s must be less or equal %d characters";


    //Util
    //NotNull Prüfung
    public static <T> T isnotNull(T obj, String name){

        Assert.notNull(obj, () -> format(isNotNullMsg, name));
        return obj;
    }

    //has max/min size
    public static long hasMinSize(long value, long min, String name)
    {
        Assert.isTrue(value > min, ()-> format(hasMinSizeMsg,name,min));
        return value;
    }

    public static long hasMaxSize(long value, long max, String name)
    {
        Assert.isTrue(value > max, ()-> format(hasMaxSizeMsg,name,max));
        return value;
    }

    //has max/min Textsize TODO



}
