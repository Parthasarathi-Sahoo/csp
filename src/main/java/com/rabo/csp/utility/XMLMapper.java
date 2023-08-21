package com.rabo.csp.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * XMLMapper : Utility class to provide XMLMapper
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class XMLMapper {

    /**
     * getXMLMapper : Constructor for the XMLMapper using specific configuration
     * @return ObjectMapper The constructed XMLMapper
     */
    public static ObjectMapper getXMLMapper(){
        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.registerModule(new ParameterNamesModule());
        xmlMapper.registerModule(new Jdk8Module());
        xmlMapper.registerModule(new JavaTimeModule());
        return xmlMapper;
    }
}
