
package com.sewerynkamil.movies.movie;

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
 *         &lt;element name="serviceStatus" type="{http://sewerynkamil.pl/movies}serviceStatus"/>
 *         &lt;element name="movieType" type="{http://sewerynkamil.pl/movies}movieType"/>
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
    "serviceStatus",
    "movieType"
})
@XmlRootElement(name = "addMovieResponse", namespace = "http://sewerynkamil.pl/movies")
public class AddMovieResponse {

    @XmlElement(namespace = "http://sewerynkamil.pl/movies", required = true)
    protected ServiceStatus serviceStatus;
    @XmlElement(namespace = "http://sewerynkamil.pl/movies", required = true)
    protected MovieType movieType;

    /**
     * Gets the value of the serviceStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceStatus }
     *     
     */
    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    /**
     * Sets the value of the serviceStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceStatus }
     *     
     */
    public void setServiceStatus(ServiceStatus value) {
        this.serviceStatus = value;
    }

    /**
     * Gets the value of the movieType property.
     * 
     * @return
     *     possible object is
     *     {@link MovieType }
     *     
     */
    public MovieType getMovieType() {
        return movieType;
    }

    /**
     * Sets the value of the movieType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MovieType }
     *     
     */
    public void setMovieType(MovieType value) {
        this.movieType = value;
    }

}
