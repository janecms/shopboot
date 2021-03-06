package org.springframework.data.auditing;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.auditing.*;
import org.springframework.data.auditing.AnnotationAuditingMetadata;
import org.springframework.data.auditing.AuditableBeanWrapper;
import org.springframework.data.auditing.DefaultAuditableBeanWrapperFactory;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
/**
 * Auditing handler to mark entity objects created and modified.
 *
 * @author Oliver Gierke
 * @since 1.5
 */
public class AuditingHandler implements InitializingBean {
  private static final Logger LOGGER = LoggerFactory.getLogger(org.springframework.data.auditing.AuditingHandler.class);
  private final DefaultAuditableBeanWrapperFactory factory = new DefaultAuditableBeanWrapperFactory();
  private DateTimeProvider dateTimeProvider = CurrentDateTimeProvider.INSTANCE;
  private AuditorAware<?> auditorAware;
  private boolean dateTimeForNow = true;
  private boolean modifyOnCreation = true;

  /**
   * Setter to inject a {@code AuditorAware} component to retrieve the current auditor.
   *
   * @param auditorAware must not be {@literal null}.
   */
  public void setAuditorAware(final AuditorAware<?> auditorAware) {

    Assert.notNull(auditorAware, "AuditorAware must not be null!");
    this.auditorAware = auditorAware;
  }

  /**
   * Setter do determine if {@link Auditable#setCreatedDate(DateTime)} and
   * {@link Auditable#setLastModifiedDate(DateTime)} shall be filled with the current Java time. Defaults to
   * {@code true}. One might set this to {@code false} to use database features to set entity time.
   *
   * @param dateTimeForNow the dateTimeForNow to set
   */
  public void setDateTimeForNow(boolean dateTimeForNow) {
    this.dateTimeForNow = dateTimeForNow;
  }

  /**
   * Set this to false if you want to treat entity creation as modification and thus set the current date as
   * modification date, too. Defaults to {@code true}.
   *
   * @param modifyOnCreation if modification information shall be set on creation, too
   */
  public void setModifyOnCreation(boolean modifyOnCreation) {
    this.modifyOnCreation = modifyOnCreation;
  }

  /**
   * Sets the {@link DateTimeProvider} to be used to determine the dates to be set.
   *
   * @param dateTimeProvider
   */
  public void setDateTimeProvider(DateTimeProvider dateTimeProvider) {
    this.dateTimeProvider = dateTimeProvider == null ? CurrentDateTimeProvider.INSTANCE : dateTimeProvider;
  }

  /**
   * Marks the given object as created.
   *
   * @param source
   */
  public void markCreated(Object source) {
    touch(source, true);
  }

  /**
   * Marks the given object as modified.
   *
   * @param source
   */
  public void markModified(Object source) {
    touch(source, false);
  }

  /**
   * Returns whether the given source is considered to be auditable in the first place
   *
   * @param source can be {@literal null}.
   * @return
   */
  protected final boolean isAuditable(Object source) {
    return factory.getBeanWrapperFor(source) != null;
  }

  private void touch(Object target, boolean isNew) {

    AuditableBeanWrapper wrapper = factory.getBeanWrapperFor(target);

    if (wrapper == null) {
      return;
    }

    Object auditor = touchAuditor(wrapper, isNew);
    Calendar now = dateTimeForNow ? touchDate(wrapper, isNew) : null;

    Object defaultedNow = now == null ? "not set" : now;
    Object defaultedAuditor = auditor == null ? "unknown" : auditor;

    LOGGER.debug("Touched {} - Last modification at {} by {}", new Object[] { target, defaultedNow, defaultedAuditor });
  }

  /**
   * Sets modifying and creating auditioner. Creating auditioner is only set on new auditables.
   *
   * @param wrapper
   * @param isNew
   * @return
   */
  private Object touchAuditor(AuditableBeanWrapper wrapper, boolean isNew) {

    if (null == auditorAware) {
      return null;
    }

    Object auditor = auditorAware.getCurrentAuditor();

    if (isNew) {
      wrapper.setCreatedBy(auditor);
      if (!modifyOnCreation) {
        return auditor;
      }
    }

    wrapper.setLastModifiedBy(auditor);
    return auditor;
  }

  /**
   * Touches the auditable regarding modification and creation date. Creation date is only set on new auditables.
   *
   * @param wrapper
   * @return
   */
  private Calendar touchDate(AuditableBeanWrapper wrapper, boolean isNew) {

    Calendar now = dateTimeProvider.getNow();

    if (isNew) {
      wrapper.setCreatedDate(now);
      if (!modifyOnCreation) {
        return now;
      }
    }

    wrapper.setLastModifiedDate(now);
    return now;
  }

  /*
   * (non-Javadoc)
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   */
  @Override
  public void afterPropertiesSet() {

    if (auditorAware == null) {
      LOGGER.debug("No AuditorAware set! Auditing will not be applied!");
    }
  }

  public AuditableBeanWrapper getBeanWrapperFor(Object source) {

    if (source == null) {
      return null;
    }

    if (source instanceof Auditable) {
      return new org.springframework.data.auditing.DefaultAuditableBeanWrapperFactory.AuditableInterfaceBeanWrapper((Auditable<Object, ?>) source);
    }

    org.springframework.data.auditing.AnnotationAuditingMetadata metadata = org.springframework.data.auditing.AnnotationAuditingMetadata.getMetadata(source.getClass());

    if (metadata.isAuditable()) {
      return new org.springframework.data.auditing.DefaultAuditableBeanWrapperFactory.ReflectionAuditingBeanWrapper(source);
    }

    return null;
  }
}
