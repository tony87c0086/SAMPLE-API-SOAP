
package com.uknowho.sample.soap.xmlmodel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for frequencyType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="frequencyType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TODAY"/>
 *     &lt;enumeration value="WEEK"/>
 *     &lt;enumeration value="MONTH"/>
 *     &lt;enumeration value="YEAR"/>
 *     &lt;enumeration value="DAILY"/>
 *     &lt;enumeration value="WEEKLY"/>
 *     &lt;enumeration value="MONTHLY"/>
 *     &lt;enumeration value="YEARLY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "frequencyType")
@XmlEnum
public enum FrequencyType {

    TODAY,
    WEEK,
    MONTH,
    YEAR,
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY;

    public String value() {
        return name();
    }

    public static FrequencyType fromValue(String v) {
        return valueOf(v);
    }

}
