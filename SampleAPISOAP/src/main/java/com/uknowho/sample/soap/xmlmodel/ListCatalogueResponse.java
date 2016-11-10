
package com.uknowho.sample.soap.xmlmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="catalogueList" type="{http://www.uknowho.com/xmlmodel/catalogue}catalogueModel" maxOccurs="unbounded" minOccurs="0"/>
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
    "catalogueList",
    "response"
})
@XmlRootElement(name = "listCatalogueResponse")
public class ListCatalogueResponse
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected List<CatalogueModel> catalogueList;
    @XmlElement(required = true)
    protected ResponseModel response;

    /**
     * Gets the value of the catalogueList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the catalogueList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCatalogueList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CatalogueModel }
     * 
     * 
     */
    public List<CatalogueModel> getCatalogueList() {
        if (catalogueList == null) {
            catalogueList = new ArrayList<CatalogueModel>();
        }
        return this.catalogueList;
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
