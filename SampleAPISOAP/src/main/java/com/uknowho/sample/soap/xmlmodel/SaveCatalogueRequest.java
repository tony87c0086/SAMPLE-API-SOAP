
package com.uknowho.sample.soap.xmlmodel;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requestBody" type="{http://www.uknowho.com/xmlmodel/catalogue}saveCatalogueBody"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "requestBody"
})
@XmlRootElement(name = "saveCatalogueRequest")
public class SaveCatalogueRequest
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected SaveCatalogueBody requestBody;

    /**
     * Gets the value of the requestBody property.
     * 
     * @return
     *     possible object is
     *     {@link SaveCatalogueBody }
     *     
     */
    public SaveCatalogueBody getRequestBody() {
        return requestBody;
    }

    /**
     * Sets the value of the requestBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link SaveCatalogueBody }
     *     
     */
    public void setRequestBody(SaveCatalogueBody value) {
        this.requestBody = value;
    }

}
