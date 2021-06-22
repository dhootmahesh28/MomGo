package com.swacorp.crew.utils.config;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class EnvironmentFactory {

    public static final Logger LOGGER = Logger.getLogger(EnvironmentFactory.class);
    private EnvironmentFactory() {
    }
    private static Map<String, Map<String, String>> configMap = new HashMap<String, Map<String, String>>();
    static {
        XMLStreamReader xsr1 = null;
        InputStream paramInputStream = null;
        try {
            configMap.clear();
            JAXBContext jc = JAXBContext.newInstance(Environments.class);

            XMLInputFactory xif = XMLInputFactory.newInstance();
            xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
                    false);
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            paramInputStream = EnvironmentFactory.class.getClassLoader()
                    .getResourceAsStream("config.xml");
            xsr1 = xif.createXMLStreamReader(paramInputStream);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Environments adaptedWrapper = (Environments) unmarshaller
                    .unmarshal(xsr1);
            List<Environment> envs = adaptedWrapper.getEnvironments();

            for (Environment env : envs) {
                if (env != null) {

                    Set<Parameter> keyNValues = env.getParam();

                    Map<String, String> map = Collections.emptyMap();

                    if (CollectionUtils.isNotEmpty(keyNValues)) {

                        map = new HashMap();

                        for (Parameter parameter : keyNValues) {

                            if (parameter != null) {
                                map.put(parameter.getKey(),
                                        parameter.getValue());
                            }

                        }
                    }

                    Object obj = configMap.put(env.getName(), map);

                    if (obj != null) {
                        LOGGER.error("Duplicate environment name in metadata file");
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (xsr1 != null || paramInputStream != null) {
                try {
                    if (xsr1 != null) {
                        xsr1.close();
                    }

                    if (paramInputStream != null) {
                        paramInputStream.close();
                    }
                } catch (XMLStreamException | IOException e) {
                    LOGGER.error(e);
                }
            }
        }

    }

    public static final String getConfigValue(String key) /*throws Exception */{

        String env = System.getProperty("test.env");

        Map<String, String> sp = configMap.get(env);
        if (sp == null) {
            assert false;
            return null;
        } else {
            return sp.get(key);
        }
    }

    public static void reinit() {
        configMap.clear();
        XMLStreamReader xsr = null;
        try (InputStream paramInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.xml")) {
            JAXBContext jc = JAXBContext.newInstance(Environments.class);
            XMLInputFactory xif = XMLInputFactory.newInstance();
            xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
                    false);
            xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);
            xsr = xif.createXMLStreamReader(paramInputStream);

            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Environments adaptedWrapper = (Environments) unmarshaller
                    .unmarshal(xsr);
            List<Environment> envs = adaptedWrapper.getEnvironments();

            for (Environment env : envs) {

                if (env != null) {

                    Set<Parameter> keyNValues = env.getParam();

                    Map<String, String> map = Collections.emptyMap();

                    if (CollectionUtils.isNotEmpty(keyNValues)) {

                        map = new HashMap<String, String>();

                        for (Parameter parameter : keyNValues) {

                            if (parameter != null) {
                                if (parameter.getKey().contains("configSpecialistUserID")) {
                                    LOGGER.info("Reinit Printing===" + parameter.getKey() + "_" + parameter.getValue());
                                }
                                map.put(parameter.getKey(),
                                        parameter.getValue());
                            }

                        }
                    }
                    Object obj = configMap.put(env.getName(), map);

                    if (obj != null) {
                        LOGGER.info("Duplicate environment name in metadata file");
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
