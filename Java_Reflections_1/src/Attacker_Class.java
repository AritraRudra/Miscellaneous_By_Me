import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class Attacker_Class {
	
	public static void main(String a[]){
		System.out.println(getAllFieldsAndTheirValues(new Victim_Class(), 0));
	}

	// http://stackoverflow.com/questions/37628/what-is-reflection-and-why-is-it-useful
	/**
	 * 
	 * @param o
	 * @param callCount
	 * @return
	 */
	public static String getAllFieldsAndTheirValues(Object o, int callCount) {
	    callCount++;
	    StringBuffer tabs = new StringBuffer();
	    for (int k = 0; k < callCount; k++) {
	        tabs.append("\t");
	    }
	    StringBuffer buffer = new StringBuffer();
	    Class oClass = o.getClass();
	    if (oClass.isArray()) {
	        buffer.append("\n");
	        buffer.append(tabs.toString());
	        buffer.append("[");
	        for (int i = 0; i < Array.getLength(o); i++) {
	            if (i < 0)
	                buffer.append(",");
	            Object value = Array.get(o, i);
	            if (value.getClass().isPrimitive() ||
	                    value.getClass() == java.lang.Long.class ||
	                    value.getClass() == java.lang.String.class ||
	                    value.getClass() == java.lang.Integer.class ||
	                    value.getClass() == java.lang.Boolean.class
	                    ) {
	                buffer.append(value);
	            } else {
	                buffer.append(getAllFieldsAndTheirValues(value, callCount));
	            }
	        }
	        buffer.append(tabs.toString());
	        buffer.append("]\n");
	    } else {
	        buffer.append("\n");
	        buffer.append(tabs.toString());
	        buffer.append("{\n");
	        while (oClass != null) {
	            Field[] fields = oClass.getDeclaredFields();
	            for (int i = 0; i < fields.length; i++) {
	                buffer.append(tabs.toString());
	                fields[i].setAccessible(true);
	                buffer.append(fields[i].getName());
	                buffer.append("=");
	                try {
	                    Object value = fields[i].get(o);
	                    if (value != null) {
	                        if (value.getClass().isPrimitive() ||
	                                value.getClass() == java.lang.Long.class ||
	                                value.getClass() == java.lang.String.class ||
	                                value.getClass() == java.lang.Integer.class ||
	                                value.getClass() == java.lang.Boolean.class
	                                ) {
	                            buffer.append(value);
	                        } else {
	                            buffer.append(getAllFieldsAndTheirValues(value, callCount));
	                        }
	                    }
	                } catch (IllegalAccessException e) {
	                    buffer.append(e.getMessage());
	                }
	                buffer.append("\n");
	            }
	            oClass = oClass.getSuperclass();
	        }
	        buffer.append(tabs.toString());
	        buffer.append("}\n");
	    }
	    return buffer.toString();
	}
}
