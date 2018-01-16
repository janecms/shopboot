package com.hellojd.shopex.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Administrator
 */
@Configuration
@ConfigurationProperties("setting")
@Data
public class ShopxxSettings {
    @Value("${setting.siteName}")
    String siteName;

    @Value("${setting.siteUrl}")
    private String siteUrl;

    @Value("${setting.logo}")
    private String logo;

    @Value("${setting.hotSearch}")
    private String hotSearch;

    @Value("${setting.address}")
    private String address;

    @Value("${setting.phone}")
    private String phone;

    @Value("${setting.zipCode}")
    private String zipCode;

    @Value("${setting.email}")
    private String email;

    @Value("${setting.certtext}")
    private String certtext;

    @Value("${setting.isSiteEnabled}")
    private boolean isSiteEnabled;

    @Value("${setting.siteCloseMessage}")
    private String siteCloseMessage;

    @Value("${setting.largeProductImageWidth}")
    private int largeProductImageWidth;

    @Value("${setting.largeProductImageHeight}")
    private int largeProductImageHeight;

    @Value("${setting.mediumProductImageWidth}")
    private int mediumProductImageWidth;

    @Value("${setting.mediumProductImageHeight}")
    private int mediumProductImageHeight;

    @Value("${setting.thumbnailProductImageWidth}")
    private int thumbnailProductImageWidth;

    @Value("${setting.thumbnailProductImageHeight}")
    private int thumbnailProductImageHeight;

    @Value("${setting.defaultLargeProductImage}")
    private String defaultLargeProductImage;

    @Value("${setting.defaultMediumProductImage}")
    private String defaultMediumProductImage;

    @Value("${setting.defaultThumbnailProductImage}")
    private String defaultThumbnailProductImage;

    @Value("${setting.watermarkAlpha}")
    private String watermarkAlpha;

    @Value("${setting.watermarkImage}")
    private String watermarkImage;

    @Value("${setting.watermarkPosition}")
    private String watermarkPosition;

    @Value("${setting.priceScale}")
    private int priceScale;

    @Value("${setting.priceRoundType}")
    private String priceRoundType;

    @Value("${setting.isShowMarketPrice}")
    private boolean isShowMarketPrice;

    @Value("${setting.defaultMarketPriceScale}")
    private String defaultMarketPriceScale;

    @Value("${setting.isRegisterEnabled}")
    private boolean isRegisterEnabled;

    @Value("${setting.isDuplicateEmail}")
    private boolean isDuplicateEmail;

    @Value("${setting.disabledUsername}")
    private String disabledUsername;

    @Value("${setting.usernameMinLength}")
    private int usernameMinLength;

    @Value("${setting.usernameMaxLength}")
    private int usernameMaxLength;

    @Value("${setting.passwordMinLength}")
    private int passwordMinLength;

    @Value("${setting.passwordMaxLength}")
    private int passwordMaxLength;
    @Value("${setting.registerPoint}")
    private String registerPoint;

    @Value("${setting.isEmailLogin}")
    private String isEmailLogin;

    @Value("${setting.captchaTypes}")
    private String captchaTypes;

    @Value("${setting.accountLockTypes}")
    private String accountLockTypes;

    @Value("${setting.accountLockCount}")
    private String accountLockCount;

    @Value("${setting.accountLockTime}")
    private String accountLockTime;

    @Value("${setting.safeKeyExpiryTime}")
    private String safeKeyExpiryTime;

    @Value("${setting.uploadMaxSize}")
    private String uploadMaxSize;

    @Value("${setting.uploadImageExtension}")
    private String uploadImageExtension;

    @Value("${setting.uploadFlashExtension}")
    private String uploadFlashExtension;

    @Value("${setting.uploadMediaExtension}")
    private String uploadMediaExtension;

    @Value("${setting.uploadFileExtension}")
    private String uploadFileExtension;

    @Value("${setting.imageUploadPath}")
    private String imageUploadPath;

    @Value("${setting.flashUploadPath}")
    private String flashUploadPath;

    @Value("${setting.mediaUploadPath}")
    private String mediaUploadPath;

    @Value("${setting.fileUploadPath}")
    private String fileUploadPath;

    @Value("${setting.smtpFromMail}")
    private String smtpFromMail;

    @Value("${setting.smtpHost}")
    private String smtpHost;

    @Value("${setting.smtpPort}")
    private String smtpPort;

    @Value("${setting.smtpUsername}")
    private String smtpUsername;

    @Value("${setting.smtpPassword}")
    private String smtpPassword;

    @Value("${setting.currencySign}")
    private String currencySign;

    @Value("${setting.currencyUnit}")
    private String currencyUnit;

    @Value("${setting.stockAlertCount}")
    private String stockAlertCount;

    @Value("${setting.stockAllocationTime}")
    private String stockAllocationTime;

    @Value("${setting.defaultPointScale}")
    private String defaultPointScale;

    @Value("${setting.isDevelopmentEnabled}")
    private String isDevelopmentEnabled;

    @Value("${setting.isReviewEnabled}")
    private String isReviewEnabled;

    @Value("${setting.isReviewCheck}")
    private String isReviewCheck;

    @Value("${setting.reviewAuthority}")
    private String reviewAuthority;

    @Value("${setting.isConsultationEnabled}")
    private String isConsultationEnabled;

    @Value("${setting.isConsultationCheck}")
    private String isConsultationCheck;

    @Value("${setting.consultationAuthority}")
    private String consultationAuthority;

    @Value("${setting.isInvoiceEnabled}")
    private String isInvoiceEnabled;

    @Value("${setting.isTaxPriceEnabled}")
    private String isTaxPriceEnabled;

    @Value("${setting.taxRate}")
    private String taxRate;

    @Value("${setting.cookiePath}")
    private String cookiePath;

    @Value("${setting.cookieDomain}")
    private String cookieDomain;

    @Value("${setting.kuaidi100Key}")
    private String kuaidi100Key;

    @Value("${setting.isCnzzEnabled}")
    private String isCnzzEnabled;

    @Value("${setting.cnzzSiteId}")
    private String cnzzSiteId;

    @Value("${setting.cnzzPassword}")
    private String cnzzPassword;

}
