package com.hellojd.shopex.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.math.BigDecimal;

/**
 * @author Administrator
 */
@Configuration
@ConfigurationProperties("setting")
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
    private RoundType priceRoundType;

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
    private Integer uploadMaxSize;

//    @Value("${setting.uploadImageExtensions}")
    private String[] uploadImageExtensions;

//    @Value("${setting.uploadFlashExtensions}")
    private String[] uploadFlashExtensions;

//    @Value("${setting.uploadMediaExtensions}")
    private String[] uploadMediaExtensions;

//    @Value("${setting.uploadFileExtensions}")
    private String[] uploadFileExtensions;

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

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getHotSearch() {
        return hotSearch;
    }

    public void setHotSearch(String hotSearch) {
        this.hotSearch = hotSearch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCerttext() {
        return certtext;
    }

    public void setCerttext(String certtext) {
        this.certtext = certtext;
    }

    public boolean isSiteEnabled() {
        return isSiteEnabled;
    }

    public void setSiteEnabled(boolean siteEnabled) {
        isSiteEnabled = siteEnabled;
    }

    public String getSiteCloseMessage() {
        return siteCloseMessage;
    }

    public void setSiteCloseMessage(String siteCloseMessage) {
        this.siteCloseMessage = siteCloseMessage;
    }

    public int getLargeProductImageWidth() {
        return largeProductImageWidth;
    }

    public void setLargeProductImageWidth(int largeProductImageWidth) {
        this.largeProductImageWidth = largeProductImageWidth;
    }

    public int getLargeProductImageHeight() {
        return largeProductImageHeight;
    }

    public void setLargeProductImageHeight(int largeProductImageHeight) {
        this.largeProductImageHeight = largeProductImageHeight;
    }

    public int getMediumProductImageWidth() {
        return mediumProductImageWidth;
    }

    public void setMediumProductImageWidth(int mediumProductImageWidth) {
        this.mediumProductImageWidth = mediumProductImageWidth;
    }

    public int getMediumProductImageHeight() {
        return mediumProductImageHeight;
    }

    public void setMediumProductImageHeight(int mediumProductImageHeight) {
        this.mediumProductImageHeight = mediumProductImageHeight;
    }

    public int getThumbnailProductImageWidth() {
        return thumbnailProductImageWidth;
    }

    public void setThumbnailProductImageWidth(int thumbnailProductImageWidth) {
        this.thumbnailProductImageWidth = thumbnailProductImageWidth;
    }

    public int getThumbnailProductImageHeight() {
        return thumbnailProductImageHeight;
    }

    public void setThumbnailProductImageHeight(int thumbnailProductImageHeight) {
        this.thumbnailProductImageHeight = thumbnailProductImageHeight;
    }

    public String getDefaultLargeProductImage() {
        return defaultLargeProductImage;
    }

    public void setDefaultLargeProductImage(String defaultLargeProductImage) {
        this.defaultLargeProductImage = defaultLargeProductImage;
    }

    public String getDefaultMediumProductImage() {
        return defaultMediumProductImage;
    }

    public void setDefaultMediumProductImage(String defaultMediumProductImage) {
        this.defaultMediumProductImage = defaultMediumProductImage;
    }

    public String getDefaultThumbnailProductImage() {
        return defaultThumbnailProductImage;
    }

    public void setDefaultThumbnailProductImage(String defaultThumbnailProductImage) {
        this.defaultThumbnailProductImage = defaultThumbnailProductImage;
    }

    public String getWatermarkAlpha() {
        return watermarkAlpha;
    }

    public void setWatermarkAlpha(String watermarkAlpha) {
        this.watermarkAlpha = watermarkAlpha;
    }

    public String getWatermarkImage() {
        return watermarkImage;
    }

    public void setWatermarkImage(String watermarkImage) {
        this.watermarkImage = watermarkImage;
    }

    public String getWatermarkPosition() {
        return watermarkPosition;
    }

    public void setWatermarkPosition(String watermarkPosition) {
        this.watermarkPosition = watermarkPosition;
    }

    public int getPriceScale() {
        return priceScale;
    }

    public void setPriceScale(int priceScale) {
        this.priceScale = priceScale;
    }

    public RoundType getPriceRoundType() {
        return priceRoundType;
    }

    public void setPriceRoundType(RoundType priceRoundType) {
        this.priceRoundType = priceRoundType;
    }

    public boolean isShowMarketPrice() {
        return isShowMarketPrice;
    }

    public void setShowMarketPrice(boolean showMarketPrice) {
        isShowMarketPrice = showMarketPrice;
    }

    public String getDefaultMarketPriceScale() {
        return defaultMarketPriceScale;
    }

    public void setDefaultMarketPriceScale(String defaultMarketPriceScale) {
        this.defaultMarketPriceScale = defaultMarketPriceScale;
    }

    public boolean isRegisterEnabled() {
        return isRegisterEnabled;
    }

    public void setRegisterEnabled(boolean registerEnabled) {
        isRegisterEnabled = registerEnabled;
    }

    public boolean isDuplicateEmail() {
        return isDuplicateEmail;
    }

    public void setDuplicateEmail(boolean duplicateEmail) {
        isDuplicateEmail = duplicateEmail;
    }

    public String getDisabledUsername() {
        return disabledUsername;
    }

    public void setDisabledUsername(String disabledUsername) {
        this.disabledUsername = disabledUsername;
    }

    public int getUsernameMinLength() {
        return usernameMinLength;
    }

    public void setUsernameMinLength(int usernameMinLength) {
        this.usernameMinLength = usernameMinLength;
    }

    public int getUsernameMaxLength() {
        return usernameMaxLength;
    }

    public void setUsernameMaxLength(int usernameMaxLength) {
        this.usernameMaxLength = usernameMaxLength;
    }

    public int getPasswordMinLength() {
        return passwordMinLength;
    }

    public void setPasswordMinLength(int passwordMinLength) {
        this.passwordMinLength = passwordMinLength;
    }

    public int getPasswordMaxLength() {
        return passwordMaxLength;
    }

    public void setPasswordMaxLength(int passwordMaxLength) {
        this.passwordMaxLength = passwordMaxLength;
    }

    public String getRegisterPoint() {
        return registerPoint;
    }

    public void setRegisterPoint(String registerPoint) {
        this.registerPoint = registerPoint;
    }

    public String getIsEmailLogin() {
        return isEmailLogin;
    }

    public void setIsEmailLogin(String isEmailLogin) {
        this.isEmailLogin = isEmailLogin;
    }

    public String getCaptchaTypes() {
        return captchaTypes;
    }

    public void setCaptchaTypes(String captchaTypes) {
        this.captchaTypes = captchaTypes;
    }

    public String getAccountLockTypes() {
        return accountLockTypes;
    }

    public void setAccountLockTypes(String accountLockTypes) {
        this.accountLockTypes = accountLockTypes;
    }

    public String getAccountLockCount() {
        return accountLockCount;
    }

    public void setAccountLockCount(String accountLockCount) {
        this.accountLockCount = accountLockCount;
    }

    public String getAccountLockTime() {
        return accountLockTime;
    }

    public void setAccountLockTime(String accountLockTime) {
        this.accountLockTime = accountLockTime;
    }

    public String getSafeKeyExpiryTime() {
        return safeKeyExpiryTime;
    }

    public void setSafeKeyExpiryTime(String safeKeyExpiryTime) {
        this.safeKeyExpiryTime = safeKeyExpiryTime;
    }

    public Integer getUploadMaxSize() {
        return uploadMaxSize;
    }

    public void setUploadMaxSize(Integer uploadMaxSize) {
        this.uploadMaxSize = uploadMaxSize;
    }

    public String[] getUploadImageExtensions() {
        return uploadImageExtensions;
    }

    public void setUploadImageExtensions(String[] uploadImageExtensions) {
        this.uploadImageExtensions = uploadImageExtensions;
    }

    public String[] getUploadFlashExtensions() {
        return uploadFlashExtensions;
    }

    public void setUploadFlashExtensions(String[] uploadFlashExtensions) {
        this.uploadFlashExtensions = uploadFlashExtensions;
    }

    public String[] getUploadMediaExtensions() {
        return uploadMediaExtensions;
    }

    public void setUploadMediaExtensions(String[] uploadMediaExtensions) {
        this.uploadMediaExtensions = uploadMediaExtensions;
    }

    public String[] getUploadFileExtensions() {
        return uploadFileExtensions;
    }

    public void setUploadFileExtensions(String[] uploadFileExtensions) {
        this.uploadFileExtensions = uploadFileExtensions;
    }

    public String getImageUploadPath() {
        return imageUploadPath+"${.now?string(''yyyyMM'')}/";
    }

    public void setImageUploadPath(String imageUploadPath) {
        this.imageUploadPath = imageUploadPath;
    }

    public String getFlashUploadPath() {
        return flashUploadPath+"${.now?string(''yyyyMM'')}/";
    }

    public void setFlashUploadPath(String flashUploadPath) {
        this.flashUploadPath = flashUploadPath;
    }

    public String getMediaUploadPath() {
        return mediaUploadPath+"${.now?string(''yyyyMM'')}/";
    }

    public void setMediaUploadPath(String mediaUploadPath) {
        this.mediaUploadPath = mediaUploadPath;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        this.fileUploadPath = fileUploadPath;
    }

    public String getSmtpFromMail() {
        return smtpFromMail;
    }

    public void setSmtpFromMail(String smtpFromMail) {
        this.smtpFromMail = smtpFromMail;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUsername() {
        return smtpUsername;
    }

    public void setSmtpUsername(String smtpUsername) {
        this.smtpUsername = smtpUsername;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }

    public String getCurrencySign() {
        return currencySign;
    }

    public void setCurrencySign(String currencySign) {
        this.currencySign = currencySign;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public String getStockAlertCount() {
        return stockAlertCount;
    }

    public void setStockAlertCount(String stockAlertCount) {
        this.stockAlertCount = stockAlertCount;
    }

    public String getStockAllocationTime() {
        return stockAllocationTime;
    }

    public void setStockAllocationTime(String stockAllocationTime) {
        this.stockAllocationTime = stockAllocationTime;
    }

    public String getDefaultPointScale() {
        return defaultPointScale;
    }

    public void setDefaultPointScale(String defaultPointScale) {
        this.defaultPointScale = defaultPointScale;
    }

    public String getIsDevelopmentEnabled() {
        return isDevelopmentEnabled;
    }

    public void setIsDevelopmentEnabled(String isDevelopmentEnabled) {
        this.isDevelopmentEnabled = isDevelopmentEnabled;
    }

    public String getIsReviewEnabled() {
        return isReviewEnabled;
    }

    public void setIsReviewEnabled(String isReviewEnabled) {
        this.isReviewEnabled = isReviewEnabled;
    }

    public String getIsReviewCheck() {
        return isReviewCheck;
    }

    public void setIsReviewCheck(String isReviewCheck) {
        this.isReviewCheck = isReviewCheck;
    }

    public String getReviewAuthority() {
        return reviewAuthority;
    }

    public void setReviewAuthority(String reviewAuthority) {
        this.reviewAuthority = reviewAuthority;
    }

    public String getIsConsultationEnabled() {
        return isConsultationEnabled;
    }

    public void setIsConsultationEnabled(String isConsultationEnabled) {
        this.isConsultationEnabled = isConsultationEnabled;
    }

    public String getIsConsultationCheck() {
        return isConsultationCheck;
    }

    public void setIsConsultationCheck(String isConsultationCheck) {
        this.isConsultationCheck = isConsultationCheck;
    }

    public String getConsultationAuthority() {
        return consultationAuthority;
    }

    public void setConsultationAuthority(String consultationAuthority) {
        this.consultationAuthority = consultationAuthority;
    }

    public String getIsInvoiceEnabled() {
        return isInvoiceEnabled;
    }

    public void setIsInvoiceEnabled(String isInvoiceEnabled) {
        this.isInvoiceEnabled = isInvoiceEnabled;
    }

    public String getIsTaxPriceEnabled() {
        return isTaxPriceEnabled;
    }

    public void setIsTaxPriceEnabled(String isTaxPriceEnabled) {
        this.isTaxPriceEnabled = isTaxPriceEnabled;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    public String getKuaidi100Key() {
        return kuaidi100Key;
    }

    public void setKuaidi100Key(String kuaidi100Key) {
        this.kuaidi100Key = kuaidi100Key;
    }

    public String getIsCnzzEnabled() {
        return isCnzzEnabled;
    }

    public void setIsCnzzEnabled(String isCnzzEnabled) {
        this.isCnzzEnabled = isCnzzEnabled;
    }

    public String getCnzzSiteId() {
        return cnzzSiteId;
    }

    public void setCnzzSiteId(String cnzzSiteId) {
        this.cnzzSiteId = cnzzSiteId;
    }

    public String getCnzzPassword() {
        return cnzzPassword;
    }

    public void setCnzzPassword(String cnzzPassword) {
        this.cnzzPassword = cnzzPassword;
    }
  public BigDecimal setScale(BigDecimal amount)
  {
    if (amount == null) {
      return null;
    }
    int roundingMode;
    if (getPriceRoundType() == RoundType.roundUp)
    {
      roundingMode = 0;
    }
    else
    {
      if (getPriceRoundType() == RoundType.roundDown) {
        roundingMode = 1;
      } else {
        roundingMode = 4;
      }
    }
    return amount.setScale(getPriceScale(), roundingMode);
  }

    public static enum WatermarkPosition
    {
        no,  topLeft,  topRight,  center,  bottomLeft,  bottomRight;
    }

    public static enum RoundType
    {
        roundHalfUp,  roundUp,  roundDown;
    }

    public static enum CaptchaType
    {
        memberLogin,  memberRegister,  adminLogin,  review,  consultation,  findPassword,  resetPassword,  other;
    }

    public static enum AccountLockType
    {
        member,  admin;
    }

    public static enum StockAllocationTime
    {
        order,  payment,  ship;
    }

    public static enum ReviewAuthority
    {
        anyone,  member,  purchased;
    }

    public static enum ConsultationAuthority
    {
        anyone,  member;
    }
}
