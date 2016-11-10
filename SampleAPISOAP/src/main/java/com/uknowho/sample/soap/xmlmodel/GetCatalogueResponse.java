
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
 *         &lt;element name="catalogue" type="{http://www.uknowho.com/xmlmodel/catalogue}catalogueModel"/>
 *         &lt;element name="response" type="{http://www.uknowho.com/xmlmodel/catalogue}responseModel"/>
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
    "catalogue",
    "response"
})
@XmlRootElement(name = "getCatalogueResponse")
public class GetCatalogueResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    protected CatalogueModel catalogue;
    @XmlElement(required = true)
    protected ResponseModel response;

    /**
     * Gets the value of the catalogue property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogueModel }
     *     
     */
    public CatalogueModel getCatalogue() {
        return catalogue;
    }

    /**
     * Sets the value of the catalogue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogueModel }
     *     
     */
    public void setCatalogue(CatalogueModel value) {
        this.catalogue = value;
    }

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseModel }
     *     
     */
    public ResponseModel getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseModel }
     *     
     */
    public void setResponse(ResponseModel value) {
        this.response = value;
    }

}
