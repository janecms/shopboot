package com.hellojd.shopex.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class JsonUtil {
  private static final ObjectMapper MAPPER = SpringUtils.getBean(ObjectMapper.class);
  /*
  static {
    JodaModule jodaModule = new JodaModule();
    DateTimeFormatterFactory formatterFactory = new DateTimeFormatterFactory();
    formatterFactory.setPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    formatterFactory.setIso(DateTimeFormat.ISO.DATE);
    jodaModule.addSerializer(DateTime.class,
        new DateTimeSerializer(new JacksonJodaDateFormat(formatterFactory.createDateTimeFormatter())));

    MAPPER.registerModule(jodaModule);
    MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
  }
  */

  private JsonUtil() {
  }

  public static ObjectMapper getMapper() {
    return MAPPER;
  }

  /**
   * json serialization.
   *
   * @param obj 要进行序列化的对象
   * @return 返回序列化之后的字符串
   */
  public static String serialize(final Object obj) {
    if (obj == null) {
      return null;
    }

    try {
      return MAPPER.writeValueAsString(obj);
    } catch (JsonProcessingException ex) {
      log.error("failed serialize object to json string", ex);
      throw new RuntimeException(ex);
    }
  }

  /**
   * json deserialization.
   *
   * @param jsonStr 序列化字符串
   * @param cls     指定反序列化之后的类信息
   * @return 返回反序列化之后的实例对象
   */
  public static <T> T deserialize(final String jsonStr, final Class<T> cls) {
    return deserialize(jsonStr, MAPPER.getTypeFactory().constructType(cls));
  }

  public static <T> T deserialize(final String jsonStr, final JavaType type) {
    if (jsonStr == null) {
      return null;
    }

    try {
      return MAPPER.readValue(jsonStr, type);
    } catch (IOException ex) {
      log.error("failed deserialize string: {} to Class {}'s instance!", jsonStr, type);

      throw new RuntimeException(ex);
    }
  }

  /**
   * 获取泛型的Collection Type
   *
   * @param collectionClass 泛型的Collection
   * @param elementClasses  元素类
   * @return JavaType Java类型
   * @since 1.0
   */
  public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
    return MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
  }

  /**
   * 转换Json到List
   *
   * @param cls：映射的类 json字符串转换成list.
   */
  public static <T> List<T> getList(String serialize, Class<T> cls) throws IOException {
    JavaType javaType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, cls);
    return MAPPER.readValue(serialize, javaType);
  }

  public static JsonNode jsonNode(final String jsonStr) {

    try {
      return MAPPER.readTree(jsonStr);
    } catch (IOException ex) {
      log.error("failed deserialize string: {} to JsonNode", jsonStr);
    }
    return null;
  }
}
